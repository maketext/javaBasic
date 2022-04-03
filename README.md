#####ChangUtils 클래스
자바 언어로 개발을 할 경우 이 헬퍼 (유틸) 클래스를 이용하여 생산성을 높인다.

######streamToString
- InputStream에서 스트림 데이터를 읽어 String 타입 값을 리턴하는 함수

######streamToStringUTF_8
- OutputStream과 쓸 문자열을 파라미터로 넘기면 써 주는 함수

######getDaysBetween
- LocalDate 타입 끼리의 비교
######wrapNullCallback
- 옵셔널 대체
##### 콘솔창 한글 깨짐 문제
- 그레이들 또는 메이븐의 캐릭터 셋 설정이 필요하다.
###### Gradle의 경우
```
compileJava.options.encoding = 'UTF-8'

tasks.withType(JavaCompile) {
options.encoding = 'UTF-8'
}
```