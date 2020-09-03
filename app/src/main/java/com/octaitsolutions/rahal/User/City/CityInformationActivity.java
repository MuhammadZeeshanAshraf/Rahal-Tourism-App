package com.octaitsolutions.rahal.User.City;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.codesgood.views.JustifiedTextView;
import com.octaitsolutions.rahal.R;

public class CityInformationActivity extends AppCompatActivity {

    TextView name ;
    JustifiedTextView detail;
    ImageView back;

    int country_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_information);

        name = findViewById(R.id.ci_name);
        detail = findViewById(R.id.ci_desc_justify);
        back = findViewById(R.id.iv_back);

        country_name = getIntent().getIntExtra("name", 0);
        name.setText(country_name);

        switch (country_name) {
            // 1
            case R.string.Argentina:
                detail.setText(R.string.Argentina_Info);
                break;
            // 2
            case R.string.Australia:
                detail.setText(R.string.Australia_Info);
                break;
            // 3
            case R.string.Austria:
                detail.setText(R.string.Austria_Info);
                break;
            // 4
            case R.string.Belgium:
                detail.setText(R.string.Belgium_Info);
                break;
            // 5
            case R.string.Bhutan:
                detail.setText(R.string.Bhutan_Info);
                break;
            // 6
            case R.string.Brazil:
                detail.setText(R.string.Brazil_Info);
                break;
            // 7
            case R.string.Colombia:
                detail.setText(R.string.Colombia_Info);
                break;
            // 8
            case R.string.canada:
                detail.setText(R.string.canada_Info);
                break;
            // 9
            case R.string.China:
                detail.setText(R.string.China_Info);
                break;
            // 10
            case R.string.Egypt:
                detail.setText(R.string.Egypt_Info);
                break;
            // 11
            case R.string.France:
                detail.setText(R.string.France_Info);
                break;
            // 12
            case R.string.Germany:
                detail.setText(R.string.Germany_Info);
                break;
            // 13
            case R.string.Greece:
                detail.setText(R.string.Greece_Info);
                break;
            // 14
            case R.string.India:
                detail.setText(R.string.India_Info);
                break;
            // 15
            case R.string.Indonesia:
                detail.setText(R.string.Indonesia_Info);
                break;
            // 16
            case R.string.Ireland:
                detail.setText(R.string.Ireland_Info);
                break;
            // 17
            case R.string.Italy:
                detail.setText(R.string.Italy_Info);
                break;
            // 18
            case R.string.Japan:
                detail.setText(R.string.Japan_Info);
                break;
            // 19
            case R.string.Kenya:
                detail.setText(R.string.Kenya_Info);
                break;
            // 20
            case R.string.Korea:
                detail.setText(R.string.Korea_Info);
                break;
            // 21
            case R.string.Malaysia:
                detail.setText(R.string.Malaysia_Info);
                break;
            // 22
            case R.string.Netherlands:
                detail.setText(R.string.Netherlands_Info);
                break;
            // 23
            case R.string.New_Zealand:
                detail.setText(R.string.New_Zealand_Info);
                break;
            // 24
            case R.string.Philippines:
                detail.setText(R.string.Philippines_Info);
                break;
            // 25
            case R.string.Poland:
                detail.setText(R.string.Poland_Info);
                break;
            // 26
            case R.string.Portugal:
                detail.setText(R.string.Portugal_Info);
                break;
            // 27
            case R.string.Russia:
                detail.setText(R.string.Russia_Info);
                break;
            // 28
            case R.string.Saudi_Arabia:
                detail.setText(R.string.Saudi_Arabia_Info);
                break;
            // 29
            case R.string.Scotland:
                detail.setText(R.string.Scotland_Info);
                break;
            // 30
            case R.string.Seychelles:
                detail.setText(R.string.Seychelles_Info);
                break;
            // 31
            case R.string.South_Africa:
                detail.setText(R.string.South_Africa_Info);
                break;
            // 32
            case R.string.Spain:
                detail.setText(R.string.Spain_Info);
                break;
            // 33
            case R.string.Sri_Lanka:
                detail.setText(R.string.Sri_Lanka_Info);
                break;
            // 34
            case R.string.Switzerland:
                detail.setText(R.string.Switzerland_Info);
                break;
            // 35
            case R.string.Bahamas:
                detail.setText(R.string.Bahamas_Info);
                break;
            // 36
            case R.string.Czech_Republic:
                detail.setText(R.string.Czech_Republic_Info);
                break;
            // 37
            case R.string.Denmark:
                detail.setText(R.string.Denmark_Info);
                break;
            // 38
            case R.string.Finland:
                detail.setText(R.string.Finland_Info);
                break;
            // 39
            case R.string.Hungary:
                detail.setText(R.string.Hungary_Info);
                break;
            // 40
            case R.string.Iceland:
                detail.setText(R.string.Iceland_Info);
                break;
            // 41
            case R.string.Luxembourg:
                detail.setText(R.string.Luxembourg_Info);
                break;
            // 42
            case R.string.Mexico:
                detail.setText(R.string.Mexico_Info);
                break;
            // 42
            case R.string.Morocoo:
                detail.setText(R.string.Morocoo_Info);
                break;
            // 43
            case R.string.Nepal:
                detail.setText(R.string.Nepal_Info);
                break;
            // 44
            case R.string.Norway:
                detail.setText(R.string.Norway_Info);
                break;
            // 45
            case R.string.Peru:
                detail.setText(R.string.Peru_Info);
                break;
            // 46
            case R.string.Serbia:
                detail.setText(R.string.Serbia_Info);
                break;
            // 47
            case R.string.Slovakia:
                detail.setText(R.string.Slovakia_Info);
                break;
            // 48
            case R.string.Sweden:
                detail.setText(R.string.Sweden_Info);
                break;
            // 49
            case R.string.Turkey:
                detail.setText(R.string.Turkey_Info);
                break;
            // 50
            case R.string.UAE:
                detail.setText(R.string.UAE_Info);
                break;
            // 51
            case R.string.United_Kingdom:
                detail.setText(R.string.United_Kingdom_Info);
                break;
            // 52
            case R.string.USA:
                detail.setText(R.string.USA_Info);
                break;
            // 53
            case R.string.Uzbekistan:
                detail.setText(R.string.Uzbekistan_Info);
                break;
            // 54
            case R.string.Vitnam:
                detail.setText(R.string.Vitnam_Info);
                break;
            // 55
            case R.string.Thailand:
                detail.setText(R.string.Thailand_Info);
                break;
            // 56
            case R.string.Pakistan:
                detail.setText(R.string.Pakistan_Info);
                break;
            // 57
            case R.string.Croatia:
                detail.setText(R.string.Croatia_Info);
                break;
            // 58
            case R.string.Estonia:
                detail.setText(R.string.Estonia_Info);
                break;
            // 59
            case R.string.Bulgaria:
                detail.setText(R.string.Bulgaria_Info);
                break;
            // 60
            case R.string.Costa_Rica:
                detail.setText(R.string.Costa_Rica_Info);
                break;
            // 61
            case R.string.Qatar:
                detail.setText(R.string.Qatar_Info);
                break;
            // 62
            case R.string.Singapore:
                detail.setText(R.string.Singapore_Info);
                break;
            // 63
            case R.string.Maldives:
                detail.setText(R.string.Maldives_Info);
                break;
            // 64
            case R.string.Oman:
                detail.setText(R.string.Oman_Info);
                break;
            // 65
            case R.string.Mauritius:
                detail.setText(R.string.Mauritius_Info);
                break;
            // 66
            case R.string.Bahrain:
                detail.setText(R.string.Bahrain_Info);
                break;
            // 67
            case R.string.Kuwait:
                detail.setText(R.string.Kuwait_Info);
                break;
            // 68
            case R.string.Jordan:
                detail.setText(R.string.Jordan_Info);
                break;
            // 69
            case R.string.Algeria:
                detail.setText(R.string.Algeria_Info);
                break;
            // 70
            case R.string.Tunisia:
                detail.setText(R.string.Tunisia_Info);
                break;

        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
