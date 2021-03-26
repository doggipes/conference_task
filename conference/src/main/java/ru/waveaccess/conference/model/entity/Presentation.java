package ru.waveaccess.conference.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "presentation")
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "presentations", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<User> users;
    @OneToOne(mappedBy = "presentation")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Schedule schedule;
}
