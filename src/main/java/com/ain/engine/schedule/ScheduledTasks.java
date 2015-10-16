package com.ain.engine.schedule;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@Log4j2
public class ScheduledTasks {

    @Autowired
    private RestTemplate csosRestTemplate;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /* ======================================================================== */
    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime() {

        HttpHeaders headers = new HttpHeaders();;
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        String postJsonData = "{" +
                        "\"id\": 0," +
                        "\"category\": {" +
                            "\"id\": 0," +
                            "\"name\": \"string\"" +
                        "}," +
                        "\"name\": \"doggie\"," +
                        "\"photoUrls\": [" +
                        "\"string\"" +
                        "]," +
                        "\"tags\": [" +
                            "{" +
                            "\"id\": 0," +
                            "\"name\": \"string\"" +
                            "}" +
                        "]," +
                        "\"status\": \"available\"" +
                        "}";

        HttpEntity<String> entity = new HttpEntity<String>(postJsonData, headers);
        String result = csosRestTemplate.postForObject("http://petstore.swagger.io/v2/pet", entity, String.class);
        log.info(result);
        log.info("The time is now " + dateFormat.format(new Date()));
    }

    @Scheduled(initialDelay=1000, fixedRate=10000)
    public void logPrintTask() {
        String result = csosRestTemplate.getForObject("http://petstore.swagger.io/v2/pet/findByStatus?status=available", String.class);
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info(result);
        log.info("something that should execute periodically");
    }
    /* ======================================================================== */

    // @Scheduled(cron = "0 */15 * * * *")
    // public void demoServiceMethod()
    // {
    //     log.info("Method executed at every 5 seconds. Current time is :: " + new Date());
    // }

    /* ========================================================================
    @Scheduled(cron = "0 0 0 15-30 5 ?")
    public void sendReminderEmailsJune() {
        log.info("Midnight every day from 15th June until end of month");
    }

    @Scheduled(cron = "0 0 0 * 6 ?")
    public void sendReminderEmailsJuly() {
        log.info("Every day in July");
    }

    @Scheduled(cron = "0 0 0 1-15 7 ?")
    public void sendRemindersEmailsAugust() {
        log.info("The first day in August to 15th August");
    }

     ======================================================================== */
}