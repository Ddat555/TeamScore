import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        List<String> list = List.of("Тестирование производительности",
                "Технический проект, схема",
                "«Узкое место», ограничение",
                "Точка останова (в отладке)",
                "Обратный вызов (функция)",
                "Журнал изменений",
                "База кода, кодовая база",
                "Задание по расписанию",
                "Время простоя",
                "Конечная точка (API)",
                "Фаервол, сетевой экран",
                "Ответвление (проекта)",
                "«Рукопожатие», установление связи",
                "Срочное исправление",
                "Наследование",
                "Интерфейс",
                "Итерация",
                "Хранилище ключей",
                "Посадочная зона (для данных)",
                "Поиск, выборка данных",
                "Промежуточное ПО",
                "Пространство имён",
                "Введение в должность/проект",
                "Открытое программное обеспечение",
                "Накладные расходы",
                "Полезная нагрузка (данные)",
                "Проверка доступности",
                "Плагин, расширение",
                "Прокси-сервер",
                "Очередь",
                "Необработанные данные",
               " Перезагрузка",
                "Рефакторинг",
                "Откат изменений",
                "Среда выполнения",
                "Песочница",
                "Снимок состояния",
                "Исходный код",
                "Режим ожидания",
                "Состояние",
                "Поток (данных)",
                "Ограничение частоты запросов",
                "Таймаут, время ожидания",
                "Токен",
                "Время работы без сбоев",
                "Восходящий поток/источник",
                "Поставщик",
                "Рабочий процесс",
                "Обёртка (код)",
                "Папка"
        );

        Random rnd = new Random();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println(list.get(rnd.nextInt(list.size())));
            scanner.next();

        }
    }
}