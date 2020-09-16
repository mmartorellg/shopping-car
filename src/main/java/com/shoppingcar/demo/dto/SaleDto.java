package com.shoppingcar.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaleDto {
    private Long id;
    private ZonedDateTime date;
    private Long clientId;
    private String clientName;

}
