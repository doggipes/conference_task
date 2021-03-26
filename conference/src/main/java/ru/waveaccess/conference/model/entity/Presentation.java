package ru.waveaccess.conference.model.entity;

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
    private List<User> users;

    @OneToOne(mappedBy = "presentation",fetch = FetchType.LAZY)
    private Schedule schedule;
}
