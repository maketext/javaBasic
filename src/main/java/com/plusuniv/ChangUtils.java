package com.plusuniv;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class ChangUtils {
    public MeasureTimeSpan measureMills = new MeasureTimeSpan();
    public class MeasureTimeSpan {
        long startTimeMills = 0L;
        long endTimeMills = 0L;
        long getTimeMills() {
            if(endTimeMills - startTimeMills < 0)
                return 0;
            return endTimeMills - startTimeMills;
        }
        long getTimeSecond() {
            return getTimeMills() / 1000;
        }
        void setTime() {
            if(startTimeMills == 0L)
                startTimeMills = Instant.now().toEpochMilli();
            else
                endTimeMills = Instant.now().toEpochMilli();
        }
    }
    public void print(String text) {
        System.out.println(text);
    }
    public String dateNow() {
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
        return localDate.toString();
    }
    public String dateTimeNow() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    @FunctionalInterface
    interface FunctionParam {
        void run() throws Exception;
    }

    public int tryFunction(FunctionParam function) {
        try {
            function.run();
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }
    private FunctionParam fn1, fnJWTValidation;
    public void setFunction1(FunctionParam fn1) {
        this.fn1 = fn1;
    }
    public void setJWTValidationFunction(FunctionParam fnJWTValidation) {
        this.fnJWTValidation = fnJWTValidation;
    }
    public int tryFunction1() {
        try {
            fn1.run();
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }
    public int tryJWTValidation() {
        try {
            fnJWTValidation.run();
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }
    public String inputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        for (int length; (length = inputStream.read(buffer)) != -1; )
            result.write(buffer, 0, length);
        inputStream.close();
        return result.toString(StandardCharsets.UTF_8.name());
    }
    public void stringToOutputStream(OutputStream outputStream, String toWrite) throws IOException {
        outputStream.write(toWrite.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
    public int getDaysBetween(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        period.normalized(); // 1달 1일 == 32일, 31일 둘 다 가능
        return (int) ChronoUnit.DAYS.between(startDate, endDate); // only  32일
    }
    public void wrapNullCallback(Object object, Runnable callbackFunction) {
        if(object == null) callbackFunction.run();
    }

}
