package json.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zappos.json.annot.JsonFormat;

public class Main {
  
  public static class Test {
    @JsonFormat("dd MM yyyy HH:mm:ss")
    private Date date = new Date();
    private String a = "hello\"";
    
    public Date getDate() {
      return date;
    }

    public void setDate(Date date) {
      this.date = date;
    }

    public String getA() {
      return a;
    }

    public void setA(String a) {
      this.a = a;
    }
    
  }
  
  public void doList(){
    List<String> list = new ArrayList<>();
    list.add("A");list.add("B");
    
    Iterator<String> iter = list.iterator();
    System.out.print("[");
    if(iter.hasNext()){
      boolean hasLast = false;
      String next = iter.next();
      do{
        System.out.print(next);
        if(hasLast = iter.hasNext()){
          next = iter.next();
          System.out.print(",");
        }
      }while(iter.hasNext());
      if(hasLast) System.out.print(next);
    }
    
    System.out.print("]");
    
    System.out.println();
    System.out.print("[");
    int j = list.size() - 1;
    for(int i = 0; i < j; i++){
      String a = null;
      System.out.print(list.get(i)+",");
    }
    if(j > -1){
      System.out.print(list.get(j));
    }
    System.out.print("]");
  }
  
  public void doMap(){
    Map map = new HashMap();
    
    Iterator iter = map.entrySet().iterator();
    
    if(iter.hasNext()){
      boolean hasLast = false;
      String key = null;
      java.util.Map.Entry entry = (Map.Entry)iter.next();
      do{
        key = (String)entry.getKey();
        Object value = entry.getValue();
        System.out.print(key + ":" + value);
        if(hasLast = iter.hasNext()){
          entry = (Map.Entry)iter.next();
          System.out.print(",");
        }
      }while(iter.hasNext());
      if(hasLast) {
        key = (String)entry.getKey();
        Object value = entry.getValue();
        System.out.print(key + ":" + value);
      }
    }
  }
  
  public static class TestMethod {
    public void doSomething(){}
  }
  
  public static void main(String[] args) throws Exception{
    int n = 10000000;
    TestMethod test = new TestMethod();
    Method method = TestMethod.class.getDeclaredMethod("doSomething");
    long t1 = System.currentTimeMillis();
    for(int i = 0; i < n; i++){
      method.invoke(test);
//      test.doSomething();
    }
    System.out.println(System.currentTimeMillis() - t1);
  }
}
