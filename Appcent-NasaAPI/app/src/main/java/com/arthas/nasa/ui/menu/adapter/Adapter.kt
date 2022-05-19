package com.arthas.nasa.ui.menu.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.arthas.nasa.R
import com.arthas.nasa.data.model.photos.Photos


class Adapter :
    PagingDataAdapter<Photos, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Photos>() {
            override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(item = getItem(position))
        holder.itemView.setOnClickListener {

            val dialog = Dialog(it.context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_dialog);


            val close: ImageView = dialog.findViewById(R.id.ivClose)
            val ivShip: ImageView = dialog.findViewById(R.id.ivShip)
            val tvDate: TextView = dialog.findViewById(R.id.tvDate)
            val tvShipName: TextView = dialog.findViewById(R.id.tvShipName)
            val tvCam: TextView = dialog.findViewById(R.id.tvCam)
            val tvStatus: TextView = dialog.findViewById(R.id.tvStatus)
            val tvFlightDate: TextView = dialog.findViewById(R.id.tvFlightDate)


            ivShip.load(getItem(position)?.img_src) {
                placeholder(R.drawable.ic_spaceshipleft)
            }
            tvDate.text = getItem(position)?.earth_date
            tvShipName.text = getItem(position)?.rover?.name
            tvCam.text =
                "${getItem(position)?.camera?.full_name} / ${getItem(position)?.camera?.name}"
            tvStatus.text = getItem(position)?.rover?.status
            tvFlightDate.text =
                "${getItem(position)?.rover?.launch_date} / ${getItem(position)?.rover?.landing_date}"


            close.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.images_list_row_items, parent, false)
        return ViewHolder(view)

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var ivNasaMain: ImageView = view.findViewById(R.id.ivNasaMain)

        fun bind(item: Photos?) {
            //loads image from network using coil extension function
            ivNasaMain.load(item?.img_src) {
                placeholder(R.drawable.ic_spaceshipleft)
            }
        }

    }


}