package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
AddCityFragment.AddCityDialogListener { // Summons the dialog
    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;
    /*
    ArrayList<City> - Makes an array list using the City class.
    City (class) - Holds the city name and province where getters and setters are provided to access
    the private variables.
    ListView - Holds the list of cities with the province beside the name.
    CityArrayAdapter - Sets the city name and province into the list item using the getters from
    the class. We need to make a custom one since we have to set both the name and province.
     */

    String warningMessage = "Please do not leave any inputs blank!";

    public void addCity (City city) {
        if (!city.getName().isEmpty() && !city.getProvince().isEmpty()) {
            cityAdapter.add(city);
            cityAdapter.notifyDataSetChanged(); // Lets the adapter know that changes were made.
        }
        else {
            Toast.makeText(getApplicationContext(), warningMessage, Toast.LENGTH_SHORT).show();
        }
    }

    public void changeCity(City selectedCity, String newCity, String newProvince) {
        if (!newCity.isEmpty() && !newProvince.isEmpty()) {
            selectedCity.setName(newCity);
            selectedCity.setProvince(newProvince);
            cityAdapter.notifyDataSetChanged(); // Lets the adapter know that changes were made.
        }
        else {
            Toast.makeText(getApplicationContext(), warningMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Edmonton", "Vancouver", "Toronto", "Hamilton", "Denver", "Los Angeles"
        };
        String[] provinces = {
                "AB", "BC", "ON", "ON", "CO", "CA"
        };

        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
            System.out.println(cities[i]);
        }
        /*
        The dataList holds an instance of the City class then we add each city and its province into
        the same class.
         */

        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList = findViewById(R.id.city_list);
        cityList.setAdapter(cityAdapter);
        /*
        Adapt the dataList to the cityAdapter so the information will be displayed correctly when
        put into the view cityList.
         */

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> new AddCityFragment(null, "", "")
                .show(getSupportFragmentManager(), "Add City"));
        /*
        You wanna
         */

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                City selectedItem = dataList.get(i);
                new AddCityFragment(selectedItem,
                        selectedItem.getName(), selectedItem.getProvince())
                        .show(getSupportFragmentManager(), "Add/Edit City");
            }
        });


    }
}
