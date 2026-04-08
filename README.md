# kotlin-pj1

Kotlin ベースの REST API のサンプルプロジェクト。

## プロジェクト構成

### 言語・フレームワーク

| 項目 | バージョン |
|------|-----------|
| Kotlin | 2.3.10 |
| JDK | Amazon Corretto 25 |
| Spring Boot | 4.0.5 |
| MyBatis Spring Boot Starter | 4.0.1 |
| Gradle | 9.2.0 |

### テスト関連

| 項目 | バージョン |
|------|-----------|
| Kotest | 5.9.1 |
| MockK | 1.13.13 |
| SpringMockK | 4.0.2 |
| Testcontainers | 1.20.4 |

### DB・コンテナ

| 項目 | 詳細 |
|------|------|
| DB | MySQL 8.0 (Docker) |
| ポート | 3307 (ホスト) -> 3306 (コンテナ) |
| データベース名 | sample1 |
| テーブル | member (id VARCHAR(3), name VARCHAR(100)) |

### ディレクトリ構成

```
src/main/kotlin/com/example/kotlinpj1/
├── Application.kt
├── controller/          # プレゼンテーション層
├── service/             # アプリケーション層
├── domain/              # ドメイン層 (モデル, リポジトリIF)
└── infrastructure/      # インフラ層 (MyBatis マッパー, リポジトリ実装)

src/main/resources/
├── application.yml
└── com/example/kotlinpj1/infrastructure/mapper/
    └── MemberMapper.xml  # MyBatis XML マッパー (インターフェースと同パッケージ構成)
```

## ビルド方法

### 前提条件

- JDK 25 (Amazon Corretto 25)
- Docker Desktop (起動済み)

### DB 起動

```bash
docker-compose up -d
```

### ビルド

```bash
./gradlew build
```

### アプリケーション起動

```bash
./gradlew bootRun
```

### API 実行例

```bash
# メンバー取得
curl http://localhost:8080/api/members/001

# バリデーションエラー (IDが4文字以上)
curl http://localhost:8080/api/members/1234
```

## テスト方法

### テスト実行

```bash
./gradlew test
```

### テスト構成

テストフレームワークは Kotest を採用。各レイヤーごとにテストを実装している。

| レイヤー | テストクラス | Kotest スタイル |
|---------|------------|----------------|
| Controller | MemberControllerTest | FunSpec |
| Service | MemberServiceTest | BehaviorSpec (Given/When/Then) |
| Infrastructure | MemberMapperTest | FunSpec |

- **Controller テスト**: MockMvc + MockK でサービス層をモック化
- **Service テスト**: MockK でリポジトリをモック化した純粋なユニットテスト
- **Infrastructure テスト**: Testcontainers で MySQL コンテナを自動起動し、実際の DB に対してテスト（TestMySQLContainer シングルトンで全テストクラス共有）

> Infrastructure テストは Docker Desktop が起動している必要がある（Testcontainers が MySQL コンテナを自動起動する）。
