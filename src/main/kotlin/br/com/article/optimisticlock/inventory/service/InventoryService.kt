package br.com.article.optimisticlock.inventory.service

import br.com.article.optimisticlock.inventory.model.Inventory
import br.com.article.optimisticlock.inventory.repository.InventoryRepository
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException

@Service
class InventoryService(
    private val inventoryRepository: InventoryRepository
) {

    fun decrementInventory(productId: String): Inventory {
        val inventory = inventoryRepository.findById(productId)
            ?: throw InventoryNotFoundException("Inventory not found for product ID: $productId")
        inventory.quantity = inventory.quantity - 1
        inventory.version = 100 // Simulating a version change for demonstration purposes
        return try {
            inventoryRepository.save(inventory)
        } catch (e: ConditionalCheckFailedException) {
            throw InventoryConflictException("Inventory conflict for product ID: $productId")
        }
    }
}