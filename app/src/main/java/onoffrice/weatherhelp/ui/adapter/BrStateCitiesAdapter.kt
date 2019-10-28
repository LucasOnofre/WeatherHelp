package onoffrice.weatherhelp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_common_list_item.view.*
import onoffrice.weatherhelp.R
import onoffrice.weatherhelp.data.remote.models.CityResume

class BrStateCitiesAdapter (
    private val listener:CityClickListener

): RecyclerView.Adapter<BrStateCitiesAdapter.StateViewHolder>() {

    interface CityClickListener {
        fun onStateClicked(selectedCity: CityResume)
    }

    var list: List<CityResume> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StateViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_common_list_item, parent, false)
        return StateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class StateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CityResume) {
            itemView.apply {

                itemName.text = item.name

                setOnClickListener {
                   listener.onStateClicked(item)
                }
            }
        }
    }
}

