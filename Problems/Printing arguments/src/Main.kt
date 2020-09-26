fun main(args: Array<String>) {
    if (args.size == 3) {
        var counter = 1
        for (arg in args) {
            println("Argument $counter: $arg. It has ${arg.length} characters")
            counter++
        }
    } else {
        println("Invalid number of program arguments")
    }
}