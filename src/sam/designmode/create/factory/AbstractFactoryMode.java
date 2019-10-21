package sam.designmode.create.factory;
/**
 * @author chenxl
 * @since 2019-10-19
 * 抽象工厂模式,一个自助商店出售3种不同口味的冰淇淋，每种冰淇淋有大份和小份两种，
 * 于是商店购买了几台有两个分别用于制作大份和小份冰淇淋窗口的机器（IceCreamFactory）,
 * 每台机器根据放如的原料决定能生产哪个口味的冰淇淋（如放如苹果，那就变成一个制作苹果口味冰淇淋的机器AppleIceCreamFactory），
 * 而制作出的冰淇淋根据大小放在不同的容器（BigIceCream或SmallIceCream)中*/
public class AbstractFactoryMode {
	/*大份冰淇淋接口*/
	interface BigIceCream{
		void taste();
	}
	/*小份冰淇淋接口*/
	interface SmallIceCream{
		void taste();
	}
	/*大份冰淇淋具体实现类*/
	class BigAppleIceCream implements BigIceCream{

		@Override
		public void taste() {
			System.out.println("taste big apple ice-cream!");
		}
		
	}
	class BigOrangeIceCream implements BigIceCream{
		
		@Override
		public void taste() {
			System.out.println("taste big orange ice-cream!");
		}
		
	}
	class BigBananaIceCream implements BigIceCream{
		
		@Override
		public void taste() {
			System.out.println("taste big banana ice-cream!");
		}
		
	}
	/*小份冰淇淋实现类*/
	class SmallAppleIceCream implements SmallIceCream{
		@Override
		public void taste(){
			System.out.println("taste small apple ice-cream!");
		}
	}
	class SmallOrangeIceCream implements SmallIceCream{
		@Override
		public void taste(){
			System.out.println("taste small orange ice-cream!");
		}
	}
	class SmallBananaIceCream implements SmallIceCream{
		@Override
		public void taste(){
			System.out.println("taste small banana ice-cream!");
		}
	}
	/*冰淇淋工厂接口，定义了生产大份和小份冰淇淋的方法*/
	interface IceCreamFactory{
		SmallIceCream createSmallIceCream();
		BigIceCream createBigIceCream();
	}
	/*冰淇淋工厂堕具体实现类*/
	class AppleIceCreamFactory implements IceCreamFactory{
		@Override
		public SmallIceCream createSmallIceCream(){
			return new SmallAppleIceCream();
		}
		@Override
		public BigIceCream createBigIceCream(){
			return new BigAppleIceCream();
		}
	}
	class OrangeIceCreamFactory implements IceCreamFactory{
		@Override
		public SmallIceCream createSmallIceCream(){
			return new SmallOrangeIceCream();
		}
		@Override
		public BigIceCream createBigIceCream(){
			return new BigOrangeIceCream();
		}
	}
	class BananaIceCreamFactory implements IceCreamFactory{
		@Override
		public SmallIceCream createSmallIceCream(){
			return new SmallBananaIceCream();
		}
		@Override
		public BigIceCream createBigIceCream(){
			return new BigBananaIceCream();
		}
	}
	public static void main(String[] args) {
		AbstractFactoryMode mode = new AbstractFactoryMode();
		IceCreamFactory appleIceCreamFactory = mode.new AppleIceCreamFactory();
		appleIceCreamFactory.createBigIceCream().taste();
		appleIceCreamFactory.createSmallIceCream().taste();
		
		IceCreamFactory orangeIceCreamFactory = mode.new OrangeIceCreamFactory();
		orangeIceCreamFactory.createBigIceCream().taste();
		orangeIceCreamFactory.createSmallIceCream().taste();
		
		IceCreamFactory bananaIceCreamFactory = mode.new BananaIceCreamFactory();
		bananaIceCreamFactory.createBigIceCream().taste();
		bananaIceCreamFactory.createSmallIceCream().taste();
		
	}

}
