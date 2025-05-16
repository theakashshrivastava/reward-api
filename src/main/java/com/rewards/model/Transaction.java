package com.rewards.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String customerId;
    private Double amount;
    private LocalDate transactionDate;

}
