import java.util.Scanner
import kotlin.math.pow
import kotlin.math.sqrt

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // put your code here

    val a = scanner.nextDouble()
    val b = scanner.nextDouble()
    val c = scanner.nextDouble()

    val part = (sqrt(b.pow(2.0) - 4 * a * c))
    val x = ( -b + part ) / 2 * a
    val y = ( -b - part ) / 2 * a

    if (x < y) {
        println("$x $y")
    } else {
        println("$y $x")
    }
}
