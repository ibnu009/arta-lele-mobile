package com.ibnu.artalele.ui.extra.category.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.CategoryEntity

class CategoryRepositoryImpl(private val categoryDataSource: CategoryDataSource) :
    CategoryRepository {
    override fun getCategories(groupType: String): LiveData<PagingData<CategoryEntity>> =
        categoryDataSource.getCategories(groupType)

    override suspend fun insertCategory(category: CategoryEntity) =
        categoryDataSource.insertCategory(category)

    override suspend fun deleteCategory(id: Int) = categoryDataSource.deleteCategory(id)
}