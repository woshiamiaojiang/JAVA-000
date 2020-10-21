## 作业一：

自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。



```java
/**
 * description: 自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，
 *              然后自己分析一下对应的字节码，有问题群里讨论。
 *
 * @since 2020-10-21
 */
public class Hello {

    public static void main(String[] args){
        int a = 20, b = 10, c = 0;
        c = a + b;
        c = a * b;
        c = a / b;
        c = a - b;
        if ( a > b ) {
            for (int i = 0; i < 10; i++) {
                c++;
            }
        }
        System.out.println(c);
    }
}
```

IDEA打开视图里的Show Bytecode With Jclasslib插件进行反编译生成的字节码

或者用javap -c xx.class文件反编译

```java
C:\App\IdeaProjects\Test\out\production\Test\com\company>javap -c Hello.class
Compiled from "Hello.java"
public class com.company.Hello {
  public com.company.Hello();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: bipush        20  // push byte 20 onto the operand stack
       2: istore_1          // Store 20 into local variable1
       3: bipush        10  // push byte 10 onto the operand stack
       5: istore_2          // Store 10 into local variable2
       6: iconst_0          // Push the int constant <0> onto the operand stack
       7: istore_3          // Store 0 into local variable3
       8: iload_1           // Load int from local variable1
       9: iload_2           // Load int from local variable2
      10: iadd              // Add value1 and value2. Both value1 and value2 must be of type int. The result is pushed onto the operand stack.
      11: istore_3          // Store sum result into local variable3
      12: iload_1           // Load int from local variable1
      13: iload_2           // Load int from local variable2
      14: imul              // Multiply value1 and value2. Both value1 and value2 must be of type int. The result is pushed onto the operand stack.
      15: istore_3          // Store product result into local variable3
      16: iload_1           // Load int from local variable1
      17: iload_2           // Load int from local variable2
      18: idiv              // Devide value1 and value2. Both value1 and value2 must be of type int. The result is pushed onto the operand stack.
      19: istore_3          // Store quotient result into local variable3
      20: iload_1           // Load int from local variable1
      21: iload_2           // Load int from local variable2    
      22: isub              // Substract value1 and value2. Both value1 and value2 must be of type int. The result is pushed onto the operand stack.
      23: istore_3          // Store result into local variable3    
      24: iload_1           // Load int from local variable1
      25: iload_2           // Load int from local variable2    
      26: if_icmple     48  // if int type variable1 compare less than or equals variable2, go to line 48
      29: iconst_0          // Push the int constant <0> onto the operand stack.
      30: istore        4   // Store 0 into local variable4
      32: iload         4   // Load int from local variable4
      34: bipush        10  // push byte 10 onto the operand stack 
      36: if_icmpge     48  // if int type variable1 compare greater than or equals variable2, go to line 48
      39: iinc          3, 1 // Increment local variable3 by constant 1
      42: iinc          4, 1 // Increment local variable4 by constant 1
      45: goto          32  // Go to line32
      48: getstatic     #2  // Get static field <java/lang/System.out> from class 
      51: iload_3           // Load int from local variable3
      52: invokevirtual #3  // Invoke instance method <java/io/PrintStream.println> 
      55: return            // Return void from method
}
```



## 作业二：

自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。

```java
public class HelloClassloader extends ClassLoader{

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException {
        try {
            Class<?> clz = new HelloClassloader().findClass("Hello");// 加载并初始化Hello类
            // 获取要调用的方法
            Method hello = clz.getDeclaredMethod("hello");
            hello.setAccessible(true);
            // 调用指定实例的方法
            hello.invoke(clz.newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(this.getClass().getResource("Hello.xlass").getPath());
        int length = (int)file.length();
        byte[] bytes = new byte[length];
        try {
            new FileInputStream(file).read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return super.findClass(name);
        }
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte)(255 - bytes[i]);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
```

把Hello.xclass文件HelloClassloader放在jvm包里面。

运行输出结果为 Hello, classLoader!

Hello.xclass



## 作业三：

画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。



![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603283676655-ecc00eb9-0904-4494-b153-f48e077cc05e.png)

Java Performance推荐公式

![image](https://cdn.nlark.com/yuque/0/2020/png/733521/1603283517232-ecedd97d-bb1f-4a12-9ad2-ebcbe6f79965.png)



## 作业四：

检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。

jstat 查看GC信息

jstack 查看线程信息

jmap 查看heap或类占用空间