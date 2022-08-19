package DesignPattern;

public class LeaderFlock extends Flock{
    @Override
    public void quack() {
        for (int i = 1 ; i <= quackers.size() ; i++) {
            if (i == 1) {
                quackers.get(i).quack();
                quackers.get(i).quack();
                quackers.get(i).quack();
            } else {
                quackers.get(i).quack();
            }
        }
    }
}
