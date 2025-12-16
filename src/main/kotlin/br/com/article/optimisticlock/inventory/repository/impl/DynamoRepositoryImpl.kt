package br.com.article.optimisticlock.inventory.repository.impl

import br.com.article.optimisticlock.inventory.model.Inventory
import br.com.article.optimisticlock.inventory.repository.InventoryRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Repository
class DynamoRepositoryImpl(
    @Value("\${aws.dynamodb.table.inventory}") private val inventoryTableName: String,
    private val client: DynamoDbEnhancedClient
) : InventoryRepository {

    override fun findById(inventoryId: String): Inventory? {
        val key = Key.builder()
            .partitionValue(inventoryId)
            .build()

        return client.table(
            inventoryTableName,
            TableSchema.fromBean(Inventory::class.java)
        ).getItem(key)
    }

    override fun save(inventory: Inventory): Inventory {
        client.table(
            inventoryTableName,
            TableSchema.fromBean(Inventory::class.java)
        ).putItem(inventory)
        return inventory
    }
}