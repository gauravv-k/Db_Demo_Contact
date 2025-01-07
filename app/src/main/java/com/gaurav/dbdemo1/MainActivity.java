package com.gaurav.dbdemo1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gaurav.dbdemo1.adapter.RecyclerViewAdapter;
import com.gaurav.dbdemo1.data.MyDbHandler;
import com.gaurav.dbdemo1.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;            // private class is made to work on recycler view.
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contact> ContactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //recycleview initialization
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyDbHandler db = new MyDbHandler(MainActivity.this);
        // first run to gradle build and debug when this conatcts is added to list then comment it out otherwise it will keep adding the same contacts!
        // we are commeting below lines aas its for understanding now finally we are displaying on the screen
        //creating a contact in a db as a object
        Contact Gaurav = new Contact();
        Gaurav.setPhone_number("7037154356"); //setting phone number as a string
        Gaurav.setName("Gaurav");            //setting name as a string
        db.addContact(Gaurav);               //adding contact to database
        db.addContact(Gaurav);               // replicating this for making more object!
        db.addContact(Gaurav);
        db.addContact(Gaurav);
        db.addContact(Gaurav);
        db.addContact(Gaurav);
        db.addContact(Gaurav);
        db.addContact(Gaurav);
        db.addContact(Gaurav);

        ContactArrayList= new ArrayList<>();

        List<Contact> contactList = db.getAllContacts();
        for (Contact contact : contactList) {
            Log.d("gauravdb", "Id: " + contact.getId() + "/n" +
                    " Name: " + contact.getName() + "/n" +
                    " Phone: " + contact.getPhone_number() + "/n");
            ContactArrayList.add(contact);
        }

        //using recycleview adapter

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, ContactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);


//        Log.d("gauravdb", "Gaurav You Have  " + db.getCount() + " in your database ");

    }
//

}






