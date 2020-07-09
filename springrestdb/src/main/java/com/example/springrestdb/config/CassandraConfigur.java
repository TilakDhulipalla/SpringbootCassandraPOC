package com.example.springrestdb.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.PlainTextAuthProvider;




@Configuration
@PropertySource(value= {"classpath:application.properties"})
@EnableCassandraRepositories({"com.example.springrestdb.repository"})
//@EnableAutoConfiguration
public class CassandraConfigur extends AbstractCassandraConfiguration {
	private static final Logger log = LoggerFactory.getLogger(CassandraConfigur.class);
	

	  @Value("${spring.data.cassandra.keyspaceName}")
	  private String keyspaceName;

	  @Value("${spring.data.cassandra.contactPoints}")
	  private String contactPoints;

	  @Value("${spring.data.cassandra.username}")
	  private String username;

	  @Value("${spring.data.cassandra.password}")
	  private String password;

	  @Value("${spring.data.cassandra.sslEnabled}")
	  private boolean sslEnabled;

		@Override
		protected String getKeyspaceName() {
			// TODO Auto-generated method stub
			return keyspaceName;
		}
	
		  @Override
		  protected String getContactPoints() {
		    return contactPoints;
		  }
		  @Override
		  protected AuthProvider getAuthProvider() {
			    return new PlainTextAuthProvider(username, password);
			  }

		  @Override
		  public SchemaAction getSchemaAction() {
		    return SchemaAction.CREATE_IF_NOT_EXISTS;
		  }

		  @Override	
		  @Bean
		  public CassandraClusterFactoryBean cluster() {
		        final CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		        log.info("Check");
		        cluster.setSslEnabled(sslEnabled);
		        cluster.setJmxReportingEnabled(false);
		        cluster.setContactPoints(contactPoints);
		        cluster.setAuthProvider(getAuthProvider());
		        
		        log.info("Cluster created with contact points  port:{}",contactPoints);
		        return cluster;
		    }
	

	




	
}
