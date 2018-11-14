package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Класс для парсинга вакансий сайта sql.ru.
 */
public class Parser {
    private static final Logger LOGGER = LogManager.getLogger(Parser.class);
    /**
     * Основной метод для парсинга сайта sql.ru.
     */
    public void prseHtml() {
        try (DBConection dbConection = new DBConection()) {
            Timestamp lastTimestmp = getLastTimestamp(dbConection);
            Timestamp timestmp = new Timestamp(System.currentTimeMillis());
            for (int i = 1; timestmp.after(lastTimestmp); i++) {
                Document html = Jsoup.connect("http://www.sql.ru/forum/job-offers/" + i).get();
                Elements elements = html.getElementsByClass("postslisttopic");
                for (Element element : elements) {
                    String text = element.child(0).text();
                    String link = element.child(0).attr("href");
                    Document innerHtml = Jsoup.connect(link).get();
                    Element dateHtml = innerHtml.getElementsByClass("msgFooter").first();
                    String date = dateHtml.text().split("\\[")[0];
                    timestmp = new Timestamp(parseDate(date));
                    if (text.matches("(.*)Java\\W(.*)") && !text.matches("(.*)Java Script(.*)")
                            && timestmp.after(lastTimestmp)) {
                        dbConection.insertData(text, link, timestmp.getTime());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ощибка парсинга сайта");
            LOGGER.error("Ощибка парсинга сайта");
        } catch (Exception e) {
            System.out.println("Ошибка в подключении к базам данных");
            LOGGER.error("Ошибка в подключении к базам данных");
        }
    }

    /**
     * Метод переводит дату с сайт в Timestamp.
     * @param date
     * @return
     */
    private long parseDate(String date) {
        long result = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yy, h:mm");
        Date articleDate = null;
        try {
            articleDate = format.parse(date);
        } catch (ParseException e) {
        }
        if (articleDate != null) {
            result = articleDate.getTime();
        }
        return result;
    }

    /**
     * Метод возвращет из базы данных дату последней записи.
     * @param dbConection - соединение с базой.
     * @return Timestamp.
     */
    private Timestamp getLastTimestamp(DBConection dbConection) {
        Timestamp lastTimestmp = dbConection.lastDate();
        if (lastTimestmp == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2018, 0, 1);
            lastTimestmp = new Timestamp(calendar.getTimeInMillis());
        }
        return lastTimestmp;
    }
}
