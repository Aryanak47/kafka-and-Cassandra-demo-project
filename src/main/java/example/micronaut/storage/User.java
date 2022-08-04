package example.micronaut.storage;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;

import java.math.BigInteger;


@Entity
public class User {


    @PartitionKey
    private  String app_id;

    @PartitionKey(1)
    private  String name;

    @ClusteringColumn
    private long balance;

    private String accountNumber;

    @Creator
    public User(String app_id, String name, long balance, String accountNumber) {
        this.app_id = app_id;
        this.name = name;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public User(){

    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean checkSufficentBalance(long transfer){
        if(this.balance >= transfer) return true;
        return false;

    }

    @Override
    public String toString() {
        return "User{" +
                "app_id='" + app_id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
