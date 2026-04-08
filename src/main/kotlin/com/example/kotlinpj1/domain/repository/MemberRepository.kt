package com.example.kotlinpj1.domain.repository

import com.example.kotlinpj1.domain.model.Member

interface MemberRepository {
    fun findById(id: String): Member?
}
