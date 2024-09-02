package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import singleton.SingletonAdvancedSynchronisedExample;

public class SingletonAdvancedSynchronisedExampleTest {

	@Test
	public void testSingletonAdvancedSynchronisedPerformance() throws InterruptedException {
		int numberOfThreads = 1000;
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

		long startTime = System.nanoTime();

		for (int i = 0; i < numberOfThreads; i++) {
			executorService.execute(() -> {
				SingletonAdvancedSynchronisedExample instance = SingletonAdvancedSynchronisedExample.getInstance();
				assertNotNull(instance);
				latch.countDown();
			});
		}

		latch.await(10, TimeUnit.SECONDS); // Wait for all threads to finish
		long endTime = System.nanoTime();
		executorService.shutdown(); // Shut down the executor service

		long duration = endTime - startTime;
		System.out.println("Time taken to create/get the singleton instance: " + duration + " nanoseconds");
	}

	@Test
	public void testSingletonAdvancedSynchronisedIsSameInstance() throws InterruptedException {
		int numberOfThreads = 100;
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

		SingletonAdvancedSynchronisedExample[] instances = new SingletonAdvancedSynchronisedExample[numberOfThreads];

		for (int i = 0; i < numberOfThreads; i++) {
			final int index = i;
			executorService.execute(() -> {
				instances[index] = SingletonAdvancedSynchronisedExample.getInstance();
				latch.countDown();
			});
		}

		latch.await(10, TimeUnit.SECONDS); // Wait for all threads to finish
		executorService.shutdown(); // Shut down the executor service

		// Verify that all threads got the same instance
		SingletonAdvancedSynchronisedExample firstInstance = instances[0];
		for (SingletonAdvancedSynchronisedExample instance : instances) {
			assertSame(firstInstance, instance);
		}
	}
}
