#Zappos JSON#

Zappos JSON is yet another JSON serializer/de-serializer. It uses code generation technique instead of
reflection. Currently, there is only one reflection call during runtime but this will be removed soon.
The only dependency library that this project requires is jboss-javassist. It uses that library to
manipulate Java byte code. Basically, it puts the hard code for serializing/de-serializing JSON 
into a class in memory.

Zappos JSON is thread-safe and it is encouraged to have only one instance per JVM except when you want to use
different JSON settings for each instance. 

You are encouraged to use only one instance. So, the constructor is protected and cannot be called directly.
You have to use a factory method `ZapposJson.getInstance()` to get an instance. If you want to have more than
one instance, you can call `ZapposJson.getInstance(name)` and provide the name of the setting.

##Using Zappos JSON with Maven

```xml
<dependencies>
    <dependency>
      <groupId>com.zappos</groupId>
      <artifactId>zappos-json</artifactId>
      <version>0.1-alpha</version>
    </dependency>
</dependencies>
```

##Features##
The main feature of Zappos JSON is a bean binding. It does not have JSON typed object because we try to eliminate
as many intermediate objects as possible.

- Binding the bean is an easy job. (Can do it in one line)
- Primitive types and its wrapper
- Array and Collection
- Nested bean
- Custom type such as Date, Enum, and other scalar-value types
- Map of scalar value and map of object
- You can mixed all of above together.
- Annotation support 

##User Guide##
Zappos JSON [user guide](https://github.com/zappos/zappos-json/blob/master/UserGuide.md):
This user guide contains several basic examples on how to use Zappos JSON.

##Usage##

```java
public static class Foo {
    private String name;
    private Bar bar;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Bar getBar() {
        return bar;
    }
    public void setBar(Bar bar) {
        this.bar = bar;
    }
}
    
public static class Bar {
    private int[] values;
    public Bar(){}
    public Bar(int values[]){
        this.values = values;
    }
    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}

Foo foo = new Foo();
foo.setBar(new Bar(new int[]{1,2,3,4,5}));
foo.setName("Anonymous");
ZapposJson zjson = ZapposJson.getInstance();
String json = zjson.toJson(foo);
System.out.println(json);
//result = {"bar":{"values":[1,2,3,4,5]},"name":"Anonymous"}
Foo foo2 = zjson.fromJson(json, Foo.class);
System.out.println(Arrays.toString(foo2.getBar().getValues()));
//result = [1, 2, 3, 4, 5]
```

##Current Limitation##
- Map key must be string (It doesn't make much sense to have other types besides String though)
- The parser doesn't support map and collection that are not inside of the bean. We need type hinting to make this thing works. Type hinting will be added in the future.
- Does not detect circular reference.
- Bean is required to have public default constructor.

##FAQ##
###What Java version does it require?
The project requires Java 8 and later to compile. But, it will run on Java 7.

###Why Zappos JSON is so slow?###
It's not supposed to be. Possibly, it is slow during the first run because it does static analysis on your object graph to generate code. The code is then compiled and loaded into memory. This process will happen only once. If you want to speed things up, call `ZapposJson.getInstance().register(className)` before using.

###Why Zappos JSON doesn't let developer call constructor directly?###
Zappos JSON uses byte code manipulation. Each instance holds the cache of modified classes with a unique name (random name). If the developer calls the constructor inside the method, the system will crash because of the notorious
exception on JVM - "java.lang.OutOfMemoryError: PermGen space"
