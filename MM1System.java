import java.util.*;

public class MM1System extends SimulatedSystem {

    private Queue<Event> queue;
    private ArrayList<Double> Tq;
    private ArrayList<Integer> Q;
    private double Ts;
    
    public MM1System(double Ts) {
        this.queue = new LinkedList<Event>();
        this.Tq = new ArrayList<Double>();
        this.Q = new ArrayList<Integer>();
        this.Ts = Ts;
    }
    
    
    public void enqueue(Event event, double arrivalTime) {
        this.queue.add(event);
        this.Tq.add(event.getIndex(), arrivalTime);
    }
    
    public void dequeue() {
            Event deadEvent = this.queue.remove();
            this.Tq.set(deadEvent.getIndex(), deadEvent.getTimestamp() - this.Tq.get(deadEvent.getIndex()));
    }
    
    public Queue getQueue() {
        return this.queue;
    }

    public ArrayList getTqs() {
        return this.Tq;
    }
    
    public int getQueueSize() {
        return this.queue.size();
    }
    
    public void tallyQueue() {
        this.Q.add(this.queue.size());     
    }
    
    public double[] getAllStats() {
        double[] allStats = new double[4];
        allStats[0] = avgQ();
        allStats[1] = avgW();
        allStats[2] = avgTq();
        allStats[3] = avgTw();
        return allStats;
    }
    
    //calculate average Q
    public double avgQ() {
        double sumQ = 0;
        double size = this.Q.size();
        for (int i = (int) size/2; i < size; i++){
            sumQ += Q.get(i);
        }
        
        //if Q is not empty, then return average of Qs
        if (size > 0) {
            return (2 *sumQ)/size;
        }
        return 0.0;
    }
    
    //calculate average W
    public double avgW() {
        double sumW = 0;
        double size = this.Q.size();
        for (int i = (int) size/2; i < size; i++){
            sumW += Q.get(i) - 1;
        }
        
        //if Q is not empty, then return average of Ws
        if (size > 0) {
            return (2 * sumW)/size;
        }
        return 0.0;
    }
    
    //calculate average Tq in queue
    public double avgTq() {
        double sumTq = 0;
        double size = this.Tq.size();
        for (int i = (int) size/2; i < size; i++){
            sumTq += Tq.get(i);
        }
        
        //if Tq is not empty, then return average of Tqs
        if (size > 0) {
            return (2 * sumTq)/size;
        }
        return 0.0;
    }
    
    //calculate average Tw in queue
    public double avgTw() {
        double sumTw = 0;
        double size = this.Tq.size();
        for (int i = (int) size/2; i < size; i++){
            sumTw += Tq.get(i) - Ts;
        }
        
        //if Tq is not empty, then return average of Tws
        if (size > 0) {
            return (2 *sumTw)/size;
        }
        return 0.0;
    }
}