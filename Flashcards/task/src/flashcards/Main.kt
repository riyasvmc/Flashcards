package flashcards

import java.io.File
import java.util.*

data class Card(val term: String, val definition: String, var error: Int = 0)

fun main(args: Array<String>) {

    var exportData = false
    var exportFileName = ""
    var importFileName = ""

    if (args.size == 2) {
        if (args[0] == "-import") {
            importFileName = args[1]
        } else { // -export
            exportData = true
            exportFileName = args[1]
        }
    } else if (args.size == 4) {
        exportData = true
        if (args[0] == "-import") {
            importFileName = args[1]
            exportFileName = args[3]
        } else {
            exportFileName = args[1]
            importFileName = args[3]
        }
    }

    val scanner = Scanner(System.`in`)
    val cards = mutableListOf<Card>()
    var log = ""

    if (importFileName.isNotBlank()) {
        // do import data
        log += importFileName + "\n"
        val file = File(importFileName)
        if (file.exists()) {
            val lines = file.readLines()
            var loadedCount = 0
            for (line in lines) {
                if (line.trim().isNotEmpty()) {
                    val parts = line.split(":::")
                    cards.add(Card(parts[0], parts[1], parts[2].toInt()))
                    loadedCount++
                }
            }
            println("$loadedCount cards have been loaded.\n")
            log += "$loadedCount cards have been loaded.\n\n"
        }
    }

    while (true) {
        // ask for type of action
        val text = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):\n"
        print(text)
        log += text

        val input = readLine()
        log += input + "\n"

        when (input) {
            "add" -> {
                val text = "The card:\n"
                print(text)
                log += text

                var term: String = readLine()!!
                log += term + "\n"

                for (card in cards) {
                    if (term == card.term) {
                        val text = "The card \"$term\" already exists.\n\n"
                        print(text)
                        log += text
                        continue
                    }
                }

                val text2 = "The definition of the card:\n"
                print(text2)
                log += text2

                var definition: String = readLine()!!
                log += definition + "\n"


                for (card in cards) {
                    if (definition == card.definition) {
                        println("The definition \"$definition\" already exists.\n")
                        log += "The definition \"$definition\" already exists.\n\n"
                        continue
                    }
                }

                cards.add(Card(term, definition))
                println("The pair (\"$term\":\"$definition\") has been added.\n")
                log += "The pair (\"$term\":\"$definition\") has been added.\n\n"
            }

            "remove" -> {
                println("The card:")
                log += "The card:\n"
                val term = readLine()!!
                log += term + "\n"

                // check the card exists or not
                var isNotRemoved = true
                val cardsToRemove = mutableListOf<Card>()
                for (card in cards) {
                    if (card.term == term) {
                        cardsToRemove.add(card)
                        isNotRemoved = false
                    }
                }

                cards.removeAll(cardsToRemove)

                if (isNotRemoved) {
                    println("Can't remove \"$term\": there is no such card.\n")
                    log += "Can't remove \"$term\": there is no such card.\n\n"
                } else {
                    println("The card has been removed.\n")
                    log += "The card has been removed.\n\n"
                }
            }

            "import" -> {
                println("File name:")
                val fileName = readLine()!!
                log += fileName + "\n"
                val file = File(fileName)
                if (file.exists()) {
                    val lines = file.readLines()
                    var loadedCount = 0
                    for (line in lines) {
                        if (line.trim().isNotEmpty()) {
                            val parts = line.split(":::")
                            cards.add(Card(parts[0], parts[1], parts[2].toInt()))
                            loadedCount++
                        }
                    }
                    println("$loadedCount cards have been loaded.\n")
                    log += "$loadedCount cards have been loaded.\n\n"
                } else {
                    println("File not found.\n")
                    log += "File not found.\n\n"
                }
            }

            "export" -> {
                println("File name:")
                log += "File name:\n"
                val fileName = readLine()!!
                log += fileName + "\n"
                val file = File(fileName)
                var textToWrite = ""
                for (card in cards) {
                    textToWrite += "${card.term}:::${card.definition}:::${card.error}\n"
                }
                file.writeText("\n$textToWrite")
                println("${cards.size} cards have been saved.\n")
                log += "${cards.size} cards have been saved.\n\n"
            }

            "ask" -> {
                println("How many times to ask?")
                log += "How many times to ask?\n"
                val times = scanner.nextInt()
                repeat(times) {
                    val card = cards.random()
                    println("Print the definition of \"${card.term}\":")
                    log += "Print the definition of \"${card.term}\":\n"
                    val answer = readLine()!!
                    log += answer + "\n"
                    if (card.definition == answer) {
                        println("Correct!")
                        log += "Correct!\n"
                    } else if (isAnswerExists(cards, answer)) {
                        println("Wrong. The right answer is \"${card.definition}\", but your definition is correct for \"${findCardForAnswer(answer, cards)?.term}\".")
                        log += "Wrong. The right answer is \"${card.definition}\", but your definition is correct for \"${findCardForAnswer(answer, cards)?.term}\".\n"
                        val newCard = Card(card.term, card.definition, card.error + 1)
                        cards.remove(card)
                        cards.add(newCard)
                    } else {
                        println("Wrong. The right answer is \"${card.definition}\".")
                        log += "Wrong. The right answer is \"${card.definition}\".\n"
                        val newCard = Card(card.term, card.definition, card.error + 1)
                        cards.remove(card)
                        cards.add(newCard)
                    }
                }
            }

            "log" -> {
                println("File name:")
                log += "File name:\n"
                val fileName = readLine()
                log += fileName + "\n"
                val file = File(fileName)
                file.writeText(log)
                println("The log has been saved.")
            }

            "hardest card" -> {
                val maxCards = findHardestCards(cards)
                if (maxCards != null) {
                    val theCard = maxCards[0]
                    var firstTerm = ""
                    var singular = true
                    if (maxCards.size == 1) {
                        firstTerm = "\"${theCard.term}\""
                    } else {
                        for (card in maxCards) {
                            firstTerm += "\"${card.term}\", "
                        }
                        firstTerm = firstTerm.substring(0, firstTerm.length - 2)
                        singular = false
                    }
                    if (singular) {
                        println("The hardest card is $firstTerm. You have ${theCard.error} errors answering it.\n")
                        log += "The hardest card is $firstTerm. You have ${theCard.error} errors answering it.\n\n"
                    } else {
                        println("The hardest cards are $firstTerm. You have ${theCard.error} errors answering them.\n")
                        log += "The hardest cards are $firstTerm. You have ${theCard.error} errors answering them.\n\n"
                    }

                } else {
                    println("There are no cards with errors.\n")
                    log += "There are no cards with errors.\n\n"
                }
            }

            "reset stats" -> {
                val newCards = mutableListOf<Card>()
                for (card in cards) {
                    card.error = 0
                    newCards.add(card)
                }
                cards.clear()
                cards.addAll(newCards)

                println("Card statistics has been reset.\n")
                log += "Card statistics has been reset.\n\n"
            }

            "exit" -> {
                println("Bye bye!")
                if (exportData) {
                    val file = File(exportFileName)
                    var textToWrite = ""
                    for (card in cards) {
                        textToWrite += "${card.term}:::${card.definition}:::${card.error}\n"
                    }
                    file.writeText("\n$textToWrite")
                    println("${cards.size} cards have been saved.\n")
                    log += "${cards.size} cards have been saved.\n\n"
                }
                break    // exit the program and, print Bye bye!
            }
        }
    }
}

fun findHardestCards(cards: MutableList<Card>): List<Card>? {
    val maxCards = mutableListOf<Card>()
    val card = cards.filter { it.error > 0 }.maxByOrNull { it.error }

    if (card != null) {
        maxCards.addAll(cards.filter { it.error == card.error })
    }

    return if (maxCards.size > 0) { maxCards
    } else {
        null
    }
}

fun findCardForAnswer(answer: String, cards: List<Card>): Card? {
    for (card in cards) {
        if (card.definition == answer) {
            return card
        }
    }
    return null
}

fun isAnswerExists(cards: List<Card>, answer: String): Boolean {
    for (card in cards) {
        if (card.definition == answer) {
            return true
        }
    }
    return false
}
