package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

import singleton.SingletonSynchronisedExample;

public class SingletonSynchronisedExampleTest {

	@Test
	public void testSingletonSynchronisedPerformance() throws InterruptedException {
		int numberOfThreads = 1000;
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

		long startTime = System.nanoTime();

		for (int i = 0; i < numberOfThreads; i++) {
			executorService.execute(() -> {
				SingletonSynchronisedExample instance = SingletonSynchronisedExample.getInstance();
				latch.countDown();
			});
		}

		latch.await(); // Wait for all threads to finish
		long endTime = System.nanoTime();
		executorService.shutdown(); // Shut down the executor service

		long duration = endTime - startTime;
		System.out.println("Time taken to create/get the singleton instance: " + duration + " nanoseconds");
	}
}
