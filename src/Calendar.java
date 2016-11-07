import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public class Calendar {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;

    public static void main(String[] args) throws IOException {
        int[][] a = new int[6][7];
        int counter;
        LocalDate specificDate = getDate(args);
        //первый день месяца
        int firstDayInMonth = specificDate.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue();


        //Change first day of week
        int firstDayOfCalendar = DayOfWeek.FRIDAY.minus(1).getValue();

        //change weekends
        int[] weekends = {DayOfWeek.MONDAY.plus(firstDayOfCalendar).getValue(), DayOfWeek.TUESDAY.plus(firstDayOfCalendar).getValue()};

        boolean console = true;
        boolean web = true;

        //узнаем количество дней в заданом месяце
        int monthLength = specificDate.lengthOfMonth();

        if (firstDayOfCalendar == 0) {
            counter = 0;
        } else counter = 7 - firstDayOfCalendar;

        //формируем массив
        fillInCalendarArray(a, firstDayInMonth + counter, monthLength);

        ///выводим введенное дату,время
        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);
        if (console && web) {
            try (PrintWriter printWriter = new PrintWriter("text3.html")) {
                printWriter.println(PrintInWeb.printCalendarInWeb(weekends, firstDayOfCalendar, a, specificDate.getDayOfMonth()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(PrintInConsole.printCalendarInConsole(weekends, firstDayOfCalendar, a, specificDate.getDayOfMonth()));
        }
    }

    private static LocalDate getDate(String[] args) {
        LocalDate today = LocalDate.now();
//        System.out.println("!!!!!!!!!!"+today.getDayOfWeek().name());
        if (args.length > 0) {
            try {
                int year = Integer.parseInt(args[0]);
                int month = Integer.parseInt(args[1]);
                int day = Integer.parseInt(args[2]);
                return LocalDate.of(year, Month.of(month), day);
            } catch (Exception e) {
                System.out.println("Pas specific date in arguments using following format: YYYY MM DD");
                System.exit(1);
            }
        }
        return today;
    }

    //формирование массива с днями месяца
    private static void fillInCalendarArray(int[][] a, int dayOfWeek, int dayInMonth) {
        int number = 1;
        for (int i = dayOfWeek - 1; i < DAYS_IN_WEEK; i++) {
            a[0][i] = number;
            number++;
        }
        for (int i = 1; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                a[i][j] = number;
                number++;
                if (number == dayInMonth + 1)
                    return;
            }
        }
    }

}