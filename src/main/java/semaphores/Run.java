package semaphores;

public class Run {

    public static void main(String[] args) throws InterruptedException {
        // Initialise semaphores
        // These semaphores ensure W and X alternate in the output
        var wSemaphore = new BinarySemaphore(true);
        var xSemaphore = new BinarySemaphore(false);
        // These semaphores ensure y and z alternate in the output
        var ySemaphore = new BinarySemaphore(true);
        var zSemaphore = new BinarySemaphore(false);
        // This semaphore ensures the count of yz never exceeds
        // the count of W
        var yzSemaphore = new Semaphore(0);

        // Initialise CharacterPrinter Runnables
        // These W and X printers have a limit of 100 each.
        // The y and z printers have a limit of 50 each.
        // This ensures the program completes without a deadlock,
        // as the count of y and z must never exceed W, then the
        // limit of W must be twice that of y and z.
        // This, combined with pairs of (W, X) and (y, z) printers
        // having the same limits is sufficient to prevent deadlock.

        var wPrinter = new CharacterPrinter('W',
            new Semaphore[] { wSemaphore },
            new Semaphore[] { xSemaphore, yzSemaphore }, 100);
        var xPrinter = new CharacterPrinter('X',
            new Semaphore[] { xSemaphore },
            new Semaphore[] { wSemaphore }, 100);
        var yPrinter = new CharacterPrinter('y',
            new Semaphore[] { ySemaphore, yzSemaphore },
            new Semaphore[] { zSemaphore }, 50);
        var zPrinter = new CharacterPrinter('z',
            new Semaphore[] { zSemaphore, yzSemaphore },
            new Semaphore[] { ySemaphore }, 50);

        // Initialise and start threads for each CharacterPrinter
        var wThread = new Thread(wPrinter);
        var xThread = new Thread(xPrinter);
        var yThread = new Thread(yPrinter);
        var zThread = new Thread(zPrinter);
        wThread.start();
        xThread.start();
        yThread.start();
        zThread.start();

        // Ensure the program does not exit before all
        // CharacterPrinter threads have run to completion
        wThread.join();
        xThread.join();
        yThread.join();
        zThread.join();

        // Exit with code 0
        System.exit(0);
    }

}
