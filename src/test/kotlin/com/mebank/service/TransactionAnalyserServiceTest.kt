package com.mebank.service

import com.mebank.domain.createTransaction
import com.mebank.utility.CsvReader
import com.mebank.utility.StreamProvider.Companion.getStreamFromString
import org.junit.jupiter.api.Test
import java.lang.Exception
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TransactionAnalyserServiceTest {

    @Test
    fun `should sum all the payments in the given time frame`() {
        val accountId = "ACC334455"
        val fromDate = "20/10/2018 00:00:00"
        val toDate = "20/10/2018 18:00:00"
        val response = TransactionAnalyserService.analyse(transactions, accountId, fromDate, toDate)
        assertEquals(response.amount, BigDecimal("-35.50"))
        assertEquals(response.transactionsCount, 2)
    }

    @Test
    fun `should handle empty transactions`() {
        val accountId = "ACC334455"
        val fromDate = "22/10/2018 00:00:00"
        val toDate = "23/10/2018 18:00:00"
        val response = TransactionAnalyserService.analyse(transactions, accountId, fromDate, toDate)
        assertEquals(response.amount, BigDecimal.ZERO)
        assertEquals(response.transactionsCount, 0)
    }

    @Test
    fun `should handle Reversal payments`() {
        val accountId = "ACC334455"
        val fromDate = "20/10/2018 17:00:00"
        val toDate = "20/10/2018 20:00:00"
        val response = TransactionAnalyserService.analyse(transactions, accountId, fromDate, toDate)
        assertEquals(response.amount, BigDecimal("0.00"))
        assertEquals(response.transactionsCount, 2)
    }

    @Test
    fun `should throw error if fromDate is greater than toDate`() {
        val accountId = "ACC334455"
        val fromDate = "21/10/2018 17:00:00"
        val toDate = "20/10/2018 20:00:00"
        assertFailsWith<Exception> { TransactionAnalyserService.analyse(transactions, accountId, fromDate, toDate) }
    }

    companion object {
        private val data = """
        fromAccountId,toAccountId,createdAt,amount,transactionType,relatedTransaction
        TX10001,ACC334455,ACC778899,20/10/2018 12:47:55,25.00,PAYMENT
        TX10002,ACC334455,ACC998877,20/10/2018 17:33:43,10.50,PAYMENT
        TX10003,ACC998877,ACC778899,20/10/2018 18:00:00,5.00,PAYMENT
        TX10004,ACC334455,ACC998877,20/10/2018 19:45:00,10.50,REVERSAL, TX10002
        TX10005,ACC334455,ACC778899,21/10/2018 09:30:00,7.25,PAYMENT
        """.trimIndent()

        val transactions = CsvReader.read(data, getStreamFromString, createTransaction)
    }
}