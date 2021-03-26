package ru.waveaccess.conference.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "room_id")
    private Room room;

    @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm")
    @Column(name = "start_date")
    private Timestamp startDate;

    @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm")
    @Column(name = "end_date")
    private Timestamp endDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "presentation_id")
    private Presentation presentation;
}
