// Similar to the Circular buffer example, but with objects

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
public class SharedFiFoQueue {
 
    private Object[] elems = null;
    private int PosW = 0;
    private int PosR = 0;
     
    private Lock lock = new ReentrantLock();
    private Condition isEmpty = lock.newCondition();
    private Condition isFull = lock.newCondition();
     
    public SharedFiFoQueue(int capacity) {
        this.elems = new Object[capacity];
    }
     
    public void add(Object elem) throws InterruptedException {
        lock.lock();
        while( ((PosW + 1) % elems.length) == PosR)
            isFull.await();
     
        elems[PosW] = elem;
         
        //We need the modulo, in order to avoid going out of bounds.
        PosW = (PosW + 1) % elems.length;
                  
        //Notify the consumer that there is data available.
        isEmpty.signal();
         
        lock.unlock();
    }
 
    public Object remove() throws InterruptedException {
        Object elem = null;
         
        lock.lock();
        while( PosR == PosW )
            isEmpty.await();
     
        elem = elems[PosR];
 
        //We need the modulo, in order to avoid going out of bounds.
        PosR = (PosR + 1) % elems.length;
         
         
        //Notify the producer that there is space available.
        isFull.signal();
         
        lock.unlock();
         
        return elem;
    }
}