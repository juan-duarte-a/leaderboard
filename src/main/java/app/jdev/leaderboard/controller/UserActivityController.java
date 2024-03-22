package app.jdev.leaderboard.controller;

import app.jdev.leaderboard.entity.UserActivity;
import app.jdev.leaderboard.service.KafkaProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
public class UserActivityController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public UserActivityController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public void publishActivity(@RequestBody UserActivity activity) throws JsonProcessingException {
        kafkaProducerService.sendUserActivity(activity);
    }

}
