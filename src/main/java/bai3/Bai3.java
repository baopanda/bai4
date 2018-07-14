package bai3;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/* Chinh sua tren client */
public class Bai3 {
	public static void main(String[] args) {
		LoadingCache<Integer,List<Integer>> caches = CacheBuilder.newBuilder()
				.maximumSize(100)
				.expireAfterAccess(10, TimeUnit.SECONDS)
				.expireAfterWrite(20, TimeUnit.SECONDS)
				.build(new CacheLoader<Integer, List<Integer>>() {

					@Override
					public List<Integer> load(Integer key) throws Exception {
						// TODO Auto-generated method stub
						return getNguyenTo(key);
					}

				});
		port(8080);
		get("/prime", (req, res) -> {
			int n = Integer.parseInt(req.queryParams("n"));
			return caches.get(n);
      
		});
		
	}
	
	public static boolean ktnguyento (int n){
	    int dem=0;
	    if(n==1)
	       dem++;
	    else{
	       for (int i=2; i<=n/2;i++){
	          if(n%i==0)
	          dem++;
	       }
	    }
	    if (dem==0)
	       return true;
	    else
	       return false;
	 }
	
	public static List<Integer> getNguyenTo(int n) {
		int i = 2;
		List<Integer> kq = new ArrayList<Integer>();
		while (i <= n){
			if (ktnguyento(i)){
				kq.add(i);
			}
			i++;
		}
		return kq;
	}
	
	
}
