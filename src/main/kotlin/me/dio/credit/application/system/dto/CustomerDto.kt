package me.dio.credit.application.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.entities.Address
import me.dio.credit.application.system.entities.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "Invalid empty firstName input") val firstName: String,
    @field:NotEmpty(message = "Invalid empty last Name input") val lastName: String,
    @field:NotEmpty(message = "Invalid empty CPF input")
    @field:CPF(message = "Invalid CPF input") val cpf: String,
    @field:NotNull(message = "Invalid null income input") val income: BigDecimal,
    @field:Email(message = "Invalid email input")
    @field:NotEmpty(message = "Invalid empty email input") val email: String,
    @field:NotEmpty(message = "Invalid empty password input") val password: String,
    @field:NotEmpty(message = "Invalid empty zipCode input") val zipCode: String,
    @field:NotEmpty(message = "Invalid empty street input") val street: String
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
            street = this.street
        )
    )
}
