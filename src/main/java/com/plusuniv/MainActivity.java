package com.plusuniv;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity {
    public boolean localDateTest() {
        LocalDate currentDate = LocalDate.now();
        LocalDate targetDate = LocalDate.of(2022,3,31);

        if(currentDate.isBefore(currentDate.plusDays(2)))
            return true;
        if(currentDate.isAfter(currentDate.minusDays(2)))
            return true;

        if(currentDate.isEqual(currentDate))
            return true;

        // second, nanoSecond 파라미터는 선택사항
        LocalDateTime targetDateTime = LocalDateTime.of(2022, 3, 31, 1, 0);
        return false;
    }

    public static void main(String[] args) {
        ChangUtils.Crypto.Key.set("F130C02F55C5C7AABFE51907E4065A7426B52B6".substring(0, 32));
        ChangUtils.Crypto.IV.set("F130C02F55C5C7AABFE51907E4065A7426B52B6".substring(0, 16));
        try {
            ChangUtils.Crypto.INSTANCE.encryptText("ASDF");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ChangUtils().wrapNullCallback(null, () -> {
            System.out.println("이 객체는 NULL 값이다.");
        });

        List<String> list = new ArrayList<String>();
            ListIterator<String> iterator = list.listIterator();
            while(iterator.hasNext()) {
                //iterator.hasPrevious();
                //iterator.previous();
                System.out.println(iterator.next());
            }
    }
}
