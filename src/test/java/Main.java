import java.util.Date;

public class Main {
    static Philosopher[] thr;

    public static void main(String[] args) {
        Date date = new Date();
        Parser parser;
        if (args.length == 0)
            parser = new Parser();
        else
            parser = new Parser(args);
        Thread myThread[] = new Thread[parser.getNumPhilo()];
        for (int i=0; i < parser.getNumPhilo(); i++){
            myThread[i] = new Thread(parser.getPhilo(i));
            myThread[i].setDaemon(true);
            myThread[i].start();
        }
        while(1>0){
            long now = date.getTime();
            int eachEat=-1;
            for (int i=0; i < parser.getNumPhilo(); i++) {
                if (now - parser.getPhilo(i).getLastEat() >= parser.getTimeToDie()) {
                    return;
                }
                if (parser.getPhilo(i).getNumEat() == 0)
                    eachEat++;
            }
            if (eachEat == parser.getNumPhilo()-1)
                return;
        }
    }
}
