package com.gaurav.dbdemo1;

// Importing required libraries and custom classes
import android.annotation.SuppressLint;
import android.os.Bundle; // For managing activity lifecycle data (e.g., saved instance state).
import android.util.Log; // For debugging and logging messages.
import android.widget.ArrayAdapter; // Adapter for simple lists (unused in this case).

import androidx.activity.EdgeToEdge; // For enabling modern, immersive UI designs.
import androidx.appcompat.app.AppCompatActivity; // Base class for modern activities with compatibility support.
import androidx.recyclerview.widget.LinearLayoutManager; // For arranging RecyclerView items in a linear fashion.
import androidx.recyclerview.widget.RecyclerView; // UI component for displaying a scrollable list of data.

import com.gaurav.dbdemo1.adapter.RecyclerViewAdapter; // Custom adapter for RecyclerView.
import com.gaurav.dbdemo1.data.MyDbHandler; // Database handler class for SQLite operations.
import com.gaurav.dbdemo1.model.Contact; // Model class representing the Contact data.

import java.util.ArrayList; // Dynamic array used to store contacts.
import java.util.List; // Interface for working with lists.

public class MainActivity extends AppCompatActivity {
    // Declaring member variables to hold references to UI components and data
    private RecyclerView recyclerView;            // RecyclerView for displaying the list of contacts.
    private RecyclerViewAdapter recyclerViewAdapter; // Adapter for binding data to the RecyclerView.
    private ArrayList<Contact> ContactArrayList;     // ArrayList to hold contacts fetched from the database.
    private ArrayAdapter<String> arrayAdapter;       // (Unused) For simple list-based adapters.

    @SuppressLint("MissingInflatedId") // Suppresses warnings about potential missing IDs in the XML layout.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Calls the superclass's onCreate method for initialization.
        EdgeToEdge.enable(this); // Enables edge-to-edge immersive UI design.
        setContentView(R.layout.activity_main); // Sets the activity's layout to `activity_main.xml`.

        // Initializing RecyclerView and setting its properties
        recyclerView = findViewById(R.id.recyclerView); // Binds the RecyclerView from the XML layout.
        recyclerView.setHasFixedSize(true); // Optimizes performance when the list size is fixed.
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Sets the layout to vertical scrolling.

        // Initializing the database handler for SQLite operations
        MyDbHandler db = new MyDbHandler(MainActivity.this);

        // Adding a Contact object to the database for demonstration
        // This part is for testing. On first run, the database will have duplicate entries.
        // Comment this out after initial testing to avoid repeated entries.
        Contact Gaurav = new Contact(); // Creating a Contact object.
        Gaurav.setPhone_number("7037154356"); // Setting the phone number of the contact.
        Gaurav.setName("Gaurav"); // Setting the name of the contact.
        db.addContact(Gaurav); // Adding the contact to the database.
        db.addContact(Gaurav); // Repeating this for testing purposes.
        db.addContact(Gaurav); // (Duplicate entries for demo).
        db.addContact(Gaurav);
        db.addContact(Gaurav);
        db.addContact(Gaurav);
        db.addContact(Gaurav);
        db.addContact(Gaurav);
        db.addContact(Gaurav);

        // Initializing the ArrayList to store contact data
        ContactArrayList = new ArrayList<>();

        // Fetching all contacts from the database
        List<Contact> contactList = db.getAllContacts(); // Fetching all contacts as a List.
        for (Contact contact : contactList) { // Iterating through the contacts.
            // Logging each contact's details for debugging.
            Log.d("gauravdb", "Id: " + contact.getId() + "/n" +
                    " Name: " + contact.getName() + "/n" +
                    " Phone: " + contact.getPhone_number() + "/n");
            ContactArrayList.add(contact); // Adding the contact to the ArrayList for RecyclerView.
        }

        // Initializing and setting the RecyclerView adapter
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, ContactArrayList); // Creating the adapter.
        recyclerView.setAdapter(recyclerViewAdapter); // Binding the adapter to the RecyclerView.

        // Debugging: Logging the number of contacts in the database
//        Log.d("gauravdb", "Gaurav You Have  " + db.getCount() + " in your database ");
    }
}
