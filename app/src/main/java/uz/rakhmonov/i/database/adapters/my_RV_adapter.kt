package uz.rakhmonov.i.database.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.rakhmonov.i.database.databinding.ItemHomeFragmentBinding
import uz.rakhmonov.i.database.models.my_contacts

class RV_adapter (val list:ArrayList<my_contacts>, val menuAction: menuAction):RecyclerView.Adapter<RV_adapter.VH>() {

    inner class VH(val itemHomeFragmentBinding: ItemHomeFragmentBinding) :
        RecyclerView.ViewHolder(itemHomeFragmentBinding.root) {
        fun onHolder(myContacts: my_contacts,position: Int) {
            itemHomeFragmentBinding.TV1.text = myContacts.name
            itemHomeFragmentBinding.TV2.setText(myContacts.number)

            itemHomeFragmentBinding.root.setOnClickListener {
                menuAction.onclick(myContacts, position, itemHomeFragmentBinding.imageMore)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemHomeFragmentBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onHolder(list[position],position )

    }

    override fun getItemCount(): Int = list.size
}

interface menuAction {
    fun onclick(myContacts: my_contacts, position: Int,imageView: ImageView)
}




