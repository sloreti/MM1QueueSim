
public class Event {
    
    private int timestamp;
    private Event next;

    public Event(int timestamp){
        self.timestamp = timestamp;
        self.next = Null;
    }

    public Event getTimestamp(){
        return self.timestamp;
    }

    public Event getNext(){
        return self.next;
    }
}