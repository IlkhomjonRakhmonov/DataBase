package uz.rakhmonov.i.database

import uz.rakhmonov.i.database.models.my_contacts

interface MyDbHelperInterface {
     fun addContact(myContacts: my_contacts)
    fun getAllContacts():List<my_contacts>
    fun deletecontact(myContacts: my_contacts)
    fun editcontact(myContacts: my_contacts):Int

}
