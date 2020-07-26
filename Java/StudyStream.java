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
		// �迭 ��Ʈ��
		String[] arr = new String[]{"a", "b", "c"};
		Stream<String> stream = Arrays.stream(arr);
		Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // 1-2 ��� [b, c]
		// �÷��� ��Ʈ��
		List<String> list = Arrays.asList("a", "b", "c");
		Stream<String> CollectionStream = list.stream();
		Stream<String> parallelStream = list.parallelStream(); // ���� ó�� ��Ʈ��
		
		// builder()�� ����� ��Ʈ�� ����
		Stream<String> builderStream =
				Stream.<String>builder()
					.add("Eric").add("Elena").add("Java") // ���������� ���ϴ� ���� ���� �� �ִ�.
					.build(); // [Eric, Elena, Java]
		// generate()�� ����� ��Ʈ�� ����
		Stream<String> generatedStream = 
				Stream.generate(() -> "gen").limit(5); // [ gen, gen, gen, gen, gen ]
		// iterate()�� ����� ��Ʈ�� ����
		Stream<Integer> iteratedStream = 
				Stream.iterate(30, n -> n + 2).limit(5); // [30, 32, 34, 36, 38]
		// �⺻ Ÿ���� ��Ʈ��
		//������ ��Ʈ��
		IntStream intStream = IntStream.range(1, 5); // [1, 2, 3, 4]
		LongStream longStream = LongStream.rangeClosed(1, 5); // [1, 2, 3, 4, 5]
		// ���� Ŭ������ ����� ������ ���� ��Ʈ��
		Stream<Integer> boxedIntStream = IntStream.range(1, 5).boxed();
		// java8�� Random Ŭ���� Ȱ���Ͽ� ���� ��Ʈ�� ����
		DoubleStream doubleStream = new Random().doubles(3); // ���� 3�� ����
		//����char ��Ʈ��
		IntStream charsStream = "Stream".chars(); // [83, 116, 114, 101, 97, 109]
		// ����ǥ����(RegEX)�� �̿��� ���ڿ� ��Ʈ��
		Stream<String> stringStream = 
				Pattern.compile(", ").splitAsStream("Eric, Elena, Java"); // [Eric, Elena, Java]
		// NIO�� Files Ŭ������ lines �޼ҵ带 ����� ���� ��Ʈ�� ����
		Stream<String> lineStream = 
				Files.lines(Paths.get("file.txt"), Charset.forName("UTF-8"));
		// ���� ��Ʈ�� ����
		Stream<Product> parallelStream = productList.parallelStream();

		// ���� ���� Ȯ��
		boolean isParallel = parallelStream.isParallel();
		// �Ʒ� �ڵ�� �� �۾��� �����带 �̿��� ���� ó���ȴ�.
		boolean isMany = parallelStream
				.map(product -> product.getAmount() * 10)
				.anyMatch(amount -> amount > 200);
		// �迭�� ����� ���� ��Ʈ��
		Arrays.stream(arr).parallel();
		
		// �÷��ǰ� �迭�� �ƴ� ��쿣 parallel �޼ҵ带 Ȱ���Ͽ� ó���Ѵ�.
		IntStream intParallelStream = IntStream.range(1, 150).parallel();
		isParallel = intStream.isParallel();

		// 시퀀셜 모드로 돌리고 싶다면 sequential()를 사용한다.
		intParallelStream = intStream.sequential();
		isParallel = intStream.isParallel();
		
		// Stream.concat 메소드를 이용해 두 개의 스트림을 연결하여 새로운 스트림을 만들어낼 수도 있다.
		Stream<String> stream1 = Stream.of("Java", "Scala", "Groovy");
		Stream<String> stream2 = Stream.of("Python", "Go", "Swift");
		Stream<String> concat = Stream.concat(stream1, stream2);
		// [Java, Scala, Groovy, Python, Go, Swift]

		OptionalInt reduced = 
  		IntStream.range(1, 4) // [1, 2, 3]
  			.reduce((a, b) -> {
  			  return Integer.sum(a, b);
  			});
	}
	
	//��� �ִ� ��Ʈ��
	public Stream<String> streamOf(List<String> list) {
		return list == null || list.isEmpty() ? Stream.empty() : list.stream();
	}
}
