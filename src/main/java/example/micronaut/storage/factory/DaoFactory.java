package example.micronaut.storage.factory;

import com.datastax.oss.driver.api.core.CqlSession;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Factory
@Requires(bean = CqlSession.class)
public class DaoFactory {

    @Inject
    DaoMapper mapper;

//    @Bean
//    @Singleton
//    public ServiceConfigDao serviceConfigDao(){
//        return mapper.getServiceConfigDao();
//    }

//    @Bean
//    @Singleton
//    public ServiceGroupDao utilityGroupDao(){
//        return mapper.getUtilityGroupDao();
//    }

//    @Bean
//    @Singleton
//    public CardDao cardDao(){
//        return mapper.getCardDao();
//    }


//    @Bean
//    @Singleton
//    public DownStreamDao downStreamDao(){
//        return mapper.getDownStreamDao();
//    }

//    @Bean
//    @Singleton
//    public UtilityTransactionDao utilityTransactionDao(){
//        return mapper.getUtilityTransactionDao();
//    }

//
//    @Bean
//    @Singleton
//    public UserToServiceDao serviceToUserDao(){
//        return mapper.getServiceToUserDao();
//    }

}
