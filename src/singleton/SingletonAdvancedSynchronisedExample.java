package singleton;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SingletonAdvancedSynchronisedExample {

	private static SingletonAdvancedSynchronisedExample sigletonAdvancedSynchronisedInstance;
	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public static SingletonAdvancedSynchronisedExample getInstance() {
		if (sigletonAdvancedSynchronisedInstance == null) { // First check (no locking)
			lock.writeLock().lock(); // Lock only if instance is null
			try {
				if (sigletonAdvancedSynchronisedInstance == null) { // Second check (with locking)
					sigletonAdvancedSynchronisedInstance = new SingletonAdvancedSynchronisedExample();
				}
			} finally {
				lock.writeLock().unlock(); // Always release the lock
			}
		}
		return sigletonAdvancedSynchronisedInstance;
	}

}
