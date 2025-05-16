package com.rewards.util;

import com.rewards.model.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardCalculatorTest {
    private final RewardCalculator rewardCalculator = new RewardCalculator();

    @Test
    void testCalculatePoints(){
        Transaction transaction =  new Transaction(
                "CUST001",120.0, LocalDate.now());
        int points = rewardCalculator.calculatePoints(transaction.getAmount());
        assertEquals(90,points);
    }
}
