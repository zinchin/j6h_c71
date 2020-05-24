package application.security;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "application.security",
        				 mongoTemplateRef = "securityMongoTemplate")
public class SecurityDataSourceConfig {
	
	@Primary
	@Bean(name = "securityMongoProperties")
	@ConfigurationProperties("security.spring.data.mongodb")
	public MongoProperties securityDataSourceProperties() {
		return new MongoProperties();
	}
	
	@Primary
    @Bean(name = "securityMongoDbFactory")
    public MongoDbFactory securityMongoDbFactory(@Qualifier("securityMongoProperties") MongoProperties dataSourceProperties){
		return new SimpleMongoClientDbFactory(
    			//MongoClients.create("mongodb://"+dataSourceProperties.getHost()+":"+dataSourceProperties.getPort()),
        		//dataSourceProperties.getDatabase()
        		dataSourceProperties.getUri()
        		);
    }
	
	@Primary
    @Bean(name = "securityMongoTemplate")
    public MongoTemplate securityMongoTemplate(@Qualifier("securityMongoDbFactory") MongoDbFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }
}