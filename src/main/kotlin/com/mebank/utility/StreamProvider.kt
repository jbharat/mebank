package com.mebank.utility

import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.io.Reader
import java.nio.charset.Charset

class StreamProvider {
    companion object {
        val getStreamFromFile: (String) -> Reader = {
            InputStreamReader(ClassLoader.getSystemResourceAsStream(it))
        }

        val getStreamFromString: (String) -> Reader = {
            InputStreamReader(ByteArrayInputStream(it.toByteArray(Charset.defaultCharset())))
        }
    }
}