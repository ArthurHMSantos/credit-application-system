package me.dio.credit.application.system.entities

import jakarta.persistence.*
import me.dio.credit.application.system.Status
import java.math.BigDecimal
import java.util.*
import java.time.LocalDate

@Entity
@Table(name = "tb_credit")
data class Credit (
    @Column(nullable = false, unique = true) val creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) val creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val dayFirstInstallment: LocalDate = LocalDate.now(),
    @Column(nullable = false) val numberOfInstallments: Int = 0,
    @Enumerated val status: Status = Status.PENDING,
    @ManyToOne val customer: Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null)