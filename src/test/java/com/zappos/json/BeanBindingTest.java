package com.zappos.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import com.zappos.jacinda.data.ContainingArray;
import com.zappos.jacinda.data.ContainingCollection;
import com.zappos.jacinda.data.ContainingFormattableObject;
import com.zappos.jacinda.data.ContainingMap;
import com.zappos.jacinda.data.NestedBean;
import com.zappos.jacinda.data.SimpleBean;

/**
 * 
 * @author hussachai
 *
 */
public class BeanBindingTest extends AbstractBaseTest {

  @Test
  public void testSimple() {

    SimpleBean bean = createSimpleBean();

    String json = jacinda.toJson(bean);
    System.out.println(json);

    bean = jacinda.fromJson(json, SimpleBean.class);
    Assert.assertEquals("simple", bean.getString());
    Assert.assertEquals(true, bean.getB());
    Assert.assertEquals(false, bean.isB2());
    Assert.assertEquals(new Integer(1), bean.getI());
    Assert.assertEquals(2, bean.getI2());
    Assert.assertEquals(new Double(1), bean.getD());
    Assert.assertEquals(2.0, bean.getD2(), 0.0001);
  }

  @Test
  public void testContaingArray() {
    ContainingArray bean = new ContainingArray();
    bean.setSimples(
        new SimpleBean[] { createSimpleBean(), createSimpleBean() });
    bean.setIntegers(new Integer[] { 1, 2, 3 });
    bean.setDoubles(new double[] { 1.0, 2.0, 3.0 });
    bean.setStrings(new String[] { "A", "B", "C" });

    String json = jacinda.toJson(bean);
    System.out.println(json);

    ContainingArray bean2 = jacinda.fromJson(json, ContainingArray.class);
    Assert.assertArrayEquals(bean.getSimples(), bean2.getSimples());
    Assert.assertArrayEquals(bean.getIntegers(), bean2.getIntegers());
    Assert.assertArrayEquals(bean.getDoubles(), bean2.getDoubles(), 0.001);
    Assert.assertArrayEquals(bean.getStrings(), bean2.getStrings());
  }

  @Test
  public void testContainingCollection() {
    /**
     * Iterable, Collection, List interface will be implemented by ArrayList
     * which is the default implementation.
     */
    ContainingCollection bean = new ContainingCollection();
    List<String> strings = new ArrayList<String>();
    strings.add("A");
    strings.add("B");
    bean.setStrings(strings);
    Collection<Integer> integers = new ArrayList<Integer>();
    integers.add(new Integer(1));
    integers.add(new Integer(2));
    bean.setIntegers(integers);
    ArrayList<Boolean> booleans = new ArrayList<Boolean>();
    booleans.add(Boolean.TRUE);
    booleans.add(Boolean.FALSE);
    bean.setBooleans(booleans);
    Vector<SimpleBean> simples = new Vector<SimpleBean>();
    simples.add(createSimpleBean());
    simples.add(createSimpleBean());
    bean.setSimples(simples);

    String json = jacinda.toJson(bean);
    System.out.println(json);

    ContainingCollection bean2 = jacinda.fromJson(json,
        ContainingCollection.class);
    Assert.assertEquals(bean.getStrings(), bean2.getStrings());
    Assert.assertEquals(bean.getIntegers(), bean2.getIntegers());
    Assert.assertEquals(bean.getBooleans(), bean2.getBooleans());
    Assert.assertEquals(bean.getSimples(), bean2.getSimples());
  }

  
  @Test
  public void testContainingMap() {
    ContainingMap bean = new ContainingMap();
    
    Map<String, String> stringMap = new HashMap<>();
    stringMap.put("A", "1");
    stringMap.put("B", "2");
    bean.setStringMap(stringMap);
    
    Map<String, Integer> intMap = new HashMap<>();
    intMap.put("A", 1);
    intMap.put("B", 2);
    bean.setIntMap(intMap);
    
    Map<String, SimpleBean> mapOfObject = new HashMap<>();
    mapOfObject.put("S1", createSimpleBean());
    mapOfObject.put("S2", createSimpleBean());
    bean.setMapOfObject(mapOfObject);
    
    Map<String, ContainingArray> mapOfObject2 = new HashMap<>();
    mapOfObject2.put("A1", new ContainingArray());
    bean.setMapOfObject2(mapOfObject2);
    
    Map<String, Integer[]> mapOfArray = new HashMap<>();
    mapOfArray.put("A1", new Integer[]{1,2,3});
    mapOfArray.put("A2", new Integer[]{4,5,6});
    bean.setMapOfArray(mapOfArray);
    
    String json = jacinda.toJson(bean);
    System.out.println(json);

    ContainingMap bean2 = jacinda.fromJson(json, ContainingMap.class);
    Assert.assertEquals(bean.getStringMap(), bean2.getStringMap());
    Assert.assertEquals(bean.getIntMap(), bean2.getIntMap());
    SimpleBean a = bean.getMapOfObject().get("S1");
    SimpleBean b = bean2.getMapOfObject().get("S1");
//    Assert.assertEquals(bean.getMapOfObject().get("S1"), bean2.getMapOfObject().get("S1"));
    Assert.assertArrayEquals(bean.getMapOfArray().get("A1"), bean2.getMapOfArray().get("A1"));
    Assert.assertArrayEquals(bean.getMapOfArray().get("A2"), bean2.getMapOfArray().get("A2"));
  }
  
  
  @Test
  public void testNestedBean() {
    NestedBean.NestedBean3 beanL3 = new NestedBean.NestedBean3();
    beanL3.setValue("Hello");
    beanL3.setSimples(
        new SimpleBean[] { createSimpleBean(), createSimpleBean() });
    NestedBean.NestedBean2 beanL2 = new NestedBean.NestedBean2();
    beanL2.setBean3(beanL3);
    NestedBean.NestedBean1 beanL1 = new NestedBean.NestedBean1();
    beanL1.setBean2(beanL2);
    NestedBean bean = new NestedBean();
    bean.setBean1(beanL1);
    bean.setBeans(new NestedBean.NestedBean1[] { beanL1 });

    String json = jacinda.toJson(bean);
    System.out.println(json);

    NestedBean bean2 = jacinda.fromJson(json, NestedBean.class);
    Assert.assertEquals(1, bean2.getBeans().length);
    Assert.assertEquals(bean, bean2);

  }

  @Test
  public void testContainingFormattableObject() {
    ContainingFormattableObject bean = new ContainingFormattableObject();

    String json = jacinda.toJson(bean);
    System.out.println(json);

    ContainingFormattableObject bean2 = jacinda.fromJson(json,
        ContainingFormattableObject.class);
    Assert.assertEquals(bean.getDate(), bean2.getDate());
    Assert.assertEquals(bean.getTimestamp(), bean2.getTimestamp());
    Assert.assertEquals(bean.getBigDecimal(), bean2.getBigDecimal());

  }

  public static void main(String[] args) {
    ContainingMap bean = new ContainingMap();
    
    Map<String, String> stringMap = new HashMap<>();
    stringMap.put("A", "1");
    stringMap.put("B", "2");
    bean.setStringMap(stringMap);
    
    Map<String, Integer> intMap = new HashMap<>();
    intMap.put("A", 1);
    intMap.put("B", 2);
    bean.setIntMap(intMap);
    
    Map<String, SimpleBean> mapOfObject = new HashMap<>();
    mapOfObject.put("S1", createSimpleBean());
    mapOfObject.put("S2", createSimpleBean());
    bean.setMapOfObject(mapOfObject);
    
    Map<String, ContainingArray> mapOfObject2 = new HashMap<>();
    mapOfObject2.put("A1", new ContainingArray());
    bean.setMapOfObject2(mapOfObject2);
    
    Map<String, Integer[]> mapOfArray = new HashMap<>();
    mapOfArray.put("A1", new Integer[]{1,2,3});
    mapOfArray.put("A2", new Integer[]{4,5,6});
    bean.setMapOfArray(mapOfArray);
    
    String json = jacinda.toJson(bean);
    System.out.println(json);
    
    jacinda.fromJson(json, ContainingMap.class);
  }
}
