package me.dio.credit.application.system.service.implement

import me.dio.credit.application.system.entities.Credit
import me.dio.credit.application.system.repository.CreditRepository

import me.dio.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepo: CreditRepository,
    private val customerService: CustomerService
) : ICreditService  {
    override fun register(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepo.save(credit)
    }
    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepo.findAllByCustomerId(customerId)
    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = this.creditRepo.findByCreditCode(creditCode)
            ?: throw RuntimeException("Credit code $creditCode not found")
        return if (credit.customer?.id == customerId) credit
        else throw RuntimeException("This is not your credit")
    }
}