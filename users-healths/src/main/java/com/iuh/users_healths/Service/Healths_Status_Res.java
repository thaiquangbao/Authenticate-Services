package com.iuh.users_healths.Service;

import com.iuh.users_healths.Dtos.Reponse.Health_Status;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class Healths_Status_Res {
    public Health_Status heathIsGood() {
        Health_Status health_status = new Health_Status();
        health_status.setHealth_condition("Normal");
        health_status.setStatus("Good");
        health_status.setMessage_suggest("Your health is good, Please maintain it!!!");
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        health_status.setCreated_at(dateTime.toString().substring(0, 16));
        return health_status;
    }
    public Health_Status heathIsNotGood() {
        Health_Status health_status = new Health_Status();
        health_status.setHealth_condition("Abnormal");
        health_status.setStatus("Error");
        health_status.setMessage_suggest("Your health is so bad, You should take care of your Health!!!");
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        health_status.setCreated_at(dateTime.toString().substring(0, 16));
        return health_status;
    }
    public Health_Status heartbeatFast() {
        Health_Status health_status = new Health_Status();
        health_status.setHealth_condition("Abnormal");
        health_status.setStatus("Heartbeat is bad");
        health_status.setMessage_suggest("Your heartbeat is fast, You should breathe regularly!!!");
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        health_status.setCreated_at(dateTime.toString().substring(0, 16));
        return health_status;
    }
    public Health_Status heartbeatSlow() {
        Health_Status health_status = new Health_Status();
        health_status.setHealth_condition("Abnormal");
        health_status.setStatus("Heartbeat is bad");
        health_status.setMessage_suggest("Your heartbeat is slow, You should breathe regularly!!!");
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        health_status.setCreated_at(dateTime.toString().substring(0, 16));
        return health_status;
    }
    public Health_Status bloodPressureIsHigh() {
        Health_Status health_status = new Health_Status();
        health_status.setHealth_condition("Abnormal");
        health_status.setStatus("BloodPressure is high");
        health_status.setMessage_suggest("Your bloodPressure is high; You should do exercise, eat well, down caffeine and down stress!!!");
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        health_status.setCreated_at(dateTime.toString().substring(0, 16));
        return health_status;
    }
    public Health_Status bloodPressureIsShort() {
        Health_Status health_status = new Health_Status();
        health_status.setHealth_condition("Abnormal");
        health_status.setStatus("BloodPressure is short");
        health_status.setMessage_suggest("Your bloodPressure is short; You should do exercise, drink tea and drink salt water!!!");
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        health_status.setCreated_at(dateTime.toString().substring(0, 16));
        return health_status;
    }
}
