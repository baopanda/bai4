package bai3;

import java.util.concurrent.ExecutionException;

import com.google.common.cache.LoadingCache;

public class GuavaTest {
	public static void main(String[] args) {
		GuavaTest guavaTest = new GuavaTest();
		try {
			// Access student first time with id 1, getStudentUsingGuava() will
			// be called.
			System.out.println(guavaTest.getStudentUsingGuava(1).getName());
			System.out.println("------------------------");
 
			// The second time we get student, data will cache
			System.out.println(guavaTest.getStudentUsingGuava(1).getName());
		} catch (ExecutionException e) {
		}
	}
 
	private Student getStudentUsingGuava(int id) throws ExecutionException {
		LoadingCache<Integer, Student> cache = StudentGuavaCache.getLoadingCache();
		System.out.println("Cache Size:" + cache.size());
		return cache.get(id);
	}
}
