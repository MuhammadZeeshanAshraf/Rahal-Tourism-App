package com.octaitsolutions.rahal.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.octaitsolutions.rahal.Adapter.CityAdapter;
import com.octaitsolutions.rahal.Adapter.CountryAdapter;
import com.octaitsolutions.rahal.Model.City;
import com.octaitsolutions.rahal.Model.Country;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.City.CityInformationActivity;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<City> list;
    private CityAdapter adapter;
    private ImageView goto_information;
    private TextView countryName;

    int country_name;
    String selectedCountry="";
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        goto_information = findViewById(R.id.goto_information);
        recyclerView = findViewById(R.id.city_recyclerview);
        list = new ArrayList<>();
        countryName = findViewById(R.id.city_country_name);

        country_name = getIntent().getIntExtra("name", 0);
        countryName.setText(country_name);
        selectedCountry =  countryName.getText().toString();

        back = findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        switch (country_name) {
            // 1
            case R.string.Argentina:
                ShowArgentinaCities();
                break;
            // 2
            case R.string.Australia:
                ShowAustraliaCities();
                break;
            // 3
            case R.string.Austria:
                ShowAustriaCities();
                break;
            // 4
            case R.string.Belgium:
                ShowBelgiumCities();
                break;
            // 5
            case R.string.Bhutan:
                ShowBhutanCities();
                break;
            // 6
            case R.string.Brazil:
                ShowBrazilCities();
                break;
            // 7
            case R.string.Colombia:
                ShowColombiaCities();
                break;
            // 8
            case R.string.canada:
                ShowCanadaCities();
                break;
            // 9
            case R.string.China:
                ShowChinaCities();
                break;
            // 10
            case R.string.Egypt:
                ShowEgyptCities();
                break;
            // 11
            case R.string.France:
                ShowFranceCities();
                break;
            // 12
            case R.string.Germany:
                ShowGermanyCities();
                break;
            // 13
            case R.string.Greece:
                ShowGreeceCities();
                break;
            // 14
            case R.string.India:
                ShowIndiaCities();
                break;
            // 15
            case R.string.Indonesia:
                ShowIndonesiaCities();
                break;
            // 16
            case R.string.Ireland:
                ShowIrelandCities();
                break;
            // 17
            case R.string.Italy:
                ShowItalyCities();
                break;
            // 18
            case R.string.Japan:
                ShowJapanCities();
                break;
            // 19
            case R.string.Kenya:
                ShowKenyaCities();
                break;
            // 20
            case R.string.Korea:
                ShowKoreaCities();
                break;
            // 21
            case R.string.Malaysia:
                ShowMalaysiaCities();
                break;
            // 22
            case R.string.Netherlands:
                ShowNetherlandsCities();
                break;
            // 23
            case R.string.New_Zealand:
                ShowNew_ZealandCities();
                break;
            // 24
            case R.string.Philippines:
                ShowPhilippinesCities();
                break;
            // 25
            case R.string.Poland:
                ShowPolandCities();
                break;
            // 26
            case R.string.Portugal:
                ShowPortugalCities();
                break;
            // 27
            case R.string.Russia:
                ShowRussiaCities();
                break;
            // 28
            case R.string.Saudi_Arabia:
                ShowSaudiArabiaCities();
                break;
            // 29
            case R.string.Scotland:
                ShowScotlandCities();
                break;
            // 30
            case R.string.Seychelles:
                ShowSeychellesCities();
                break;
            // 31
            case R.string.South_Africa:
                ShowSouthAfricaCities();
                break;
            // 32
            case R.string.Spain:
                ShowSpainCities();
                break;
            // 33
            case R.string.Sri_Lanka:
                ShowSriLankaCities();
                break;
            // 34
            case R.string.Switzerland:
                ShowSwitzerlandCities();
                break;
            // 35
            case R.string.Bahamas:
                ShowBahamasCities();
                break;
            // 36
            case R.string.Czech_Republic:
                ShowCzechRepublicCities();
                break;
            // 37
            case R.string.Denmark:
                ShowDenmarkCities();
                break;
            // 38
            case R.string.Finland:
                ShowFinlandCities();
                break;
            // 39
            case R.string.Hungary:
                ShowHungaryCities();
                break;
            // 40
            case R.string.Iceland:
                ShowIcelandCities();
                break;
            // 41
            case R.string.Luxembourg:
                ShowLuxembourgCities();
                break;
            // 42
            case R.string.Mexico:
                ShowMexicoCities();
                break;
            // 42
            case R.string.Morocoo:
                ShowMorocooCities();
                break;
            // 43
            case R.string.Nepal:
                ShowNepalCities();
                break;
            // 44
            case R.string.Norway:
                ShowNorwayCities();
                break;
            // 45
            case R.string.Peru:
                ShowPeruCities();
                break;
            // 46
            case R.string.Serbia:
                ShowSerbiaCities();
                break;
            // 47
            case R.string.Slovakia:
                ShowSlovakiaCities();
                break;
            // 48
            case R.string.Sweden:
                ShowSwedenCities();
                break;
            // 49
            case R.string.Turkey:
                ShowTurkeyCities();
                break;
            // 50
            case R.string.UAE:
                ShowUAECities();
                break;
            // 51
            case R.string.United_Kingdom:
                ShowUnitedKingdomCities();
                break;
            // 52
            case R.string.USA:
                ShowUSACities();
                break;
            // 53
            case R.string.Uzbekistan:
                ShowUzbekistanCities();
                break;
            // 54
            case R.string.Vitnam:
                ShowVitnamCities();
                break;
            // 55
            case R.string.Thailand:
                ShowThailandCities();
                break;
            // 56
            case R.string.Pakistan:
                ShowPakistanCities();
                break;
            // 57
            case R.string.Croatia:
                ShowCroatiaCities();
                break;
            // 56
            case R.string.Estonia:
                ShowEstoniaCities();
                break;
            // 56
            case R.string.Bulgaria:
                ShowBulgariaCities();
                break;
            // 56
            case R.string.Costa_Rica:
                ShowCostaRicaCities();
                break;
            // 7
            case R.string.Cambodia:
                ShowCambodiaCities();
                break;

        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(CityActivity.this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CityAdapter(CityActivity.this, list , selectedCountry);

        recyclerView.setAdapter(adapter);

        goto_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SendUserToActivity(new CityInformationActivity());
            }
        });
    }

    private void ShowCambodiaCities() {
        int[] name = {R.string.Banlung,
                R.string.Battambang,
                R.string.Kampot,
                R.string.Koh_Rong,
                R.string.Kratie,
                R.string.Sihanoukville
        };

        int[] image = {R.drawable.banlung,
                R.drawable.battambang,
                R.drawable.kampot,
                R.drawable.koh_rong,
                R.drawable.kratie,
                R.drawable.sihanoukville
        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void SendUserToActivity(Activity activity)
    {
        Intent intent = new Intent(CityActivity.this, activity.getClass());
        intent.putExtra("name", country_name);
        startActivity(intent);
    }


    private void ShowCostaRicaCities()
    {
        int[] name = {R.string.Arenal,
                R.string.Cahuita,
                R.string.Corcovado,
                R.string.Puerto_Viejo,
                R.string.Tortuguero
        };

        int[] image = {R.drawable.arenal,
                R.drawable.cahuita,
                R.drawable.corcovado,
                R.drawable.puerto_viejo,
                R.drawable.tortuguero
        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowBulgariaCities()
    {
        int[] name = {R.string.Nesebar,
                R.string.Plovdiv,
                R.string.Sofia,
                R.string.Varna

        };

        int[] image = {R.drawable.nesebar,
                R.drawable.plovdiv,
                R.drawable.sofia,
                R.drawable.varna
        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowEstoniaCities()
    {
        int[] name = {R.string.Parnu,
                R.string.Tallinn,
                R.string.Tartu
        };

        int[] image = {R.drawable.parnu,
                R.drawable.tallinn,
                R.drawable.tartu

        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowCroatiaCities()
    {
        int[] name = {R.string.Dubrovnik,
                R.string.Split,
                R.string.Zadar,
                R.string.Zagreb

        };

        int[] image = {R.drawable.dubrovnik,
                R.drawable.split,
                R.drawable.zadar,
                R.drawable.zagreb

        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowPakistanCities()
    {
        int[] name = {R.string.Islamabad,
                R.string.Lahore,
                R.string.Karachi,
                R.string.Peshawar,
                R.string.Kashmir,
                R.string.Gwadar,

        };

        int[] image = {R.drawable.islamabad,
                R.drawable.lahore,
                R.drawable.karachi,
                R.drawable.peshawar,
                R.drawable.kashmir,
                R.drawable.gwadar

        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowThailandCities()
    {
        int[] name = {R.string.Ayutthaya,
                R.string.Bangkok,
                R.string.Chiang_Mai,
                R.string.Chiang_Rai,
                R.string.Hua_Hin,
                R.string.Kanchanaburi,
                R.string.Krabi_Town,
                R.string.Nong_Khai,
                R.string.Pai,
                R.string.Pattaya,
                R.string.Phuket
        };

        int[] image = {R.drawable.ayutthaya,
                R.drawable.bangkok,
                R.drawable.chiang_mai,
                R.drawable.chiang_rai,
                R.drawable.hua_hin,
                R.drawable.kanchanaburi,
                R.drawable.krabi_town,
                R.drawable.nong_khai,
                R.drawable.pai,
                R.drawable.pattaya,
                R.drawable.phuket
        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowVitnamCities()
    {
        int[] name = {R.string.Can_Tho,
                R.string.Da_Nang,
                R.string.Dalat,
                R.string.Hanoi,
                R.string.Ho_Chi_Minh_City,
                R.string.Hoiz_An,
                R.string.Hue,
                R.string.Nha_Trang
        };

        int[] image = {R.drawable.can_tho,
                R.drawable.da_nang,
                R.drawable.dalat,
                R.drawable.hanoi,
                R.drawable.ho_chi_minh_city,
                R.drawable.hoiz_an,
                R.drawable.hue,
                R.drawable.nha_trang
        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowUzbekistanCities()
    {
        int[] name = {R.string.Andijan,
                R.string.Bukhara,
                R.string.Gulistan
        };

        int[] image = {R.drawable.andijan,
                R.drawable.bukhara,
                R.drawable.gulistan
        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowUSACities()
    {
        int[] name = {R.string.Boston,
                R.string.Chicago,
                R.string.Honolulu,
                R.string.Las_Vegas,
                R.string.Los_Angeles,
                R.string.Miami,
                R.string.New_York,
                R.string.Orlando,
                R.string.San_Diego,
                R.string.San_Francisco,
                R.string.Washington
        };

        int[] image = {R.drawable.boston,
                R.drawable.chicago,
                R.drawable.honolulu,
                R.drawable.las_vegas,
                R.drawable.los_angeles,
                R.drawable.miami,
                R.drawable.new_york,
                R.drawable.orlando,
                R.drawable.san_diego,
                R.drawable.san_francisco,
                R.drawable.washington
        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowUnitedKingdomCities()
    {
        int[] name = {R.string.Bath,
                R.string.Birmingham,
                R.string.Brighton,
                R.string.Bristol,
                R.string.Edinburgh,
                R.string.Glasgow,
                R.string.Liverpool,
                R.string.London,
                R.string.Manchester,
                R.string.Newcastle,
                R.string.Oxford,
                R.string.York
        };

        int[] image = {R.drawable.bath,
                R.drawable.birmingham,
                R.drawable.brighton,
                R.drawable.bristol,
                R.drawable.edinburgh,
                R.drawable.glasgow,
                R.drawable.liverpool,
                R.drawable.london,
                R.drawable.manchester,
                R.drawable.newcastle,
                R.drawable.oxford,
                R.drawable.york
        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowUAECities()
    {
        int[] name = {R.string.Abu_Dhabi,
                R.string.Dubai
        };

        int[] image = {R.drawable.abu_dhabi,
                R.drawable.dubai
        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }


    private void ShowTurkeyCities()
    {
        int[] name = {R.string.Alanya,
                R.string.Ankara,
                R.string.Antalya,
                R.string.Bodrum,
                R.string.Bursa,
                R.string.Fethiye,
                R.string.Istanbul,
                R.string.Izmir,
                R.string.Edirne,
                R.string.Konya,
                R.string.Kusadasi,
                R.string.Trabzon,
                R.string.Urfa,
                R.string.Urgup
        };

        int[] image = {R.drawable.alanya,
                R.drawable.ankara,
                R.drawable.antalya,
                R.drawable.bodrum,
                R.drawable.bursa,
                R.drawable.fethiye,
                R.drawable.istanbul,
                R.drawable.izmir,
                R.drawable.edirne,
                R.drawable.konya,
                R.drawable.kusadasi,
                R.drawable.trabzon,
                R.drawable.urfa,
                R.drawable.urgup

        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowSwedenCities()
    {
        int[] name = {R.string.Gothenburg,
                R.string.Helsingborg,
                R.string.Karlskrona,
                R.string.Malmo,
                R.string.Stockholm
        };

        int[] image = {R.drawable.gothenburg,
                R.drawable.helsingborg,
                R.drawable.karlskrona,
                R.drawable.malmo,
                R.drawable.stockholm

        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowSlovakiaCities()
    {
        int[] name = {R.string.Banska_bystrica,
                R.string.Bratislava,
                R.string.Levoca,
                R.string.Nitra
        };

        int[] image = {R.drawable.banska_bystrica,
                R.drawable.bratislava,
                R.drawable.levoca,
                R.drawable.nitra

        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowSerbiaCities()
    {
        int[] name = {R.string.Belgrade,
                R.string.Kragujevac,
                R.string.Kraljevo,
                R.string.Novi_sad
        };

        int[] image = {R.drawable.belgrade,
                R.drawable.kragujevac,
                R.drawable.kraljevo,
                R.drawable.novi_sad

        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowPeruCities()
    {
        int[] name = {R.string.Arequipa,
                R.string.Cusco,
                R.string.Lima,
                R.string.Puno
        };

        int[] image = {R.drawable.arequipa,
                R.drawable.cusco,
                R.drawable.lima,
                R.drawable.puno

        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowNorwayCities()
    {
        int[] name = {R.string.Alesund,
                R.string.Bergen,
                R.string.Oslo,
                R.string.Stavanger,
                R.string.Trondheim
        };

        int[] image = {R.drawable.alesund,
                R.drawable.bergen,
                R.drawable.oslo,
                R.drawable.stavanger,
                R.drawable.trondheim

        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowNepalCities()
    {
        int[] name = {R.string.Bhaktapur,
                R.string.Kathmandu,
                R.string.Pokhara
        };

        int[] image = {R.drawable.bhaktapur,
                R.drawable.kathmandu,
                R.drawable.pokhara
        };

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowMorocooCities()
    {
        int[] name = {R.string.Agadir,
                R.string.Casablanca,
                R.string.Fez,
                R.string.Marrakech,
                R.string.Meknes,
                R.string.Tangier

        };

        int[] image = {R.drawable.agadir,
                R.drawable.casablanca,
                R.drawable.fez,
                R.drawable.marrakech,
                R.drawable.meknes,
                R.drawable.tangier};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowMexicoCities() {
        int[] name = {R.string.Cabo_san_lucas,
                R.string.Cancun,
                R.string.Playa_del_carmen,
                R.string.Puebla
        };

        int[] image = {R.drawable.cabo_san_lucas,
                R.drawable.cancun,
                R.drawable.playa_del_carmen,
                R.drawable.puebla_city};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowLuxembourgCities()
    {
        int[] name = {R.string.Echternach,
                R.string.Ettelbruck
        };

        int[] image = {R.drawable.echternach,
                R.drawable.ettelbruck};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowIcelandCities()
    {
        int[] name = {R.string.Akureyri,
                R.string.Husavik,
                R.string.Reykjavik,
                R.string.Vik_i_myrdal
        };

        int[] image = {R.drawable.akureyri,
                R.drawable.husavik,
                R.drawable.reykjavik,
                R.drawable.vik_i_myrdal};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowHungaryCities()
    {
        int[] name = {R.string.Budapest,
                R.string.Debrecen,
                R.string.Eger,
                R.string.Pecs,
                R.string.Szeged
        };

        int[] image = {R.drawable.budapest,
                R.drawable.debrecen,
                R.drawable.eger,
                R.drawable.pecs,
                R.drawable.szeged};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowFinlandCities() {
        int[] name = {R.string.Helsinki,
                R.string.Jyvaskyla,
                R.string.Rovaniemi,
                R.string.Tampere,
                R.string.Turku
        };

        int[] image = {R.drawable.helsinki,
                R.drawable.jyvaskyla,
                R.drawable.rovaniemi,
                R.drawable.tampere,
                R.drawable.turku};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowDenmarkCities() {
        int[] name = {R.string.Aalborg,
                R.string.Aarhus,
                R.string.Copenhagen,
                R.string.Frederiksberg,
                R.string.Odense
        };

        int[] image = {R.drawable.aalborg,
                R.drawable.aarhus,
                R.drawable.copenhagen,
                R.drawable.frederiksberg,
                R.drawable.odense};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowCzechRepublicCities() {
        int[] name = {R.string.Cesky_Krumlov,
                R.string.Prague};

        int[] image = {R.drawable.cesky_krumlov,
                R.drawable.prague};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowBahamasCities() {
        int[] name = {R.string.Lucaya,
                R.string.Matthew_town,
                R.string.Nassau};

        int[] image = {R.drawable.lucaya,
                R.drawable.matthew_town,
                R.drawable.nassau};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowSwitzerlandCities() {
        int[] name = {R.string.Basel,
                R.string.Bellinzona,
                R.string.Bern,
                R.string.Geneva,
                R.string.Lausanne,
                R.string.Lucerne,
                R.string.Lugano,
                R.string.St_Gallen,
                R.string.Thun,
                R.string.Zurich};

        int[] image = {R.drawable.basel,
                R.drawable.bellinzona,
                R.drawable.bern,
                R.drawable.geneva,
                R.drawable.lausanne,
                R.drawable.lucerne,
                R.drawable.lugano,
                R.drawable.st_gallen,
                R.drawable.thun,
                R.drawable.zurich};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowSriLankaCities() {
        int[] name = {R.string.Colombo,
                R.string.Galle,
                R.string.Hikkaduwa,
                R.string.Kandy,
                R.string.Negombo,
                R.string.Nuwara_Eliya};

        int[] image = {R.drawable.colombo,
                R.drawable.galle,
                R.drawable.hikkaduwa,
                R.drawable.kandy,
                R.drawable.negombo,
                R.drawable.nuwara_eliya};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowSpainCities() {
        int[] name = {R.string.Barcelona,
                R.string.Bilbao,
                R.string.Cordoba,
                R.string.Granada,
                R.string.Ibiza,
                R.string.Las_Palmas,
                R.string.Madrid,
                R.string.Malaga,
                R.string.Mallorca,
                R.string.San_Sebastian,
                R.string.Seville,
                R.string.Toledo,
                R.string.Valencia};

        int[] image = {R.drawable.barcelona,
                R.drawable.bilbao,
                R.drawable.cordoba_b,
                R.drawable.granada,
                R.drawable.ibiza,
                R.drawable.las_palmas,
                R.drawable.madrid,
                R.drawable.malaga,
                R.drawable.mallorca,
                R.drawable.san_sebastian,
                R.drawable.seville,
                R.drawable.toledo,
                R.drawable.valencia};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }


    private void ShowSouthAfricaCities() {
        int[] name = {R.string.Cape_Town,
                R.string.Durban,
                R.string.Johannesburg,
                R.string.Port_Elizabeth,
                R.string.Pretoria};

        int[] image = {R.drawable.cape_town,
                R.drawable.durban,
                R.drawable.johannesburg,
                R.drawable.port_elizabeth,
                R.drawable.pretoria};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowSeychellesCities() {
        int[] name = {R.string.Baie_Ste_Anne,
                R.string.Beau_Vallon,
                R.string.Victoria};

        int[] image = {R.drawable.baie_ste_anne,
                R.drawable.beau_vallon,
                R.drawable.victoria};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowScotlandCities() {
        int[] name = {R.string.Edinburgh,
                R.string.Falkirk,
                R.string.Perth,
                R.string.St_Andrews};

        int[] image = {R.drawable.edinburgh,
                R.drawable.falkirk,
                R.drawable.perth_b,
                R.drawable.st_andrews};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowSaudiArabiaCities() {
        int[] name = {R.string.Dammam,
                R.string.Jeddah,
                R.string.Makkah,
                R.string.Medina,
                R.string.Riyadh};

        int[] image = {R.drawable.dammam,
                R.drawable.jeddah,
                R.drawable.makkah,
                R.drawable.medina,
                R.drawable.riyadh};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowRussiaCities() {
        int[] name = {R.string.Irkutsk,
                R.string.Moscow,
                R.string.Novosibirsk,
                R.string.Saint_Petersburg,
                R.string.Vladivostok,
                R.string.Yekaterinburg};

        int[] image = {R.drawable.irkutsk,
                R.drawable.moscow,
                R.drawable.novosibirsk,
                R.drawable.saint_petersburg,
                R.drawable.vladivostok,
                R.drawable.yekaterinburg};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowPortugalCities() {
        int[] name = {R.string.Aveiro,
                R.string.Braga,
                R.string.Coimbra,
                R.string.Lisbon,
                R.string.Porto,
                R.string.Sintra};

        int[] image = {R.drawable.aveiro,
                R.drawable.braga,
                R.drawable.coimbra,
                R.drawable.lisbon,
                R.drawable.porto,
                R.drawable.sintra};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowPolandCities() {
        int[] name = {R.string.Gdansk,
                R.string.Krakow,
                R.string.Poznan,
                R.string.Warsaw,
                R.string.Wroclaw};

        int[] image = {R.drawable.gdansk,
                R.drawable.krakow,
                R.drawable.poznan,
                R.drawable.warsaw,
                R.drawable.wroclaw};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowPhilippinesCities() {
        int[] name = {R.string.Bacolod,
                R.string.Baguio,
                R.string.Cagayan_de_Oro,
                R.string.Manila};

        int[] image = {R.drawable.bacolod,
                R.drawable.baguio,
                R.drawable.cagayan_de_oro,
                R.drawable.manila};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowNew_ZealandCities() {
        int[] name = {R.string.Auckland,
                R.string.Hamilton,
                R.string.Tauranga,
                R.string.Wellington};

        int[] image = {R.drawable.auckland,
                R.drawable.hamilton,
                R.drawable.tauranga,
                R.drawable.wellington};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowNetherlandsCities() {
        int[] name = {R.string.Amsterdam,
                R.string.Delft,
                R.string.Haarlem,
                R.string.Maastricht,
                R.string.Rotterdam,
                R.string.Utrecht};

        int[] image = {R.drawable.amsterdam,
                R.drawable.delft,
                R.drawable.haarlem,
                R.drawable.maastricht,
                R.drawable.rotterdam,
                R.drawable.utrecht};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowMalaysiaCities() {
        int[] name = {R.string.Johor_Bahru,
                R.string.Kota_Kinabalu,
                R.string.Kuala_Lumpur,
                R.string.Kuching,
                R.string.Langkawi,
                R.string.Penang};

        int[] image = {R.drawable.johor_bahru,
                R.drawable.kota_kinabalu,
                R.drawable.kuala_lumpur,
                R.drawable.kuching,
                R.drawable.langkawi,
                R.drawable.penang};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowKoreaCities() {
        int[] name = {R.string.Busan,
                R.string.Gyeongju,
                R.string.Jinhae,
                R.string.Seoul};

        int[] image = {R.drawable.busan,
                R.drawable.gyeongju,
                R.drawable.jinhae,
                R.drawable.seoul};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowKenyaCities() {
        int[] name = {R.string.Amboseli,
                R.string.Maasai};

        int[] image = {
                R.drawable.amboseli,
                R.drawable.maasai};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowJapanCities() {
        int[] name = {R.string.Fukuoka,
                R.string.Hiroshima,
                R.string.Kyoto,
                R.string.Nagasaki,
                R.string.Nagoya,
                R.string.Osaka,
                R.string.Tokyo,
                R.string.Yokohama};

        int[] image = {R.drawable.fukuoka,
                R.drawable.hiroshima,
                R.drawable.kyoto,
                R.drawable.nagasaki,
                R.drawable.nagoya,
                R.drawable.osaka,
                R.drawable.tokyo,
                R.drawable.yokohama};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowItalyCities() {
        int[] name = {R.string.Bologna,
                R.string.Florenc,
                R.string.Genoa,
                R.string.Milan,
                R.string.Naples,
                R.string.Rome,
                R.string.Venice,
                R.string.Verona};

        int[] image = {R.drawable.bologna,
                R.drawable.florenc,
                R.drawable.genoa,
                R.drawable.milan,
                R.drawable.naples,
                R.drawable.rome,
                R.drawable.venice,
                R.drawable.verona};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowIrelandCities() {
        int[] name = {R.string.Dublin,
                R.string.Galway,
                R.string.Kilkenny,
                R.string.Killarney,
                R.string.Limerick};

        int[] image = {R.drawable.dublin,
                R.drawable.galway,
                R.drawable.kilkenny,
                R.drawable.killarney,
                R.drawable.limerick};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowIndonesiaCities() {
        int[] name = {R.string.Bandung,
                R.string.Jakarta,
                R.string.Kuta,
                R.string.Ubud,
                R.string.Yogyakarta};

        int[] image = {R.drawable.bandung,
                R.drawable.jakarta,
                R.drawable.kuta,
                R.drawable.ubud,
                R.drawable.yogyakarta};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }


    private void ShowIndiaCities() {
        int[] name = {R.string.Agra,
                R.string.Chennai,
                R.string.Delhi,
                R.string.Jaipur,
                R.string.Mumbai};

        int[] image = {R.drawable.agra,
                R.drawable.chennai,
                R.drawable.delhi,
                R.drawable.jaipur,
                R.drawable.mumbai};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowGreeceCities() {
        int[] name = {R.string.Athens,
                R.string.Corfu,
                R.string.Meteora,
                R.string.Mykonos,
                R.string.Santorini,
                R.string.Thessaloniki};

        int[] image = {R.drawable.athens,
                R.drawable.corfu,
                R.drawable.meteora,
                R.drawable.mykonos,
                R.drawable.santorini,
                R.drawable.thessaloniki};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowGermanyCities() {
        int[] name = {R.string.Berlin,
                R.string.Cologne,
                R.string.Dresden,
                R.string.Frankfurter,
                R.string.Freiburg,
                R.string.Hamburg,
                R.string.Heidelberg,
                R.string.Kiel,
                R.string.Munich,
                R.string.Potsdam};

        int[] image = {R.drawable.berlin,
                R.drawable.cologne,
                R.drawable.dresden,
                R.drawable.frankfurter,
                R.drawable.freiburg,
                R.drawable.hamburg,
                R.drawable.heidelberg,
                R.drawable.kiel,
                R.drawable.munich,
                R.drawable.potsdam};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowFranceCities() {
        int[] name = {R.string.Lyon,
                R.string.Nice,
                R.string.Paris,
                R.string.Strasbourg};

        int[] image = {R.drawable.lyon,
                R.drawable.nice,
                R.drawable.paris,
                R.drawable.strasbourg};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowEgyptCities() {
        int[] name = {R.string.Alexandria,
                R.string.Cairo,
                R.string.Luxor,
                R.string.Sharm_El_Sheikh,
                R.string.Hurghada};

        int[] image = {R.drawable.alexandria,
                R.drawable.cairo,
                R.drawable.luxor,
                R.drawable.sharm_el_sheikh,
                R.drawable.hurghada};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowChinaCities() {
        int[] name = {R.string.Beijing,
                R.string.Chengdu,
                R.string.Guilin,
                R.string.Shanghai,
                R.string.Xi_an};

        int[] image = {R.drawable.beijing,
                R.drawable.chengdu,
                R.drawable.guilin,
                R.drawable.shanghai,
                R.drawable.xi_an};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowCanadaCities() {
        int[] name = {R.string.Montreal,
                R.string.Niagara_Falls,
                R.string.Quebec_City,
                R.string.The_Canadian_Rockies,
                R.string.The_Yukon,
                R.string.Toronto,
                R.string.Vancouver,
                R.string.Whistler};

        int[] image = {R.drawable.montreal,
                R.drawable.niagara_falls,
                R.drawable.quebec_city,
                R.drawable.the_canadian_rockies,
                R.drawable.the_yukon,
                R.drawable.toronto,
                R.drawable.vancouver,
                R.drawable.whistler};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowColombiaCities() {
        int[] name = {R.string.Bogota,
                R.string.Cali,
                R.string.Medellin};

        int[] image = {R.drawable.bogota,
                R.drawable.cali,
                R.drawable.medellin};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowBrazilCities() {
        int[] name = {R.string.Florianópolis,
                R.string.Foz_do_Lguaçu,
                R.string.Rio_de_Laneiro,
                R.string.Sao_Paulo};

        int[] image = {R.drawable.florianopolis,
                R.drawable.foz_do_lguacu,
                R.drawable.rio_de_janeiro,
                R.drawable.sao_paulo};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowBhutanCities() {
        int[] name = {R.string.Paro,
                R.string.Punakha,
                R.string.Thimphu,
                R.string.Wangduephodrang};

        int[] image = {R.drawable.paro,
                R.drawable.punakha,
                R.drawable.thimphu,
                R.drawable.wangduephodrang};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowBelgiumCities() {
        int[] name = {R.string.Antwerp,
                R.string.Bruges,
                R.string.Brussels,
                R.string.Ghent,
                R.string.Leuven,
                R.string.Liege,
                R.string.Mechelen,
                R.string.Mons,
                R.string.Namur};

        int[] image = {R.drawable.antwerp,
                R.drawable.bruges,
                R.drawable.brussels,
                R.drawable.ghent,
                R.drawable.leuven,
                R.drawable.liege,
                R.drawable.mechelen,
                R.drawable.mons,
                R.drawable.namur};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowAustriaCities() {
        int[] name = {R.string.East_Austria,
                R.string.Hallstatt,
                R.string.Innsbruck,
                R.string.Salzburg,
                R.string.Vienna};

        int[] image = {R.drawable.east_austria,
                R.drawable.hallstatt,
                R.drawable.innsbruck,
                R.drawable.salzburg,
                R.drawable.vienna};

        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowAustraliaCities() {
        int[] name = {R.string.Alice_Springs,
                R.string.Cairns,
                R.string.Canberra,
                R.string.Gold_Coast,
                R.string.Hobart,
                R.string.Melbourne,
                R.string.Perth,
                R.string.Sydney};

        int[] image = {R.drawable.alice_springs,
                R.drawable.cairns,
                R.drawable.canberra,
                R.drawable.gold_coast,
                R.drawable.hobart,
                R.drawable.melbourne,
                R.drawable.perth,
                R.drawable.sydney};
        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    private void ShowArgentinaCities() {
        int[] name = {R.string.Bariloche,
                R.string.Buenos_Aires,
                R.string.Cordoba,
                R.string.Mar_Del_Plata,
                R.string.Mendoza,
                R.string.Ushuaia};
        int[] image = {R.drawable.bariloche,
                R.drawable.buenos_aires,
                R.drawable.cordoba,
                R.drawable.mar_del_plata,
                R.drawable.mendoza,
                R.drawable.ushuaia};
        for (int i = 0; i < name.length; i++) {
            City model = new City(image[i], name[i]);
            list.add(model);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
