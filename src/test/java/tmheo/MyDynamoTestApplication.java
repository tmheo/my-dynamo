package tmheo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.model.*;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taemyung on 2015. 10. 24..
 */
@SpringBootApplication
@EnableDynamoDBRepositories
public class MyDynamoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyDynamoTestApplication.class, args);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        System.setProperty("java.library.path", "./DynamoDBLocal_lib");

        try {
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AmazonDynamoDB amazonDynamoDB = DynamoDBEmbedded.create();

        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("id")
                .withAttributeType("S"));

        List<KeySchemaElement> keySchema = new ArrayList<>();
        keySchema.add(new KeySchemaElement()
                .withAttributeName("id")
                .withKeyType(KeyType.HASH));

        CreateTableRequest request = new CreateTableRequest()
                .withTableName("person")
                .withKeySchema(keySchema)
                .withAttributeDefinitions(attributeDefinitions)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(5L));

        amazonDynamoDB.createTable(request);

        return amazonDynamoDB;
    }

}
