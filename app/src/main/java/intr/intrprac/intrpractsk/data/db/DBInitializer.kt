package intr.intrprac.intrpractsk.data.db

import android.content.Context
import androidx.room.Room
import intr.intrprac.intrpractsk.utils.Constants
import java.lang.RuntimeException

class DBInitializer(mcontext : Context) {


    private val studentDatabase : StudentDatabase
    private val context : Context = mcontext


     companion object {
        private var instance : DBInitializer? = null

         fun initDb(context: Context){
             if (instance == null) {
                 instance = DBInitializer(context)
             }
         }

        fun getInstance(): DBInitializer {
            if (instance!=null){
                return instance!!
            }else {
                throw RuntimeException("Init first")
            }
        }
    }

    init {
        studentDatabase = Room.databaseBuilder(context,StudentDatabase::class.java,Constants.DATABASE_NAME)
            .build()
    }

    fun getDatabase() : StudentDatabase{
        return studentDatabase
    }

}