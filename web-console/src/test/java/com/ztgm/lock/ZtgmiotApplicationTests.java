package com.ztgm.lock;

import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;*/

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ZtgmiotApplicationTests {

	// @Test
	public void contextLoads() {
	}

	public static void main(String[] args) {
		reactorTest();
	}

	
	public static <T>  T test( T data)
	{
		return data;
	}
	
	
	public static void reactorTest() {

	/*	Flux<String> m = Flux.just(new String[] { "1", "2" });
		m.subscribe(System.out::print);

		 Flux.range(1, 10).reduce((x, y) -> x + y).log("my-test"). subscribe(System.out::println);
		 Flux.range(1, 10).reduceWith(() -> 10, (x, y) -> x + y).subscribe(System.out::println);
		//Flux.empty().subscribe(System.out::println);
		
		//Flux.interval(Duration.ofMillis(400)).subscribe(System.out::println);

		 
		 List<String > strs=new ArrayList<>();
		 strs.add("1");
		 strs.add("2");
		 strs.add("3");
		 
		 strs.stream();
		 
		 
		CountDownLatch latch = new CountDownLatch(1);
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public static void mailTest() {
		// AuthorizationCodeParameters
	}
}
