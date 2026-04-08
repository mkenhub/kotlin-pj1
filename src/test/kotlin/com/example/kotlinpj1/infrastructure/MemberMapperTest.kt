package com.example.kotlinpj1.infrastructure

import com.example.kotlinpj1.infrastructure.mapper.MemberMapper
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.jdbc.Sql

@SpringBootTest
@Sql("/sql/init-test.sql")
class MemberMapperTest : FunSpec() {

    override fun extensions() = listOf(SpringExtension)

    companion object {
        @DynamicPropertySource
        @JvmStatic
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { TestMySQLContainer.instance.jdbcUrl }
            registry.add("spring.datasource.username") { TestMySQLContainer.instance.username }
            registry.add("spring.datasource.password") { TestMySQLContainer.instance.password }
        }
    }

    @Autowired
    lateinit var memberMapper: MemberMapper

    init {
        // C0: 存在するIDで検索
        test("存在するIDで検索するとメンバーが返される") {
            val member = memberMapper.findById("001")

            member.shouldNotBeNull()
            member.id shouldBe "001"
            member.name shouldBe "Alice"
        }

        // C0: 別のメンバーも取得できることを確認
        test("別のメンバーもIDで取得できる") {
            val member = memberMapper.findById("002")

            member.shouldNotBeNull()
            member.id shouldBe "002"
            member.name shouldBe "Bob"
        }

        // C1: 存在しないIDで検索 -> null
        test("存在しないIDで検索するとnullが返される") {
            val member = memberMapper.findById("999")

            member.shouldBeNull()
        }
    }
}
