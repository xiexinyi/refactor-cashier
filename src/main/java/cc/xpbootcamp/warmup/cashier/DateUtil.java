package cc.xpbootcamp.warmup.cashier;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

  private Map<DayOfWeek, String> dayOfWeekMap = new HashMap<>();

  DateUtil() {
    dayOfWeekMap.put(DayOfWeek.MONDAY, "星期一");
    dayOfWeekMap.put(DayOfWeek.TUESDAY, "星期二");
    dayOfWeekMap.put(DayOfWeek.WEDNESDAY, "星期三");
    dayOfWeekMap.put(DayOfWeek.THURSDAY, "星期四");
    dayOfWeekMap.put(DayOfWeek.FRIDAY, "星期五");
    dayOfWeekMap.put(DayOfWeek.SATURDAY, "星期六");
    dayOfWeekMap.put(DayOfWeek.SUNDAY, "星期日");
  }

  public String getDateAsString() {
    return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
  }

  public String getDateInWeekAsString() {
    return dayOfWeekMap.get(LocalDate.now().getDayOfWeek());
  }
}
