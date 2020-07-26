# JAVA Stream

Created: Jul 16, 2020 9:22 PM
Tags: Java

[Java 스트림 Stream (1) 총정리](https://futurecreator.github.io/2018/08/26/java-8-streams/)

해당 문서는 Java 8의 스트림(Stream)을 살펴본다.

이번 문서에서 다루는 내용은 다음과 같다.

- 생성하기
    - 배열 / 컬렉션 / 빈 스트림
    - *Stream.builder()* / *Stream.generate()* / *Stream.iterate()*
    - 기본 타입형 / *String* / 파일 스트림
    - 병렬 스트림 / 스트림 연결하기
- 가공하기
    - Filtering
    - Mapping
    - Sorting
    - Iterating
- 결과 만들기
    - Calculating
    - Reduction
    - Collecting
    - Matching
    - Iterating

## **스트림 Streams**

자바 8에서 추가한 스트림(*Streams*)은 람다를 활용할 수 있는 기술 중 하나입니다. 자바 8 이전에는 배열 또는 컬렉션 인스턴스를 다루는 방법은 `for` 또는 `foreach` 문을 돌면서 요소 하나씩을 꺼내서 다루는 방법이었습니다. 간단한 경우라면 상관없지만 로직이 복잡해질수록 코드의 양이 많아져 여러 로직이 섞이게 되고, 메소드를 나눌 경우 루프를 여러 번 도는 경우가 발생합니다.

스트림은 '데이터의 흐름’입니다. 배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를 필터링하고 가공된 결과를 얻을 수 있습니다. 또한 람다를 이용해서 코드의 양을 줄이고 간결하게 표현할 수 있습니다. 즉, 배열과 컬렉션을 함수형으로 처리할 수 있습니다.

또 하나의 장점은 간단하게 병렬처리(multi-threading)가 가능하다는 점입니다. 하나의 작업을 둘 이상의 작업으로 잘게 나눠서 동시에 진행하는 것을 병렬 처리(parallel processing)라고 합니다. 즉 쓰레드를 이용해 많은 요소들을 빠르게 처리할 수 있습니다.

스트림에 대한 내용은 크게 세 가지로 나눌 수 있다.

1. 생성하기 : 스트림 인스턴스 생성.
2. 가공하기 : 필터링(*filtering*) 및 맵핑(*mapping*) 등 원하는 결과를 만들어가는 중간 작업(*intermediate operations*).
3. 결과 만들기 : 최종적으로 결과를 만들어내는 작업(*terminal operations*).

```
전체 -> 맵핑 -> 필터링 1 -> 필터링 2 -> 결과 만들기 -> 결과물
```

## 생성하기

보통 배열과 컬렉션을 이용해서 스트림을 만들지만 이 외에도 다양한 방법으로 스트림을 만들 수 있다.

**배열 스트림**

스트림을 이용하기 위해서는 먼저 생성을 해야 한다. 스트림은 배열 또는 컬렉션 인스턴스를 이용해서 생성할 수 있다. 배열은 다음과 같이 `Arrays.stream` 메소드를 사용한다.

```java
String[] arr = new String[]{"a", "b", "c"};
Stream<String> stream = Arrays.stream(arr);
Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // *1-2 요소 [b, c]*
```

**컬렉션 스트림**

컬렉션 타입(Collection, List, Set, Queue)의 경우 인터페이스에 추가된 디폴트 메소드 `stream` 을 이용하여 스트림을 만들 수 있다.

```java
public interface Collection<E> extends iterable<E> {
	default Stream<E> stream() {
		return StreamSupport.stream(spliteratore(), false);
	}
	// . . .
}
```

그러면 다음과 같이 생성할 수 있다.

```java
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();
Stream<String> parallelStream = list.parallelStream(); // *병렬 처리 스트림*
```

**비어 있는 스트림**

비어 있는 스트림(empty stream)도 생성할 수 있습니다. 비어 있는 스트림은 요소가 없을 때 `null` 대신 사용할 수 있다.

```java
public Stream<String> streamOf(List<String> list) {
	return list == null || list.isEmpty() ? Stream.empty() : list.stream();
}
```

**Stream.builder()**

빌더(Builder)를 사용하면 스트림에 직접적으로 원하는 값을 넣을 수 있다.

```java
Stream<String> builderStream =
	Stream.<String>builder()
		.add("Eric").add("Elena").add("Java")
		.build(); // *[Eric, Elena, Java]*
```

**Stream.generate()**

`generate` 메소드를 이용하면 `Supplier<T>` 에 해당하는 람다로 값을 넣을 수 있습니다. `Supplier<T>` 는 인자는 없고 리턴값만 있는 함수형 인터페이스(Functional Interface)다. 람다식으로 익명 객체를 생성해 참조시킬 수 있다.

```java
public static<T> Stream<T> generate(Supplier<T> s) { . . .}
```

이때 생성되는 스트림은 크기가 정해져 있지 않고 무한(Infinite)하기 때문에 특정 사이즈로 최대 크기를 제한해야 한다.

```java
Stream<String> generatedStream = 
	Stream.generate(() -> "gen").limit(5); // *[ gen, gen, gen, gen, gen ]*
```

5개의 "gen"을 가진 스트림이 생성된다.

**Stream.iterate()**

`iterate` 메소드를 이용하면 초기값과 해당 값을 다루는 람다식을 이용하여 스트림에 들어갈 요소를 만든다. 아래의 예제에서 30이 초기값이고 값이 2씩 증가하는 값들이 들어간다. 즉 요소가 다음 요소의 인풋으로 들어가게 된다. 이 방식 또한 스트림의 사이즈가 무한하기 때문에 특정 사이즈로 최대 크기를 제한해야 한다.

```java
Stream<Integer> iteratedStream = 
	Stream.iterate(30, n -> n + 2).limit(5); // *[30, 32, 34, 36, 38]*
```

**기본 타입형 스트림**

제네릭을 사용하게 되면 리스트나 배열을 이용해서 기본 타입(int, long, double) 스트림을 생성할 수 있다. 하지만 제네릭을 사용하지 않고 직접적으로 해당 타입의 스트림을 생성할 수도 있다. `range` 와 `rangeClosed` 이 두 방식으로 생성이 가능한데, 차이점은 범위 지정 방식이 다르다. 두 번째 인자인 종료지점 '이하'인지 '미만'인지의 차이다.

```java
IntStream intStream = IntStream.range(1, 5); // *[1, 2, 3, 4]*
LongStream longStream = LongStream.rangeclosed(1, 5); // *[1, 2, 3, 4, 5]*
```

제네릭을 사용하지 않기 때문에 불필요한 오토박싱(auto-boxing)이 일어나지 않는다. 필요한 경우 `boxed` 메소드를 이용해 박싱(boxing)할 수도 있다.

```java
Stream<Integer> boxedIntStream = IntStream.range(1, 5).boxed();
```

Java 8의 `Random` 클래스는 난수를 가지고 세 가지 타입의 스트림(IntStream, LongStream, DoubleStream)을 만들어낼 수 있다. 쉽게 난수 스트림을 생성하여 여러가지 후속 작업을 취할 수 있어 유용하다.

```java
DoubleStream doubleStream = new Random().doubles(3); // *난수 3개 생성*
```

**문자열 스트링**

스트림을 이용하여 스트림을 생성할 수도 있다. 다음은 스트링의 각 문자(char)를 `IntStream` 으로 변환하는 예제이다. `char` 는 문자지만 본질적으로 숫자이기 때문에 가능하다.

```java
IntStream charsStream = "Stream".chars(); // *[83, 116, 114, 101, 97, 109]*
```

다음은 정규표현식(RegEX)을 이용하여 문자열을 자르고, 각 요소들로 스트림을 만든 예제이다.

```java
Stream<String> stringStream = 
	Pattern.compile(", ").splitAsStream("Eric, Elena, Java"); // *[Eric, Elena, Java]*
```

**파일 스트림**

자바 NIO의 `Files` 클래스의 `lines` 메소드는 해당 파일의 각 라인을 스트링 타입의 스트림으로 만들어준다.

```java
Stream<String> lineStream = 
	Files.lines(Paths.get("file.txt"), Charset.forName("UTF-8"));
```

**병렬 스트림 Parallel Stream**

스트림 생성 시 사용하는 `stream` 대신 `parallelStream` 메소드를 사용하여 병렬 스트림을 쉽게 생성할 수 있다. 내부적으로 쓰레드를 처리하기 위해 자바 7부터 도입된 Fork/Join Framework를 사용한다.

```java
// *병렬 스트림 생성*
Stream<Product> parallelStream = productList.parallelStream();

// *병렬 여부 확인
boolean isParallel = parallelStream.isParallel();*
```

따라서 다음 코드는 각 작업을 쓰레드를 이용해 병렬 처리된다.

```java
boolean isMany = parallelStream
	.map(product -> product.getAmount() * 10)
	.anyMatch(amount -> amount > 200);
```

다음은 배열을 이용하여 병렬 스트림을 생성하는 예제이다.

```java
Arrays.stream(arr).parallel();
```

컬렉션과 배열이 아닌 경우는 다음과 같이 `parallel` 메소드를 이용하여 처리한다.

```java
IntStream intStream = IntStream.range(1, 150).parallel();
boolean isParallel = intStream.isParallel();
```

다시 시퀀셜(sequential) 모드로 돌리고 싶다면 다음처럼 `sequential` 메소드를 사용합니다. 뒤에서 한 번 더 다루게 되지만 반드시 병렬 스트림이 좋은 것은 아니다.

```java
IntStream intStream = intStream.sequential();
boolean isParallel = intStream.isParallel();
```

**스트림 연결하기**

`Stream.concat` 메소드를 이용해 두 개의 스트림을 연결하여 새로운 스트림을 만들어낼 수도 있다.

```java
Stream<String> stream1 = Stream.of("Java", "Scala", "Groovy");
Stream<String> stream2 = Stream.of("Python", "Go", "Swift");
Stream<String> concat = Stream.concat(stream1, stream2);
// *[Java, Scala, Groovy, Python, Go, Swift]*
```

## 가공하기

전체 요소 중에서 다음과 같은 API를 이용해 내가 원하는 것만 뽑아낼 수 있다. 이러한 가공 단계를 중간 작업(intermediate operation)이라 한다. 이 작업은 스트림을 리턴하기 때문에 여러 작업을 이어 붙여(chaining) 작성할 수 있다.

```java
List<String> names = Arrays.asList("Eric", "Elana", "Java");
```

위의 예제에 적힌 List를 대상으로 예제를 살펴보자.

**Filtering**

필터(filter)는 스트림 내 요소들을 하나씩 평가해 걸러내는 작업이다. 인자로 받는 Predicate는 Boolean을 리턴하는 함수형 인터페이스로 평가식이 들어간다.

```java
Stream<T> filter(Predicate<? supter T> predicate);
```

예를 들어

```java
Stream<String> stream =
	names.stream()
	.filter(name -> name.contains("a")); // *[Elana, Java]*
```

스트림에 각 요소에 대해서 평가식을 실행한다. 람다식으로 생성한 익명 객체를 통해 "a"란 문자를 포함한 인스턴스들을 가진 Stream 가 리턴된다.

**Mapping**

맵(map)은 스트림 내 각 요소들을 특정 값으로 변환해준다. 이 때 값을 변환하기 위해 람다식을 인자로 받는다.

```java
<R> Stream<R> map(Fucntion<? super T, ? extends R> mapper);
```

스트림에 들어가 있는 요소들이 input 되어 특정 로직을 거친 후 output이 되어 새로운 스트림에 담기게 된다. 이 작업을 매핑(mapping)이라 한다.

다음 예제는 스트림 내 String의 `toUpperCase` 메소드를 실행하여 대문자로 변환한 값들이 담긴 스트림을 리턴하는 예제이다.

```java
Stream<String> stream =
	names.stream()
	.map(String::toUpperCase); // *[ERIC, ELENA, JAVA]*
```

다음과 같이 인스턴스 내에 들어가있는 Product 개체의 수량을 꺼내올 수도 있다. 각 Product 인스턴스를 Product의 Amount로 매핑하는 것이다.

```java
Stream<Integer> stream =
	productList.stream()
	.map(Product::getAmount); // *[23, 14, 13, 23, 13]*
```

`map` 외에 조금 더 복잡한 `flatMap` 메소드도 있다.

```java
<R> Stream<R> flatMap(Function<? super T, ? exteds Stream<? extends R>> mapper);
```

인자로 `mapper` 를 받는데, 리턴 타입이 Stream이다. 즉 새로운 스트림을 생성해서 리턴하는 람다식을 인자로 넘겨야한다. `flatMap` 은 중첩 구조를 한 단계 제거하고 단일 컬렉션으로 만들어주는 역할을 한다. 이러한 작업을 플래트닝(flattening)이라 한다.

아래와 같은 중첩된 리스트를 예제로 들자면

```java
List<List<String>> list = 
	Arrays.asList(Arrays.asList("a"),
								Arrays.asList("b")); // *[[a], [b]]*
```

이를 `flatMap` 을 사용해서 중첩 구조를 제거한 후 작업할 수 있다.

```java
List<String> flatList = 
	list.stream()
	.flatMap(Collection::stream)
	.collect(Collectors.toList()); // *[a, b]*
```

아래의 객채에 적용된 예시를 보자.

```java
students.stream()
	.flatMapToInt(student -> IntStream.of(student.getKor(),
																				student.getEng(),
																				student.getMath()))
	.average().ifPresent(avg -> System.out.println(Math.round(avg * 10) / 10.0));
```

위 예제는 학생 객체를 가진 스트림에서 학생의 국어, 영어, 수학 점수를 받아 새로운 스트림을 만들고 평균을 구하는 예제이다. 이는 `map` 메소드 자체만으로 한 번에 수행할 수 없는 기능이다.

**Sorting**

정렬은 다른 정렬과 마찬가지로 `Comparator` 를 사용한다.

```java
Stream<T> sorted();
Stream<T> sorted(Comparator<? super T> comparator);
```

인자 없이 호출할 경우 기본적으로 오름차순으로 정렬한다.

```java
IntStream.of(14, 11, 20, 39, 23)
	.sorted()
	.boxed()
	.collect(Collectors.toList()); // *[11, 14, 20, 23, 39]*
```

인자를 넘기는 경우와 비교해보자. 스트링 리스트에서 알파벳 순으로 정렬한 코드와 Comparator를 넘겨 역순으로 정렬한 코드다.

```java
List<String> lang =
	Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");

lang.stream()
	.sorted()
	.collect(Collectors.toList()); // *[Go, Groovy, Java, Python, Scala, Swift]*

lang.stream()
	.sorted(Comparator.reverseOrder())
	.collect(Collectors.toList()); // *[Swift, Scala, Python, Java, Groovy, Go]*
```

Comparator의 `compare` 메소드는 두 인자를 비교하여 값을 리턴한다.

```java
int compare(T o1, T o2)
```

기본적인 Comparator 사용법과 동일하다. 이를 이용하여 문자열 길이를 기준으로 정렬해보자.

```java
lang.stream()
	.sorted(Comparator.comparingInt(String::length))
	.collect(Collectors.toList()); // *[Go, Java, Scala, Swift, Groovy, Python]*

lang.stream()
	.sorted((s1, s2) -> s2.length() - s1.length())
	.collect(Collectors.toList()); // *[Groovy, Python, Scala, Java, Go]*
```

**Iterating**

스트림 내 요소들 각각을 대상으로 특정 연산을 수행하는 메소드로는 `peek` 이 있다. peek은 그냥 확인해본다는 단어의 뜻처럼 특정 결과를 반호나하지 않는 함수형 인터페이스 Consumer를 인자로 받는다.

```java
Stream<T> peek(Consumer<? super T> action);
```

스트림 내 요소들 각각에 특정 작업을 수행할 뿐 결과에 영향을 미치지 않는다. 다음처럼 작업을 처리하는 중간에 결과를 확인해볼 때 사용할 수 있다.

```java
int sum = IntStream.of(1, 3, 5, 7, 9)
	.peek(System.out::println)
	.sum();
```

## **결과 만들기**

가공한 스트림을 가지고 내가 사용할 결과값으로 만들어내는 단계이다. 스트림을 끝내는 최종 작업(terminal operations)

**Calculating**

스트림 API는 다양한 최종 작업을 제공한다. 최소, 최대, 합, 평균 등 기본 타입으로 결과를 만들어낼 수도 있다.

```java
long count = IntStream.of(1, 3, 5, 7, 9).count();
long sum= LongStream.of(1, 3, 5, 7, 9).sum();
```

만약 스트림이 비어 있는 경우 `count` 와 `sum` 은 0을 출력하게 된다. 하지만 평균, 최소, 최대의 경우 표현할 수 없기 때문에 Optional을 사용해 반환한다.

```java
OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
OptionalInt max = IntStream.of(1, 3, 5, 7, 9).max();
```

스트림에서 바로 `ifPresent` 메소드를 활용해 Optional을 처리할 수 있다.

```java
DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5)
	.average()
	.ifPresent(System.out::println);
```

이 외에도 사용자가 원하는 결과를 만들어내기 위해 `reduce` 와 `collect` 메소드를 제공한다.

**Reduction**

스트림은 `reduce` 라는 메소드를 이용해 결과를 만들어낸다.  스트림에 있는 여러 요소의 총합을 낼 수도 있다.

다음은 `reduce` 메소드는 총 세 가지의 파라미터를 받을 수 있다.

- accumulator : 각 요소를 처리하는 계산 로직. 각 요소가 올 때마다 중간 결과를 생성하는 로직.
- identity : 계산을 위한 초기값으로 스트림이 비어있어 계산할 내용이 없더라도 이 값이 리턴.
- combiner : 병럴(parallel) 스트림에서 나눠 계산한 결과를 하나로 합쳐주는 로직.

```java
// *1개 (accmulator)
Optional<T> reduce(BinaryOperator<T> accumlator);*

// *2개 (identity)*
T reduce(T identity, BinaryOperator<T> accumlator);

// *3개 (combiner)*
<U> U reduce(U identity, BiFunction<U, ? super T, U> accumlator, BinaryOperator<U> combiner);
```

인자가 하나만 있는 경우, `BinaryOperator<T>` 는 같은 타입의 인자 두 개를 받아 같은 타입의 결과를 반환하는 함수형 인터페이스다. 다음 예제에서는 두 값을 더 하는 람다식으로 익명 객체를 넘겨준다.

```java
OptionalInt reduced = 
	IntStream.range(1, 4) // *[1, 2, 3]*
	.reduce((a, b) -> Integer.sum(a, b)); // *결과 6*
```

이번엔 두 개의 인자를 받는 경우이다. 여기서 10은 초기값이고, 스트림 내 값을 더하여 결과는 16(10 + 1 + 2 + 3)이 된다. 여기서 람다는 메소드 참조(method reference)를 이용하여 넘길 수 있다.

```java
int reducedTwoParams =
	IntStream.range(1, 4) // *[1, 2, 3]
	.reduce(10, Integer::sum);* // *method reference*
```

마지막으로 세 개의 인자를 받는 경우이다. Combiner가 하는 역할을 코드로 한 번 살펴보자. 아래의 코드를 실행할 경우 마지막 인자인 Combiner는 실행되지 않는다.

```java
Integer reducedParams = Stream.of(1, 2, 3)
	.reduce(10, Integer::sum, (a, b) -> {
						System.out.println("Combiner was called");
						return a + b;
					});
```

Combiner는 병렬 처리 시 각자 다른 쓰레드에서 실행한 결과를 마지막에 합치는 단계이다. 따라서 병렬 스트림에서만 작동한다.

```java
Integer reducedParallel = Arrays.asList(1, 2, 3)
	.parallelStream()
	.reduce(10, Integer::sum, (a, b) -> {
						System.out.println("Combiner was called");
						return a + b;
					});
```

결과는 36이 나온다. 먼저 accumulator는 총 세 번 동작하는데, 초기값 10에 각 스트림 값을 더한 세 개의 값(10 + 1 = 11, 10 + 2 = 12, 10 + 3 = 13)을 계산한다. Combiner는 identity와 accumulator를 가지고 여러 쓰레드에서 나눠 계산한 결과를 합치는 역할을 한다. 12 + 13 = 25, 25 + 11 = 36 이렇게 두 번의 호출과 실행을 거친다.

```java
combiner was called
combiner was called
36
```

병렬 스트림이 무조건 시퀀셜보다 좋은 것이 아니다. 오히려 간단한 경우에는 이렇게 부가적인 처리가 필요하기 때문에 오히려 느려질 수가 있다.

**Collecting**

`collect` 메소드는 또 다른 종료 작업이다. `Collector` 타입의 인자를 받아서 처리 하는데, 자주 사용되는 작업은 `Collectors` 객체에서 제공하고 있다.

이번 예제에선 다음과 같은 간단한 리스트를 사용한다. Product 객체는 수량(amount)과 이름(name)을 가지고 있다.

```java
List<Product> productList =
	Arrays.asList(new Product(23, "potato"),
								new Product(14, "orange"),
								new Product(13, "lemon"),
								new Product(23, "bread"),
								new Product(13, "sugar"),
```

**Collectors.toList()**

스트림에서 작업한 결과를 담은 리스트로 반환한다. 다음 예제에서는 `map()`  으로 각 요소의 이름을 가져온 후 `Collectors.toList` 를 이용해 리스트로 결과를 가져온다.

```java
List<String> collectorCollection =
	productList.stream()
		.map(Product::getName)
		.collect(Collectors.toList()); // *[potato, orange, lemon, bread, sugar]*
```

**Collectors.joining()**

스트림에서 작업한 결과를 하나의 스트링으로 이어 붙일 수 있다.

```java
String listToString =
	productList.stream()
		.map(Product::getName)
		.collect(Collectors.joining()); // *potatoorangelemonbreadsugar*
```

`Collectors.joining` 은 세 개의 인자를 받을 수 있다. 이를 이용하면 간단하게 스트링을 조합할 수 있다.

- delimiter : 각 요소 중간에 들어가 요소를 구분 시켜주는 구분자
- prefix : 결과 맨 앞에 붙는 문자
- suffix : 결과 맨 뒤에 붙는 문자

```java
String listToString = 
	productList.stream()
		.map(Product::getName)
		.collect(Collectors.joining(", ", "<", ">")); // *<potato, orange, lemon, bread, sugar>*
```

**Collectors.averageingInt()**

정수값(Integer value)의 평균(arithmetic mean)을 낸다.

```java
Double averageAmount =
	productList.stream()
		.collect(Collectors.averagingInt(Product::getAmount)); // *17.2*
```

**Collectors.summingInt()**

정수값의 합(sum)을 낸다.

```java
Integer summingAmount =
	productList.strea()
		.collect(Collectors.summingInt(Product::getAmount)); // *86*
```

**Collectors.summarizingInt()**

만약 합계와 평균 값 모두 필요하다면 스트림을 두 번 생성해야 할까? 이런 정보를 한 번에 받을 수 있는 방법으로 `summarizingInt` 메소드가 있다

```java
IntSummaryStatistics statistics =
	productList.stream()
		.collect(Collectors.summarizingInt(Product::getAmount));
```

이렇게 받아온 IntSummaryStatistics 객체에는 다음과 같은 정보가 담겨 있다.

```java
IntSummaryStatistics {count = 5, sum = 86, min = 13, average = 17.200000, max = 23}
```

- 개수 *getCount()*
- 합계 *getSum()*
- 평균 *getAverage()*
- 최소 *getMin()*
- 최대 *getMax()*

이를 활용하면 `collect` 전에 이런 통계 작업을 위한 `map` 을 호출할 필요가 없다. 위에서 살펴본 averaging, summing, summarizing 메소드는 각 기본 타입(int, long, double)별로 제공된다.

**Collectos.groupingBy()**

특정 조건으로 요소들을 그룹 지을 수 있다. 수량을 기준으로 그룹핑해보자. 여기서 받는 인자는 함수형 인터페이스 Function이다.

```java
Map<Integer, List<Product>> collectorMapOfLists =
	productList.stream()
		collect(Collectors.groupingBy(Prodcut::getAmount));
```

결과는 Map 타입으로 반환되며, 같은 수량이면 리스트로 묶어 나타난다.

```java
{23=[Product{amount=23, name='potato'}, 
     Product{amount=23, name='bread'}], 
 13=[Product{amount=13, name='lemon'}, 
     Product{amount=13, name='sugar'}], 
 14=[Product{amount=14, name='orange'}]}
```

**Collectors.partitioningBy()**

위의 `groupingBy` 함수형 인터페이스 Function을 이용해 특정 값을 기준으로 스트림 내 요소들을 묶었다면, `partitioningBy` 는 함수형 인터페이스 Predicate를 받는다. Predicate는 인자를 받아 boolean값을 리턴한다.

```java
Map<Boolean, List<Product>> mapPratitioned =
	productList.stream()
		.collect(Collectors.partitioningBy(el -> el.getAmount() > 15));
```

따라서 평가를 하는 함수를 통해 스트림 내 요소들을 true와 false 두 가지로 나눌 수 있다.

```java
{false=[Product{amount=14, name='orange'}, 
        Product{amount=13, name='lemon'}, 
        Product{amount=13, name='sugar'}], 
 true=[Product{amount=23, name='potato'}, 
       Product{amount=23, name='bread'}]}
```

**Collectors.collectingAndThen()**

특정 타입으로 결과를 `collect` 한 후에 추가 작업이 필요한 경우에 사용할 수 있다. 이 메소드의 시그니처는 다음과 같다. `finisher` 가 추가된 모양으로 이 피니셔는 collect를 한 후에 실행할 작업을 의미한다.

```java
public static<T, A, R, RR> Collector<T, A, RR> collectingAndThen(
	Collector<T, A, R> downstream,
	Function<R, RR> finisher) { . . . }
```

다음 예제는 `Collectors.toSet` 을 이용해 결과를 Set으로 collect한 후 수정 불가한 Set으로 변환하는 작업을 추가로 실행하는 코드다.

```java
Set<Product> unmodifiableSet =
	productList.stream()
		.collect(Collectors.collectingAndThen(Collectors.toSet(),
																					Collections::unmodifiableSet));
```

**Collector.of**

여러 상황에서 사용할 수 있는 메소드를 살펴 보았다. 이 외에 필요한 로직이 있다면 직접 collector를 만들 수도 있다. accumulator와 combiner는 `reduce` 에서 살펴본 개념과 동일하다.

```java
public static<T, R> Collector<T, R, R> of(
	Supplier<R> supplier, // *new collector 생성*
	Biconsumer<R, T> accumulator, // *두 값를 가지고 계산*
	BinaryOperator<R> combiner, // *계산한 결과를 수집하는 함수*
	Characterstics... characteristics) { . . . }
```

코드를 살펴보자. 다음 코드는 collector 하나를 생성한다. 컬렉터를 생성하는 supplier에 LinckedList의 생성자를 넘겨주고 accumulator에는 리스트에 추가하는 `add` 메소드를 넘겨준다. 따라서 이 컬렉터는 스트림의 각 요소에 대해서 LinkedList를 만들고 요소를 추가하게 된다. 마지막으로 combiner를 이용해 결과를 조합하는데, 생성된 리스트들을 하나의 리스트로 합친다.

```java
Collector<Product, ?, LinkedList<Product>> toLinkedList = 
	Collector.of(LinkedList::new,
							 LinkedList::add,
							 (first, second) -> {
								 first.addAll(second);
								 return first;
							 });
```

따라서 다음과 같이 `collect` 메소드에 우리가 만든 커스텀 컬렉터를 넘겨줄 수 있고, 결과가 담긴 LinkedList가 반환된다.

```java
LinkedList<Product> linkedListOfProduct =
	productList.stream()
		.collect(toLinkedList);
```

**Matching**

매칭은 조건식 람다 predicate를 받아 해당 조건을 만족하는 요소가 있는 지 체크한 결과를 반환한다. 다음 세 가지 메소드가 있다.

- 하나라도 조건을 만족하는 요소가 있는지(anyMatch)
- 모두 조건을 만족하는지(allMatch)
- 모두 조건을 만족하지 않는지(noneMatch)

```java
boolean anyMatch(Predicate<? super T> predicate);
boolean allMatch(Predicate<? super T> predicate);
boolean noneMatch(Predicate<? super T> predicate);
```

간단한 예제로 알아보자. 다음 매칭 결과는 모두 `true` 이다.

```java
List<String> names = Arrays.asList("Eric", "Elena", "Java");

boolean anyMatch = names.stream()
  .anyMatch(name -> name.contains("a"));
boolean allMatch = names.stream()
  .allMatch(name -> name.length() > 3);
boolean noneMatch = names.stream()
  .noneMatch(name -> name.endsWith("s"));
```

**Iterating**

`foreach` 는 요소를 돌면서 실행되는 최종 작업이다. 보통 Systrem.out.println 메소드를 넘겨서 결과를 출력할 때 사용하곤 한다. 앞서 살펴본 `peek` 과는 중간 작업과 최종 작업의 차이가 있다.

```java
names.stream().forEach(System.out::println);
```