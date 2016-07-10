package com.mini_proj.annetao.wego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.mini_proj.annetao.wego.util.map.QQMapSupporter;
import com.mini_proj.annetao.wego.util.map.SearchAddrForMapActivity;
import com.mini_proj.annetao.wego.util.map.WeGoLocation;

public class TencentMapActivity extends AppCompatActivity {
    private QQMapSupporter qqMapSupporter;
    private MapView qqMapView;
    private String mapType;
    private WeGoLocation weGoLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencent_map);
        initView();

    }


    void initView() {
        mapType = QQMapSupporter.QQ_MAP_TYPE_EXERCISES;
        final String city = "深圳";
        qqMapView = new MapView(this);
        qqMapSupporter = new QQMapSupporter(this,qqMapView,mapType);
        LinearLayout mapLayout = (LinearLayout) findViewById(R.id.map_layout);
        mapLayout.addView(qqMapView);
        qqMapSupporter.initialMapView();

        LinearLayout searchGuideLayout = (LinearLayout) findViewById(R.id.map_search_guide_layout);
        if(QQMapSupporter.QQ_MAP_TYPE_LOCATION == mapType){
            searchGuideLayout.setVisibility(View.VISIBLE);
            searchGuideLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(TencentMapActivity.this, SearchAddrForMapActivity.class);
                    Bundle bundle=new Bundle();
                    startActivityForResult(intent, 0);

                }
            });

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                String str=data.getStringExtra("wego_location_str");
                weGoLocation = new WeGoLocation(str);
                qqMapSupporter.addMarkerFromSearch(weGoLocation);

                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {

        super.onStart();
        qqMapView.onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        qqMapView.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        qqMapView.onResume();

    }
    @Override
    protected void onPause() {
        super.onPause();
        qqMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        qqMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        qqMapView.onDestroy();
        super.onDestroy();
    }



}
