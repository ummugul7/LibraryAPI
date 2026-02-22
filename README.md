# Library API

Spring Boot ile geliştirilmiş basit bir kütüphane yönetim REST API'si. Yazarlar ve kitaplar üzerinde CRUD işlemleri, arama ve filtreleme sunar.

## Tech Stack

- **Java 21**
- **Spring Boot 3.4**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Lombok**
- **Bean Validation**

## Gereksinimler

- JDK 21+
- Maven 3.8+
- Docker & Docker Compose (veritabanı için)

## Kurulum ve Çalıştırma

### 1. Veritabanını Başlat

```bash
docker-compose up -d
```

Bu komut PostgreSQL ve pgAdmin container'larını başlatır:
- PostgreSQL: `localhost:5433`
- pgAdmin: `http://localhost:15432` (admin@admin.com / admin123)

### 2. Uygulamayı Çalıştır

```bash
./mvnw spring-boot:run
```

Uygulama `http://localhost:8080` adresinde ayağa kalkar.

## API Endpoints

### Author

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| POST | `/author/save` | Yeni yazar ekle |
| GET | `/author/list` | Tüm yazarları listele |
| GET | `/author/detail?firstname=&lastname=` | Yazar detayı (kitaplarla birlikte) |
| DELETE | `/author/delete` | Yazar sil (JSON body) |

### Book

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| POST | `/book/addbook` | Yeni kitap ekle |
| GET | `/book/list` | Tüm kitapları listele |
| GET | `/book/search?name=` | İsme göre kitap ara |
| GET | `/book/best` | En yüksek puanlı kitaplar |
| PUT | `/book/update?name=` | Kitap güncelle |
| DELETE | `/book/delete?name=` | Kitap sil |

## Proje Yapısı

```
src/main/java/com/ummugul/libraryapi/
├── Controller/     # REST API controller'ları
├── Model/          # JPA Entity'ler (Author, Book)
├── Service/        # İş mantığı katmanı
├── Repository/     # Veri erişim katmanı
└── dto/            # Data Transfer Objects
```

## Veritabanı

- **Host:** localhost
- **Port:** 5433
- **Database:** library_db
- **User:** admin
- **Password:** admin123
