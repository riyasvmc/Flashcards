import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // put your code here
    var number = scanner.nextInt()

    var sum = 0

    repeat(3) {
        val reminder = number % 10
        sum += reminder
        number /= 10
    }

    print(sum)

}