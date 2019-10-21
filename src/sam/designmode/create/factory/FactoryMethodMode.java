package sam.designmode.create.factory;

/**
 * @author chenxl
 * @since 2019-10-13
 * 工厂方法模式，创建一个工厂接口和创建多个工厂实现类，这样一旦需要增加新的功能，直接增加新的工厂类就可以了，不需要修改之前的代码。
 */
public class FactoryMethodMode {
	interface Car{    //产品接口
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
	
	interface CarFactory{  //工厂接口
		Car createCar();
	}
	/*具体工厂*/
	class BenzFactory implements CarFactory{
		@Override
		public Car createCar() {
			return new Benz();
		}
	}
	
	class BMWFactory implements CarFactory{
		@Override
		public Car createCar() {
			return new BMW();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CarFactory benzFactory = new FactoryMethodMode().new BenzFactory();   //根据需要生成不同对工厂
		CarFactory BMWFactory = new FactoryMethodMode().new BMWFactory();
		System.out.println(benzFactory.createCar().getChineseName());
		System.out.println(BMWFactory.createCar().getChineseName());
	}

}
