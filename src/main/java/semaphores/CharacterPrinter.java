package semaphores;

/**
* The CharacterPrinter class implements a {@link Runnable} class which prints
* a character to the console repeatedly, up to a configurable limit, using
* semaphores to synchronise with other instances.
* 
* Specifically, all semaphores passed in the entrySemaphores array will
* be acquired by an instance before each call to print a single character,
* and all semaphores passed in the exitSemaphores array will be released
* after each print.
* 
* To simulate the randomness of, for example, blocking IO over a network,
* this implementation artificially sleeps for a random number of
* milliseconds between 100 and 500 after acquiring permission from the
* semaphore to print.
*/
public class CharacterPrinter implements Runnable {

    private char c;
    private Semaphore[] entrySemaphores;
    private Semaphore[] exitSemaphores;
    private int limit;

    public CharacterPrinter(
        char c, 
        Semaphore[] entrySemaphores,
        Semaphore[] exitSemaphores,
        int limit
    ) {
        this.c = c;
        this.entrySemaphores = entrySemaphores;
        this.exitSemaphores = exitSemaphores;
        this.limit = limit;
    }

    public void run() {
        var printedCharacters = 0;

        // Print characters whilst below limit
        while (printedCharacters < limit) {
            try {
                // Acquire entry semaphore permits
                for (var s : entrySemaphores) {
                    s.P();
                }
                
                // Add jitter to output timings
                Thread.sleep(10 + Math.round(Math.random() * 40));
                
                // Print character to console
                System.out.print(c);

                // Increment printed character count
                printedCharacters++;
            } catch (InterruptedException e) {
                // This exception should never be raised in this
                // application, because we are not calling interrupt()
                System.out.println("Thread interrupted.");
            } finally {
                // Release exit semaphore permits
                for (var s : exitSemaphores) {
                    s.V();
                }
            }
        }
    }
    
}
