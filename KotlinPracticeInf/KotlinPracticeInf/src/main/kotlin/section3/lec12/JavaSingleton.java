package section3.lec12;

public class JavaSingleton {

    private JavaSingleton() {
    }

    public static JavaSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {

        private LazyHolder() {
        }

        public static final JavaSingleton INSTANCE = new JavaSingleton();
    }
}
