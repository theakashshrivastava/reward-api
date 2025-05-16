package com.rewards.controller;

import com.rewards.exception.NoTransactionFoundException;
import com.rewards.model.RewardResponse;
import com.rewards.model.Transaction;
import com.rewards.service.RewardService;
import com.rewards.util.DummyDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @Autowired
    private DummyDataUtil  dummyDataUtil;

    @GetMapping("/dummy")
    public List<RewardResponse> getRewardsFromDummyData() {
        List<Transaction> transactions =
                dummyDataUtil.getDummyTransactions();
        return rewardService.calculateRewards(transactions);
    }

    @PostMapping
    public ResponseEntity<List<RewardResponse>> getRewards(@RequestBody List<Transaction> transaction) {
        if(transaction == null || transaction.isEmpty()) {
            throw new NoTransactionFoundException("Transaction list is empty");
        }
        return ResponseEntity.ok(rewardService.calculateRewards(transaction));
    }
}
