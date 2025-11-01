package Repository

import Domain.CategoryModel
import Domain.ItemsModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener


class MainRepository {

    // Instance to get Firebase data
    private val fireBaseDatabase = FirebaseDatabase.getInstance()


    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        // Holds list of categories retrieved from Firebase
        val listData = MutableLiveData<MutableList<CategoryModel>>()
        // References Category from database
        val ref = fireBaseDatabase.getReference("Category")
        // Looks for changes in database
        ref.addValueEventListener(object : ValueEventListener {
            // Loops through categories and adds to list
            override fun onDataChange(snapshot : DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (listSnapshot in snapshot.children) {
                    val item = listSnapshot.getValue(CategoryModel::class.java)
                    item?.let { list.add(it) }
                }

                listData.value = list
            }
            // Error Handler
            override fun onCancelled(error: DatabaseError) {
                // Return an empty list to LiveData so app doesn't crash
                listData.value = mutableListOf()
            }

        })
        return listData
    }


    fun loadPopular(): LiveData<MutableList<ItemsModel>> {
        // Holds list of categories retrieved from Firebase
        val listData = MutableLiveData<MutableList<ItemsModel>>()
        // References Category from database
        val ref = fireBaseDatabase.getReference("Popular")

        // Looks for changes in database
        ref.addValueEventListener(object : ValueEventListener {
            // Loops through categories and adds to list //
            override fun onDataChange(snapshot : DataSnapshot) {
                // Logs how many popular exercises there are in the database //
                Log.d("FirebaseData", "Snapshot children count: ${snapshot.childrenCount}")
                // mutableList = add or remove elements for flexibility and control //
                val list = mutableListOf<ItemsModel>()
                for (listSnapshot in snapshot.children) {
                    val item = listSnapshot.getValue(ItemsModel::class.java)
                    // Logs how many popular exercises have loaded (for testing)
                    Log.d("FirebaseData", "Exercise loaded: $item")
                    item?.let { list.add(it) }
                }
                // Logs how many popular exercises have loaded (for testing)
                Log.d("FirebaseData", "Loaded ${list.size} exercise items")
                listData.value = list
            }
            // Error Handler
            override fun onCancelled(error: DatabaseError) {
                // Return an empty list to LiveData so app doesn't crash
                listData.value = mutableListOf()
            }

        })
        return listData
    }

    fun loadItemsCategory(categoryId: String): LiveData<MutableList<ItemsModel>> {
        val itemsData = MutableLiveData<MutableList<ItemsModel>>()
        val reference = fireBaseDatabase.getReference("Exercises")
        val query: Query = reference.orderByChild("categoryId").equalTo(categoryId.toDouble())

        query.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list=mutableListOf<ItemsModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ItemsModel::class.java)
                    item?.let { list.add(it)
                    }
                    itemsData.value=list
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                itemsData.value = mutableListOf()
            }

        })
        return itemsData
    }
}