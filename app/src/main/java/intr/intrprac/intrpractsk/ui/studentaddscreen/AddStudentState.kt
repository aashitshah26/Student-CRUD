package intr.intrprac.intrpractsk.ui.studentaddscreen

sealed class AddStudentState{
    data class StudentAddedSuccessfully(val id : Long) : AddStudentState()
    data class StudentAddingFailed(val reason : String) : AddStudentState()
    data class ShowToast(val data : String) : AddStudentState()
}