package com.example.kotlinpj1.service

import com.example.kotlinpj1.domain.model.Member
import com.example.kotlinpj1.domain.repository.MemberRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class MemberServiceTest : BehaviorSpec({

    val memberRepository = mockk<MemberRepository>()
    val memberService = MemberService(memberRepository)

    // C0: メンバーが見つかる場合
    given("リポジトリにメンバーが存在する場合") {
        val expected = Member("001", "Alice")
        every { memberRepository.findById("001") } returns expected

        `when`("IDで検索すると") {
            val result = memberService.findById("001")

            then("メンバーが返される") {
                result shouldBe expected
                verify(exactly = 1) { memberRepository.findById("001") }
            }
        }
    }

    // C1: メンバーが見つからない場合
    given("リポジトリにメンバーが存在しない場合") {
        every { memberRepository.findById("999") } returns null

        `when`("存在しないIDで検索すると") {
            val result = memberService.findById("999")

            then("nullが返される") {
                result.shouldBeNull()
                verify(exactly = 1) { memberRepository.findById("999") }
            }
        }
    }
})
