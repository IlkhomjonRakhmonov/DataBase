package uz.rakhmonov.i.database.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.rakhmonov.i.database.MyDbHelper
import uz.rakhmonov.i.database.databinding.FragmentSecondBinding
import uz.rakhmonov.i.database.models.my_contacts

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment : Fragment() {
    private var isEdit: Boolean = false
    private var myContacts: my_contacts? = null

    private val binding:FragmentSecondBinding  by lazy { FragmentSecondBinding.inflate(layoutInflater) }
    private lateinit var dbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dbHelper= MyDbHelper(binding.root.context)

        isEdit=arguments?.getBoolean("isEdit")!!

        if (isEdit){
            myContacts=arguments?.getSerializable("MYCONTACT") as my_contacts


        }
        if (isEdit){
            binding.edt1.setText(myContacts?.name)
            binding.edt2.setText(myContacts?.number)
        }


        binding.btnSave.setOnClickListener {
            if (isEdit) {
                myContacts?.name = binding.edt1.text.toString()
                myContacts?.number = binding.edt2.text.toString()
                dbHelper.editcontact(myContacts!!)
            } else {

                val myContacts = my_contacts(
                    binding.edt1.text.toString(),
                    binding.edt2.text.toString()
                )
            dbHelper.addContact(myContacts)
            Toast.makeText(context, "saqlandi", Toast.LENGTH_SHORT).show()
        }
            findNavController().popBackStack()
        }
       return binding.root
    }

}