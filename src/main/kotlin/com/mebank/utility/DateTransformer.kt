package com.mebank.utility

import java.text.SimpleDateFormat
import java.util.Date

class DateTransformer {
    companion object {

        private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

        fun parse(dateString: String): Date = simpleDateFormat.parse(dateString)
    }
}