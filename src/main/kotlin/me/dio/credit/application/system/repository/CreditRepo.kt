package me.dio.credit.application.system.repository

import me.dio.credit.application.system.entities.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRepo: JpaRepository<Credit, Long>