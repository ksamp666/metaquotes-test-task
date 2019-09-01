package Utils;

import Pages.EventPage.HistoryTableRowComponent;
import io.qameta.allure.Step;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static Utils.Log.getLogger;
import static Utils.TestUtils.getYearMonthObjectFromPeriod;

public class HistoryTablePrinter {
    public static void printTable(List<HistoryTableRowComponent> historyItems, YearMonth start, YearMonth end) {
        List<String> tableRows = createTable(historyItems, start, end);
        printTableToLog(tableRows);
    }

    @Step("Generate history table")
    private static List<String> createTable(List<HistoryTableRowComponent> historyItems, YearMonth start, YearMonth end) {
        getLogger().info("Generating history table");
        List<String> tableRows = new ArrayList<>();
        final String loggerTemplate = "| %s | %s | %s | %s |";
        tableRows.add("History table:");
        tableRows.add(String.format(loggerTemplate, "Date", "Actual", "Forecast", "Previous"));
        historyItems.forEach(item -> {
            YearMonth itemPeriod = getYearMonthObjectFromPeriod(item.getPeriod(), "LLL yyyy");
            if(itemPeriod.compareTo(start)>=0 && itemPeriod.compareTo(end)<0) {
                tableRows.add(String.format(loggerTemplate, item.getDate(), item.getActual(), item.getForecast(), item.getPrevious()));
            }
        });

        return tableRows;
    }

    @Step("Print history table")
    private static void printTableToLog(List<String> tableRows) {
        getLogger().info("Printing history table.");
        tableRows.forEach(row -> {
            getLogger().info(row);
        });
    }
}