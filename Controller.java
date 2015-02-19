
import java.util.*;

public class Controller {
    
    private static final int LAMBDA = 1;

    private static int currentTime;
    private static int endTime;
    private static Event head;
    private static int scheduleLength;


    public Controller(int endTime){
        this.endTime = endTime;
        this.currentTime = 0;
        this.head = null;
        this.scheduleLength =0;

        initWorld();
    }

    public static void add(Event e){

        Event currentEvent = head; 
        while (currentEvent.getNext() != null) {
            currentEvent = currentEvent.getNext();
        }

        currentEvent.setNext(e);
        scheduleLength++;
    }

    public static void initWorld(){

        while(currentTime < endTime){

            int timestamp = getPoisson();
            currentTime += timestamp;

            // Flip a coin
            if (Math.random() > .5){
                BirthEvent temp = new BirthEvent(timestamp);
                add(temp);
            } else {
                DeathEvent temp = new DeathEvent(timestamp);
                add(temp);
            }  

        }

        currentTime = 0;
    }

    public static int getPoisson(){
        return 1;
    }


    
}