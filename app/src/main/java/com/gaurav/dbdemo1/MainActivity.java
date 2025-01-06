package com.gaurav.dbdemo1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import data.MyDbHandler;
import model.Contact;

public class MainActivity extends AppCompatActivity {
ListView listView;     // we are making a array list inorder to display on the screen
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        MyDbHandler db = new MyDbHandler(MainActivity.this);
        // first run to gradle build and debug when this conatcts is added to list then comment it out otherwise it will keep adding the same contacts!
        // we are commeting below lines aas its for understanding now finally we are displaying on the screen
        //creating a contact in a db as a object
//        Contact Gaurav = new Contact();
//        Gaurav.setPhone_number("7037154356"); //setting phone number as a string
//        Gaurav.setName("Gaurav");            //setting name as a string
//        db.addContact(Gaurav);               //adding contact to database
//
//        Contact Rakshit = new Contact();
//        Rakshit.setPhone_number("7037154314");
//        Rakshit.setName("Rakshit");
//        db.addContact(Rakshit);
//
//        // updated ..!
//        Rakshit.setName("Rakshit changed");
//        Rakshit.setPhone_number("0000000000");
//        int infectedRows = db.updateContacts(Rakshit);  // done only for rakshit
//        Log.d("gauravdb", "the rak updated successfully" + infectedRows);
//
//
//        Contact Vishal = new Contact();
//        Vishal.setPhone_number("7371520356");
//        Vishal.setName("Vishal");
//        db.addContact(Vishal);
//        Log.d("gauravdb", "the id of rak and vishal is added successfully");
//
//       // Deleting the records by id as which is been declared by creating function Delete in MyDbHandler as public void DeleteConatctById and Writing Sql Queries And And the String as Id!
//        db.DeleteContactById(1);
//        db.DeleteContactById(10);
//        db.DeleteContactById(14);
//
////        Deleteing the whole Contact as set the function in DBHandler As Publ;ic void DeletingContact and changing the string contact.getId()
//        db.DeleteContact(Rakshit);
//        db.DeleteContact(Vishal);

        ArrayList<String>  contacts = new ArrayList<>();
        listView = findViewById(R.id.listView); // as declared above and linking the id to xml file


        List<Contact> contactList = db.getAllContacts();
        for (Contact contact : contactList) {
            Log.d("gauravdb", "Id: " + contact.getId() + "/n" +
                    " Name: " + contact.getName() + "/n" +
                    " Phone: " + contact.getPhone_number() + "/n");
            contacts.add(contact.getName() + " [" + contact.getPhone_number() + "] ");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(arrayAdapter);
//        Log.d("gauravdb", "Gaurav You Have  " + db.getCount() + " in your database ");

    }


}






