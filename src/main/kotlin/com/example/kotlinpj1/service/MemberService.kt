package com.example.kotlinpj1.service

import com.example.kotlinpj1.domain.model.Member
import com.example.kotlinpj1.domain.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun findById(id: String): Member? = memberRepository.findById(id)
}
