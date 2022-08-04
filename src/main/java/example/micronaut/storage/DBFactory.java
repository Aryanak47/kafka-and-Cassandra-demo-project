package example.micronaut.storage;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import java.net.InetSocketAddress;
import java.util.List;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Factory
public class DBFactory {


    private static final String CREATE_SCHEMA = "CREATE KEYSPACE IF NOT EXISTS util WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}";

    @Bean
    @Singleton
    @Context
    public CqlSession getSession(){
        System.out.println("hello get session ..... cql .....");
        var builder  =  CqlSession.builder();
        builder.addContactPoint(new InetSocketAddress("192.168.1.69", 9042));
        builder.withLocalDatacenter("datacenter1");
        var session = builder.build();
        runCql(session);
        return session;
    }

    private void runCql(CqlSession session){
        createKeyspaceIfNotExist(session);
//       for (String statement : getStatements("utility.cql")) {
//            System.out.println("Executing Statement : " + statement);
//            session.execute(SimpleStatement.newInstance(statement)
//                    .setExecutionProfileName(session.getContext().getConfig().getDefaultProfile().getName()));
//        }
    }

    private void createKeyspaceIfNotExist(CqlSession session){
        session.execute(
                SimpleStatement.newInstance(CREATE_SCHEMA)
                        .setExecutionProfileName(session.getContext()
                                .getConfig().getDefaultProfile().getName()));
        session.execute("USE "+ "util");
    }

    private List<String> getStatements(String fileName){
        try {
            Path path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
            String contents = Files.readString(path);
            System.out.println(contents+"----->"+"contents");
            return Arrays.stream(contents.split(":"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


}
