package ru.teamscore;

import org.hibernate.Session;
import ru.teamscore.common.utils.HibernateUtil;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Подключено к БД!");

        }
    }
}