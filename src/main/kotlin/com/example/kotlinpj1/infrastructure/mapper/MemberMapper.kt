package com.example.kotlinpj1.infrastructure.mapper

import com.example.kotlinpj1.domain.model.Member
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MemberMapper {
    fun findById(id: String): Member?
}
