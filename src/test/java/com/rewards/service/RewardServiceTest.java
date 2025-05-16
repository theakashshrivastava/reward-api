package com.rewards.service;

import com.rewards.model.RewardResponse;
import com.rewards.model.Transaction;
import com.rewards.util.RewardCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RewardServiceTest {

    @InjectMocks
    private RewardService rewardService;

    @Mock
    private RewardCalculator rewardCalculator;

    private Transaction recentTransaction_1;
    private Transaction recentTransaction_2;
    private Transaction oldTransaction_1;

    @BeforeEach
    void setUp(){
        recentTransaction_1 = new Transaction("CUST001",125.0, LocalDate.now().minusDays(12));
        recentTransaction_2 = new Transaction("CUST001",225.0, LocalDate.now().minusDays(37));
        oldTransaction_1 = new Transaction("CUST001",225.0, LocalDate.now().minusMonths(5));
    }

    @Test
    void testRewards_withInThreeMonthsPeriod(){
        List<Transaction> transactions = Arrays.asList(
                recentTransaction_1,recentTransaction_2,oldTransaction_1);
        when(rewardCalculator.calculatePoints(recentTransaction_1.getAmount())).thenReturn(100);
        when(rewardCalculator.calculatePoints(recentTransaction_2.getAmount())).thenReturn(300);

        List<RewardResponse> responses = rewardService.calculateRewards(transactions);
        assertEquals(1,responses.size());
        RewardResponse response = responses.getFirst();
        assertEquals("CUST001",response.getCustomerId());
        assertEquals(2,response.getMonthlyPoints().size());
        assertEquals(400,response.getTotalPoints());
        //Checking number of interactions for the rewardCalculator
        verify(rewardCalculator,times(1)).calculatePoints(recentTransaction_1.getAmount());
        verify(rewardCalculator,times(1)).calculatePoints(recentTransaction_2.getAmount());
        verifyNoMoreInteractions(rewardCalculator);
    }
}
