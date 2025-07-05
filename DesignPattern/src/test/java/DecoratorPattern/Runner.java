package DecoratorPattern;

public class Runner {
    public static void main(String[] args) {
        Coffee myCoffee = new SimpleCoffee();
        System.out.println(myCoffee.getDescription() + " $" + myCoffee.getCost());//Simple Coffee $5.0

        myCoffee = new MilkDecorator(myCoffee);
        System.out.println(myCoffee.getDescription() + " $" + myCoffee.getCost());//Simple Coffee , Milk $6.5

        myCoffee = new SugarDecorator(myCoffee);
        System.out.println(myCoffee.getDescription() + " $" + myCoffee.getCost());//Simple Coffee , Milk , Sugar $7.0

        myCoffee = new CreamDecorator(myCoffee);
        System.out.println(myCoffee.getDescription() + " $" + myCoffee.getCost());//Simple Coffee , Milk , Sugar , Cream $10.5
    }
}
