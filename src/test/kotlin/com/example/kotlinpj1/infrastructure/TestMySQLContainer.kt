package com.example.kotlinpj1.infrastructure

import org.testcontainers.containers.MySQLContainer

/**
 * テスト用 MySQL コンテナのシングルトン定義
 *
 * object = Kotlin のシングルトン。JVM 内で1インスタンスのみ。
 * 複数のテストクラスから参照しても、コンテナは1つだけ起動される。
 */
object TestMySQLContainer {
    val instance: MySQLContainer<*> = MySQLContainer("mysql:8.0").apply {
        withDatabaseName("sample1")
        withUsername("test")
        withPassword("test")
        start()
    }
}
