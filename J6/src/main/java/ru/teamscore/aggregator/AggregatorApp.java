package ru.teamscore.aggregator;

import ru.teamscore.common.SensorType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AggregatorApp {

    public static void main(String[] args) {
        if(args.length < 4){
            System.err.println("Должно быть 4 параметра аргументов!");
            return;
        }
        try {
            SensorType sensorType = SensorType.valueOf(args[0]);
            LocalDateTime startTime = LocalDateTime.parse(args[1]);
            LocalDateTime endTime = LocalDateTime.parse(args[2]);
            Interval interval = Interval.valueOf(args[3]);
            String deviceName = args.length > 4 ? args[4] : null;
            AggregatorService aggregatorService = new AggregatorService();
            var result = aggregatorService.getAggregateList(sensorType, startTime, endTime, interval, deviceName);
            if(result != null)
                printResults(result, sensorType);
        }
        catch (IllegalArgumentException e){
            System.err.println("Ошибка параметров: " + e.getMessage());
        }
    }

    public static void printResults(List<AggregationResult> results, SensorType sensorType) {
        System.out.println("\n" + getHeader(sensorType));
        System.out.println("-".repeat(80));

        int total = results.size();
        int printed = 0;
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < total; i++) {
            AggregationResult result = results.get(i);
            String formattedLine = formatResultLine(result, sensorType);
            System.out.println(formattedLine);
            printed++;

            if (printed % 16 == 0 && i < total - 1) {
                System.out.printf("\nВыведено %d строк из %d. Нажмите Enter для продолжения...", printed, total);
                scanner.nextLine();
                System.out.println("\n" + getHeader(sensorType));
                System.out.println("-".repeat(80));
            }
        }

        System.out.printf("\nВыведено %d строк из %d. Конец таблицы==========\n", printed, total);
    }

    private static String getHeader(SensorType type) {
        return switch (type) {
            case LIGHT -> String.format("%-20s %-20s   %-10s", "DEVICE", "DATE", "LIGHT");
            case BAROMETER -> String.format("%-20s %-20s   %-12s", "DEVICE", "DATE", "AIR_PRESSURE");
            case LOCATION -> String.format("%-20s %-20s   %-10s %-10s", "DEVICE", "DATE", "LONGITUDE", "LATITUDE");
            case ACCELEROMETER -> String.format("%-20s %-20s   %-8s %-8s %-8s", "DEVICE", "DATE", "X", "Y", "Z");
        };
    }

    private static String formatResultLine(AggregationResult result, SensorType type) {
        String device = result.getDeviceName();
        String date = result.getDateTime().toString();

        return switch (type) {
            case LIGHT, BAROMETER ->
                    String.format("%-20s %-20s   %-10s", device, date, result.getValue());

            case LOCATION -> {
                String[] parts = result.getValue().split(";");
                String longitude = parts.length > 0 ? parts[0] : "0.0000";
                String latitude = parts.length > 1 ? parts[1] : "0.0000";
                yield String.format("%-20s %-20s   %-10s %-10s",
                        device, date, longitude, latitude);
            }

            case ACCELEROMETER -> {
                String[] parts = result.getValue().split(";");
                String x = parts.length > 0 ? parts[0] : "0.00";
                String y = parts.length > 1 ? parts[1] : "0.00";
                String z = parts.length > 2 ? parts[2] : "0.00";
                yield String.format("%-20s %-20s   %-8s %-8s %-8s",
                        device, date, x, y, z);
            }
        };
    }
}
