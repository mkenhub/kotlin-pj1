package com.example.kotlinpj1.controller

import com.example.kotlinpj1.domain.model.Member
import com.example.kotlinpj1.service.MemberService
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(MemberController::class)
class MemberControllerTest : FunSpec() {

    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var memberService: MemberService

    init {
        // C0: 正常系 - メンバーが見つかる場合
        test("正常系: 有効なIDでメンバーが存在する場合、メンバー情報を返す") {
            every { memberService.findById("001") } returns Member("001", "Alice")

            mockMvc.perform(get("/api/members/001"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id").value("001"))
                .andExpect(jsonPath("$.name").value("Alice"))
        }

        // C1: メンバーが見つからない場合 -> 404
        test("異常系: メンバーが見つからない場合、404を返す") {
            every { memberService.findById("999") } returns null

            mockMvc.perform(get("/api/members/999"))
                .andExpect(status().isNotFound)
        }

        // C1: バリデーションエラー（id が 4文字以上）-> 400
        test("異常系: IDが3文字を超える場合、400を返す") {
            mockMvc.perform(get("/api/members/1234"))
                .andExpect(status().isBadRequest)
        }
    }
}
