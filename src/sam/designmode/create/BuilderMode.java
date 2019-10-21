package sam.designmode.create;

import java.util.ArrayList;
import java.util.List;
/**
 * @author chenxl
 * @since 2019-10-20
 * 建造者模式
 * 	建造者模式（Builder Pattern）使用多个简单的对象一步一步构建成一个复杂的对象。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 * 	一个 Builder 类会一步一步构造最终的对象。该 Builder 类是独立于其他对象的。
 * 
 * 介绍
	意图：将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。
	
	主要解决：主要解决在软件系统中，有时候面临着"一个复杂对象"的创建工作，其通常由各个部分的子对象用一定的算法构成；由于需求的变化，这个复杂对象的各个部分经常面临着剧烈的变化，但是将它们组合在一起的算法却相对稳定。
	
	何时使用：一些基本部件不会变，而其组合经常变化的时候。
	
	如何解决：将变与不变分离开。
	
	关键代码：建造者：创建和提供实例，导演：管理建造出来的实例的依赖关系。
	
	应用实例： 1、去肯德基，汉堡、可乐、薯条、炸鸡翅等是不变的，而其组合是经常变化的，生成出所谓的"套餐"。 2、JAVA 中的 StringBuilder。
	
	优点： 1、建造者独立，易扩展。 2、便于控制细节风险。
	
	缺点： 1、产品必须有共同点，范围有限制。 2、如内部变化复杂，会有很多的建造类。
	
	使用场景： 1、需要生成的对象具有复杂的内部结构。 2、需要生成的对象内部属性本身相互依赖。
	
	注意事项：与工厂模式的区别是：建造者模式更加关注与零件装配的顺序。
 */
public class BuilderMode {
	/*食物包装接口*/
	public interface Packing{
		public String pack();
	}
	/*食物接口*/
	public interface Item{
		public String name();
		public Packing packing();
		public float price();
	}
	/*纸袋包装*/
	public class Wrapper implements Packing{
		@Override
		public String pack(){
			return "纸袋包装";
		}
	}
	/*玻璃瓶包装*/
	public class Bottle implements Packing{
		@Override
		public String pack(){
			return "玻璃瓶包装";
		}
	}
	/*汉堡包*/
	public abstract class Burger implements Item{
		@Override
		public Packing packing(){
			return new Wrapper();
		}
	}
	/*冷饮*/
	public abstract class ColdDrink implements Item{
		@Override
		public Packing packing(){
			return new Bottle();
		}
	}
	/*具体食物*/
	 public class VegBurger extends Burger{
		 @Override
		 public String name(){
			 return "蔬菜汉堡";
		 }
		 @Override
		 public float price(){
			 return 18.0f;
		 }
	 }
	 public class ChickenBurger extends Burger{
		 @Override
		 public String name(){
			 return "鸡肉汉堡";
		 }
		 @Override
		 public float price(){
			 return 23.0f;
		 }
	 }
	 public class Coke extends ColdDrink{
		 @Override
		 public String name(){
			 return "可口可乐";
		 }
		 @Override
		 public float price(){
			 return 6.0f;
		 }
	 }
	 public class Pepsi extends ColdDrink{
		 @Override
		 public String name(){
			 return "百事可乐";
		 }
		 @Override
		 public float price(){
			 return 6.5f;
		 }
	 }
	 /*套餐*/
	 public class Meal{
		 List<Item> items = new ArrayList<Item>();
		 public void addItem(Item item){
			 if(null != item){
				 items.add(item);
			 }
		 }
		 public float getCost(){
			 float cost = 0f;
			 for(Item item : items){
				 cost += item.price();
			 }
			 return cost;
		 }
		 public void showItems(){
			 for(Item item : items){
				 System.out.println("名称: " + item.name() + ", 包装: " + item.packing().pack() + ", 单价: " + item.price());
			 }
		 }
	 }
	 /*套餐创建器，根据已有的食物搭配出不同的套餐*/
	 public class MealBuilder{
		 public Meal getMeal1(){   //套餐1：鸡肉汉堡＋可口可乐
			 Meal meal = new Meal();
			 meal.addItem(new ChickenBurger());
			 meal.addItem(new Coke());
			 return meal;
		 }
		 public Meal getMeal2(){   //套餐2:蔬菜汉堡＋百事可乐
			 Meal meal = new Meal();
			 meal.addItem(new VegBurger());
			 meal.addItem(new Pepsi());
			 return meal;
		 }
		 public Meal getMeal3(){   //套餐3:鸡肉汉堡＋百事可乐
			 Meal meal = new Meal();
			 meal.addItem(new ChickenBurger());
			 meal.addItem(new Pepsi());
			 return meal;
		 }
	 }
	 public static void main(String[] args) {
		 BuilderMode mode = new BuilderMode();
		 MealBuilder builder = mode.new MealBuilder();
		 
		 Meal meal1 = builder.getMeal1();
		 System.out.println("套餐1:");
		 meal1.showItems();
		 System.out.println("费用：" + meal1.getCost());
		 
		 Meal meal2 = builder.getMeal1();
		 System.out.println("套餐2:");
		 meal2.showItems();
		 System.out.println("费用：" + meal2.getCost());
		 
		 Meal meal3 = builder.getMeal1();
		 System.out.println("套餐3:");
		 meal3.showItems();
		 System.out.println("费用：" + meal3.getCost());
	 }

}
