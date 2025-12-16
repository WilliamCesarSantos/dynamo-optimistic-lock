package br.com.article.optimisticlock.inventory.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Configuration
class DynamoConfig(
    @Value("\${aws.region}") private val region: String,
    @Value("\${aws.dynamodb.endpoint}") private val endpoint: String
) {

    @Bean
    fun dynamoDbClient(): DynamoDbClient {
        val builder = DynamoDbClient.builder()
            .region(Region.of(region))
            .credentialsProvider(DefaultCredentialsProvider.create())

        if (endpoint.isNotBlank()) {
            builder.endpointOverride(URI.create(endpoint))
        }

        return builder.build()
    }

    @Bean
    fun dynamoEnhancedClient(dynamoDbClient: DynamoDbClient): DynamoDbEnhancedClient =
        DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient)
            .build()
}
