package example.micronaut;

import example.micronaut.storage.User;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.Acknowledgement;
import io.micronaut.messaging.annotation.MessageBody;
import io.micronaut.messaging.annotation.MessageHeader;

@KafkaClient
public interface Producer {

    @Topic("money")
    void sendMoney(@KafkaKey String userId, User user, @MessageHeader String transferId, @MessageHeader String amount);


    @Topic("message")
    void sendMessage(@MessageBody  String message);
}
