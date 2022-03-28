package intr.intrprac.intrpractsk.ui.mainscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import intr.intrprac.intrpractsk.R
import intr.intrprac.intrpractsk.data.db.entity.Student
import intr.intrprac.intrpractsk.databinding.ItemStudentRvBinding

class AdapterStudent(private val context: Context) : ListAdapter<Student,AdapterStudent.ViewHolder>(StudentDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemStudentRvBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_student_rv_,parent,false)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)
        holder.bind(position, getItem(position))
    }

    class ViewHolder(val binding : ItemStudentRvBinding) : RecyclerView.ViewHolder(binding.root) {
        private var previousExpandedPosition = -1
        private var mExpandedPosition  = -1

        fun bind(pos : Int , model : Student){

            binding.tvStuName.text = model.stuName
            binding.stuId.text = "Student Id : "+model.sr_no
            binding.gender.text =when( model.stuGender){
                0->{"Gender : Male" }
            else->{"Gender : Female"}
        }



            val isExpanded : Boolean= pos == mExpandedPosition;
            binding.expandableLayout.visibility = (if (isExpanded) View.VISIBLE else View.GONE)

            if (isExpanded)
                previousExpandedPosition = pos



            binding.mainParent.setOnClickListener {
                mExpandedPosition = if (isExpanded) -1 else pos
            }
        }

    }

}