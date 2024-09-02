package singleton;

public class SingletonSynchronisedExample {

	private static SingletonSynchronisedExample singletonSynchornisedInstance;

	private SingletonSynchronisedExample() {

	}

	public static synchronized SingletonSynchronisedExample getInstance() {
		if (singletonSynchornisedInstance == null)
			singletonSynchornisedInstance = new SingletonSynchronisedExample();
		return singletonSynchornisedInstance;
	}
}
