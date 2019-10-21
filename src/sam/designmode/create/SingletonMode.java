package sam.designmode.create;

/**
 * @author chenxl
 * @since 2019-10-19
 * 单例模式（Singleton Pattern）是 Java 中最简单的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。

这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。

注意：

1、单例类只能有一个实例。
2、单例类必须自己创建自己的唯一实例。
3、单例类必须给所有其他对象提供这一实例。*/
public class SingletonMode {
	
	public static void main(String[] args) {
		
	}

}

/*饿汉模式*/
class Singleton1{
	private static Singleton1 instance = new Singleton1();
	private Singleton1(){}
	public Singleton1 getInstance(){
		return instance;
	}
}
/*懒汉模式*/
class Singleton2{
	private static Singleton2 instance;
	private Singleton2(){}
	public synchronized Singleton2 getInstance(){
		if(null == instance){
			instance = new Singleton2();
		}
		return instance;
	}
}

/*双检锁/双重校验锁（DCL，即 double-checked locking）
 * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 * getInstance() 的性能对应用程序很关键。
 */
class Singleton3{
	private volatile static Singleton3 instance;
	private Singleton3(){}
	public Singleton3 getInstance(){
		if(null == instance){
			synchronized(this){
				if(null == instance){
					instance = new Singleton3();
				}
			}
		}
		return instance;
	}
}

/*登记式/静态内部类
是否 Lazy 初始化：是

是否多线程安全：是

实现难度：一般

描述：这种方式能达到双检锁方式一样的功效，但实现更简单。对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。
这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
这种方式同样利用了 classloader 机制来保证初始化 instance 时只有一个线程，
它跟第 3 种方式不同的是：第 3 种方式只要 Singleton 类被装载了，那么 instance 就会被实例化（没有达到 lazy loading 效果），
而这种方式是 Singleton 类被装载了，instance 不一定被初始化。因为 SingletonHolder 类没有被主动使用，
只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance。
想象一下，如果实例化 instance 很消耗资源，所以想让它延迟加载，另外一方面，又不希望在 Singleton 类加载时就实例化，
因为不能确保 Singleton 类还可能在其他的地方被主动使用从而被加载，那么这个时候实例化 instance 显然是不合适的。
这个时候，这种方式相比第 3 种方式就显得很合理。*/
class Singleton4{
	private Singleton4(){}
	private static class SingletonHolder{
		final static Singleton4 INSTANCE = new Singleton4();
	}
	public final static Singleton4 getInstance(){
		return SingletonHolder.INSTANCE;
	}
}

/*枚举
JDK 版本：JDK1.5 起

是否 Lazy 初始化：否

是否多线程安全：是

实现难度：易

描述：这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。不过，由于 JDK1.5 之后才加入 enum 特性，用这种方式写不免让人感觉生疏，在实际工作中，也很少用。
不能通过 reflection attack 来调用私有构造方法。*/
enum Singleton5{
	INSTANCE;
	public void whateverMethod(){
		System.out.println("任意想要完成的功能");
	}
}


