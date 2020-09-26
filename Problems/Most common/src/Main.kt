fun main() {
    val words = mutableMapOf<String, Int>()
    var i = 0
    while (true) {
        val word = readLine()!!.toLowerCase()
        if (word == "stop") break
        if (words.containsKey(word)) words[word] = words[word]!! + 1 else words[word] = 1
        i++
    }

    if (words.isEmpty()) {
        println("null")
    } else {
        val count = words.values.max()
        if (count != null) println(getKey(words, count))
    }
}

fun <K, V> getKey(hashMap: Map<K, V>, target: V): K {
    return hashMap.filter { target == it.value }.keys.first()
}
