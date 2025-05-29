package com.chrispassold.presentation.formatters

import com.chrispassold.domain.models.BankAccountType

object BankAccountTypeFormatter {
    fun format(bankAccountType: BankAccountType): String {
        return when (bankAccountType) {
            BankAccountType.CHECKING_ACCOUNT -> "Checking Account"
            BankAccountType.SAVING_ACCOUNT -> "Saving Account"
        }
    }
}