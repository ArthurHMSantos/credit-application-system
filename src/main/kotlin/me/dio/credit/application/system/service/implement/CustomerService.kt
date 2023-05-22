package me.dio.credit.application.system.service.implement

import me.dio.credit.application.system.entities.Customer
import me.dio.credit.application.system.repository.CustomerRepo
import me.dio.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepo: CustomerRepo
): ICustomerService {
    override fun register(customer: Customer): Customer =
        this.customerRepo.save(customer)

    override fun findById(id: Long): Customer =
        this.customerRepo.findById(id).orElseThrow {
            throw RuntimeException("Id $id not found")
    }

    override fun delete(id: Long) =
        this.customerRepo.deleteById(id)
}