package com.example.kotlinpj1.infrastructure.repository

import com.example.kotlinpj1.domain.model.Member
import com.example.kotlinpj1.domain.repository.MemberRepository
import com.example.kotlinpj1.infrastructure.mapper.MemberMapper
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    private val memberMapper: MemberMapper
) : MemberRepository {
    override fun findById(id: String): Member? = memberMapper.findById(id)
}
