import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class ChangUtils {
    public String streamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        for (int length; (length = inputStream.read(buffer)) != -1; )
            result.write(buffer, 0, length);
        inputStream.close();
        return result.toString(StandardCharsets.UTF_8.name());
    }
    public void streamToStringUTF_8(OutputStream outputStream, String toWrite) throws IOException {
        outputStream.write(toWrite.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
    public int getDaysBetween(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        period.normalized(); // 1달 1일 == 32일, 31일 둘 다 가능
        return (int) ChronoUnit.DAYS.between(startDate, endDate); // only  32일

    }
    void wrapNullCallback(Object object, Runnable callbackFunction) {
        if(object == null) callbackFunction.run();
    }
}
