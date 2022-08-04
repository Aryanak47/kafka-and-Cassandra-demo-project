package example.micronaut.storage;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.querybuilder.Literal;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.term.Term;
import io.micronaut.context.annotation.Bean;
import jakarta.inject.Singleton;
import org.graalvm.nativebridge.In;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Singleton
public class UserRepo {

    private final CqlSession session = new DBFactory().getSession();
    private final String TABLE_NAME = "USERS";
    private final String KEY_SPACE = "util";

    public String insertUser(User user) {
        System.out.println("inserting user data");
        RegularInsert insertInto = QueryBuilder.insertInto(TABLE_NAME)
                .value("app_id", QueryBuilder.bindMarker())
                .value("name", QueryBuilder.bindMarker())
                .value("balance", QueryBuilder.bindMarker())
                .value("accountNumber", QueryBuilder.bindMarker());

        SimpleStatement insertStatement = insertInto.build();

        PreparedStatement preparedStatement = session.prepare(insertStatement);

        BoundStatement statement = preparedStatement.bind()
                .setString(0, "hamropatrouser123")
                .setString(1, user.getName())
                .setLong(2, user.getBalance())
                .setString(3, user.getAccountNumber());

        session.execute(statement);
        return user.getApp_id();
    }

    public List<User> getUsersFromIds(List<String> ids){
//        Set<Integer> value = new HashSet<>(Arrays.asList(100, 200));
        Select select = QueryBuilder.selectFrom(KEY_SPACE, "users").all().whereColumn("balance")
                .in(ids.stream().map(QueryBuilder::literal).collect(Collectors.toList())).allowFiltering();
        ResultSet resultSet = session.execute(select.build());
        List<User> users  =  new ArrayList<>();
        List<Row> row = resultSet.all();
        for (Row r: row) {
            User user = new User(r.getString("app_id"),r.getString("name"),r.getLong("balance"),r.getString("accountnumber"));
            users.add(user);
        }
        return users;
    }

    public List<User> getUsers(){
        Select select = QueryBuilder.selectFrom(KEY_SPACE, "users").all();
        ResultSet resultSet = session.execute(select.build());
        List<User> users  =  new ArrayList<>();
        Row row = resultSet.one();
        while (row != null){
            User user = new User(row.getString("app_id"),row.getString("name"),row.getLong("balance"),row.getString("accountnumber"));
            users.add(user);
            row = resultSet.one();
        }
        return users;
    }
}
