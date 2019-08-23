
### 简介

> **JRE** 全称Java Runtime Environment，Java运行环境，也就是Java平台。所有的Java程序都要在JRE下才能运行。JDK的工具也是Java程序，也需要JRE才能运行。为了保持JDK的独立性和完整性，在JDK的安装过程中，JRE也是安装的一部分。所以，目前ORACLE官网介绍看，JRE则属于JDK的一部分，如果不想开发，只是想跑跑JAVA程序，官网可以下载到单独的JRE包，但如果要开发，还是需安装上面的JDK。
>
> **JDK** 全称Java development toolkit，相当于是Java的库函数，是编译、运行java程序的工具包，是一切java应用程序的基础,所有java应用程序是构建在这个之上的。它是一组API，也可以说是一些java Class。JDK作为Java开发工具包，主要用于构建在Java平台上运行的应用程序、Applet 和组件等，目前ORACLE官网介绍看，JDK属于SDK的一部分，JDK和SDK一起发行的。如：jdk-8u65-windows-i586.exe，单一运行java程序的工具包。
>
> **SDK** 全称Software Develop Kit，软件开发工具包，用于帮助开发人员提高开发效率。各种不同类型的软件开发，都可以有自己的SDK。Windows有Windows SDK，DirectX 有 DirectX 9 SDK，.NET开发也有Microsoft .NET Framework SDK。JAVA开发当然也有自己的Java SDK。如：Java EE 7 SDK，集成了企业开发的很多工具，包括jdk。
>
> **JVM** 全称Java Virtual Machine，Java虚拟机，是JRE的一部分。它是一个虚构出来的计算机，是通过在实际的计算机上仿真模拟各种计算机功能来实现的。JVM有自己完 善的硬件架构，如处理器、堆栈、寄存器等，还具有相应的指令系统。Java语言最重要的特点就是跨平台运行。使用JVM就是为了支持与操作系统无关，实现跨平台。

> **J2SE** 是Java 2 Platform Standard Edition的缩写，直译过来就是Java2平台标准版，适用于开发小应用程序和C/S架构的桌面程序, J2SE 包含那些构成Java语言核心的类。
>
> **J2EE** 全称Java 2 Enterprise Edition,从JDK 5.0开始，改名为Java EE，是Java的一种企业版，用于企业级应用开发。
>
> **J2ME** 全称Java 2 Micro Edition，是Java的微型版，用于手机、PDA等嵌入式开发，针对手机开发，还有专门的J2ME Wireless Toolkit免费套件提供。

### 变量作用域

Java用一对大括号作为语句块的范围，称为作用域。

Java的变量作用域一共有四种，分别是类级、对象实例级、方法级、块级。

```java
public class demo
{
    public static String name = "hello";//类级变量
    public int i;//对象级变量，默认为0
    static{
        int j = 1;//块级变量，只能在块内部访问
    }
    public void test()
    {
        int k = 2;//方法级变量，只能在该方法内使用
        System.out.println("i=" + i);
    }
    public static void main(String[] args)
    {
        System.out.println("name");//类级变量不需要实例化对象就可使用
        demo d = new demo();
        d.test();
    }
}
```



