package com.mebank.utility

import org.junit.jupiter.api.Test
import java.text.ParseException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DateTransformerTest {

    @Test
    fun `should read date in right format`() {
        val dateString = "21/10/2018 20:00:00"
        val date = DateTransformer.parse(dateString)
        assertEquals(date.time.toString(), "1540112400000")
    }

    @Test
    fun `should throw exception if date is not in the right format`() {
        val dateString = "21/10/2018 20"
        assertFailsWith<ParseException> { DateTransformer.parse(dateString) }
    }
}