package intr.intrprac.intrpractsk.ui.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import intr.intrprac.intrpractsk.data.db.DBInitializer
import intr.intrprac.intrpractsk.data.db.entity.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelMainActivity : ViewModel() {

    private val dbHandler : DBInitializer?
    val emptyState : MutableLiveData<Boolean> = MutableLiveData(false)
    private val dataState : MutableLiveData<StudentListState> = MutableLiveData()
       init {
           dbHandler = DBInitializer.getInstance()
       }

    fun fetchData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dbHandler?.getDatabase()?.studentDao()?.fetchAllStudents()?.let {
                    dataState.postValue(StudentListState.ListLiveData(it))
                }
            }catch (e : Exception){
                dataState.postValue(StudentListState.ShowError(e.message?:""))
            }

        }
    }

    fun updateEmptyState(isEmpty : Boolean){
        emptyState.postValue(isEmpty)
    }

    fun getStateFlow() : LiveData<StudentListState>{
        return dataState
    }

}