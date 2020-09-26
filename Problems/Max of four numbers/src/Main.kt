import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // write your code here
    val numbers = IntArray(4)

    numbers[0] = scanner.nextInt()
    numbers[1] = scanner.nextInt()
    numbers[2] = scanner.nextInt()
    numbers[3] = scanner.nextInt()

    println(numbers.max())

}