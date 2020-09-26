import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val a = scanner.nextDouble()
    val b = scanner.nextDouble()
    val c = scanner.nextDouble()

    val x = (c - b) / a

    println(x)

}