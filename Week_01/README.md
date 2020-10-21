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



![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603289126007-4e4c9d78-224a-43a6-af92-ed891a774fe3.png)

Java Performance推荐公式

![image](https://cdn.nlark.com/yuque/0/2020/png/733521/1603283517232-ecedd97d-bb1f-4a12-9ad2-ebcbe6f79965.png)



## 作业四：

检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。

jstat 查看GC信息

jstack 查看线程信息

jmap 查看heap或类占用空间



jps 打印所有java进程pid

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603287072171-aa53c87b-8019-4b6f-a87a-ba650c549952.png)

通过jps -lmv 打印所有java进程pid详细信息

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603287014848-f17cdc6f-f3bd-4ea6-810f-7fa77aa17c43.png)

jinfo 打印配置信息，包括命令行参数、系统变量。

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603287246739-fea1a13a-354f-4f45-accc-a790c1511e8b.png)



jstat -gc  1000(ms执行次数) 1000(执行次数)

打印GC相关的堆内存信息(单位kb)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603287545378-a39e21dd-d7de-463e-819e-0debaffe997b.png)



S0C、S1C、S0U、S1U：Survivor 0/1区容量（Capacity）和使用量（Used）

EC、EU：伊甸区容量和使用量

OC、OU：老年代容量和使用量

MC、MU：元数据区容量和使用量

YGC、YGT：年轻代GC次数和GC耗时

FGC、FGCT：Full GC次数和Full GC耗时

GCT：GC总耗时

CCSC、CCSU: 压缩class空间容量和使用量



jstat -gcutil  pid 1000(ms执行次数) 1000(执行次数)

打印GC相关的堆内存信息(单位百分比)

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603288368340-4535e744-b288-43db-997c-87828e9ac009.png)



S0 就是0号存活区的百分比使用率。0%很正常，因为S0和S1随时有一个是空的。

S1 就是1号存活区的百分比使用率。

E 就是Eden区，新生代的百分比使用率。

O 就是Old区，老年代。百分比使用率。

M 就是Meta区，元数据区百分比使用率。

CCS 压缩 class空间（ Compressed class space）的百分比使用率。

YGC （Young GC）年轻代GC的次数。49次。

YGCT 年轻代GC消耗的总时间。2.5秒，占总运行时间的万分之一不到，基本上可忽略。

FGC FULL GC的次数，可以看到只发生了6次，问题应该不大。

FGCT FULL GC的总时间，0.8秒，平均每次160ms左右

GCT 所有GC加起来消耗的总时间，即YGCT+FGCT



jmap -histo pid > a.txt

vim a.txt

-histo 看哪些类占用的空间最多, 直方图

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603289260074-9f9e3273-9e2a-45d9-8d8a-8595be94bec1.png)

Char数组

Byte数组

Int数组

类数组



jmap -heap pid 

打印堆内存（内存池）的配置和 使用信息。

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603289556124-1e8a4447-a3ba-49ac-b210-915efd2e573f.png)

jstack -l pid 

长列表模式. 将线程相关的 locks 信息一起输出， 比如持有的锁，等待的锁。

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603289956808-569cf330-cbb8-4636-b34c-935a2f15428e.png)



Jcmd综合了前面的几个命令



示例

jcmd pid help

jcmd pid VM.version

jcmd pid VM.flags

jcmd pid VM.command_line

jcmd pid VM.system_properties 系统信息

jcmd pid Thread.print 打印线程，效果等同于jstack -l pid 

jcmd pid GC.Class_histogram

jcmd pid GC.heap_info



jcmd pid VM.version

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603290133889-decf45cf-c6f4-432e-ba96-88d9670049a5.png)



jcmd pid VM.flags 设置的参数

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603290193121-85e73cf9-ea08-44a0-bc13-48950bab3ef5.png)

jcmd pid VM.command_line 启动命令行

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603290270478-7c2a8130-70be-4cf9-a253-9149c68970b1.png)





分析出来的一些问题：

1.平均每次Full GC要160ms，时间有点长

2.老年代使用率才5%，伊甸区使用率40%，配置大小不太合理。

3.年轻代GC次数频繁，年轻代分配的空间偏小了



## 作业五：

IDEA启动添加虚拟机配置 

-XX:+UseG1GC -XX:MaxGCPauseMillis=50



查看配置

jcmd pid VM.flags

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603291672294-d874bad8-a8ec-4751-81f0-b128e0f50120.png?x-oss-process=image%2Fresize%2Cw_1959)



jcmd pid GC.heap_info

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603291780923-802cf8aa-df98-4d8c-8f93-4462be110ecd.png?x-oss-process=image%2Fresize%2Cw_1959)



jstat -gc pid 1000 1000

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603291836271-fa8f5472-f07c-45f6-882e-445fc68f4517.png?x-oss-process=image%2Fresize%2Cw_1959)

jstat -gcutil pid 1000 1000

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603291898867-06d331a3-46dc-4eb9-8def-9d0259fee509.png)



jmap -heap pid

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603292018995-5ad08dde-50c0-406c-9ae8-1e40d352d133.png)