import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // put your code here
    val distance = scanner.nextDouble()
    val time = scanner.nextDouble()

    val speed = distance / time

    println(speed)
}