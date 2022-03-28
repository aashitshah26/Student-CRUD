package intr.intrprac.intrpractsk.utils

import android.widget.EditText

class Constants {

   companion object{
       const val DATABASE_NAME = "database_student"
   }

}

fun EditText.validateEmpty() : Boolean {
    return text.isNullOrBlank().not()
}