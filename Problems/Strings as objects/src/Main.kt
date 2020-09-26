fun main() {
    val input = readLine()!!
    // write code here

    // check it starts with i
    if (input.isNotEmpty()) {
        when {
            input.first() == 'i' -> {
                print(input.drop(1).toInt() + 1)
            }
            input.first() == 's' -> {
                print(input.drop(1).reversed())
            }
            else -> {
                print(input)
            }
        }
    } else {
        println()
    }

}
