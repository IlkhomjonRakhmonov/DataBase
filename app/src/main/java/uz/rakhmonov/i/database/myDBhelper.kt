package uz.rakhmonov.i.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.rakhmonov.i.database.models.my_contacts

class  MyDbHelper(val context: Context) : SQLiteOpenHelper(context, "MyDbContacts", null, 1),
    MyDbHelperInterface {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "create table myContacts(id integer not null primary key autoincrement unique, name text not null, number text not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addContact(myContacts: my_contacts) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", myContacts.name)
        contentValues.put("number", myContacts.number)
        database.insert("MyContacts", null, contentValues)
        database.close()
    }
    override fun getAllContacts(): ArrayList<my_contacts> {
        val database = this.readableDatabase
        val query = "select * from myContacts"
        val cursor = database.rawQuery(query, null)
        val list = ArrayList<my_contacts>()
        if (cursor.moveToFirst()){
            do {
                val myContacts = my_contacts(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(myContacts)
            }while (cursor.moveToNext())
        }
        return list
    }
    override fun deletecontact(myContacts: my_contacts) {
        val database=this.writableDatabase
        database.delete("MyContacts", "id=?", arrayOf(myContacts.id.toString()))
        database.close()
    }
    override fun editcontact(myContacts: my_contacts): Int {
        val database=this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put("id", myContacts.id)
        contentValues.put("name", myContacts.name)
        contentValues.put("number", myContacts.number)
        return database.update("MyContacts",contentValues,"id=?", arrayOf(myContacts.id.toString()))

    }
}