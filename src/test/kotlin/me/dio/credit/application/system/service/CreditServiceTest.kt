package me.dio.credit.application.system.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import me.dio.credit.application.system.entities.Address
import me.dio.credit.application.system.entities.Credit
import me.dio.credit.application.system.entities.Customer
import me.dio.credit.application.system.repository.CreditRepository
import me.dio.credit.application.system.service.implementation.CreditService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {
    @MockK
    lateinit var creditRepository: CreditRepository
    @InjectMockKs
    lateinit var creditService: CreditService

    @Test
    fun `should create credit`() {
        //given
        val fakeCustomer: Customer = buildCustomer()
        val fakeCredit: Credit = buildCredit(customer = fakeCustomer)
        every { creditRepository.save(fakeCredit) } returns fakeCredit
        //when
        val actual: Credit = creditService.save(fakeCredit)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.save(fakeCredit) }
    }

    @Test
    fun `should findAllByCustomerId`(){
        //given
        val fakeId: Long = 1L
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        val fakeCredit: Credit = buildCredit(customer = fakeCustomer)
        every { creditRepository.findAllByCustomerId(fakeId) } returns listOf(fakeCredit)
        //when
        val actual: List<Credit> = creditService.findAllByCustomer(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isNotEmpty
        Assertions.assertThat(actual).hasSize(1)
        Assertions.assertThat(actual[0]).isSameAs(fakeCredit)
    }

    @Test
    fun `should not find any credit by customerId`(){
        //given
        val fakeId: Long = 1L
        every { creditRepository.findAllByCustomerId(fakeId) } returns emptyList()
        //when
        val actual: List<Credit> = creditService.findAllByCustomer(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isEmpty()
    }

    @Test
    fun `should find by creditCode`(){
        //given
        val fakeId: Long = 1L
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        val fakeCredit: Credit = buildCredit(customer = fakeCustomer)
        every { creditRepository.findByCreditCode(fakeCredit.creditCode) } returns fakeCredit
        //when
        val actual: Credit = creditService.findByCreditCode(fakeId, fakeCredit.creditCode)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
    }

    @Test
    fun `should not find by creditCode`(){
        //given
        val fakeId: Long = 1L
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        val fakeCredit: Credit = buildCredit(customer = fakeCustomer)
        every { creditRepository.findByCreditCode(fakeCredit.creditCode) } returns null
        //when
        val actual: Credit = creditService.findByCreditCode(fakeId, fakeCredit.creditCode)
        //then
        Assertions.assertThat(actual).isNull()
    }

    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.APRIL, 22),
        numberOfInstallments: Int = 5,
        customer: Customer
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstOfInstallment = dayFirstInstallment,
        numberOfInstallments = numberOfInstallments,
        customer = customer
    )
    private fun buildCustomer(
        firstName: String = "Arthur",
        lastName: String = "Santos",
        cpf: String = "115088819962",
        email: String = "arthur@gmail.com",
        password: String = "123456",
        zipCode: String = "297",
        street: String = "Rua Manoel Graciliano de Souza",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        id: Long = 1L
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