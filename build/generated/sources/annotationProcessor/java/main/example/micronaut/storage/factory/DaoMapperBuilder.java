package example.micronaut.storage.factory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.mapper.MapperBuilder;
import com.datastax.oss.driver.internal.mapper.DefaultMapperContext;
import java.lang.Override;
import java.lang.SuppressWarnings;

/**
 * Builds an instance of {@link DaoMapper} wrapping a driver {@link CqlSession}.
 *
 * <p>Generated by the DataStax driver mapper, do not edit directly.
 */
@SuppressWarnings("all")
public class DaoMapperBuilder extends MapperBuilder<DaoMapper> {
  public DaoMapperBuilder(CqlSession session) {
    super(session);
  }

  @Override
  public DaoMapper build() {
    DefaultMapperContext context = new DefaultMapperContext(session, defaultKeyspaceId, defaultExecutionProfileName, defaultExecutionProfile, customState);
    return new DaoMapperImpl__MapperGenerated(context);
  }
}