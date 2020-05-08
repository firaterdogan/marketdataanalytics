package com.marketdataanalytics.service;

public interface MailCommandService {

    void sendMail(String[] to, String subject, String body);
}
