import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StudyStream {
	public static void main(String[] args) throws IOException {
		// 배열 스트림
		String[] arr = new String[]{"a", "b", "c"};
		Stream<String> stream = Arrays.stream(arr);
		Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // 1-2 요소 [b, c]
		// 컬랙션 스트림
		List<String> list = Arrays.asList("a", "b", "c");
		Stream<String> CollectionStream = list.stream();
		Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림
		
		// builder()를 사용한 스트림 생성
		Stream<String> builderStream =
				Stream.<String>builder()
					.add("Eric").add("Elena").add("Java") // 직접적으로 원하는 값을 넣을 수 있다.
					.build(); // [Eric, Elena, Java]
		// generate()를 사용한 스트림 생성
		Stream<String> generatedStream = 
				Stream.generate(() -> "gen").limit(5); // [ gen, gen, gen, gen, gen ]
		// iterate()를 사용한 스트림 생성
		Stream<Integer> iteratedStream = 
				Stream.iterate(30, n -> n + 2).limit(5); // [30, 32, 34, 36, 38]
		// 기본 타입형 스트림
		//정수형 스트림
		IntStream intStream = IntStream.range(1, 5); // [1, 2, 3, 4]
		LongStream longStream = LongStream.rangeClosed(1, 5); // [1, 2, 3, 4, 5]
		// 웨퍼 클래스를 사용한 참조형 변수 스트림
		Stream<Integer> boxedIntStream = IntStream.range(1, 5).boxed();
		// java8의 Random 클래스 활용하여 난수 스트림 생성
		DoubleStream doubleStream = new Random().doubles(3); // 난수 3개 생성
		//문자char 스트림
		IntStream charsStream = "Stream".chars(); // [83, 116, 114, 101, 97, 109]
		// 정규표현식(RegEX)을 이용한 문자열 스트림
		Stream<String> stringStream = 
				Pattern.compile(", ").splitAsStream("Eric, Elena, Java"); // [Eric, Elena, Java]
		// NIO의 Files 클래스의 lines 메소드를 사용한 파일 스트림 생성
		Stream<String> lineStream = 
				Files.lines(Paths.get("file.txt"), Charset.forName("UTF-8"));
		// 병렬 스트림 생성
		Stream<Product> parallelStream = productList.parallelStream();

		// 병렬 여부 확인
		boolean isParallel = parallelStream.isParallel();
		// 아래 코드는 각 작업을 쓰레드를 이용해 병렬 처리된다.
		boolean isMany = parallelStream
				.map(product -> product.getAmount() * 10)
				.anyMatch(amount -> amount > 200);
		// 배열을 사용한 병렬 스트림
		Arrays.stream(arr).parallel();
		
		// 컬랙션과 배열이 아닌 경우엔 parallel 메소드를 활용하여 처리한다.
		IntStream intParallelStream = IntStream.range(1, 150).parallel();
		isParallel = intStream.isParallel();
		
		
	}
	
	//비어 있는 스트림
	public Stream<String> streamOf(List<String> list) {
		return list == null || list.isEmpty() ? Stream.empty() : list.stream();
	}
}
