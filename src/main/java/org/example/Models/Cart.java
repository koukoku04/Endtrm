package org.example.Models;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    private int order_id;
    private String login;
    private int car_id;
}
