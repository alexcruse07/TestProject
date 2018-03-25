import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Test2 {

  public static void main(String[] args) {
    Date date = new Date();
    System.out.println(date);
    System.out.println(Instant.now().toString() );
    System.out.println(Calendar.getInstance().getTime());
  }

}
