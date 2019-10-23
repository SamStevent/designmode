package sam.designmode.structure;

/**
 * @author chenxl
 * @since 2019-10-22
 * 代理模式
	意图：为其他对象提供一种代理以控制对这个对象的访问。
	主要解决：在直接访问对象时带来的问题，比如说：要访问的对象在远程的机器上。在面向对象系统中，有些对象由于某些原因（比如对象创建开销很大，或者某些操作需要安全控制，或者需要进程外的访问），直接访问会给使用者或者系统结构带来很多麻烦，我们可以在访问此对象时加上一个对此对象的访问层。
	何时使用：想在访问一个类时做一些控制。
	如何解决：增加中间层。
	关键代码：实现与被代理类组合。
	应用实例： 1、Windows 里面的快捷方式。 2、猪八戒去找高翠兰结果是孙悟空变的，可以这样理解：把高翠兰的外貌抽象出来，高翠兰本人和孙悟空都实现了这个接口，猪八戒访问高翠兰的时候看不出来这个是孙悟空，所以说孙悟空是高翠兰代理类。 3、买火车票不一定在火车站买，也可以去代售点。 4、一张支票或银行存单是账户中资金的代理。支票在市场交易中用来代替现金，并提供对签发人账号上资金的控制。 5、spring aop。
	优点： 1、职责清晰。 2、高扩展性。 3、智能化。
	缺点： 1、由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度变慢。 2、实现代理模式需要额外的工作，有些代理模式的实现非常复杂。
	使用场景：按职责来划分，通常有以下使用场景： 1、远程代理。 2、虚拟代理。 3、Copy-on-Write 代理。 4、保护（Protect or Access）代理。 5、Cache代理。 6、防火墙（Firewall）代理。 7、同步化（Synchronization）代理。 8、智能引用（Smart Reference）代理。
	注意事项： 1、和适配器模式的区别：适配器模式主要改变所考虑对象的接口，而代理模式不能改变所代理类的接口。 2、和装饰器模式的区别：装饰器模式为了增强功能，而代理模式是为了加以控制。
*/
public class ProxyMode {
	/*图片接口，代理类和被代理类都要实现这个接口*/
	public interface Image{
		public void display();   //展示图片
	}
	/*从硬盘加载信息的图片对象*/
	public class ImageLoadFromDisk implements Image{
		private String fileName;
		public ImageLoadFromDisk(String fileName) {
			this.fileName = fileName;
			loadFromDisk();
		}
		/*加载图片的方法*/
		private void loadFromDisk() {
			System.out.println("loading image " + this.fileName + "from disk.");
		}
		@Override
		public void display() {
			System.out.println("display image " + this.fileName);
		}
	}
	/*从数据库加载信息的图片对象*/
	public class ImageLoadFromDataBase implements Image{
		private String fileName;
		public ImageLoadFromDataBase(String fileName) {
			this.fileName = fileName;
			loadFromMemory();
		}
		/*加载图片的方法*/
		private void loadFromMemory() {
			System.out.println("loading image " + this.fileName + "from database.");
		}
		@Override
		public void display() {
			System.out.println("display image " + this.fileName);
		}
	}
	public class ImageProxy implements Image{
		/*配置从哪加载照片，在具体运用中可以通过读配置文件的方式进行*/
//		final String from = "disk";
		final String from = "database";
		
		private volatile Image realImage;  //被代理的类，这里采用延时加载
		private String fileName;
		public ImageProxy(String fileName) {
			this.fileName = fileName;
		}
		@Override
		public void display() {
			if(null == realImage) {    //延迟加载文件时间
				synchronized(this) {      //采用双重校验，防止高并发情况下通过对象初始化两次
					if(null == realImage) {
						switch(from) {         //根据配置使用不同的实现类
						case "disk":
							this.realImage = new ImageLoadFromDisk(this.fileName);
							break;
						case "database":
							this.realImage = new ImageLoadFromDataBase(this.fileName);
							break;
						default:
							throw new RuntimeException("加载类型不正确，请检查配置信息");
						}
					}
				}
				
			}
			this.realImage.display();   //实际调用的方法
		}
	}
	
	public static void main(String[] args) {
		ProxyMode mode = new ProxyMode();
		Image image = mode.new ImageProxy("abc.txt");
		image.display();
		System.out.println("-------------");
		image.display();
	}

}
