package intr.intrprac.intrpractsk.ui.studentaddscreen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import intr.intrprac.intrpractsk.data.db.DBInitializer
import intr.intrprac.intrpractsk.data.db.entity.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class ViewModelStudentAdd : ViewModel() {

    private val dbHandler : DBInitializer?
    private val state : MutableLiveData<AddStudentState> = MutableLiveData()

    init {
        dbHandler = DBInitializer.getInstance()
    }

    fun getStateFlow(): LiveData<AddStudentState> {
        return state
    }

    fun addData(image : String, name : String, gender : Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val dataModel = Student(0,
                name,
                image,
                gender)
            try {
                dbHandler?.getDatabase()?.studentDao()?.insertStudentData(dataModel)
                state.postValue(AddStudentState.StudentAddedSuccessfully(2))
            }catch (e : Exception){
                state.postValue(AddStudentState.StudentAddingFailed(e.message?:""))
            }

        }
    }

}