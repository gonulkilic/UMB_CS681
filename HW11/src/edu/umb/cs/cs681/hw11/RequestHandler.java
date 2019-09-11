package edu.umb.cs.cs681.hw11;

import java.io.File;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {

	private ReentrantLock lock = new ReentrantLock();
	private boolean Interrupted = false;
	private File dummyFolder = new File("src/test/dummy_files");
	private File[] dummyFiles = dummyFolder.listFiles();
	private Random rndom = new Random();


	public void setInterruption() {
		lock.lock();
		System.out.println("Locked Interruption by thread: " + Thread.currentThread().getId());
		try {
			Interrupted = true;
		}
		finally {
			lock.unlock();
			System.out.println("Unlocked Interruption by thread: " + Thread.currentThread().getId());
		}
	}



	@Override
	public void run() {
		while(true) {
			lock.lock();
			System.out.println("Locked Thread: " + Thread.currentThread().getId());
			try {
				if (Interrupted) 
				{
					System.out.println("Flag flipped , Thread: " + Thread.currentThread().getId() + " Exiting");
					break;
				}

		File files = dummyFiles[rndom.nextInt(dummyFiles.length)];
		AccessCounter.getInstance().increment(files.toPath());
		System.out.println();
		System.out.println("Access Count : " + AccessCounter.getInstance().getCount(files.toPath())+ "||" + "Requested : " + files.toPath());
		System.out.println();
		Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("Thread Interrupted. " + Thread.currentThread().getId());
			}
			finally {
				lock.unlock();
				System.out.println("Unlocked thread: " + Thread.currentThread().getId());
			}
		}
	}

}
