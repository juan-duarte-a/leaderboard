package app.jdev.leaderboard.service;

import app.jdev.leaderboard.entity.UserActivity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final NewTopic userActivitiesTopic;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, NewTopic userActivitiesTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.userActivitiesTopic = userActivitiesTopic;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public void sendUserActivity(UserActivity activity)
            throws JsonProcessingException, ExecutionException, InterruptedException {
        CompletableFuture<SendResult<String, String>> sendResult =
                kafkaTemplate.send(userActivitiesTopic.name(), objectMapper.writeValueAsString(activity));
        log.info("Publish user activity: {}", sendResult.get().toString());
    }

}
