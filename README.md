# Coding Challenge For MEBank

This project analyses transactions for a given accountId and a date range.
The transactions are read from a csv file in resources directory.

## Project Info
* Java 8
* Kotlin
* Gradle

## Instructions
* `./gradlew build` will install the dependencies, builds the project and runs tests.
* `./gradlew test` to run tests.
* `./gradlew run` will execute the App.kt.
* This project can also be imported into Intellij. To execute in Intellij, run App.kt file.

## Structure and Details
Project contains 3 packages
* Domain - Contains data classes.
* Service - Contains the transaction analyser.
* Utility - Contains helper classes.

#### CsvReader
CSV files are read using generic CsvReader which takes a filename and two higher order functions.
First HOC returns a stream from the passed data. This can be a file as used in Transaction.kt or just plain string
as used in CsvReaderTest. Second HOC creates the specified object. This makes this implementation generic.

#### TransactionAnalyserService
This contains the core logic of generating the summary from the list of transactions. It uses 2 loops
to calculate the summary. 1st loop is to filter the transactions in the range and with matching accountId.
2nd loop calculates the sum and reverse payment sum of the filtered transactions. It throws an exception
 if fromDate is greater that toDate.
