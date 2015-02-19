
import java.util.Math;

public class Controller {
    
    private static final END_WORLD_TIME = 100000;
    private static final LAMBDA = 1;

    private int currentTime;
    private Event head;
    private int scheduleLength;

    public void add(Event e){

        while (crunchifyCurrent.getNext() != null) {
            crunchifyCurrent = crunchifyCurrent.getNext();
        }
        // the last node's "next" reference set to our new node
        crunchifyCurrent.setNext(crunchifyTemp);
        listCount++;// increment the number of elements variable
    }

    public void initWorld(){
        self.currentTime = 0;

        while(currentTime < END_WORLD_TIME){

            // Flip a coin
            if (Math.random > .05){
                Event temp = new BirthEvent()
            } else {
                Event temp = new DeathEvent()
            }

            add(temp);

        }
    }

    public static void main(String[] args) {
        initWorld();
        
    }
    
}