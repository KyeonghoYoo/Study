package section3.Lec10

abstract class Animal(
    protected val spcies: String,
    protected open val legCount: Int
) {
    abstract fun move()

}