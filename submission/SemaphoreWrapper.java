
//---------------------------------------------------------------
//File Semaphore.java
//-----------------------------------------------------------------

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// An implementation of semaphore.
class SemaphoreWrapper {
	Semaphore semp;

    // Create a new semaphore. value should be its initial
    // value. It should be 1 in most cases. It returns a
    // handle to the semaphore.
    public SemaphoreWrapper(int value) {
    	semp = new Semaphore(value);
    }

    // Operation P on a semaphore.  Same operation as wait below. Use P
    // if you are familiar with the lectures, and wait if you are familiar with the course
    // book 
    void P(int sem) {
    	wait(sem);
    }
    
    // Operation wait on a semaphore.  Same operation as P  above. Use P
    // if you are familiar with the lectures, and wait if you are familiar with the course
    // book 
    // Wait for the semaphore. It will block until the semaphore
    // is available.
    void wait(int sem) {
    	try {
    		semp.acquire(sem);
    	} catch (Exception e) {
            System.out.print("Exception: ");
			System.out.println(">" + e.toString() + "<");
		}
    }

    // Operation V on a semaphore.  Same operation as signal below.
    // Use V if you are familiar with the lectures, and signal if you are familiar with the course
    // book 
    void V(int sem) {
		signal(sem);
	}
    
    // Operation siganal on a semaphore.  Same operation as V above.
    // Use V if you are familiar with the lectures, and signal if you are familiar with the course
    // book 
    void signal(int sem) {
    	try {
    		semp.release(sem);
    	} catch (Exception e) {
            System.out.print("Exception: ");
			System.out.println(">" + e.toString() + "<");
		}
    }

}
