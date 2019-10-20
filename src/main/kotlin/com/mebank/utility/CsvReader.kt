package com.mebank.utility

import java.io.BufferedReader
import java.io.IOException
import java.io.Reader
import java.text.ParseException

class CsvReader {
    companion object {

        private const val COMMA_DELIMITER = ","

        fun <T> read(fileName: String, getStream: (String) -> Reader, transform: (List<String>) -> T): List<T> {
            val data = ArrayList<T>()
            try {
                BufferedReader(getStream(fileName)).use {
                    it.readLine()   // skip header
                    it.forEachLine { line ->
                        try {
                            // skip transaction in case of parse exception
                            data.add(transform(line.split(COMMA_DELIMITER)))
                        } catch (e: ParseException) {
                            println(e.message)
                        }
                    }
                }
            } catch (e: IOException) {
                println(e.message)
            }
            return data
        }
    }
}