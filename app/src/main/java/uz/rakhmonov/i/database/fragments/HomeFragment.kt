package uz.rakhmonov.i.database.fragments

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.rakhmonov.i.database.MyDbHelper
import uz.rakhmonov.i.database.R
import uz.rakhmonov.i.database.adapters.RV_adapter
import uz.rakhmonov.i.database.adapters.menuAction
import uz.rakhmonov.i.database.databinding.FragmentHomeBinding
import uz.rakhmonov.i.database.models.my_contacts

class HomeFragment : Fragment(), menuAction {
    private val binding:FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    lateinit var myRV_adapter: RV_adapter
    lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        myDbHelper=MyDbHelper(binding.root.context)
        myRV_adapter=RV_adapter(myDbHelper.getAllContacts() as ArrayList,this)
        binding.myRecView.adapter=myRV_adapter

      return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_option,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.secondFragment, bundleOf("isEdit" to false))
        return super.onOptionsItemSelected(item)
    }

    override fun onclick(myContacts: my_contacts, position: Int, imageView: ImageView) {
        val popupMenu=PopupMenu(context,imageView)

        popupMenu.inflate(R.menu.menu_popup_item)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.delete->{
                    myDbHelper.deletecontact(myContacts)
                    myRV_adapter.list.remove(myContacts)
                    myRV_adapter.notifyItemRemoved(position)



                    Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()
                }
                R.id.edit->{
                    findNavController().navigate(R.id.secondFragment, bundleOf("isEdit" to true, "MYCONTACT" to myContacts))

                    Toast.makeText(context, "editten", Toast.LENGTH_SHORT).show()
                }
            }


            true
        }
        popupMenu.show()


    }

}


