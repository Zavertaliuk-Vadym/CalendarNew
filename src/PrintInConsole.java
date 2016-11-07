import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Created by employee on 11/4/16.
 */
class PrintInConsole {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final String GREEN_TEXT_START_TOKEN = (char) 27 + "[32m";
    private static final String EXT_END_TOKEN = (char) 27 + "[0m";
    private static final String RED_TEXT_START_TOKEN = (char) 27 + "[31m";

    static String printCalendarInConsole(int[] weekends, int monthStartWithThisDate, int[][] a, int nowDay) {
        return printCalendarHeader(weekends, monthStartWithThisDate) +
                printCalendarArray(a, nowDay, weekends, monthStartWithThisDate);
    }

    public static String printCalendarArray(int[][] a, int currentDay, int[] weekends, int monthStart) {
        StringBuilder printerCalendarArray = new StringBuilder();
        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                if (a[i][j] == 0) {
                    printerCalendarArray.append("    ");
                    continue;
                }
                if (a[i][j] == currentDay)
                    printerCalendarArray.append(String.format(GREEN_TEXT_START_TOKEN + "%4d" + EXT_END_TOKEN, a[i][j]));
                else if (j == weekends[0] - monthStart || j == weekends[1] - monthStart)
                    printerCalendarArray.append(String.format(RED_TEXT_START_TOKEN + "%4d" + EXT_END_TOKEN, a[i][j]));
                else {
                    printerCalendarArray.append(String.format("%4d", a[i][j]));
                }
            }
            printerCalendarArray.append("\n");
        }
        return printerCalendarArray.toString();
    }

    public static String printCalendarHeader(int[] weekends, int firstDaySelectedMonth) {
        StringBuilder printerCalendarHeader = new StringBuilder();
        for (int i = firstDaySelectedMonth; i < DAYS_IN_WEEK + firstDaySelectedMonth; i++) {
            if (i == weekends[0] || i == weekends[1]) {
                printerCalendarHeader.append(String.format(RED_TEXT_START_TOKEN + "%4s" + EXT_END_TOKEN, WeekFields.of(Locale.UK)
                        .getFirstDayOfWeek()
                        .plus(i)
                        .getDisplayName(TextStyle.SHORT, Locale.UK)
                        .toUpperCase()));
            } else {
                printerCalendarHeader.append(String.format("%4s", WeekFields.of(Locale.UK)
                        .getFirstDayOfWeek()
                        .plus(i)
                        .getDisplayName(TextStyle.SHORT, Locale.UK)
                        .toUpperCase()));

            }
        }
        printerCalendarHeader.append("\n");
        return printerCalendarHeader.toString();
    }
}
