package com.arthas.lefrango.ui.menu.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arthas.lefrango.R
import com.arthas.lefrango.data.menu.home.Restaurants
import com.arthas.lefrango.databinding.SliderRestaurantItemBinding


class SliderAdapter(private val restaurantsList: List<Restaurants>) :
    RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    class ViewHolder(val itemLayoutBinding: SliderRestaurantItemBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutBinding = DataBindingUtil.inflate<SliderRestaurantItemBinding>(
            LayoutInflater.from(parent.context), R.layout.slider_restaurant_item, parent, false
        )

        return ViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemLayoutBinding.imageSlide.setImageResource(restaurantsList[position].restaurantsIcon)
        holder.itemLayoutBinding.ivRestaurantImageView.setImageURI(restaurantsList[position].restaurantsStars.toUri())
        holder.itemLayoutBinding.tvRestaurantName.text = restaurantsList[position].restaurantsName

        holder.itemLayoutBinding.cvNeu.setOnClickListener {
            holder.itemLayoutBinding.cvNeu.visibility = View.GONE
            holder.itemLayoutBinding.foodList.root.visibility = View.VISIBLE
            holder.itemLayoutBinding.foodList.ivRestaurantIcon.setImageResource(restaurantsList[position].restaurantsIcon)
            holder.itemLayoutBinding.foodList.tvRestaurantName.text =
                restaurantsList[position].restaurantsStars
        }

    }

    override fun getItemCount(): Int {
        return restaurantsList.size
    }

}