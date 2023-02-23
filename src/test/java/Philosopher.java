import javafx.scene.ParallelCamera;

import java.util.Date;

public class Philosopher implements Runnable{
    private Date date;
    private long lastEat;
    private boolean isEating;
    private int position;
    private int numEat;
    private Fork fork;
    private Parser pars;
    private Philosopher next;
    private Thread thread;

    public int getNumEat() {
        return numEat;
    }

    public void setLastEat(long lastEat) {
        this.lastEat = lastEat;
    }

    public long getLastEat() {
        return lastEat;
    }

    public Fork isFork() {
        return fork;
    }

    public void setFork(Fork fork) {
        this.fork = fork;
    }

    public Philosopher(int numEachMustEat, int pos, Parser prs, Philosopher phil/*, Thread thr*/) {
        date = new Date();
        numEat = numEachMustEat;
        isEating = false;
        position = pos;
        lastEat = System.currentTimeMillis();
        pars = prs;
        fork = new Fork((byte) 0);
        next = phil;
    }

    public Philosopher getNext() {
        return next;
    }

    public void setNext(Philosopher next) {
        this.next = next;
    }

    private void print(int position, String str){
        System.out.println(System.currentTimeMillis() + " philo №" + position + str);
    }

    public void run(){
        if (position%2 != 0)
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        while(1>0) {
            synchronized (fork){
//                if (fork.getIsTaken() == 0) {
                    print((position + 1), " has taken left a fork");
                    fork.setIsTaken((byte) 1);
//                }
                synchronized (next.isFork()) {
                    if (pars.getNumPhilo() == 1)
                        while(true) ;
//                if (next.isFork().getIsTaken() == 0) {
                    print((position + 1), " has taken right a fork");
                    next.isFork().setIsTaken((byte) 1);
//                }
                }
                print((position+1), " is eating");
                try {
                    Thread.sleep(pars.getTimeToEat());
                } catch (InterruptedException ex) {}

                if (numEat > 0)
                    numEat-=1;

                setLastEat(System.currentTimeMillis()); //fix

                checkLive();

                if (fork.getIsTaken() != 0) {
                    print((position + 1), " has drop forks");
                    next.isFork().setIsTaken((byte) 0);
                    if (next.isFork().getIsTaken() != 0)
                        fork.setIsTaken((byte) 0);
                }

            }
            print((position+1), " is sleeping");
            try {
                Thread.sleep(pars.getTimeToSleep());
            } catch (InterruptedException ex) { }
            print((position+1), " is thinking");
        }
    }

    private void checkLive() {
        if (lastEat < System.currentTimeMillis() - pars.getTimeToDie()) {
            print(position + 1, " dead");
            System.exit(0);
        }
    }
}
