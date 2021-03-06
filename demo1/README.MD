================================
IntelliJ IDEA 2017.3.4
JDK1.8.0_152
================================

多线程：
 参考：https://www.cnblogs.com/tonglin0325/p/6260485.html
 
多个线程访问共享对象和数据的方式
 1. 如果每个线程执行的代码相同，可以使用同一个Runnable对象，这个Runnable对象属性可以用于共享数据。
 2. 如果每个线程执行的代码不同，这时候需要用不同的Runnable对象。
    a). 将共享数据封装在另外一个对象中，然后将这个对象逐一传递给各个Runnable对象。每个线程对共享数据的操作方法也分配到那个对象身上去完成，这样容易实现针对该数据进行的各个操作的互斥和通信。
    b). 将这些Runnable对象作为某一个类中的内部类，共享数据作为这个外部类中的成员变量，每个线程对共享数据的操作方法也分配给外部类，以便实现对共享数据进行的各个操作的互斥和通信，作为内部类的各个Runnable对象调用外部类的这些方法。
    c). 极端且简单的方式，即在任意一个类中定义一个static的变量，这将被所有线程共享。
    
同一线程范围的共享变量的方式
 1. ThreadLocal方式
    