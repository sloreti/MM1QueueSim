
public class Event {
    
    public double timestamp;
    public Event next;
    public Type type;
    public int index;

     public enum Type {
         BIRTH, DEATH, LOG
     }
    
    public Event(double timestamp, Type t, int index){
        this.type = t;
        this.timestamp = timestamp;
        this.next = null;
        this.index = index;
    }
    
    public Event myFunction(MM1System system, double time, double Ts, double anExpRV) {
        switch (type) {
            case BIRTH:
                double Tq = 0;
                for(int i = 0; i < system.getQueueSize() + 1; i++){    // 1 for its own service time + the service time of all those in line
                    Tq += Controller.getExponential(1/Ts);
                }
                system.enqueue(this, time);
                double timeOfDeath = time + Tq;
                Event death = new Event(timeOfDeath, Event.Type.DEATH, index);
                return death;
                
            case DEATH:
                system.dequeue();
                return null;
                
            case LOG:
                system.tallyQueue();
                Event log = new Event(time + anExpRV, Event.Type.LOG, -1);
                return log;             
        }
        
        return null; 
    }

    public double getTimestamp(){
        return this.timestamp;
    }
    
    public Type getType(){
        return this.type;
    }

    public Event getNext(){
        return this.next;
    }
    
    public int getIndex(){
        return this.index;
    }

    public void setNext(Event e){
        this.next = e;
    }
    
    public String toString(){
        return "a " + type + " scheduled for " + this.timestamp;
    }

}