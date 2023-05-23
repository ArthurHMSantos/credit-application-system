package me.dio.credit.application.system.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.entities.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerUpdateDto (
    @field:NotEmpty(message = "Error empty input") val firstName: String,
    @field:NotEmpty(message = "Error empty input") val lastName: String,
    @field:NotNull(message = "Invalid input") val income: BigDecimal,
    @field:NotEmpty(message = "Error empty input") val zipCode: String,
    @field:NotEmpty(message = "Error empty input") val street: String
) {
    fun toEntity(customer : Customer) : Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}