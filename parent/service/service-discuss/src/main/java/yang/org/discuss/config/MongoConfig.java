package yang.org.discuss.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LvXueYang
 * @description mongodb配置类
 */
@EnableMongoRepositories(basePackages = "yang.org.discuss.dao")
@Configuration
@ComponentScan(basePackages = { "yang.org.discuss" })
public class MongoConfig extends AbstractMongoConfiguration {
    @Value("${spring.mongodb.host}")
    private String host;
    @Value("${spring.mongodb.username}")
    private String username;
    @Value("${spring.mongodb.password}")
    private String password;
    @Value("${spring.mongodb.database}")
    private String database;
    @Value("${spring.mongodb.port}")
    private Integer port;
    @Value("${spring.mongodb.authentication-database}")
    private String authenticationDatabase;
    @Value("${spring.mongodb.connectionsPerHost}")
    private Integer connectionsPerHost;
    @Value("${spring.mongodb.minConnectionsPerHost}")
    private Integer minConnectionsPerHost;
    @Value("${spring.mongodb.threadsAllowedToBlockForConnectionMultiplier}")
    private Integer threadsAllowedToBlockForConnectionMultiplier;
    @Value("${spring.mongodb.connectTimeout}")
    private Integer connectTimeout;
    @Value("${spring.mongodb.maxWaitTime}")
    private Integer maxWaitTime;
    @Value("${spring.mongodb.socketKeepAlive}")
    private Boolean socketKeepAlive;
    @Value("${spring.mongodb.socketTimeout}")
    private Integer socketTimeout;


    @Override
    public MongoClient mongoClient() {
        ServerAddress address = new ServerAddress(host,port);
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(MongoCredential.createScramSha256Credential(username,authenticationDatabase,password.toCharArray()));
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        builder.connectionsPerHost(connectionsPerHost);
        builder.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier);
        builder.connectTimeout(connectTimeout);
        builder.maxWaitTime(maxWaitTime);
        builder.socketKeepAlive(socketKeepAlive);
        builder.socketTimeout(socketTimeout);
        builder.minConnectionsPerHost(minConnectionsPerHost);
        return new MongoClient(address, credentials, builder.build());
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoClient(),database);
    }
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Override
    protected String getDatabaseName() {
        return database;
    }
}
