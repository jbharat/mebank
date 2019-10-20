package com.mebank.service

import com.mebank.domain.Transaction
import com.mebank.domain.TransactionType
import com.mebank.domain.TransactionsSummary
import com.mebank.utility.DateTransformer
import java.math.BigDecimal

class TransactionAnalyserService {
    companion object {
        fun analyse(transactions: List<Transaction>, accountId: String, fromDateString: String, toDateString: String): TransactionsSummary {

            val fromDate = DateTransformer.parse(fromDateString)
            val toDate = DateTransformer.parse(toDateString)

            if (fromDate > toDate) throw Exception("fromDate should be less than toDate")

            var sum = BigDecimal.ZERO
            var revSum = BigDecimal.ZERO

            val filteredTransactions = transactions.filter {
                it.createAt in fromDate..toDate && it.fromAccountId == accountId
            }

            filteredTransactions.forEach {
                when (it.transactionType) {
                    TransactionType.PAYMENT -> {
                        sum = sum.plus(it.amount)
                    }
                    else -> {
                        revSum = revSum.plus(it.amount)
                    }
                }
            }

            return TransactionsSummary(sum.negate().plus(revSum), filteredTransactions.size)
        }
    }
}
