package me.dio.credit.application.system.service.implementation

import me.dio.credit.application.system.entities.Address
import me.dio.credit.application.system.entities.Customer
import me.dio.credit.application.system.exception.BusinessException
import me.dio.credit.application.system.repository.CustomerRepository
import me.dio.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)

    override fun findById(id: Long): Customer = this.customerRepository.findById(id)
        .orElseThrow{throw BusinessException("Id $id not found") }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }

     fun buildCustomer(
        firstName: String = "Arthur",
        lastName: String = "Santos",
        cpf: String = "115088819962",
        email: String = "arthur@gmail.com",
        password: String = "123456",
        zipCode: String = "297",
        street: String = "Rua Manoel Graciliano de Souza",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
    )
}