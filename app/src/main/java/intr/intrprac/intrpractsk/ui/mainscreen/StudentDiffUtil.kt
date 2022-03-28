package intr.intrprac.intrpractsk.ui.mainscreen

import androidx.recyclerview.widget.DiffUtil
import intr.intrprac.intrpractsk.data.db.entity.Student

class StudentDiffUtil : DiffUtil.ItemCallback<Student>() {

    override fun areItemsTheSame(oldItem: Student, newItem: Student) = oldItem.sr_no == newItem.sr_no

    override fun areContentsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem
}