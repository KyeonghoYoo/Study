package section3.lec12

fun main() {
    movesomething(object : Movable {
        override fun move() {
            TODO("Not yet implemented")
        }

        override fun fly() {
            TODO("Not yet implemented")
        }
    })
}

private fun movesomething(movable: Movable) {
    movable.move()
    movable.fly()
}