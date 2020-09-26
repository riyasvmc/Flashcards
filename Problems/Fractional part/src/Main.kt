import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // put your code here

    var number = scanner.nextDouble()
    number *= 10

    println(number.toInt() % 10)
}