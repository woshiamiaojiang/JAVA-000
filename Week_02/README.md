## 作业一：

使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。



## 作业二：

使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。



## 作业三：

如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。



## 作业四：

根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结，提交到 Github。

**串行GC**

java -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603894228067-96dfe231-3907-4327-bfb5-694c7aaf10a0.png?x-oss-process=image%2Fresize%2Cw_1764)

性能很差，避免使用。

**并行GC**

java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603894260565-d9a5357a-8eb0-4786-908a-8b8f4f7d60e3.png?x-oss-process=image%2Fresize%2Cw_1764)

**CMS GC**

java -XX:+UseConcMarkSweepGC-Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603894280707-69fcab4d-643c-49bd-8762-15179554d747.png?x-oss-process=image%2Fresize%2Cw_1764)

**G1 GC**

java -XX:+UseG1GC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDateStamps GCLogAnalysis

![image.png](https://cdn.nlark.com/yuque/0/2020/png/733521/1603894300907-e8bebbb5-7557-4dfb-9548-ab88c1785089.png?x-oss-process=image%2Fresize%2Cw_1764)

对比：

Serial collector适合单核CPU与小堆，收集时会暂停所有工作线程

Parallel collector适合多核CPU，收集时会暂停所有工作线程

CMS相较于Parallel，需要更多的CPU资源，最短停顿时间，堆小于4G建议使用

G1适合大堆（>4GB），最短停顿时间



收集器权衡：

尽快算出结果 -> 吞吐量

SLA应用 -> 低延迟

客户端应用 -> 内存占用



## 作业五：

运行课上的例子，以及 Netty 的例子，分析相关现象。



## 作业六：

写一段代码，使用 HttpClient 或 OkHttp 访问 [http://localhost:8801 ](http://localhost:8801/)，代码提交到 Github。

```java
public class OkHttpDemo {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder().url("http://localhost:8801").build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } finally {
            client.clone();
        }
    }
}
```