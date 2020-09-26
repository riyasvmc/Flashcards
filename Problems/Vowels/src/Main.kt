fun main() {
    val letter = readLine()!!.toLowerCase()
    val vowels = mutableMapOf(1 to "a", 5 to "e", 9 to "i", 15 to "o", 21 to "u")
    var result = 0
    for ((key, value) in vowels) {
        if (value == letter) {
            result = key
        }
    }
    println(result)
}
