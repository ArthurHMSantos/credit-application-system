package me.dio.credit.application.system.controller

import me.dio.credit.application.system.dto.CreditDto
import me.dio.credit.application.system.entities.Credit
import me.dio.credit.application.system.service.implement.CreditService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/credits")
class CreditController (
    private val creditService: CreditService
){
    @PostMapping
    fun registerCredit(@RequestBody creditDto : CreditDto): String {
        val credit: Credit = this.creditService.register(creditDto.toEntity())
        return "Credit ${credit.creditValue} added successfully: ${credit.customer?.firstName}!!!"
    }


}

