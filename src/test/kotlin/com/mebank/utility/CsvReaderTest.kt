package com.mebank.utility

import com.mebank.domain.createTransaction
import com.mebank.utility.StreamProvider.Companion.getStreamFromString
import org.junit.jupiter.api.Test
import java.io.IOException
import java.io.Reader
import kotlin.test.assertEquals

class CsvReaderTest {

    @Test
    fun `should parse csv and create list of given object`() {
        val data = """
        ID,VAL1,VLA2,VAL#
        01,455,899,25.00
        02,455,877,10.50
        03,877,899,5.00
        04,455,877,10.50
        05,455,899,7.25
        """.trimIndent()

        val objects = CsvReader.read(data, getStreamFromString, createObj)
        assertEquals(objects.size, 5)
        assertEquals(objects[1].val3, "10.50")
    }

    @Test
    fun `should handle parse errors`() {
        val data = """
        fromAccountId,toAccountId,createdAt,amount,transactionType,relatedTransaction
        TX10001,ACC334455,ACC778899,20/10/2018 12:47:55,25.00,PAYMENT
        TX10002,ACC334455,ACC998877,20/10/2018 17,10.50,PAYMENT
        """.trimIndent()

        val objects = CsvReader.read(data, getStreamFromString, createTransaction)
        assertEquals(objects.size, 1)
    }

    @Test
    fun `should handle IO exception`() {
        val throwException: (String) -> Reader = {
            throw IOException("File not found")
        }
        val objects = CsvReader.read("", throwException, createTransaction)
        assertEquals(objects.size, 0)
    }

    data class TestObj(
            val id: String,
            val val1: String,
            val val2: String,
            val val3: String
    )

    companion object {
        val createObj: (List<String>) -> TestObj = { tokens ->
            TestObj(
                    tokens[0],
                    tokens[1],
                    tokens[2],
                    tokens[3]
            )
        }
    }
}