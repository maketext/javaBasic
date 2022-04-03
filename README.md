### ChangUtils 클래스
자바 언어로 개발을 할 경우 이 헬퍼 (유틸) 클래스를 이용하여 생산성을 높인다.

##### Crypto Enum 클래스
AES256 헬퍼 클래스이다.
###### 사용 예
```
ChangUtils.Crypto.Key.set("F130C02F55C5C7AABFE51907E4065A7426B52B6".substring(0, 32));
ChangUtils.Crypto.IV.set(ChangUtils.Crypto.Key.get().substring(0, 16));

System.out.println(ChangUtils.Crypto.INSTANCE.encryptText("ASDF"));
System.out.println(ChangUtils.Crypto.INSTANCE.decryptText("cHqGnqRZn/kgvVqE6tIqNg=="));
```

##### ChangUtils 클래스
자바 언어로 개발을 할 경우 이 헬퍼 (유틸) 클래스를 이용하여 생산성을 높인다.

##### streamToString
- InputStream에서 스트림 데이터를 읽어 String 타입 값을 리턴하는 함수
```
public String streamToString(InputStream inputStream) throws IOException {
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[512];
    for (int length; (length = inputStream.read(buffer)) != -1; )
        result.write(buffer, 0, length);
    inputStream.close();
    return result.toString(StandardCharsets.UTF_8.name());
}
```
##### streamToStringUTF_8
- OutputStream과 쓸 문자열을 파라미터로 넘기면 써 주는 함수
```
public void streamToStringUTF_8(OutputStream outputStream, String toWrite) throws IOException {
    outputStream.write(toWrite.getBytes(StandardCharsets.UTF_8));
    outputStream.close();
}
```
##### getDaysBetween
- LocalDate 타입 끼리의 비교
##### wrapNullCallback
- 옵셔널 대체
```
void wrapNullCallback(Object object, Runnable callbackFunction) {
    if(object == null) callbackFunction.run();
}
```
##### 콘솔창 한글 깨짐 문제
- 그레이들 또는 메이븐의 캐릭터 셋 설정이 필요하다.
###### Gradle의 경우
```
compileJava.options.encoding = 'UTF-8'

tasks.withType(JavaCompile) {
options.encoding = 'UTF-8'
}
```