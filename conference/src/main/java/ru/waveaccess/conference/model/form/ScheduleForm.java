package ru.waveaccess.conference.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.waveaccess.conference.model.dto.ScheduleDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleForm {
    private String room;
    private String presentation;
    private String startDate;
    private String endDate;
}