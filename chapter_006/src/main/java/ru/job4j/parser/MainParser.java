package ru.job4j.parser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Основной класс программы запускающий парсинг сайта по распианию.
 */
public class MainParser {
    public static void main(String[] args) throws SchedulerException {
        try {
            PropLoader.loadProps(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Стандартный файл с Properties");
        }
        String cronexpresssion = PropLoader.cron;
        JobDetail job = JobBuilder.newJob(JobParser.class).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronexpresssion)).build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
