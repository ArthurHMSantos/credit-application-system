package me.dio.credit.application.system.dto

import jakarta.validation.constraints.Email
import me.dio.credit.application.system.entities.Address
import me.dio.credit.application.system.entities.Customer
import java.math.BigDecimal
import java.net.PasswordAuthentication

data class CustomerDTO (
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val password: String,
    val zipCode: String,
    val street: String,
    val number: String,
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street,
        )
    )

}
