fun main() {
    val letters = mutableMapOf<Int, String>()

    var i = 0

    while (true) {
        letters[i] = readLine()!!.toLowerCase()
        if (letters[i] == "z") {
            break
        }
        i++
    }

    if(letters.size >= 4) print(letters[3]) else print("null")
}