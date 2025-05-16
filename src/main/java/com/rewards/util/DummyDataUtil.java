package com.rewards.util;

import com.rewards.model.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DummyDataUtil {
    public List<Transaction> getDummyTransactions() {
        LocalDate date = LocalDate.now();
        return Arrays.asList(
                new  Transaction("CUST001",120.0, date.minusDays(11)),
                new  Transaction("CUST001",120.0, date.minusDays(55)),
                new  Transaction("CUST002",120.0, date.minusDays(300)),
                new  Transaction("CUST002",120.0, date.minusDays(90)),
                new  Transaction("CUST003",120.0, date.minusDays(48)),
                new  Transaction("CUST004",120.0, date.minusDays(71))
        );
    }
}
