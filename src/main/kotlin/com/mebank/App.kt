package com.mebank

import com.mebank.domain.createTransaction
import com.mebank.service.TransactionAnalyserService
import com.mebank.utility.CsvReader
import com.mebank.utility.CurrencyTransformer
import com.mebank.utility.StreamProvider.Companion.getStreamFromFile

fun main(args: Array<String>) {
    val fileName = "data.csv"
    val accountId = "ACC334455"
    val fromDate = "20/10/2018 00:00:00"
    val toDate = "21/10/2018 20:00:00"

    val transactions = CsvReader.read(fileName, getStreamFromFile, createTransaction)

    TransactionAnalyserService.analyse(transactions, accountId, fromDate, toDate).apply {
        println("Relative balance for the period is: ${CurrencyTransformer.format(amount)}")
        println("Number of transactions included is: $transactionsCount")
    }
}
