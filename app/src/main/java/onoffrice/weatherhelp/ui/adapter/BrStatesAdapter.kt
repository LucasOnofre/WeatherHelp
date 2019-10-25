package onoffrice.weatherhelp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_state_item.view.*
import onoffrice.weatherhelp.R


class BrStatesAdapter (
    private val listener:StateClickListener

): RecyclerView.Adapter<BrStatesAdapter.StateViewHolder>() {

    interface StateClickListener {
        fun onStateClicked(selectedState: String)
    }

    var list: MutableList<String> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StateViewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_state_item, parent, false)

        return StateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class StateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String) {
            itemView.apply {

                stateName.text = item

                setOnClickListener {
                   listener.onStateClicked(item)
                }
            }
        }
    }
}

