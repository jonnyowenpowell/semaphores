package semaphores;

public class RunReduced {

    public static void main(String[] args) throws InterruptedException {
        // Initialise semaphores
        // These semaphores ensure W and X alternate in the output
        var wSemaphore = new BinarySemaphore(true);
        var xSemaphore = new BinarySemaphore(false);

        // Initialise CharacterPrinter Runnables
        // These W and X printers have a limit of 100 each.
        var wPrinter = new CharacterPrinter('W',
            new Semaphore[] { wSemaphore },
            new Semaphore[] { xSemaphore }, 100);
        var xPrinter = new CharacterPrinter('X',
            new Semaphore[] { xSemaphore },
            new Semaphore[] { wSemaphore }, 100);

        // Initialise and start threads for each CharacterPrinter
        var wThread = new Thread(wPrinter);
        var xThread = new Thread(xPrinter);
        wThread.start();
        xThread.start();

        // Ensure the program does not exit before all
        // CharacterPrinter threads have run to completion
        wThread.join();
        xThread.join();

        // Exit with code 0
        System.exit(0);
    }

}
