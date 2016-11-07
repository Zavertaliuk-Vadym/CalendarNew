import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

class PrintInWeb {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final String RED_TEXT_START_TOKEN_IN_WEB = "<font style ='color:red;'>";
    private static final String TEXT_END_TOKEN_IN_WEB = "</font>";
    private static final String GREEN_TEXT_START_TOKEN_IN_WEB = "<font style ='color:gold;'>";
    private static final String OPEN_TAG_TABLE_COLUMN = "<td>";
    private static final String CLOSE_TAG_TABLE_COLUMN = "</td>";
    private static final String OPEN_TAG_TABLE_ROW = "<tr>";
    private static final String CLOSE_TAG_TABLE_ROW = "</tr>";

    static String printCalendarInWeb(int[] weekends, int monthStartWithThisDate, int[][] a, int nowDay) {
        return printHeaderHTML() +
                printCalendarHeader(weekends, monthStartWithThisDate) +
                printCalendarArray(a, nowDay, weekends, monthStartWithThisDate) +
                printDownHTML();
    }


    private static String printCalendarArray(int[][] a, int day, int[] weekends, int monthStartWithThisDate) {
        StringBuilder printerCalendarArray = new StringBuilder();
        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            printerCalendarArray.append(OPEN_TAG_TABLE_ROW);
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                if (a[i][j] == 0) {
                    printerCalendarArray.append(OPEN_TAG_TABLE_COLUMN + "    " + CLOSE_TAG_TABLE_COLUMN);
                    continue;
                }
                if (a[i][j] == day)
                    printerCalendarArray.append(String.format(OPEN_TAG_TABLE_COLUMN + GREEN_TEXT_START_TOKEN_IN_WEB + "%4d" + TEXT_END_TOKEN_IN_WEB + CLOSE_TAG_TABLE_COLUMN, a[i][j]));
                else if (j == weekends[0] - monthStartWithThisDate || j == weekends[1] - monthStartWithThisDate) {
                    printerCalendarArray.append(String.format(OPEN_TAG_TABLE_COLUMN + RED_TEXT_START_TOKEN_IN_WEB + "%4d" + TEXT_END_TOKEN_IN_WEB + CLOSE_TAG_TABLE_COLUMN, a[i][j]));
                } else {
                    printerCalendarArray.append(String.format(OPEN_TAG_TABLE_COLUMN + "%4d" + CLOSE_TAG_TABLE_COLUMN, a[i][j]));
                }
            }
            printerCalendarArray.append("\n" + CLOSE_TAG_TABLE_ROW);
        }
        return printerCalendarArray.toString();
    }

    private static String printCalendarHeader(int[] weekends, int dayStartWithThisDate) {
        int j = 0;
        StringBuilder printerCalendarHeader = new StringBuilder();
        printerCalendarHeader.append(OPEN_TAG_TABLE_ROW);
        for (int i = dayStartWithThisDate; i < DAYS_IN_WEEK + dayStartWithThisDate; i++) {
            if (i == weekends[0] || i == weekends[1]) {
                printerCalendarHeader.append(String.format(OPEN_TAG_TABLE_COLUMN + RED_TEXT_START_TOKEN_IN_WEB + "%4s" + TEXT_END_TOKEN_IN_WEB + CLOSE_TAG_TABLE_COLUMN, WeekFields.of(Locale.UK)
                        .getFirstDayOfWeek()
                        .plus(i)
                        .getDisplayName(TextStyle.SHORT, Locale.UK)
                        .toUpperCase()));
            } else {
                printerCalendarHeader.append(String.format(OPEN_TAG_TABLE_COLUMN + "%4s" + CLOSE_TAG_TABLE_COLUMN, WeekFields.of(Locale.UK)
                        .getFirstDayOfWeek()
                        .plus(i)
                        .getDisplayName(TextStyle.SHORT, Locale.UK)
                        .toUpperCase()));
            }
            j++;
        }
        printerCalendarHeader.append(CLOSE_TAG_TABLE_ROW);
        return printerCalendarHeader.toString();
    }

    private static String printHeaderHTML() {

        return "<Html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>";
    }

    private static String printDownHTML() {

        return "</table>\n" +
                "</body>\n" +
                "</Html>";
    }
}