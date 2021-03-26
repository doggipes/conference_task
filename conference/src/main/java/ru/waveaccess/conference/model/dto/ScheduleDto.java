package ru.waveaccess.conference.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.waveaccess.conference.model.form.ScheduleForm;
import ru.waveaccess.conference.util.DateAndTimeParser;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDto {

    public ScheduleDto(ScheduleForm scheduleForm){
        this.room = scheduleForm.getRoom();
        this.presentation = scheduleForm.getPresentation();
        this.startDate = DateAndTimeParser.parseStringToTimestamp(scheduleForm.getStartDate());
        this.endDate = DateAndTimeParser.parseStringToTimestamp(scheduleForm.getEndDate());
    }

    private String room;
    private String presentation;
    private Timestamp startDate;
    private Timestamp endDate;
}
