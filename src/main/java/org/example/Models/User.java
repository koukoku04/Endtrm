package org.example.Models;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int user_id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Boolean gender;
}
