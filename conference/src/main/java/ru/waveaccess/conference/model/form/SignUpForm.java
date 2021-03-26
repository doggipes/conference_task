package ru.waveaccess.conference.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpForm {
    private String name;
    private String surname;
    private String email;
    private String password;
}
