package bai3;

import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class StudentGuavaCache {
	private static LoadingCache<Integer, Student> cache;
    static {
		cache = CacheBuilder.newBuilder()
		       .maximumSize(100) //set size
		       .expireAfterWrite(10, TimeUnit.MINUTES) //set time expire
		       .build(
		           new CacheLoader<Integer, Student>() {
						@Override
						public Student load(Integer id) throws Exception {
							return getEmployeeById(id);
						}
		           }
		       );
    }
    public static LoadingCache<Integer, Student> getLoadingCache() {
		return cache;
    }
    // this method demo get data from database or file
	public static Student getEmployeeById(int id) {
		System.out.println("--Executing getStudent--");
		Student student = new Student(1, "Thach Le");
		return student;
	}
}
class Student {
	private int id;
	private String name;
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
