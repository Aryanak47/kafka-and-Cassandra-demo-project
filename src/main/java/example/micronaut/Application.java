package example.micronaut;

import example.micronaut.storage.User;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.kafka.common.Uuid;
import org.graalvm.nativebridge.In;

import java.math.BigInteger;
import java.util.ArrayList;

import static io.micronaut.context.env.Environment.DEVELOPMENT;

@Singleton
public class Application {
    private final Producer client;


    public static void main(String[] args) {
        Micronaut.run(Application.class,args);
    }
    public Application(Producer client){
        this.client = client;
    }

    @EventListener
    void startup(StartupEvent startupEvent){
        ArrayList<User> users =  new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String id  = Uuid.randomUuid().toString();
            User user =  new User(id,"user "+i, 500+1,Uuid.randomUuid().toString());
            users.add(user);
        }
        client.sendMoney(users.get(0).getAccountNumber(),users.get(0), users.get(1).getAccountNumber(), "20");
//        client.sendMoney(users.get(2).getAccountNumber(),users.get(2), users.get(2).getAccountNumber(), "20");
//        client.sendMoney(users.get(0).getAccountNumber(),users.get(0), users.get(1).getAccountNumber(), "20");
//        client.sendMoney(users.get(1).getAccountNumber(),users.get(1), users.get(2).getAccountNumber(), "20");
//        client.sendMessage("hello there !");
//        System.out.println("Sending money by--------------------------------->"+" "+users.get(0).getName()+" "+"to"+" "+ users.get(1).getAccountNumber());
//        client.sendMoney();
    }


}
