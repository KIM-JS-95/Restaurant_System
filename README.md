# 레스토랑 예약 api구현
"등록된 레스토랑의 메뉴 확인 부터 예약 서비스 제작"

![map](https://user-images.githubusercontent.com/65659478/112718369-d389e380-8f35-11eb-97b5-f7e9b3b6b5d4.jpg)

사용자(CUSTUMER) / 관리자(ADMIN) / 공통(COMMON) 으로 구분

```bash
implementation project(':eat-common')
```

## Data Base Relation
![DB](https://user-images.githubusercontent.com/65659478/112718366-d258b680-8f35-11eb-9a01-4b6a1f40f2aa.jpg)


## BCrypt 암호화
![BCrypt](https://user-images.githubusercontent.com/65659478/112718346-c40a9a80-8f35-11eb-9b62-f1ece93dbbcb.png)

```bash
import org.springframework.security.crypto.password.PasswordEncoder;
  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  String encodedPassword = passwordEncoder.encode(password);
```

## Spring Security


## HTTP Security


## 인증(Authentication)


## VERSION
```bash
JAVA '15.VER'

GRADLE '6.7.VER'

springBootVersion '2.2.1.RELEASE'
```