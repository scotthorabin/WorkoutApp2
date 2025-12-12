package ViewModel

import Domain.CategoryModel
import Domain.ItemsModel
import Repository.MainRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val repository= MainRepository()

    // Mutable least for categories, MutableList so we can change things
    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }

    fun loadPopular(): LiveData<MutableList<ItemsModel>>{
        return repository.loadPopular()
    }

    fun loadItems(categoryId: String) = repository.loadItemsCategory(categoryId)

}