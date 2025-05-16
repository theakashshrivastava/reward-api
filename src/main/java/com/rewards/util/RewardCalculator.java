package com.rewards.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class RewardCalculator {
    public int calculatePoints(double amount){
        int points = 0;
        if(amount > 100){
            points += (int) ((amount-100)*2);
            points += 50;
        } else if(amount > 50) {
            points += (int) (amount-50);
        }
        return points;
    }
}
