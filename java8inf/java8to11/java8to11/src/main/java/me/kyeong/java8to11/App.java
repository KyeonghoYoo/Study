package me.kyeong.java8to11;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// 익명 내부 클래스 anonymous inner class
        RunSomething runSomething = new RunSomething() {
			
			@Override
			public void doIt() {
				System.out.println("Hello");
			}
		};
		// 람다 표현식을 이용하여 코드를 간결하게
		RunSomething runSomthing_lambda = () -> System.out.println("Hello");
		RunSomething runSomthing_lambda2 = () -> {
			System.out.println("Hello");
			System.out.println("Kyeongho");
		};
    }
}
