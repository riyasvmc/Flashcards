import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // put your code here

    val x = scanner.nextDouble()
    val result = x * x * x + x * x + x + 1

    println(result)
}