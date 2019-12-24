package com.example.demoapprecycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapprecycleview.databinding.RowHomeCoursesBinding
import com.example.demoapprecycleview.model.HomeDataResponse
import com.stlacademy.home.interfaces.OnCourseItemClickListener

class HomeCourseAdapter : RecyclerView.Adapter<HomeCourseAdapter.ViewHolder>() {


    private lateinit var homeSubCourseAdapter: HomeSubCourseAdapter
    private lateinit var coursesList: MutableList<HomeDataResponse.CoursesData>
    private lateinit var onCourseItemClickListener: OnCourseItemClickListener

    fun setData(
        coursesList: MutableList<HomeDataResponse.CoursesData>,
        onCourseItemClickListener: OnCourseItemClickListener
    ) {
        this.coursesList = coursesList
        this.onCourseItemClickListener = onCourseItemClickListener
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RowHomeCoursesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowHomeCoursesBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        homeSubCourseAdapter = HomeSubCourseAdapter()
        coursesList[position].coursesInfo?.let {
            homeSubCourseAdapter.setData(
                it,
                onCourseItemClickListener
            )
            holder.binding.courseDataName = coursesList[position]

//            holder.binding.rvSubCourseList.addItemDecoration(MarginItemDecoration(20))
            holder.binding.rvSubCourseList.adapter = homeSubCourseAdapter

            holder.binding.txtLblCategoryTitle.setOnClickListener {
                onCourseItemClickListener.onViewAllClick(coursesList[position].courseShowAll)
            }
        }


    }

    override fun getItemCount(): Int {
        return coursesList.size
    }
}