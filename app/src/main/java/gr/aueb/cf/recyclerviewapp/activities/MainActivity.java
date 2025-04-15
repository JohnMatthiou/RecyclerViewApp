package gr.aueb.cf.recyclerviewapp.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import gr.aueb.cf.recyclerviewapp.R;
import gr.aueb.cf.recyclerviewapp.adapters.MyAdapter;
import gr.aueb.cf.recyclerviewapp.models.Places;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Places> placesArrayList;
    private MyAdapter myAdapter;
    private String[] placesHeadings;
    private int[] placesImages;
    private int[] months;
    private String[] shortDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        placesArrayList = new ArrayList<Places>();

        setMyData();
        getData();

    }

    private void setMyData() {
        placesHeadings = new String[] {
                "Assos Village", "Melissani Cave", "Drogarati Cave", "Saint Thedoroi Lighthouse",
                "Assos Fortress", "Antisamos Beach"
        };

        placesImages = new int[] {
                R.drawable.image01,
                R.drawable.image02,
                R.drawable.image03,
                R.drawable.image04,
                R.drawable.image05,
                R.drawable.image06
        };

        shortDescription = new String[] {
                getString(R.string.places01),
                getString(R.string.places02),
                getString(R.string.places03),
                getString(R.string.places04),
                getString(R.string.places05),
                getString(R.string.places06)
        };

        months = new int[] {4, 12, 5, 6, 1, 9};
    }

    private void getData() {
        for (int i = 0; i < placesHeadings.length; i++) {
            Places places = new Places(placesHeadings[i], placesImages[i], months[i], shortDescription[i]);
            placesArrayList.add(places);
        }

        Collections.sort(placesArrayList, new Comparator<Places>() {
            @Override
            public int compare(Places o1, Places o2) {
                return o1.getMonth() - o2.getMonth();
            }
        });

        Collections.reverse(placesArrayList);

        myAdapter = new MyAdapter(this, placesArrayList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item, menu);

        MenuItem menuItem = menu.findItem(R.id.search_item);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search here...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}