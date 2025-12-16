package br.com.article.optimisticlock.inventory.repository

import br.com.article.optimisticlock.inventory.model.Inventory

interface InventoryRepository {

    fun findById(inventoryId: String): Inventory?

    fun save(inventory: Inventory): Inventory

}