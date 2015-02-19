
public class Event {
    
    public int timestamp;
    public Event next;

    public Event(int timestamp){
        this.timestamp = timestamp;
        this.next = null;
    }

    public int getTimestamp(){
        return this.timestamp;
    }

    public Event getNext(){
        return this.next;
    }

    public void setNext(Event e){
        this.next = e;
    }

}