import java.lang.reflect.Array;
import java.util.Scanner;

public class Parser {
    private int numPhilo;
    private long timeToDie;
    private long timeToEat;
    private long timeToSleep;
    private int numEachMustEat;
    private Philosopher[] philo;

    public int getNumPhilo() {
        return numPhilo;
    }

    public long getTimeToDie() {
        return timeToDie;
    }

    public long getTimeToEat() {
        return timeToEat;
    }

    public long getTimeToSleep() {
        return timeToSleep;
    }

    public int getNumEachMustEat() {
        return numEachMustEat;
    }

    public Philosopher getPhilo(int i) {
        return philo[i];
    }

    public Parser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write number of philisophers: ");
        numPhilo = scanner.nextInt();
        System.out.println("Write time to die: ");
        timeToDie = scanner.nextLong();
        System.out.println("Write time to eat: ");
        timeToEat = scanner.nextLong();
        System.out.println("Write time to sleep: ");
        timeToSleep = scanner.nextLong();
        System.out.println("Write number of times each philosopher must eat or 0: ");
        numEachMustEat = scanner.nextInt();
        goPhilosophers();
    }

    public Parser(String str[]) {
        this.numPhilo = Integer.parseInt(str[0]);
        this.timeToDie = Integer.parseInt(str[1]);
        this.timeToEat = Integer.parseInt(str[2]);
        this.timeToSleep = Integer.parseInt(str[3]);
        if (!str[4].isEmpty())
            this.numEachMustEat = Integer.parseInt(str[4]);
        goPhilosophers();
    }

    private void goPhilosophers(){
        if (numEachMustEat == 0)
            numEachMustEat = -1;
        philo = new Philosopher[numPhilo];
        for (int i=0; i < numPhilo; i++)
            philo[i] = new Philosopher(numEachMustEat, i, this, i > 0 ? philo[i-1] : null);
        philo[0].setNext(philo[numPhilo - 1]);
    }

    @Override
    public String toString() {
        return "Phil{" +
                "numPhilo=" + numPhilo +
                ", timeToDie=" + timeToDie +
                ", timeToEat=" + timeToEat +
                ", timeToSleep=" + timeToSleep +
                ", numEachMustEat=" + numEachMustEat +
                '}';
    }
}
