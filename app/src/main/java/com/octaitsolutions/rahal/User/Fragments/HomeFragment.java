package com.octaitsolutions.rahal.User.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.octaitsolutions.rahal.Adapter.CountryAdapter;
import com.octaitsolutions.rahal.Admin.AddShopping;
import com.octaitsolutions.rahal.Model.Country;
import com.octaitsolutions.rahal.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


public class HomeFragment extends Fragment implements MaterialSearchBar.OnSearchActionListener {

    private View view;
    private RecyclerView recyclerView;
    private ArrayList<Country> list;
    private CountryAdapter adapter;

    MaterialSearchBar searchBar;
    AutoCompleteTextView editText;
    LinearLayout topbar;
    ImageView searchIcon;
    RelativeLayout autoSearch , autoBack;

    int searchChecker = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        autoBack = view.findViewById(R.id.auto_back);
        autoBack.setVisibility(View.GONE);
        autoSearch = view.findViewById(R.id.auto_search);
        String[] countries = getResources().getStringArray(R.array.Country);
        editText = view.findViewById(R.id.actv);
        editText.setVisibility(View.GONE);
        ArrayAdapter<String> adapterone = new ArrayAdapter<String>(getContext(),
                R.layout.custom_list_item, R.id.text_view_list_item, countries);
        editText.setAdapter(adapterone);

        searchBar = view.findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);
        searchBar.setVisibility(View.GONE);
        searchBar.setNavButtonEnabled(true);

        topbar = view.findViewById(R.id.topbar);
        searchIcon = view.findViewById(R.id.search_icon);
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.home_recyclerview);
        list = new ArrayList<>();

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topbar.setVisibility(View.GONE);
                searchBar.setVisibility(View.GONE);
                searchBar.openSearch();
                editText.setVisibility(View.VISIBLE);
                autoBack.setVisibility(View.VISIBLE);
            }
        });

        autoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(View.GONE);
                autoBack.setVisibility(View.GONE);
                topbar.setVisibility(View.VISIBLE);
            }
        });
        autoSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query = editText.getText().toString().trim();
                if (query == null) {
                    Toasty.error(getContext(), "Write Country To Search It...!", Toasty.LENGTH_SHORT).show();
                } else {
                    String[] stringsList;
                    ArrayAdapter<String> stringAdapter;
                    stringsList = getResources().getStringArray(R.array.Country);
                    for (int i = 0; i < stringsList.length; i++) {

                        if (query.equalsIgnoreCase(stringsList[i])) {
                            list.clear();
                            //1
                            if (query.equalsIgnoreCase(getString(R.string.Argentina))) {
                                Country model = new Country(R.drawable.argentina, R.string.Argentina);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);

                            }
                            //2
                            if (query.equalsIgnoreCase(getString(R.string.Australia))) {
                                Country model = new Country(R.drawable.australia, R.string.Australia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //3
                            if (query.equalsIgnoreCase(getString(R.string.Austria))) {
                                Country model = new Country(R.drawable.austria, R.string.Austria);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //4
                            if (query.equalsIgnoreCase(getString(R.string.Belgium))) {
                                Country model = new Country(R.drawable.belgium, R.string.Belgium);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }
                            //5
                            if (query.equalsIgnoreCase(getString(R.string.Bhutan))) {
                                Country model = new Country(R.drawable.bhutan, R.string.Bhutan);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //6
                            if (query.equalsIgnoreCase(getString(R.string.Brazil))) {
                                Country model = new Country(R.drawable.brazil, R.string.Brazil);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //7
                            if (query.equalsIgnoreCase(getString(R.string.Colombia))) {
                                Country model = new Country(R.drawable.colombia, R.string.Colombia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //8
                            if (query.equalsIgnoreCase(getString(R.string.Colombia))) {
                                Country model = new Country(R.drawable.colombia, R.string.Colombia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //9
                            if (query.equalsIgnoreCase(getString(R.string.canada))) {
                                Country model = new Country(R.drawable.canda, R.string.canada);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //10
                            if (query.equalsIgnoreCase(getString(R.string.China))) {
                                Country model = new Country(R.drawable.china, R.string.China);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //11
                            if (query.equalsIgnoreCase(getString(R.string.Egypt))) {
                                Country model = new Country(R.drawable.egypt, R.string.Egypt);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //11
                            if (query.equalsIgnoreCase(getString(R.string.France))) {
                                Country model = new Country(R.drawable.france, R.string.France);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //12
                            if (query.equalsIgnoreCase(getString(R.string.Germany))) {
                                Country model = new Country(R.drawable.germany, R.string.Germany);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //13
                            if (query.equalsIgnoreCase(getString(R.string.Greece))) {
                                Country model = new Country(R.drawable.greece, R.string.Greece);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //14
                            if (query.equalsIgnoreCase(getString(R.string.India))) {
                                Country model = new Country(R.drawable.india, R.string.India);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //14
                            if (query.equalsIgnoreCase(getString(R.string.Indonesia))) {
                                Country model = new Country(R.drawable.indonesia, R.string.Indonesia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //15
                            if (query.equalsIgnoreCase(getString(R.string.Ireland))) {
                                Country model = new Country(R.drawable.ireland, R.string.Ireland);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //16
                            if (query.equalsIgnoreCase(getString(R.string.Japan))) {
                                Country model = new Country(R.drawable.japan, R.string.Japan);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //17
                            if (query.equalsIgnoreCase(getString(R.string.Italy))) {
                                Country model = new Country(R.drawable.i, R.string.Italy);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //18
                            if (query.equalsIgnoreCase(getString(R.string.Kenya))) {
                                Country model = new Country(R.drawable.kenya, R.string.Kenya);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //19
                            if (query.equalsIgnoreCase(getString(R.string.Korea))) {
                                Country model = new Country(R.drawable.korea, R.string.Korea);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //20
                            if (query.equalsIgnoreCase(getString(R.string.Malaysia))) {
                                Country model = new Country(R.drawable.malaysia, R.string.Malaysia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //21
                            if (query.equalsIgnoreCase(getString(R.string.Netherlands))) {
                                Country model = new Country(R.drawable.netherlands, R.string.Netherlands);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //22
                            if (query.equalsIgnoreCase(getString(R.string.New_Zealand))) {
                                Country model = new Country(R.drawable.new_zealand, R.string.New_Zealand);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //23
                            if (query.equalsIgnoreCase(getString(R.string.Philippines))) {
                                Country model = new Country(R.drawable.philippines, R.string.Philippines);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //24
                            if (query.equalsIgnoreCase(getString(R.string.Poland))) {
                                Country model = new Country(R.drawable.poland, R.string.Poland);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //25
                            if (query.equalsIgnoreCase(getString(R.string.Portugal))) {
                                Country model = new Country(R.drawable.portugal, R.string.Portugal);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //26
                            if (query.equalsIgnoreCase(getString(R.string.Russia))) {
                                Country model = new Country(R.drawable.russia, R.string.Russia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //27
                            if (query.equalsIgnoreCase(getString(R.string.Saudi_Arabia))) {
                                Country model = new Country(R.drawable.saudi_arabia, R.string.Saudi_Arabia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //28
                            if (query.equalsIgnoreCase(getString(R.string.Scotland))) {
                                Country model = new Country(R.drawable.scotland, R.string.Scotland);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //29
                            if (query.equalsIgnoreCase(getString(R.string.Seychelles))) {
                                Country model = new Country(R.drawable.seychelles, R.string.Seychelles);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //30
                            if (query.equalsIgnoreCase(getString(R.string.South_Africa))) {
                                Country model = new Country(R.drawable.south_africa, R.string.South_Africa);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //31
                            if (query.equalsIgnoreCase(getString(R.string.Spain))) {
                                Country model = new Country(R.drawable.spain, R.string.Spain);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //32
                            if (query.equalsIgnoreCase(getString(R.string.Sri_Lanka))) {
                                Country model = new Country(R.drawable.sri_lanka, R.string.Sri_Lanka);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //33
                            if (query.equalsIgnoreCase(getString(R.string.Switzerland))) {
                                Country model = new Country(R.drawable.switzerland, R.string.Switzerland);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //34
                            if (query.equalsIgnoreCase(getString(R.string.Bahamas))) {
                                Country model = new Country(R.drawable.bahamas, R.string.Bahamas);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //35
                            if (query.equalsIgnoreCase(getString(R.string.Czech_Republic))) {
                                Country model = new Country(R.drawable.czech_republic, R.string.Czech_Republic);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //36
                            if (query.equalsIgnoreCase(getString(R.string.Denmark))) {
                                Country model = new Country(R.drawable.denmark, R.string.Denmark);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //37
                            if (query.equalsIgnoreCase(getString(R.string.Finland))) {
                                Country model = new Country(R.drawable.finland, R.string.Finland);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //38
                            if (query.equalsIgnoreCase(getString(R.string.Hungary))) {
                                Country model = new Country(R.drawable.hungary, R.string.Hungary);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //39
                            if (query.equalsIgnoreCase(getString(R.string.Iceland))) {
                                Country model = new Country(R.drawable.ice_land, R.string.Iceland);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //40
                            if (query.equalsIgnoreCase(getString(R.string.Luxembourg))) {
                                Country model = new Country(R.drawable.luxembourg, R.string.Luxembourg);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //41
                            if (query.equalsIgnoreCase(getString(R.string.Mexico))) {
                                Country model = new Country(R.drawable.mexico, R.string.Mexico);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //42
                            if (query.equalsIgnoreCase(getString(R.string.Morocoo))) {
                                Country model = new Country(R.drawable.morocco, R.string.Morocoo);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //43
                            if (query.equalsIgnoreCase(getString(R.string.Nepal))) {
                                Country model = new Country(R.drawable.nepal, R.string.Nepal);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //44
                            if (query.equalsIgnoreCase(getString(R.string.Norway))) {
                                Country model = new Country(R.drawable.norway, R.string.Norway);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //45
                            if (query.equalsIgnoreCase(getString(R.string.Peru))) {
                                Country model = new Country(R.drawable.peru, R.string.Peru);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //46
                            if (query.equalsIgnoreCase(getString(R.string.Serbia))) {
                                Country model = new Country(R.drawable.serbia, R.string.Serbia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //47
                            if (query.equalsIgnoreCase(getString(R.string.Slovakia))) {
                                Country model = new Country(R.drawable.slovakia, R.string.Slovakia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //48
                            if (query.equalsIgnoreCase(getString(R.string.Turkey))) {
                                Country model = new Country(R.drawable.turkey, R.string.Turkey);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //49
                            if (query.equalsIgnoreCase(getString(R.string.UAE))) {
                                Country model = new Country(R.drawable.uae, R.string.UAE);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //50
                            if (query.equalsIgnoreCase(getString(R.string.United_Kingdom))) {
                                Country model = new Country(R.drawable.united_kingdom, R.string.United_Kingdom);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //51
                            if (query.equalsIgnoreCase(getString(R.string.USA))) {
                                Country model = new Country(R.drawable.usa, R.string.USA);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //52
                            if (query.equalsIgnoreCase(getString(R.string.Uzbekistan))) {
                                Country model = new Country(R.drawable.uzbekistan, R.string.Uzbekistan);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //53
                            if (query.equalsIgnoreCase(getString(R.string.Vitnam))) {
                                Country model = new Country(R.drawable.vitnam, R.string.Vitnam);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //54
                            if (query.equalsIgnoreCase(getString(R.string.Thailand))) {
                                Country model = new Country(R.drawable.thailand, R.string.Thailand);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //55
                            if (query.equalsIgnoreCase(getString(R.string.Pakistan))) {
                                Country model = new Country(R.drawable.pakistan, R.string.Pakistan);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //56
                            if (query.equalsIgnoreCase(getString(R.string.Croatia))) {
                                Country model = new Country(R.drawable.croatiaa, R.string.Croatia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //57
                            if (query.equalsIgnoreCase(getString(R.string.Estonia))) {
                                Country model = new Country(R.drawable.estoniaa, R.string.Estonia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //58
                            if (query.equalsIgnoreCase(getString(R.string.Bulgaria))) {
                                Country model = new Country(R.drawable.bulgariaa, R.string.Bulgaria);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //59
                            if (query.equalsIgnoreCase(getString(R.string.Costa_Rica))) {
                                Country model = new Country(R.drawable.costa_ricaa, R.string.Costa_Rica);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            //60
                            if (query.equalsIgnoreCase(getString(R.string.Cambodia))) {
                                Country model = new Country(R.drawable.cambodia, R.string.Cambodia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }


                            // Special Countries
                            if (query.equalsIgnoreCase(getString(R.string.Qatar))) {
                                Country model = new Country(R.drawable.q, R.string.Qatar);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            if (query.equalsIgnoreCase(getString(R.string.Singapore))) {
                                Country model = new Country(R.drawable.singapore, R.string.Singapore);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            if (query.equalsIgnoreCase(getString(R.string.Maldives))) {
                                Country model = new Country(R.drawable.maldives, R.string.Maldives);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            if (query.equalsIgnoreCase(getString(R.string.Oman))) {
                                Country model = new Country(R.drawable.oman, R.string.Oman);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            if (query.equalsIgnoreCase(getString(R.string.Bahrain))) {
                                Country model = new Country(R.drawable.bahrain, R.string.Bahrain);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }

                            if (query.equalsIgnoreCase(getString(R.string.Kuwait))) {
                                Country model = new Country(R.drawable.kuwait, R.string.Kuwait);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);

                            }
                            if (query.equalsIgnoreCase(getString(R.string.Jordan))) {
                                Country model = new Country(R.drawable.jordan, R.string.Jordan);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }
                            if (query.equalsIgnoreCase(getString(R.string.Algeria))) {
                                Country model = new Country(R.drawable.algeria, R.string.Algeria);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }
                            if (query.equalsIgnoreCase(getString(R.string.Tunisia))) {
                                Country model = new Country(R.drawable.tunisia, R.string.Tunisia);
                                list.add(model);
                                adapter = new CountryAdapter(getContext(), list);
                                recyclerView.setAdapter(adapter);
                            }
                        } else {
//                    Toasty.error(getContext(), "Sorry! We are unable find your search country..." , Toasty.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        int[] name = {R.string.Maldives,
                R.string.Argentina,
                R.string.Australia,
                R.string.Austria,
                R.string.Algeria,
                R.string.Bhutan,
                R.string.Belgium,
                R.string.Brazil,
                R.string.Bahrain,
                R.string.Bahamas,
                R.string.Bulgaria,
                R.string.Cambodia,
                R.string.canada,
                R.string.China,
                R.string.Czech_Republic,
                R.string.Colombia,
                R.string.Croatia,
                R.string.Costa_Rica,
                R.string.Denmark,
                R.string.Egypt,
                R.string.Estonia,
                R.string.France,
                R.string.Finland,
                R.string.Germany,
                R.string.Greece,
                R.string.Hungary,
                R.string.Indonesia,
                R.string.Ireland,
                R.string.India,
                R.string.Italy,
                R.string.Iceland,
                R.string.Japan,
                R.string.Jordan,
                R.string.Kenya,
                R.string.Kuwait,
                R.string.Korea,
                R.string.Luxembourg,
                R.string.Malaysia,
                R.string.Mauritius,
                R.string.Mexico,
                R.string.Morocoo,
                R.string.Netherlands,
                R.string.New_Zealand,
                R.string.Nepal,
                R.string.Norway,
                R.string.Oman,
                R.string.Pakistan,
                R.string.Peru,
                R.string.Philippines,
                R.string.Poland,
                R.string.Portugal,
                R.string.Russia,
                R.string.Scotland,
                R.string.Seychelles,
                R.string.South_Africa,
                R.string.Spain,
                R.string.Sri_Lanka,
                R.string.Saudi_Arabia,
                R.string.Switzerland,
                R.string.Sweden,
                R.string.Singapore,
                R.string.Serbia,
                R.string.Slovakia,
                R.string.Thailand,
                R.string.Turkey,
                R.string.United_Kingdom,
                R.string.Uzbekistan,
                R.string.UAE,
                R.string.USA,
                R.string.Vitnam,
                R.string.Qatar,
                R.string.Tunisia};

        int[] image = {R.drawable.maldives,
                R.drawable.argentina,
                R.drawable.australia,
                R.drawable.austria,
                R.drawable.algeria,
                R.drawable.bhutan,//butt
                R.drawable.belgium,
                R.drawable.brazil,
                R.drawable.bahrain,
                R.drawable.bahamas,
                R.drawable.bulgariaa,
                R.drawable.cambodia,
                R.drawable.canda,//canada
                R.drawable.china,
                R.drawable.czech_republic,//cr
                R.drawable.colombia,
                R.drawable.croatiaa,//croa
                R.drawable.costa_ricaa,//costa
                R.drawable.denmark,
                R.drawable.egypt,//egyp
                R.drawable.estoniaa,//esto
                R.drawable.france,
                R.drawable.finland,//fina
                R.drawable.germany,
                R.drawable.greece,
                R.drawable.hungary,
                R.drawable.indonesia,
                R.drawable.ireland,
                R.drawable.india,
                R.drawable.i,
                R.drawable.ice_land,
                R.drawable.japan,
                R.drawable.jordan,
                R.drawable.kenya,
                R.drawable.kuwait,
                R.drawable.korea,
                R.drawable.luxembourg,
                R.drawable.malaysia,
                R.drawable.mauritius,
                R.drawable.mexico,
                R.drawable.morocco,
                R.drawable.netherlands,
                R.drawable.new_zealand,
                R.drawable.nepal,
                R.drawable.norway,
                R.drawable.oman,
                R.drawable.pakistan,
                R.drawable.peru,
                R.drawable.philippines,
                R.drawable.poland,
                R.drawable.portugal,
                R.drawable.russia,
                R.drawable.scotland,
                R.drawable.seychelles,
                R.drawable.south_africa,
                R.drawable.spain,
                R.drawable.sri_lanka,//sirla
                R.drawable.saudi_arabia,//saudi
                R.drawable.switzerland,//switer
                R.drawable.sweden,//swe
                R.drawable.singapore,
                R.drawable.serbia,//ser
                R.drawable.slovakia,//slova
                R.drawable.thailand,//thai
                R.drawable.turkey,//turkey
                R.drawable.united_kingdom,
                R.drawable.uzbekistan,
                R.drawable.uae,
                R.drawable.usa,
                R.drawable.vitnam,
                R.drawable.q,
                R.drawable.tunisia};


        for (int i = 0; i < name.length; i++) {
            Country model = new Country(image[i], name[i]);
            list.add(model);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CountryAdapter(getContext(), list);

        recyclerView.setAdapter(adapter);


        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }


    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

        searchChecker = 1;
        String query = text.toString().trim();
        if (query == null) {
            Toasty.error(getContext(), "Write Country To Search It...!", Toasty.LENGTH_SHORT).show();
        } else {
            String[] stringsList;
            ArrayAdapter<String> stringAdapter;
            stringsList = getResources().getStringArray(R.array.Country);
            for (int i = 0; i < stringsList.length; i++) {

                if (query.equalsIgnoreCase(stringsList[i])) {
                    list.clear();
                    //1
                    if (query.equalsIgnoreCase(getString(R.string.Argentina))) {
                        Country model = new Country(R.drawable.argentina, R.string.Argentina);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);

                    }
                    //2
                    if (query.equalsIgnoreCase(getString(R.string.Australia))) {
                        Country model = new Country(R.drawable.australia, R.string.Australia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //3
                    if (query.equalsIgnoreCase(getString(R.string.Austria))) {
                        Country model = new Country(R.drawable.austria, R.string.Austria);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //4
                    if (query.equalsIgnoreCase(getString(R.string.Belgium))) {
                        Country model = new Country(R.drawable.belgium, R.string.Belgium);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }
                    //5
                    if (query.equalsIgnoreCase(getString(R.string.Bhutan))) {
                        Country model = new Country(R.drawable.bhutan, R.string.Bhutan);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //6
                    if (query.equalsIgnoreCase(getString(R.string.Brazil))) {
                        Country model = new Country(R.drawable.brazil, R.string.Brazil);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //7
                    if (query.equalsIgnoreCase(getString(R.string.Colombia))) {
                        Country model = new Country(R.drawable.colombia, R.string.Colombia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //8
                    if (query.equalsIgnoreCase(getString(R.string.Colombia))) {
                        Country model = new Country(R.drawable.colombia, R.string.Colombia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //9
                    if (query.equalsIgnoreCase(getString(R.string.canada))) {
                        Country model = new Country(R.drawable.canda, R.string.canada);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //10
                    if (query.equalsIgnoreCase(getString(R.string.China))) {
                        Country model = new Country(R.drawable.china, R.string.China);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //11
                    if (query.equalsIgnoreCase(getString(R.string.Egypt))) {
                        Country model = new Country(R.drawable.egypt, R.string.Egypt);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //11
                    if (query.equalsIgnoreCase(getString(R.string.France))) {
                        Country model = new Country(R.drawable.france, R.string.France);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //12
                    if (query.equalsIgnoreCase(getString(R.string.Germany))) {
                        Country model = new Country(R.drawable.germany, R.string.Germany);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //13
                    if (query.equalsIgnoreCase(getString(R.string.Greece))) {
                        Country model = new Country(R.drawable.greece, R.string.Greece);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //14
                    if (query.equalsIgnoreCase(getString(R.string.India))) {
                        Country model = new Country(R.drawable.india, R.string.India);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //14
                    if (query.equalsIgnoreCase(getString(R.string.Indonesia))) {
                        Country model = new Country(R.drawable.indonesia, R.string.Indonesia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //15
                    if (query.equalsIgnoreCase(getString(R.string.Ireland))) {
                        Country model = new Country(R.drawable.ireland, R.string.Ireland);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //16
                    if (query.equalsIgnoreCase(getString(R.string.Japan))) {
                        Country model = new Country(R.drawable.japan, R.string.Japan);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //17
                    if (query.equalsIgnoreCase(getString(R.string.Italy))) {
                        Country model = new Country(R.drawable.i, R.string.Italy);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //18
                    if (query.equalsIgnoreCase(getString(R.string.Kenya))) {
                        Country model = new Country(R.drawable.kenya, R.string.Kenya);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //19
                    if (query.equalsIgnoreCase(getString(R.string.Korea))) {
                        Country model = new Country(R.drawable.korea, R.string.Korea);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //20
                    if (query.equalsIgnoreCase(getString(R.string.Malaysia))) {
                        Country model = new Country(R.drawable.malaysia, R.string.Malaysia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //21
                    if (query.equalsIgnoreCase(getString(R.string.Netherlands))) {
                        Country model = new Country(R.drawable.netherlands, R.string.Netherlands);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //22
                    if (query.equalsIgnoreCase(getString(R.string.New_Zealand))) {
                        Country model = new Country(R.drawable.new_zealand, R.string.New_Zealand);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //23
                    if (query.equalsIgnoreCase(getString(R.string.Philippines))) {
                        Country model = new Country(R.drawable.philippines, R.string.Philippines);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //24
                    if (query.equalsIgnoreCase(getString(R.string.Poland))) {
                        Country model = new Country(R.drawable.poland, R.string.Poland);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //25
                    if (query.equalsIgnoreCase(getString(R.string.Portugal))) {
                        Country model = new Country(R.drawable.portugal, R.string.Portugal);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //26
                    if (query.equalsIgnoreCase(getString(R.string.Russia))) {
                        Country model = new Country(R.drawable.russia, R.string.Russia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //27
                    if (query.equalsIgnoreCase(getString(R.string.Saudi_Arabia))) {
                        Country model = new Country(R.drawable.saudi_arabia, R.string.Saudi_Arabia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //28
                    if (query.equalsIgnoreCase(getString(R.string.Scotland))) {
                        Country model = new Country(R.drawable.scotland, R.string.Scotland);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //29
                    if (query.equalsIgnoreCase(getString(R.string.Seychelles))) {
                        Country model = new Country(R.drawable.seychelles, R.string.Seychelles);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //30
                    if (query.equalsIgnoreCase(getString(R.string.South_Africa))) {
                        Country model = new Country(R.drawable.south_africa, R.string.South_Africa);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //31
                    if (query.equalsIgnoreCase(getString(R.string.Spain))) {
                        Country model = new Country(R.drawable.spain, R.string.Spain);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //32
                    if (query.equalsIgnoreCase(getString(R.string.Sri_Lanka))) {
                        Country model = new Country(R.drawable.sri_lanka, R.string.Sri_Lanka);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //33
                    if (query.equalsIgnoreCase(getString(R.string.Switzerland))) {
                        Country model = new Country(R.drawable.switzerland, R.string.Switzerland);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //34
                    if (query.equalsIgnoreCase(getString(R.string.Bahamas))) {
                        Country model = new Country(R.drawable.bahamas, R.string.Bahamas);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //35
                    if (query.equalsIgnoreCase(getString(R.string.Czech_Republic))) {
                        Country model = new Country(R.drawable.czech_republic, R.string.Czech_Republic);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //36
                    if (query.equalsIgnoreCase(getString(R.string.Denmark))) {
                        Country model = new Country(R.drawable.denmark, R.string.Denmark);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //37
                    if (query.equalsIgnoreCase(getString(R.string.Finland))) {
                        Country model = new Country(R.drawable.finland, R.string.Finland);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //38
                    if (query.equalsIgnoreCase(getString(R.string.Hungary))) {
                        Country model = new Country(R.drawable.hungary, R.string.Hungary);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //39
                    if (query.equalsIgnoreCase(getString(R.string.Iceland))) {
                        Country model = new Country(R.drawable.ice_land, R.string.Iceland);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //40
                    if (query.equalsIgnoreCase(getString(R.string.Luxembourg))) {
                        Country model = new Country(R.drawable.luxembourg, R.string.Luxembourg);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //41
                    if (query.equalsIgnoreCase(getString(R.string.Mexico))) {
                        Country model = new Country(R.drawable.mexico, R.string.Mexico);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //42
                    if (query.equalsIgnoreCase(getString(R.string.Morocoo))) {
                        Country model = new Country(R.drawable.morocco, R.string.Morocoo);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //43
                    if (query.equalsIgnoreCase(getString(R.string.Nepal))) {
                        Country model = new Country(R.drawable.nepal, R.string.Nepal);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //44
                    if (query.equalsIgnoreCase(getString(R.string.Norway))) {
                        Country model = new Country(R.drawable.norway, R.string.Norway);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //45
                    if (query.equalsIgnoreCase(getString(R.string.Peru))) {
                        Country model = new Country(R.drawable.peru, R.string.Peru);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //46
                    if (query.equalsIgnoreCase(getString(R.string.Serbia))) {
                        Country model = new Country(R.drawable.serbia, R.string.Serbia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //47
                    if (query.equalsIgnoreCase(getString(R.string.Slovakia))) {
                        Country model = new Country(R.drawable.slovakia, R.string.Slovakia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //48
                    if (query.equalsIgnoreCase(getString(R.string.Turkey))) {
                        Country model = new Country(R.drawable.turkey, R.string.Turkey);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //49
                    if (query.equalsIgnoreCase(getString(R.string.UAE))) {
                        Country model = new Country(R.drawable.uae, R.string.UAE);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //50
                    if (query.equalsIgnoreCase(getString(R.string.United_Kingdom))) {
                        Country model = new Country(R.drawable.united_kingdom, R.string.United_Kingdom);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //51
                    if (query.equalsIgnoreCase(getString(R.string.USA))) {
                        Country model = new Country(R.drawable.usa, R.string.USA);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //52
                    if (query.equalsIgnoreCase(getString(R.string.Uzbekistan))) {
                        Country model = new Country(R.drawable.uzbekistan, R.string.Uzbekistan);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //53
                    if (query.equalsIgnoreCase(getString(R.string.Vitnam))) {
                        Country model = new Country(R.drawable.vitnam, R.string.Vitnam);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //54
                    if (query.equalsIgnoreCase(getString(R.string.Thailand))) {
                        Country model = new Country(R.drawable.thailand, R.string.Thailand);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //55
                    if (query.equalsIgnoreCase(getString(R.string.Pakistan))) {
                        Country model = new Country(R.drawable.pakistan, R.string.Pakistan);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //56
                    if (query.equalsIgnoreCase(getString(R.string.Croatia))) {
                        Country model = new Country(R.drawable.croatiaa, R.string.Croatia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //57
                    if (query.equalsIgnoreCase(getString(R.string.Estonia))) {
                        Country model = new Country(R.drawable.estoniaa, R.string.Estonia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //58
                    if (query.equalsIgnoreCase(getString(R.string.Bulgaria))) {
                        Country model = new Country(R.drawable.bulgariaa, R.string.Bulgaria);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //59
                    if (query.equalsIgnoreCase(getString(R.string.Costa_Rica))) {
                        Country model = new Country(R.drawable.costa_ricaa, R.string.Costa_Rica);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    //60
                    if (query.equalsIgnoreCase(getString(R.string.Cambodia))) {
                        Country model = new Country(R.drawable.cambodia, R.string.Cambodia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }


                    // Special Countries
                    if (query.equalsIgnoreCase(getString(R.string.Qatar))) {
                        Country model = new Country(R.drawable.q, R.string.Qatar);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    if (query.equalsIgnoreCase(getString(R.string.Singapore))) {
                        Country model = new Country(R.drawable.singapore, R.string.Singapore);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    if (query.equalsIgnoreCase(getString(R.string.Maldives))) {
                        Country model = new Country(R.drawable.maldives, R.string.Maldives);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    if (query.equalsIgnoreCase(getString(R.string.Oman))) {
                        Country model = new Country(R.drawable.oman, R.string.Oman);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    if (query.equalsIgnoreCase(getString(R.string.Bahrain))) {
                        Country model = new Country(R.drawable.bahrain, R.string.Bahrain);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    if (query.equalsIgnoreCase(getString(R.string.Kuwait))) {
                        Country model = new Country(R.drawable.kuwait, R.string.Kuwait);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);

                    }
                    if (query.equalsIgnoreCase(getString(R.string.Jordan))) {
                        Country model = new Country(R.drawable.jordan, R.string.Jordan);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }
                    if (query.equalsIgnoreCase(getString(R.string.Algeria))) {
                        Country model = new Country(R.drawable.algeria, R.string.Algeria);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }
                    if (query.equalsIgnoreCase(getString(R.string.Tunisia))) {
                        Country model = new Country(R.drawable.tunisia, R.string.Tunisia);
                        list.add(model);
                        adapter = new CountryAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }
                } else {
//                    Toasty.error(getContext(), "Sorry! We are unable find your search country..." , Toasty.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onButtonClicked(int buttonCode) {


        switch (buttonCode) {

            case MaterialSearchBar.BUTTON_BACK:
                searchBar.closeSearch();
                topbar.setVisibility(View.VISIBLE);
                searchBar.setVisibility(View.GONE);
                allCountriesRecyclerview();
                searchChecker = 0;
                break;

        }
    }

    public void allCountriesRecyclerview() {
        list.clear();
        int[] name = {R.string.Maldives,
                R.string.Argentina,
                R.string.Australia,
                R.string.Austria,
                R.string.Algeria,
                R.string.Bhutan,
                R.string.Belgium,
                R.string.Brazil,
                R.string.Bahrain,
                R.string.Bahamas,
                R.string.Bulgaria,
                R.string.Cambodia,
                R.string.canada,
                R.string.China,
                R.string.Czech_Republic,
                R.string.Colombia,
                R.string.Croatia,
                R.string.Costa_Rica,
                R.string.Denmark,
                R.string.Egypt,
                R.string.Estonia,
                R.string.France,
                R.string.Finland,
                R.string.Germany,
                R.string.Greece,
                R.string.Hungary,
                R.string.Indonesia,
                R.string.Ireland,
                R.string.India,
                R.string.Italy,
                R.string.Iceland,
                R.string.Japan,
                R.string.Jordan,
                R.string.Kenya,
                R.string.Kuwait,
                R.string.Korea,
                R.string.Luxembourg,
                R.string.Malaysia,
                R.string.Mauritius,
                R.string.Mexico,
                R.string.Morocoo,
                R.string.Netherlands,
                R.string.New_Zealand,
                R.string.Nepal,
                R.string.Norway,
                R.string.Oman,
                R.string.Pakistan,
                R.string.Peru,
                R.string.Philippines,
                R.string.Poland,
                R.string.Portugal,
                R.string.Russia,
                R.string.Scotland,
                R.string.Seychelles,
                R.string.South_Africa,
                R.string.Spain,
                R.string.Sri_Lanka,
                R.string.Saudi_Arabia,
                R.string.Switzerland,
                R.string.Sweden,
                R.string.Singapore,
                R.string.Serbia,
                R.string.Slovakia,
                R.string.Thailand,
                R.string.Turkey,
                R.string.United_Kingdom,
                R.string.Uzbekistan,
                R.string.UAE,
                R.string.USA,
                R.string.Vitnam,
                R.string.Qatar,
                R.string.Tunisia};

        int[] image = {R.drawable.maldives,
                R.drawable.argentina,
                R.drawable.australia,
                R.drawable.austria,
                R.drawable.algeria,
                R.drawable.bhutan,//bhutann
                R.drawable.belgium,
                R.drawable.brazil,
                R.drawable.bahrain,
                R.drawable.bahamas,
                R.drawable.bulgariaa,
                R.drawable.cambodia,
                R.drawable.canda,//canda
                R.drawable.china,
                R.drawable.czech_republic,//cr
                R.drawable.colombia,
                R.drawable.croatiaa,//croatiaa
                R.drawable.costa_ricaa,//costa_rica
                R.drawable.denmark,
                R.drawable.egypt,//egy
                R.drawable.estoniaa,//estonia
                R.drawable.france,
                R.drawable.finland,//fina
                R.drawable.germany,
                R.drawable.greece,
                R.drawable.hungary,
                R.drawable.indonesia,
                R.drawable.ireland,
                R.drawable.india,
                R.drawable.i,
                R.drawable.ice_land,
                R.drawable.japan,
                R.drawable.jordan,
                R.drawable.kenya,
                R.drawable.kuwait,
                R.drawable.korea,
                R.drawable.luxembourg,
                R.drawable.malaysia,
                R.drawable.mauritius,
                R.drawable.mexico,
                R.drawable.morocco,
                R.drawable.netherlands,
                R.drawable.new_zealand,
                R.drawable.nepal,
                R.drawable.norway,
                R.drawable.oman,
                R.drawable.pakistan,
                R.drawable.peru,
                R.drawable.philippines,
                R.drawable.poland,
                R.drawable.portugal,
                R.drawable.russia,
                R.drawable.scotland,
                R.drawable.seychelles,
                R.drawable.south_africa,
                R.drawable.spain,
                R.drawable.sri_lanka,//srila
                R.drawable.saudi_arabia,//saudi
                R.drawable.switzerland,//switerlan
                R.drawable.sweden,//sweden
                R.drawable.singapore,
                R.drawable.serbia,//serbia
                R.drawable.slovakia,//slovakia
                R.drawable.thailand,//thai
                R.drawable.turkey,//turkey
                R.drawable.united_kingdom,
                R.drawable.uzbekistan,
                R.drawable.uae,
                R.drawable.usa,
                R.drawable.vitnam,
                R.drawable.q,
                R.drawable.tunisia};


        for (int i = 0; i < name.length; i++) {
            Country model = new Country(image[i], name[i]);
            list.add(model);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CountryAdapter(getContext(), list);

        recyclerView.setAdapter(adapter);

    }


}
