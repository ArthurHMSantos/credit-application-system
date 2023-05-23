package me.dio.credit.application.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.entities.Address
import me.dio.credit.application.system.entities.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal
import java.net.PasswordAuthentication

data class CustomerDTO (
    @field:NotEmpty(message = "Error empty input") val firstName: String,
    @field:NotEmpty(message = "Error empty input") val lastName: String,
    @field:CPF(message = "This CPF is invalid") val cpf: String,
    @field:NotNull(message = "Invalid input") val income: BigDecimal,
    @field:Email(message = "Error email input") val email: String,
    @field:NotEmpty(message = "Error empty input") val password: String,
    @field:NotEmpty(message = "Error empty input") val zipCode: String,
    @field:NotEmpty(message = "Error empty input") val street: String,
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
