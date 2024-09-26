import java.util.TimeZone;

public class Timezones {
    public static void main(String[] args) {
        TimeZone tz = TimeZone.getTimeZone("America/New_York");
        System.out.println(tz);
    }
}
