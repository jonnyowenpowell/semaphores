package semaphores;

/**
* The BinarySemaphore class implements a binary semaphore, which is
* defined as a semaphore which restricts access to a single resource. 
*
* This class extends {@link semaphores.Semaphore}, which provides the
* logic to implement a counting semaphore, and hence the only work
* done in this class is to call the super constructor with a value of 1,
* thus implementing a binary semaphore.
*/
public class BinarySemaphore extends Semaphore {

    public BinarySemaphore(boolean available) {
        super(available ? 1 : 0);
    }

}
