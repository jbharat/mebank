package com.mebank.domain

import com.mebank.utility.DateTransformer
import java.math.BigDecimal
import java.util.Date

data class Transaction(
        val transactionId: String,
        val fromAccountId: String,
        val toAccountId: String,
        val createAt: Date,
        val amount: BigDecimal,
        val transactionType: TransactionType,
        val relatedTransaction: String?
)

val createTransaction: (List<String>) -> Transaction = { tokens ->
    Transaction(
            tokens[0],
            tokens[1],
            tokens[2],
            DateTransformer.parse(tokens[3]),
            BigDecimal(tokens[4]),
            TransactionType.valueOf(tokens[5]),
            when {
                tokens.size > 6 -> tokens[6]  // read relatedTransaction
                else -> null
            }
    )
}
