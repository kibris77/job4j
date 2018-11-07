package ru.job4j.parser;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Класс опредляет задние для планировщика фреймворка Quartz.
 */
public class JobParser implements Job {
    private final Parser parser = new Parser();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        parser.prseHtml();
    }
}
