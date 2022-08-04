package example.micronaut;

import example.micronaut.storage.User;
import example.micronaut.storage.UserRepo;
import io.micronaut.configuration.kafka.annotation.*;
import io.micronaut.messaging.Acknowledgement;
import io.micronaut.messaging.annotation.MessageBody;
import io.micronaut.messaging.annotation.MessageHeader;
import jakarta.inject.Inject;

import java.util.Arrays;

//@Requires(notEnv = Environment.TEST)
@KafkaListener(groupId = "send-money",offsetReset = OffsetReset.EARLIEST, offsetStrategy = OffsetStrategy.DISABLED)
public class Listener {

    private final Service service;
    private final UserRepo userRepo;

    @Inject
    public Listener(Service service, UserRepo userRepo) { // <3>
        this.service = service;
        this.userRepo = userRepo;
    }

    @Topic("money")
    public void transfer(@KafkaKey String userId, User user, @MessageHeader String transferId, @MessageHeader  String amount, Acknowledgement acknowledgement) {
        System.out.println(userRepo.getUsersFromIds(Arrays.asList("100","200")));
//        service.transferMoney(user, transferId, Long.parseLong(amount));
//        userRepo.insertUser(user);
//        System.out.println("User------------------------------------------------------->"+" "+user.getName());
//        System.out.println("Balance"+" "+user.getBalance());
//        System.out.println("trans"+" "+ service.getUserTransaction());
        acknowledgement.ack();
    }

    @Topic("message")
    public  void receiveMessage(@MessageBody  String msg){
//        System.out.println(userRepo.getUsers());

        System.out.println("message received---------------------------------------------------------------------------------------> : " +msg);
    }
}
