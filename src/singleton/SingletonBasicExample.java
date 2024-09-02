package singleton;

public class SingletonBasicExample {

	private static SingletonBasicExample singletonInstance;

	private SingletonBasicExample() {

	}

	public static SingletonBasicExample getInstance() {
		if (singletonInstance == null)
			singletonInstance = new SingletonBasicExample();
		return singletonInstance;
	}

}
