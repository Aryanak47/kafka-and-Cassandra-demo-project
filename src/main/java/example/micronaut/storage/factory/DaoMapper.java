package example.micronaut.storage.factory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.mapper.MapperBuilder;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;


@Mapper
public interface DaoMapper {

//    @DaoFactory
//    ServiceConfigDao getServiceConfigDao();
//
//    @DaoFactory
//    ServiceGroupDao getUtilityGroupDao();
//
//    @DaoFactory
//    CardDao getCardDao();
//
//
//    @DaoFactory
//    DownStreamDao getDownStreamDao();
//
//    @DaoFactory
//    UtilityTransactionDao getUtilityTransactionDao();
//
//    @DaoFactory
//    UserToServiceDao getServiceToUserDao();
//
//
//    static MapperBuilder<DaoMapper> builder(CqlSession session){
//        return new DaoMapperBuilder(session);
//    }
}