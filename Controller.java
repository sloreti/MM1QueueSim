
import java.util.*;

public class Controller {
    
    public static final int END_WORLD_TIME = 100;
    public static final double LAMBDA = 50.0;
    public static final double Ts = 0.015;

    public static double currentTime;
    public static Event head;
    public static int scheduleLength;
    
/////////////////////////// CALENDAR METHODS /////////////////////////////////
    
    public static void add(Event e){

        Event currentEvent = head; 
        while (currentEvent.getNext() != null && e.getTimestamp() > currentEvent.getNext().getTimestamp()) {
            currentEvent = currentEvent.getNext();
        }

        if(currentEvent.getNext() == null){ // reached end of LL
            currentEvent.setNext(e);
        } else {   // New event has to be placed somewhere in middle of LL 
            Event temp = currentEvent.getNext();
            currentEvent.setNext(e);
            e.setNext(temp);
        }
        scheduleLength++;
    }
    
    public static void printCalendar(Event e){
        while (e.getNext() != null) {
            System.out.println(e);
            e = e.getNext();
        }
    }

    public static void initWorld(){   
        head = new Event(getExponential(LAMBDA), Event.Type.BIRTH, 0);
        head.next = new Event(getExponential(LAMBDA), Event.Type.LOG, 0);
    }
    
    public static double getExponential(double lambda){
        double r = Math.random();
        return -Math.log(1 - r) / lambda;
    }


    public static void main(String[] args) {
        currentTime= 0;

        initWorld();
        MM1System ourSystem = new MM1System(Ts);
        printCalendar(head);

        while(currentTime < END_WORLD_TIME * 2 && head != null){

            Event deathOrLog  = head.myFunction(ourSystem, currentTime, Ts, getExponential(LAMBDA));
            
            // head was a BIRTH, head.myFunction() adds a corresponding DEATH and a new BIRTH to the calendar
            if(deathOrLog != null && deathOrLog.getType() == Event.Type.DEATH){
                Event birth = new Event(currentTime + getExponential(LAMBDA), Event.Type.BIRTH, head.getIndex() + 1);
                add(birth);
                add(deathOrLog);
            // head was a LOG, head.myFunction() adds a new LOG to the calendar
            } else if (deathOrLog != null && deathOrLog.getType() == Event.Type.LOG){
                add(deathOrLog);
            }
            
            head = head.getNext();
            
            // new head is a BIRTH, update global time
            if(head.getType() == Event.Type.BIRTH){
                currentTime = head.getTimestamp();
            } 
        }
        
        // report averages
        double[] allStats = ourSystem.getAllStats();
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println();
        System.out.println("Avg Q: " + allStats[0] + " Avg W: " + allStats[1] + " Avg Tq: " + allStats[2] + " Avg Tw: " + allStats[3]);
        System.out.println();
        System.out.println("///////////////////////////////////////////////////////");
  
    }
    
}