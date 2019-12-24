package com.example.demoapprecycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapprecycleview.databinding.RowSubCourseBinding
import com.example.demoapprecycleview.model.HomeDataResponse
import com.stlacademy.home.interfaces.OnCourseItemClickListener

class HomeSubCourseAdapter : RecyclerView.Adapter<HomeSubCourseAdapter.ViewHolder>() {

    private lateinit var coursesInfo: List<HomeDataResponse.CoursesData.CoursesInfo>
    private lateinit var onCourseItemClickListener: OnCourseItemClickListener

    fun setData(
        coursesInfo: List<HomeDataResponse.CoursesData.CoursesInfo>,
        onCourseItemClickListener: OnCourseItemClickListener
    ) {
        this.coursesInfo = coursesInfo
        this.onCourseItemClickListener = onCourseItemClickListener
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RowSubCourseBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowSubCourseBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.courseInfo = coursesInfo[position]

        holder.binding.clCourseItem.setOnClickListener {
            onCourseItemClickListener.onCourseItemClick(position, coursesInfo[position].courseId)
        }
    }

    override fun getItemCount(): Int {
        return coursesInfo.size
    }
}