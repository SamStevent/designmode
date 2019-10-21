package sam.designmode.create.factory;


/**
 * @author chenxl
 * @since 2019-10-13
 * 简单工厂的定义：提供一个创建对象实例的功能，而无须关心其具体实现。被创建实例的类型可以是接口、抽象类，也可以是具体的类
 */
public class SimpleFactoryMode {
	
	interface Car{    //接口
		String getChineseName();
	}
	
	/*具体实现*/
	class Benz implements Car{
		@Override
		public String getChineseName() {
			return "奔驰";
		}
	}
	class BMW implements Car{
		@Override
		public String getChineseName() {
			return "宝马";
		}
	}
	/*普通简单工厂类，要避免输入的参数不存在*/
	class CarCommenFactory{
		Car createCar(String name){
			switch(name){   //根据传如参数生成不同的实现类
			case "Benz":
				return new Benz();
			case "BMW":
				return new BMW();
			default:
				return null;
			}
		}
	}
	/*多方法工厂，调用不同多方法生成不同多产品*/
	class CarMangMethodFactory{
		Car createBenz(){
			return new Benz();
		}
		Car createBMW(){
			return new BMW();
		}
	}
	/*静态方法工厂,不需要创建工厂实例，直接调用方法即可*/
	 static class CarStaticMethodFactory{
		 static Car createBenz(){
			return new SimpleFactoryMode().new Benz();  //benz是内部类
		}
		static Car createBMW(){
			return new SimpleFactoryMode().new BMW();
		}
	 }
	
	abstract class IceCream{   //抽象类，里面有一些信息是所有子类共有且相同的
		IceCream(String name, int storageTemperature, String sameInfo){
			this.name = name;
			this.storageTemperature = storageTemperature;
			this.sameInfo = sameInfo;
		}
		String name;
		int storageTemperature;//储存温度
		String sameInfo;//其他相同的信息
		abstract String getName();
	}
	class AppleIceCream extends IceCream{
		AppleIceCream(String name, int storageTemperature, String sameInfo) {
			super(name, storageTemperature, sameInfo);
		}

		String getName(){
			return "苹果味冰淇淋";
		}
	}
	class OrangeIceCream extends IceCream{
		OrangeIceCream(String name, int storageTemperature, String sameInfo) {
			super(name, storageTemperature, sameInfo);
		}

		String getName(){
			return "橘子味冰淇淋";
		}
	}
	class BananaIceCream extends IceCream{
		BananaIceCream(String name, int storageTemperature, String sameInfo) {
			super(name, storageTemperature, sameInfo);
		}

		String getName(){
			return "香蕉味冰淇淋";
		}
	}
	
	class IceCreamFactory{
		IceCream createIceCream(String name){
			int storageTemperature = 0;//储存温度（相同信息）
			String sameInfo = "info";//其他相同的信息
			switch(name){
			case "apple":
				return new AppleIceCream(name, storageTemperature, sameInfo);
			case "orange":
				return new OrangeIceCream(name, storageTemperature, sameInfo);
			case "banana":
				return new BananaIceCream(name, storageTemperature, sameInfo);
			default:
				return null;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("-----------普通简单工厂，需要保证传入参数对正确性，否则返回null-------------");
		CarCommenFactory carCommenFactory = new SimpleFactoryMode().new CarCommenFactory();  //这里工厂类是内部类
		Car Benz = carCommenFactory.createCar("Benz");
		System.out.println(Benz.getChineseName());
		Car BMW = carCommenFactory.createCar("BMW");
		System.out.println(BMW.getChineseName());
		System.out.println("-----------多方法简单工厂，调用不同对方法生成对应对对象，不过需要先实例化工厂对象-------------");
		CarMangMethodFactory carMangMethodFactory = new SimpleFactoryMode().new CarMangMethodFactory();
		System.out.println(carMangMethodFactory.createBenz().getChineseName());
		System.out.println(carMangMethodFactory.createBMW().getChineseName());
		System.out.println("-----------静态方法简单工厂，直接调用生成方法-------------");
		System.out.println(SimpleFactoryMode.CarStaticMethodFactory.createBenz().getChineseName());
		System.out.println(SimpleFactoryMode.CarStaticMethodFactory.createBMW().getChineseName());
		System.out.println("-----------上面是对接口对实现，这里是对类对实现，这个更能体现出工厂对好处，调用者不需要关心细节-------------");
		IceCreamFactory iceCreamFactory = new SimpleFactoryMode().new IceCreamFactory();
		IceCream appleIceCream = iceCreamFactory.createIceCream("apple");
		System.out.println(appleIceCream.getName());
		IceCream orangeIceCream = iceCreamFactory.createIceCream("orange");
		System.out.println(orangeIceCream.getName());
		IceCream bananaIceCream = iceCreamFactory.createIceCream("banana");
		System.out.println(bananaIceCream.getName());
	}

}
