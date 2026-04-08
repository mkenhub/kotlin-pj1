package com.example.kotlinpj1.controller

import com.example.kotlinpj1.domain.model.Member
import com.example.kotlinpj1.service.MemberService
import jakarta.validation.constraints.Size
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
@Validated
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/{id}")
    fun getMember(
        @PathVariable @Size(max = 3, message = "idは3文字以内で指定してください") id: String
    ): ResponseEntity<Member> {
        val member = memberService.findById(id)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(member)
    }
}
