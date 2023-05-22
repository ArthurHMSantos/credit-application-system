package me.dio.credit.application.system.service

import me.dio.credit.application.system.entities.Credit
import java.util.UUID

interface ICreditService {
    fun register(credit: Credit ): Credit
    fun findAllByCustomer(customerId: Long): List<Credit>
    fun findByCreditCode(customerId: Long, creditCode: UUID): Credit
}