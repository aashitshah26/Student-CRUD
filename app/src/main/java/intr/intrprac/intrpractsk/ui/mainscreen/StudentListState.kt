package intr.intrprac.intrpractsk.ui.mainscreen

import androidx.lifecycle.LiveData
import intr.intrprac.intrpractsk.data.db.entity.Student

sealed class StudentListState {
    data class ListLiveData(val students : LiveData<List<Student>>) : StudentListState()
    data class ShowError(val error : String) : StudentListState()
    data class ShowToast(val message : String) : StudentListState()
}