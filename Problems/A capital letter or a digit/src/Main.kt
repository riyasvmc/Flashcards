import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val character: Char = scanner.next()[0]
    if (character in 'A'..'Z' || character in '1'..'9') {
        println(true)
    } else {
        println(false)
    }
}