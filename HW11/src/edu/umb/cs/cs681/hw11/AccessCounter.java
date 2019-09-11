package edu.umb.cs.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

	private AccessCounter() {};
	private static AccessCounter instance = null;
	private HashMap<Path, Integer> Map = new HashMap<Path, Integer>();
    private static ReentrantLock lock = new ReentrantLock();

	public static AccessCounter getInstance() {
		lock.lock();
		try {
			if (instance == null)
				instance = new AccessCounter();
		}
		finally {
			lock.unlock();
		}
		return instance;
	}
    
    public Integer getCount(Path path) {
    	lock.lock();
    	try {
    		if (Map.containsKey(path)) {
    			return Map.get(path);
    		}
    		else {
    			return 0;
    		}
    	}
    	finally {
    		lock.unlock();
    	}
    }

    public void increment(Path path) {
    	lock.lock();
    	try {
    		if (Map.containsKey(path)) {
    			Map.put(path, Map.get(path) + 1);
    		}
    		else {
    			Map.put(path, 1);
    		}
    	}
    	finally {
    		lock.unlock();
    	}
    }
}

