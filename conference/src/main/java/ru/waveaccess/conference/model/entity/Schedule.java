package ru.waveaccess.conference.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "room_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Room room;

    @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm")
    @Column(name = "start_date")
    private Timestamp startDate;

    @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm")
    @Column(name = "end_date")
    private Timestamp endDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "presentation_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Presentation presentation;
}
