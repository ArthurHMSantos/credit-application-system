package me.dio.credit.application.system.dto

import me.dio.credit.application.system.entities.Credit
import java.math.BigDecimal
import java.util.UUID

data class CreditViewList (
    val creditcode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallments: Int,)
{
    constructor(credit: Credit) : this(
        creditcode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallments = credit.numberOfInstallments)
}
