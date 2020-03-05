package com.example.demo.mapper;

import com.example.demo.entity.AlarmRecord;

import java.util.List;

public interface AlarmRecordMapper {
    int insertAlarmRecord(AlarmRecord alarmRecord);
    List<AlarmRecord> searchAlarmRecordAll();
    AlarmRecord searchAlarmRecordByID(int almRecID);
    int deleteAlarmRecordByID(int almRecID);
    int updateAlarmRecord(AlarmRecord alarmRecord);
    int deleteAlarmRecordByUserID(int userID);
}
