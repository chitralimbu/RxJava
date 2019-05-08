/*OnNext, OnError, OnCompleted*/
All Credits to the code belong to: https://www.baeldung.com/rx-java
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import rx.Observable;

public class RXMethods {
	String result = "";
	private static String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
	private static String[] titles = {"title"};
	private static List<String> titleList = Arrays.asList(titles);
	private static Integer[] numbers = {0,1,2,3,4,5,6,7,8,9,10};
	
	@Test
	public void testMethods() {

		Observable<String> observable = Observable.from(letters);
		observable.subscribe(
				i -> result += i,  //OnNext
				Throwable::printStackTrace, //OnError
				() -> result += "_Completed" //OnCompleted
				);
		assertTrue(result.equals("abcdefghi_Completed"));
	}

	//Transform items emitted by Observable by applying a function to each item
	@Test
	public void testMap() {
		Observable.from(letters)
		.map(String::toUpperCase)
		.subscribe(letter -> result += letter);
		assertTrue(result.equals("ABCDEFGHI"));
	}

	//Flatmap -> used to flatten Observables whenever we end up with nested Observables. 
	public Observable<String> getTitle(){
		return Observable.from(titleList);
	}
	@Test
	public void testFlatMap() {
		Observable.just("book1", "book2")
			.flatMap(s -> getTitle())
			.subscribe(l -> result+=l);
		assertTrue(result.equals("titletitle"));
	}
	
	//Scan - Applies a function to each item emitted by an Observable sequentially and emits
	//each successive value. 
	@Test
	public void testScan() {
		String[] letter = {"a","b", "c"};
		Observable.from(letter)
			.scan(new StringBuilder(), StringBuilder::append)
			.subscribe(total -> result += total.toString());
		assertTrue(result.equals("aababc"));
	}
	
	//GroupBy - classify the events in the input Observable into output categories.  
	@Test
	public void testGroupBy() {
		String[] EVEN = {""};
        String[] ODD = {""};
		Observable.from(numbers)
			.groupBy(i -> 0 == (i % 2) ? "EVEN" : "ODD")
			.subscribe((group) -> group.subscribe((number) -> {
				if(group.getKey().toString().equals("EVEN")) {
					EVEN[0] += number;
				}else {
					ODD[0] += number;
				}
			}));
		assertTrue(EVEN[0].equals("0246810"));
		assertTrue(ODD[0].equals("13579"));
	}
	
	//Filter: emits only those items from an observable that pass a predicate test. 
	@Test
	public void testFilter() {
		Observable.from(numbers)
			.filter(i -> (i % 2 == 1))
			.subscribe(i -> result += i);
		
		assertTrue(result.equals("13579"));
	}
	
	//DefaultIfEmpty emits from the source Observable - or a default item if the source observable
	//is empty.
	@Test
	public void testDefaultIfEmpty() {
		Observable.empty()
		.defaultIfEmpty("Observable is empty")
		.subscribe(s -> result += s);
		assertTrue(result.equals("Observable is empty"));
	}
	
	/*emits the first letter of the alphabet 'a' because the array letters is not empty*/
	@Test
	public void testDefaultEmpty() {
		Observable.from(letters)
			.defaultIfEmpty("Observable is empty")
			.first()
			.subscribe(s -> result += s);
		assertTrue(result.equals("a"));
	}
	
	/*TakeWhile -> discards items emitted by an observable after a specified condition becomes false
	 * Includes - Contain, SkipWhile, SkipUntil, TakeUntil.
	 * */
	@Test
	public void testTakeWhile() {
		int[] sum = {0};
		Observable.from(numbers)
			.takeWhile(i -> i < 5)
			.subscribe(s -> sum[0] += s);
		assertTrue(sum[0] == 10);
	}
	
}
