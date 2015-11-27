/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *     http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.zappos.json;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zappos.json.format.ValueFormatter;
import com.zappos.json.util.Reflections;
import com.zappos.json.util.Strings;
import com.zappos.json.util.TypeImpl;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * 
 * @author Hussachai
 *
 */
public class JsonReaderCodeGenerator {

  private final Map<Class<?>, JsonReaderInvoker> JSON_READER_INVOKERS = new ConcurrentHashMap<>();
  
  private ZapposJson jacinda;
  
  private JsonBeanIntrospector beanIntrospector;
  
  public JsonReaderCodeGenerator(ZapposJson jacinda, JsonBeanIntrospector beanIntrospector){
    this.jacinda = jacinda;
    this.beanIntrospector = beanIntrospector;
  }
  
  protected JsonReaderInvoker getReader(Class<?> clazz){
    return JSON_READER_INVOKERS.get(clazz);
  }
  
  protected synchronized void deregisterAll(){
    JSON_READER_INVOKERS.clear();
  }
  
  protected void deregister(Class<?> clazz){
    synchronized(clazz){
      JSON_READER_INVOKERS.remove(clazz);
    }
  }
  
  protected JsonReaderInvoker registerReader(Class<?> clazz)
      throws Exception {
    synchronized(clazz){
      JsonReaderInvoker readerInvoker = JSON_READER_INVOKERS.get(clazz);
      if (readerInvoker != null) {
        return readerInvoker;
      }
  
      StringBuilder methodBody = new StringBuilder();
      methodBody.append("{\n");
      List<PathAndCode> pathAndCodes = generateJsonReaderBody(clazz);
  
      /*
       * Compiler will optimize if(String.equals) when the number of conditions <=
       * 16
       */
      if (pathAndCodes.size() > 16
          && new HashSet<>(pathAndCodes).size() == pathAndCodes.size()) {
  
        methodBody.append("int pathHash = getPath($1).hashCode();\n");
        methodBody.append("switch(pathHash){\n");
        for (PathAndCode pathAndCode : pathAndCodes) {
          methodBody.append("case ")
              .append(String.valueOf(pathAndCode.getPath().hashCode()))
              .append(":\n{").append(pathAndCode.getCode()).append("\n}");
        }
        methodBody.append("}\n");
  
      } else {
  
        int i = 0;
        methodBody.append("String path = getPath($1);\n");
        for (PathAndCode pathAndCode : pathAndCodes) {
          if (i == 0) {
            methodBody.append("if");
          } else {
            methodBody.append("else if");
          }
          methodBody.append("(\"" + pathAndCode.getPath() + "\".equals(path)){\n");
          methodBody.append(pathAndCode.getCode());
          methodBody.append("}\n");
          i++;
        }
  
      }
      methodBody.append("return $2;\n}");
      jacinda.debug("\nReader code for \"@\"\n=========\n@\n=========\n", clazz, methodBody);
      ClassPool classPool = ClassPool.getDefault();
      classPool.importPackage("java.util");
      classPool.importPackage("java.math");
      CtClass jsonCtClass = classPool.get(JsonReader.class.getName());
  
      String randomName = Strings.randomAlphabetic(16);
      jsonCtClass.setName(randomName + "_JSON_Reader");
      CtMethod ctMethod = jsonCtClass.getDeclaredMethod("createObject",
          new CtClass[] { classPool.get(int.class.getName()),
              classPool.get(Map.class.getName()) });
      ctMethod.setBody(methodBody.toString());
      readerInvoker = new JsonReaderInvoker(jacinda, jsonCtClass.toClass());
      JSON_READER_INVOKERS.put(clazz, readerInvoker);
      
      return readerInvoker;
    }
  }
  
  
  private List<PathAndCode> generateJsonReaderBody(Class<?> clazz)
      throws Exception {

    List<PathAndCode> pathAndCodes = new ArrayList<>();
    Map<String, TypeMapping> typeMappings = new HashMap<>();

    traverseObjectTree(clazz, new String[JsonReader.MAX_OBJECT_TREE_DEEP], 1, typeMappings);

    for (Map.Entry<String, TypeMapping> entry : typeMappings.entrySet()) {

      jacinda.debug("Path: @, Mapping: @", entry.getKey(), entry.getValue());

      String path = entry.getKey();
      TypeMapping mapping = entry.getValue();
      String typeName = mapping.getType().getName();

      String code = typeName + " __o = new " + typeName + "();\n";

      for (AttributeMapping attrMapping : mapping.getAttributeMappings()) {

        String writeMethodName = attrMapping.getWriteMethod().getName();
        String jsonKey = attrMapping.getJsonKey();
        String valueName = Character.toLowerCase(writeMethodName.charAt(3))
            + writeMethodName.substring(4);
        String tmpValueName = "_" + valueName;
        Class<?> attrType = attrMapping.getAttributeType();
        code += "Object " + tmpValueName + " = $2.get(\"" + jsonKey + "\");\n";
        code += "if(" + tmpValueName + " != null){\n";

        if (attrType.isArray()
            || attrMapping.getArrayType() == 1) { /* native array type */
          /*
           * Note: javassist doesn't understand syntax after Java 1.4
           */
          Class<?> componentType = (attrMapping.getArrayType() == 1) ? attrType : attrType.getComponentType();
          if(componentType == byte.class){
            code += "byte "+valueName+"[] =  javax.xml.bind.DatatypeConverter.parseBase64Binary((String)"+tmpValueName+");\n";
          }else{
            code += generateArrayFromListCode(componentType, valueName, tmpValueName);
          }
        } else if (Iterable.class.isAssignableFrom(attrType)
            || attrMapping.getArrayType() == 2) { /* collection type */
          Class<?> collectionType = null;
          Class<?> componentType = null;
          if (attrMapping.getArrayType() == 2) {
            /* collection of object */
            componentType = attrType;
            collectionType = attrMapping.getCollectionType();
          } else {
            componentType = Reflections.getFirstGenericParameterType(attrMapping.getWriteMethod());
            collectionType = attrType;
          }
          code += generateCollectionCode(collectionType, componentType, valueName, tmpValueName);
          
        } else if (Map.class.isAssignableFrom(attrType)) { /* map type */
          
          Class<?> componentType = Reflections.getSecondGenericParameterType(attrMapping.getWriteMethod());
          if (componentType == null) {
            throw new RuntimeException("Cannot find type of Map");
          }
          
          Class<?> mapType = attrType;
          JsonType jsonType = JsonType.toJsonType(componentType);
          
          TypeImpl typeImpl = TypeImpl.getMapImpl(mapType);
          code += "Map " + tmpValueName + "_map = (Map)" + tmpValueName + ";\n";
          code += typeImpl.getInfClass().getName() + " " + valueName
                    + " = new " + typeImpl.getImplClass().getName() + "();\n";
          code += "Iterator " + tmpValueName + "_iter = " + tmpValueName + "_map.keySet().iterator();\n";
          code += "while(" + tmpValueName + "_iter.hasNext()){\n";
          code += "String _key = (String)" + tmpValueName + "_iter.next();\n";
          code += "Object _m1 = " + tmpValueName + "_map.get(_key);\n";
          if(jsonType == JsonType.OBJECT){
            /* This do the trick but it is going to be slow to convert json -> map -> json */
            code += "Object _m2 = jacinda.fromJson(jacinda.toJson((Map)_m1), "+componentType.getName()+".class);\n";
          }else if(jsonType == JsonType.ARRAY){
            if(componentType.isArray()){
              componentType = componentType.getComponentType();
              code += generateArrayFromListCode(componentType, "_m2", "_m1");
            }else{
              throw new RuntimeException("Map of collection not support");
            }
          }else{
            code += generateTypeConversionCode(componentType, "_m2", "_m1");
          }
          code += valueName + ".put(_key, _m2);\n";
          code += "}\n";
          
        } else if(attrMapping.getFormatterClass() != null){
          
            String formatterClassName = attrMapping.getFormatterClass().getName();
            String formatterVar = Strings.randomAlphabetic(6)+"_fmt";
            code += formatterClassName + " " + formatterVar + " = new " + formatterClassName + "();\n";
            
            if(attrMapping.getFormatterPattern() != null){
              code += formatterVar + ".setPattern(\"" + attrMapping.getFormatterPattern() + "\");\n";
            }
            
            code += attrType.getName() + " " + valueName + " = (" + attrType.getName()
              + ")" + formatterVar + ".parse(String.valueOf(" + tmpValueName + "));\n";
            
        } else {
          
          code += generateTypeConversionCode(attrType, valueName, tmpValueName);
          
        }
        
        code += "__o." + writeMethodName + "(" + valueName + ");\n";
        code += "};\n";
        
      }

      code += "return __o;\n";

      pathAndCodes.add(new PathAndCode(path, code));
      
    }

    return pathAndCodes;

  }

  private void traverseObjectTree(Class<?> clazz, String paths[], int level,
      Map<String, TypeMapping> typeMappings) throws Exception {

    List<JsonBeanAttribute> attributes = beanIntrospector.getMutators(clazz);
    
    for (JsonBeanAttribute beanAttr: attributes) {

      Method writeMethod = beanAttr.getMethod();
      String jsonKey = beanAttr.getJsonKey();
      Class<?> attrType = writeMethod.getParameterTypes()[0];
      JsonType jsonType = JsonType.toJsonType(attrType);

      boolean arrayOfObject = false;
      if (attrType.isArray()) {
        if (JsonType.toJsonType(attrType.getComponentType()) == JsonType.OBJECT) {
          arrayOfObject = true;
          attrType = attrType.getComponentType();
        }
      }

      Class<?> collectionType = null;
      boolean collectionOfObject = false;
      if (Iterable.class.isAssignableFrom(attrType)) {
        Class<?> genericType = Reflections.getFirstGenericParameterType(writeMethod);
        if (genericType != null) {
          if (JsonType.toJsonType(genericType) == JsonType.OBJECT) {
            collectionType = attrType;
            collectionOfObject = true;
            attrType = genericType;
          }
        }else{
          throw new JsonException("Unknown type of collection");
        }
      }
 
      if (Map.class.isAssignableFrom(attrType)) {
        Class<?> genericTypes[] = Reflections.getGenericParameterTypes(writeMethod);
        if (genericTypes != null && genericTypes.length == 2) {
          if (genericTypes[0] != String.class) {
            throw new JsonException("Map key must be string");
          }
        }else{
          throw new JsonException("Unknown type of map");
        }
      }
      
      if ((jsonType == JsonType.OBJECT && !Map.class.isAssignableFrom(attrType)
          && jacinda.getValueFormatter(attrType) == null) || arrayOfObject
          || collectionOfObject) {
        paths[level] = jsonKey;
        String path = getObjectAccessPath(paths, level);
        TypeMapping typeMapping = getTypeMapping(typeMappings, path, clazz);
        AttributeMapping attrMapping = typeMapping.addAttributeMapping(attrType, writeMethod, jsonKey);
        if (arrayOfObject) {
          attrMapping.setArrayType(1);
        } else if (collectionOfObject) {
          attrMapping.setArrayType(2).setCollectionType(collectionType);
        }
        traverseObjectTree(attrType, paths, level + 1, typeMappings);
      } else {
        String path = getObjectAccessPath(paths, level);
        TypeMapping typeMapping = getTypeMapping(typeMappings, path, clazz);
        AttributeMapping attrMapping = typeMapping.addAttributeMapping(attrType, writeMethod, jsonKey);
        attrMapping.setFormatterClass(beanAttr.getFormatterClass())
          .setFormatterPattern(beanAttr.getFormatterPattern());
      }
    }

  }

  private String getObjectAccessPath(String paths[], int level) {

    if (level == 1)
      return "";
    String path = paths[1];
    for (int i = 2; i < level; i++) {
      path = path + "." + paths[i];
    }

    return path;

  }

  private TypeMapping getTypeMapping(Map<String, TypeMapping> typeMaps,
      String path, Class<?> superType) {

    TypeMapping typeMapping = typeMaps.get(path);
    if (typeMapping == null) {
      typeMapping = new TypeMapping(superType);
      typeMaps.put(path, typeMapping);
    }

    return typeMapping;

  }
  
  private String generateCollectionCode(Class<?> collectionType, Class<?> componentType,
      String valueName, String tmpValueName){
    if (componentType == null) {
      throw new RuntimeException("Cannot find type of Collection");
    }
    String code = "";
    TypeImpl typeImpl = TypeImpl.getIterableImpl(collectionType);
    code += "List " + tmpValueName + "_list = (List)" + tmpValueName + ";\n";
    code += typeImpl.getInfClass().getName() + " " + valueName
        + " = new " + typeImpl.getImplClass().getName() + "();\n";
    code += "for(int _i = 0; _i < " + tmpValueName + "_list.size(); _i++){\n";
    code += "Object _l1 = " + tmpValueName + "_list.get(_i);\n";
    code += generateTypeConversionCode(componentType, "_l2", "_l1");
    code += valueName + ".add(_l2);\n";
    code += "}\n";
    return code;
  }
  
  private String generateArrayFromListCode(Class<?> attrType, String valueName,
      String tmpValueName) {

    String code = "";
    code += "List " + tmpValueName + "_list = (List)" + tmpValueName + ";\n";
    code += attrType.getName() + " " + valueName + "[] = new "
            + attrType.getName() + "[" + tmpValueName + "_list.size()];\n";
    code += "for(int _i = 0; _i < " + tmpValueName + "_list.size(); _i++){\n";
    code += "Object _l1 = " + tmpValueName + "_list.get(_i);\n";
    code += generateTypeConversionCode(attrType, "_l2", "_l1");
    code += valueName + "[_i] = _l2;\n";
    code += "}\n";
    code += "";
    return code;

  }

  private String generateTypeConversionCode(Class<?> attrType, String valueName,
      String tmpValueName) {

    String valueDeclaration = null;

    if (attrType.isPrimitive()) {

      if (attrType == boolean.class) {
        valueDeclaration = "boolean @ = ((Boolean)@).booleanValue();\n";
      } else if (attrType == char.class) {
        valueDeclaration = "char @ = new Character((String)@).charValue();\n";
      } else if (attrType == byte.class) {
        valueDeclaration = "byte @ = Byte.parseByte((String)@);\n";
      } else if (attrType == short.class) {
        valueDeclaration = "short @ = Short.parseShort((String)@);\n";
      } else if (attrType == int.class) {
        valueDeclaration = "int @ = Integer.parseInt((String)@);\n";
      } else if (attrType == long.class) {
        valueDeclaration = "long @ = Long.parseLong((String)@);\n";
      } else if (attrType == float.class) {
        valueDeclaration = "float @ = Float.parseFloat((String)@);\n";
      } else if (attrType == double.class) {
        valueDeclaration = "double @ = Double.parseDouble((String)@);\n";
      }

    } else if (Number.class.isAssignableFrom(attrType)) {

      if (attrType == Byte.class || attrType == Short.class
          || attrType == Integer.class || attrType == Long.class
          || attrType == Float.class || attrType == Double.class
          || attrType == BigDecimal.class || attrType == BigInteger.class) {
        valueDeclaration = attrType.getName() + " @ = new " + attrType.getName() + "((String)@);\n";
      } else {
        // TODO: make it better
        valueDeclaration = attrType.getName() + " @ = (" + attrType.getName()
            + ")jacinda.getValueFormatter(" + attrType.getName()
            + ".class).parse((String)@);\n";
      }

    } else if (attrType == Boolean.class) {
      /* No need to parse. The type is already Boolean */
      valueDeclaration = "Boolean @ =  (Boolean)@;\n";

    } else if (attrType == Character.class) {
      valueDeclaration = "Character @ = new Character((String)@);\n";

    } else if (attrType == String.class) {
      /* No need to parse. The type is already String */
      valueDeclaration = "String @ =  (String)@;\n";
    } else {
      // object?
      if (jacinda.getValueFormatter(attrType) != null) {
        valueDeclaration = attrType.getName() + " @ = (" + attrType.getName()
            + ")jacinda.getValueFormatter(" + attrType.getName()
            + ".class).parse(String.valueOf(@));\n";
      } else {
        valueDeclaration = attrType.getName() + " @ = (" + attrType.getName() + ")@;\n";
      }

    }

    return Strings.format(valueDeclaration, valueName, tmpValueName);
  }
  
  static class PathAndCode {

    private String path;

    private String code;

    public PathAndCode(String path, String code) {
      this.path = path;
      this.code = code;
    }

    public String getPath() {
      return path;
    }

    public String getCode() {
      return code;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((path == null) ? 0 : path.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      PathAndCode other = (PathAndCode) obj;
      if (path == null) {
        if (other.path != null)
          return false;
      } else if (!path.equals(other.path))
        return false;
      return true;
    }

  }

  static class TypeMapping {

    private Class<?> type;

    private List<AttributeMapping> attrMappings = new ArrayList<>();

    public TypeMapping(Class<?> type) {
      this.type = type;
    }

    @Override
    public String toString() {
      return type.getSimpleName() + attrMappings;
    }

    public Class<?> getType() {
      return type;
    }

    public AttributeMapping addAttributeMapping(Class<?> type,
        Method writeMethod, String jsonKey) {
      AttributeMapping attrMapping = new AttributeMapping(type, writeMethod, jsonKey);
      attrMappings.add(attrMapping);
      return attrMapping;
    }

    public List<AttributeMapping> getAttributeMappings() {
      return attrMappings;
    }

  }

  static class AttributeMapping {

    private Class<?> attrType;

    /**
     * 0 for not an array, 1 for native, 2 for
     * collection
     */
    private int arrayType = 0; 

    private Class<?> collectionType;

    private Method writeMethod;

    private String jsonKey;
    
    private Class<? extends ValueFormatter<?>> formatterClass;
    
    private String formatterPattern;
    
    public AttributeMapping(Class<?> attrType, Method writeMethod,
        String jsonKey) {
      this.attrType = attrType;
      this.writeMethod = writeMethod;
      this.jsonKey = jsonKey;
    }

    @Override
    public String toString() {
      return attrType.getSimpleName() + "#" + writeMethod.getName() + "->"
          + jsonKey;
    }

    public AttributeMapping setArrayType(int arrayType) {
      this.arrayType = arrayType;
      return this;
    }

    public int getArrayType() {
      return arrayType;
    }

    public AttributeMapping setCollectionType(Class<?> collectionType) {
      this.collectionType = collectionType;
      return this;
    }

    public Class<?> getCollectionType() {
      return collectionType;
    }

    public Class<?> getAttributeType() {
      return attrType;
    }

    public Method getWriteMethod() {
      return writeMethod;
    }

    public String getJsonKey() {
      return jsonKey;
    }

    public Class<? extends ValueFormatter<?>> getFormatterClass() {
      return formatterClass;
    }

    public AttributeMapping setFormatterClass(
        Class<? extends ValueFormatter<?>> formatterClass) {
      this.formatterClass = formatterClass;
      return this;
    }

    public String getFormatterPattern() {
      return formatterPattern;
    }

    public AttributeMapping setFormatterPattern(String formatterPattern) {
      this.formatterPattern = formatterPattern;
      return this;
    }
    
    
  }
}
