package example.micronaut;

import example.micronaut.storage.User;
import jakarta.inject.Singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class Service {

    private final Map<User, Long> userTransaction = new ConcurrentHashMap<>();

    public void transferMoney(User user, String transferId, long amount) {
        if (!user.checkSufficentBalance(amount)) throw new RuntimeException("Insufficient balance");
        userTransaction.compute(user, (k, v) -> {
            return v == null ? amount : v + amount;
        });
        user.setBalance(user.getBalance() - amount);
    }

    public Map<User, Long>getUserTransaction() { // <3>
        return userTransaction;
    }
}
