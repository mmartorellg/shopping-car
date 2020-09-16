package com.shoppingcar.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(builderMethodName = "newInstance")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto {
    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private String phone;
    private String email;
}
