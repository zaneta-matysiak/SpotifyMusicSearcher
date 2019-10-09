package pl.zanettj.musicSearcher.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import static java.util.Objects.nonNull;

//@Configuration
//@EnableReactiveMongoRepositories
//@EnableConfigurationProperties(MongoProperties.class)
//@RequiredArgsConstructor
//@Slf4j
@AllArgsConstructor
public class MongoConfiguration {

//    private final MongoProperties mongoProperties;
//    private final ConfigurableEnvironment env;


//    protected String getDatabaseName() {
//        return mongoProperties.getMongoClientDatabase();
//    }

//    public @Bean MongoClient mongoClient() {
//        return new MongoClient("localhost");
//    }
//
//    public @Bean
//    MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoClient(), "mydatabase");
//    }

}//extends AbstractMongoConfiguration {

//    private final MongoProperties mongoProperties;
//    private final ConfigurableEnvironment env;
//
//    @Override
//    protected String getDatabaseName() {
//        return mongoProperties.getMongoClientDatabase();
//    }
//
//    @Override
//    @Bean(destroyMethod = "close")
//    public MongoClient reactiveMongoClient() {
//        Integer localPort = env.getProperty("local.mongo.port", Integer.class);
//
//        if (nonNull(localPort)) {
//            log.debug("Found embedded Mongo running on port: {}.", localPort);
//            return MongoClients.create(String.format("mongodb://localhost:%d/%s", localPort, getDatabaseName()));
//        }
//
//        return MongoClients.create(mongoProperties.determineUri());
//    }
//
//    @Profile("embedded")
//    @Configuration
//    @Import({EmbeddedMongoAutoConfiguration.class})
//    public static class EmbeddedMongoConfiguration {
//    }

//    @Override
//    protected String getDatabaseName() {
//        return "test";
//    }
//
//    @Override
//    public MongoClient mongoClient() {
//        return new MongoClient("127.0.0.1", 27017);
//    }
//
//    @Override
//    protected String getMappingBasePackage() {
//        return "org.baeldung";
//    }

