val fileName = "MyCases.txt"
val myFile = File(fileName)
val textToWrite = "The Sign of the Four"
myFile.appendText("\n")
myFile.appendText(textToWrite)
