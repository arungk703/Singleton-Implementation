package test;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import singleton.SingletonBasicExample;

public class SingletonBasicExampleTest {

	@Test
	public void testSingletonInMultithreadedEnvironment() throws InterruptedException {
		int numberOfThreads = 100;
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		AtomicReference<SingletonBasicExample> instanceReference = new AtomicReference<>();

		for (int i = 0; i < numberOfThreads; i++) {
			executorService.execute(() -> {
				SingletonBasicExample instance = SingletonBasicExample.getInstance();
				instanceReference.compareAndSet(null, instance);
				assertSame(instanceReference.get(), instance);
				latch.countDown();
			});
		}

		latch.await(); // Wait for all threads to finish
		executorService.shutdown(); // Shut down the executor service
	}
}
