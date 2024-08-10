# Java Security 회원가입 로그인

## 목적
 - Spring Security를 활용한 JWT 기반 회원가입 로그인 기능 구현을 목적으로 하며 Junit을 활용하여 Test Code를 작성하는 것을 목표로 한다.
 - Swagger UI를 활용하여 api 명세 및 테스트 활용할 수 있도록 한다. 
 - 최종적으로 개발된 application은 빌드하여 서버에 배포하는 것을 목표로 한다.

## api 명세

### 회원가입

#### Request Message

```json
{
  "username": "JIN HO",
  "password": "12341234",
  "nickname": "Mentos"
}
```

#### Response Message

```json
{
  "username": "JIN HO",
  "nickname": "Mentos",
  "authorities": [
    {
      "authorityName": "ROLE_USER"
    }
  ]
}
```
### 로그인

#### Request Message

```json
{
  "username": "JIN HO",
  "password": "12341234"
}
```

#### Response Message

```json
{
  "token": "eKDIkdfjoakIdkfjpekdkcjdkoIOdjOKJDFOlLDKFJKL",
}
```