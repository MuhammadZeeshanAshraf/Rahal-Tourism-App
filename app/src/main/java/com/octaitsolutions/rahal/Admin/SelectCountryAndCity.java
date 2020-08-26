package com.octaitsolutions.rahal.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.octaitsolutions.rahal.R;

import es.dmoral.toasty.Toasty;

public class SelectCountryAndCity extends AppCompatActivity {

    Button next;
    Spinner countrySpinner , citySpinner;

    String selectedCountry = "" , selectedCity = "";
    String checker = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_country_and_city);

        try {
            checker = getIntent().getStringExtra("checker");
        }catch (Exception e)
        {
            checker = "1";
        }
        next= findViewById(R.id.mp_next);
        countrySpinner =  findViewById(R.id.country_spinner);
        citySpinner =  findViewById(R.id.city_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                R.array.Country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countrySpinner.setAdapter(adapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedCountry = countrySpinner.getSelectedItem().toString().trim();
                //1
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Argentina)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Argentina_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }
                //2
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Australia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Australia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //3
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Austria)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Austria_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //4
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Belgium)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Belgium_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }
                //5
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Bhutan)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Bhutan_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //6
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Brazil)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Brazil_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //7
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Colombia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Colombia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //8
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Colombia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Colombia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //9
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.canada)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Canada_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //10
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.China)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.China_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //11
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Egypt)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Egypt_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //11
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.France)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.France_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //12
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Germany)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Germany_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //13
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Greece)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Greece_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //14
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.India)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.India_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //14
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Indonesia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Indonesia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //15
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Ireland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Ireland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //16
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Japan)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Japan_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //17
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Italy)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Italy_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //18
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Kenya)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Kenya_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //19
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Korea)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Korea_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //20
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Malaysia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Malaysia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //21
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Netherlands)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Netherlands_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //22
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.New_Zealand)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.New_Zealand_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //23
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Philippines)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Philippines_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //24
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Poland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Poland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //25
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Portugal)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Portugal_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //26
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Russia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Russia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //27
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Saudi_Arabia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Saudi_Arabia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //28
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Scotland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Scotland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //29
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Seychelles)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Seychelles_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //30
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.South_Africa)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.South_Africa_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //31
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Spain)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Spain_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //32
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Sri_Lanka)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Sri_Lanka_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //33
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Switzerland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Switzerland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //34
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Bahamas)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Bahamas_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //35
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Czech_Republic)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Czech_Republic_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //36
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Denmark)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Denmark_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //37
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Finland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Finland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //38
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Hungary)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Hungary_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //39
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Iceland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.IceLand_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //40
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Luxembourg)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Luxembourg_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //41
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Mexico)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Mexico_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //42
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Morocoo)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Morocoo_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //43
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Nepal)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Nepal_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //44
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Norway)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Norway_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //45
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Peru)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Peru_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //46
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Serbia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Serbia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //47
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Slovakia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Sweden_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //48
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Turkey)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Turkey_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //49
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.UAE)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.UAE_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //50
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.United_Kingdom)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.United_Kingdom_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //51
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.USA)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.USA_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //52
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Uzbekistan)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Uzbekistan_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //53
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Vitnam)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Vitnam_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //54
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Thailand)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Thiland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //55
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Pakistan)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Pakistan_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //56
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Croatia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Croatia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //57
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Estonia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Estonia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //58
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Bulgaria)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Bulgaria_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //59
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Costa_Rica)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Costa_Rice_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //60
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Cambodia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Cambodia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }


                // Special Countries
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Qatar)) )
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Qatar_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                if( countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Singapore)) )
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Singapore_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                if( countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Maldives)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Maldives_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                if(countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Oman)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Oman_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                if(countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Bahrain)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Bahrain_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                if(countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Kuwait)))
                { ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                        R.array.Kuwait_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);

                }
                if(countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Jordan)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Jordan_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }
                if( countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Algeria)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Algeria_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }
                if( countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Tunisia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SelectCountryAndCity.this,
                            R.array.Tunisia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = citySpinner.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(selectedCountry) || selectedCountry.equals("Select Country"))
                {
                    Toasty.error(SelectCountryAndCity.this, "Kindly select the desire country....!", Toasty.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(selectedCity)  || selectedCity.equals("Select City"))
                {
                    Toasty.error(SelectCountryAndCity.this, "Kindly select the desire city....!", Toasty.LENGTH_SHORT).show();
                }else
                {
                    if(checker.equals("1"))
                    {
                        SendUserToActivity(new ManageShopping());
                    }
                    if(checker.equals("2"))
                    {
                        SendUserToActivity(new ManageRestaurant());
                    }
                    if(checker.equals("3"))
                    {
                        SendUserToActivity(new ManageCafe());
                    }
                    if(checker.equals("4"))
                    {
                        SendUserToActivity(new ManageThings());
                    }

                }
            }
        });

    }

    private void SendUserToActivity(Activity activity )
    {
        Intent intent = new Intent(SelectCountryAndCity.this , activity.getClass());
        intent.putExtra("selectedCountry", selectedCountry);
        intent.putExtra("selectedCity", selectedCity);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}