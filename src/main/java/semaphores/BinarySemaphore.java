package semaphores;

/**
* The BinarySemaphore class implements a binary semaphore, which is
* defined as a semaphore which restricts access to a single resource. 
*
* This class extends {@link semaphores.Semaphore}, which provides the
* logic to implement a counting semaphore, and hence the only work
* done in this class is to call the super constructor with a value of
* either 0 or 1, depending on if the semaphore should initially be
* available.
*/
public class BinarySemaphore extends Semaphore {

    public BinarySemaphore(boolean available) {
        super(available ? 1 : 0);
    }

    public synchronized void V() {
        value = 1;
        notify();
    }

}
