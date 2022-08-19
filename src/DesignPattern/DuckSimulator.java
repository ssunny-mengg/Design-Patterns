package DesignPattern;

public class DuckSimulator {
    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        simulator.simulateAbstract();
    }

    void simulate() {
        Quackable mallardDuck = new QuackCounter(new MallardDuck());
        Quackable redheadDuck = new QuackCounter(new RedheadDuck());
        Quackable duckCall = new QuackCounter(new DuckCall());
        Quackable rubberDuck = new QuackCounter(new RubberDuck());
        Quackable gooseDuck = new GooseAdapter(new Goose());
        Quackable pigeon = new PigeonAdapter(new Pigeon());

        System.out.println("\nDuck Simulator");

        simulate(mallardDuck);
        simulate(redheadDuck);
        simulate(duckCall);
        simulate(rubberDuck);
        simulate(gooseDuck);
        simulate(pigeon);

//        Quackable decoratorDuck1 = new QuackCounter(new QuackEcho(new MallardDuck()));
        Quackable decoratorDuck2 = new QuackEcho(new QuackCounter(new MallardDuck()));

//        simulate(decoratorDuck1);
        simulate(decoratorDuck2);

        System.out.println("The duck quacked " + QuackCounter.getQuacks() + " times");
    }

    void simulateAbstract() {
        AbstractDuckFactory duckFactory = new DuckFactory();
        AbstractDuckFactory duckCountingFactory = new CountingDuckFactory();
        AbstractDuckFactory duckCountingAndEchoFactory = new CountingAndEchoDuckFactory();

        Quackable mallardDuck = duckFactory.createMallardDuck();
        Quackable redheadDuck = duckCountingFactory.createRedheadDuck();
        Quackable duckCall = duckCountingAndEchoFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable gooseDuck = new GooseAdapter(new Goose());

        System.out.println("\nDuck Simulator: With Abstract Factory");

        simulate(mallardDuck);
        simulate(redheadDuck);
        simulate(duckCall);
        simulate(rubberDuck);
        simulate(gooseDuck);

        System.out.println("The duck quacked " + QuackCounter.getQuacks() + " times");
    }

    void simulateComposite(AbstractDuckFactory duckFactory) {
        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable gooseDuck = new GooseAdapter(new Goose());

        System.out.println("\nDuck Simulator: With Composite - Flocks");

        Flock flockOfDucks = new Flock();
        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckCall);
        flockOfDucks.add(rubberDuck);
        flockOfDucks.add(gooseDuck);

        Flock flockOfMallards = new Flock();
        Quackable mallardOne = duckFactory.createMallardDuck();
        Quackable mallardTwo = duckFactory.createMallardDuck();
        Quackable mallardThree = duckFactory.createMallardDuck();
        Quackable mallardFour = duckFactory.createMallardDuck();
        flockOfMallards.add(mallardOne);
        flockOfMallards.add(mallardTwo);
        flockOfMallards.add(mallardThree);
        flockOfMallards.add(mallardFour);
        flockOfDucks.add(flockOfMallards);

        System.out.println("\nDuck Simulator: Whole Flock Simulation");

        simulate(flockOfDucks);

        System.out.println("\nDuck Simulator: Mallard Flock Simulation");

        simulate(flockOfMallards);

        System.out.println("\nThe ducks quacked " + QuackCounter.getQuacks() + " times");
    }

    void simulate(Quackable duck) {
        duck.quack();
    }
}
