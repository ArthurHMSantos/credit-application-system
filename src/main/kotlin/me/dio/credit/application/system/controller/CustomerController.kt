package me.dio.credit.application.system.controller

import me.dio.credit.application.system.dto.CustomerDTO
import me.dio.credit.application.system.dto.CustomerView
import me.dio.credit.application.system.entities.Customer
import me.dio.credit.application.system.service.implement.CustomerService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customer")
class CustomerController (
    private val customerService: CustomerService
) {

    @PostMapping
    fun registerCustomer(@RequestBody customerDTO: CustomerDTO): String {
        val registeredCustomer = this.customerService.register(customerDTO.toEntity())
        return "Customer ${registeredCustomer.firstName} ${registeredCustomer.lastName} registered successfully!"
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CustomerView{
        val customer: Customer = this.customerService.findById(id)
        return CustomerView(customer)
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) {
        this.customerService.delete(id)
    }
}