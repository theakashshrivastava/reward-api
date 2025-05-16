package com.rewards.service;

import com.rewards.model.RewardResponse;
import com.rewards.model.Transaction;
import com.rewards.util.RewardCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class RewardService {

    @Autowired
    private RewardCalculator rewardCalculator;

    public List<RewardResponse> calculateRewards(List<Transaction> transactions) {
        Map<String, Map<String,Integer>> customerMonthlyPoints = new HashMap<>();
        LocalDate threeMonthAgo = LocalDate.now().minusMonths(3);

        for(Transaction transaction : transactions){
            if(transaction.getTransactionDate().isBefore(threeMonthAgo)){
                continue;
            }
            String customerId =  transaction.getCustomerId();
            int points = rewardCalculator.calculatePoints(transaction.getAmount());
            String month = transaction.getTransactionDate()
                    .getMonth()
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

            customerMonthlyPoints.computeIfAbsent(customerId, k -> new HashMap<>())
                    .merge(month, points, Integer::sum);
        }

        List<RewardResponse> rewardResponses = new ArrayList<>();
        for(Map.Entry<String, Map<String,Integer>> entry : customerMonthlyPoints.entrySet()) {
            String customerId = entry.getKey();
            Map<String,Integer> monthlyPoints = entry.getValue();
            int totalPoints = monthlyPoints
                    .values().stream().mapToInt(Integer::intValue).sum();

            rewardResponses.add(new RewardResponse(customerId,monthlyPoints,totalPoints));
        }
        return rewardResponses;
    }

}
