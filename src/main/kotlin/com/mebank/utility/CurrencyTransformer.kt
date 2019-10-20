package com.mebank.utility

import java.math.BigDecimal
import java.text.DecimalFormat

class CurrencyTransformer {
    companion object {

        private val df = DecimalFormat("$#,##0.00;-$#,##0.00")

        fun format(amount: BigDecimal): String = df.format(amount)
    }
}