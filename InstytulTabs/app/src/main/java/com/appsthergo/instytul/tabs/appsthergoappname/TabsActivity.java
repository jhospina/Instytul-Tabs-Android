package com.appsthergo.instytul.tabs.appsthergoappname;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;

import com.appsthergo.instytul.tabs.appsthergoappname.listanoticias.AdaptadorItemNoticia;
import com.appsthergo.instytul.tabs.appsthergoappname.listanoticias.ItemNoticia;

import java.util.ArrayList;
import java.util.List;

import libreria.sistema.App;
import libreria.sistema.AppConfig;


public class TabsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tabs);
        App.establecerBarraAccion(this, AppConfig.txt_modulo_noticias);
        establecerTabs();
        establecerApariencia();

        tabNoticias();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void establecerApariencia() {
        ((LinearLayout) findViewById(R.id.tabNoticias)).setBackground(new ColorDrawable(Color.parseColor(AppConfig.txt_modulo_noticias_colorFondo)));
        ((LinearLayout) findViewById(R.id.tabInstitucional)).setBackground(new ColorDrawable(Color.parseColor(AppConfig.txt_modulo_institucional_colorFondo)));
        ((LinearLayout) findViewById(R.id.tabEncuestas)).setBackground(new ColorDrawable(Color.parseColor(AppConfig.txt_modulo_encuestas_colorFondo)));
        ((LinearLayout) findViewById(R.id.tabPqr)).setBackground(new ColorDrawable(Color.parseColor(AppConfig.txt_modulo_pqr_colorFondo)));
    }


    private void establecerTabs() {
        Resources res = getResources();

        TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
        tabs.setup();

        //Tab - Noticias
        TabHost.TabSpec spec = tabs.newTabSpec("tab1");
        spec.setContent(R.id.tabNoticias);
        spec.setIndicator(null, res.getDrawable(R.mipmap.img_menu_btn_2));
        tabs.addTab(spec);

        //Tab - Institucional
        spec = tabs.newTabSpec("tab2");
        spec.setContent(R.id.tabInstitucional);
        spec.setIndicator(null, res.getDrawable(R.mipmap.img_menu_btn_1));
        tabs.addTab(spec);

        //Tab - Encuestas
        spec = tabs.newTabSpec("tab3");
        spec.setContent(R.id.tabEncuestas);
        spec.setIndicator(null, res.getDrawable(R.mipmap.img_menu_btn_3));
        tabs.addTab(spec);

        //Tab - Pqr
        spec = tabs.newTabSpec("tab4");
        spec.setContent(R.id.tabPqr);
        spec.setIndicator(null, res.getDrawable(R.mipmap.img_menu_btn_4));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId == "tab1")
                    App.establecerBarraAccion(TabsActivity.this, AppConfig.txt_modulo_noticias);
                if (tabId == "tab2")
                    App.establecerBarraAccion(TabsActivity.this, AppConfig.txt_modulo_institucional);
                if (tabId == "tab3")
                    App.establecerBarraAccion(TabsActivity.this, AppConfig.txt_modulo_encuestas);
                if (tabId == "tab4")
                    App.establecerBarraAccion(TabsActivity.this, AppConfig.txt_modulo_pqr);
            }
        });


        // tabs.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor(AppConfig.txt_modulo_noticias_colorFondo));
        // tabs.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor(AppConfig.txt_modulo_institucional_colorFondo));
        // tabs.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor(AppConfig.txt_modulo_encuestas_colorFondo));
        // tabs.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor(AppConfig.txt_modulo_pqr_colorFondo));
    }




    private void tabNoticias(){
        ListView listaNoticias = (ListView) findViewById(R.id.listView_noticias);

        List items = new ArrayList();
        items.add(new ItemNoticia(R.mipmap.img_menu_btn_1, "Following",
                "http://www.imdb.com/title/tt0154506/"));
        items.add(new ItemNoticia(R.mipmap.img_menu_btn_1, "Memento",
                "http://www.imdb.com/title/tt0209144/"));
        items.add(new ItemNoticia(R.mipmap.img_menu_btn_1, "Batman Begins",
                "http://www.imdb.com/title/tt0372784/"));
        items.add(new ItemNoticia(R.mipmap.img_menu_btn_1, "The Prestige",
                "http://www.imdb.com/title/tt0482571/"));
        items.add(new ItemNoticia(R.mipmap.img_menu_btn_1, "The Dark Knight",
                "http://www.imdb.com/title/tt0468569/"));
        items.add(new ItemNoticia(R.mipmap.img_menu_btn_1, "Inception",
                "http://www.imdb.com/title/tt1375666/"));
        items.add(new ItemNoticia(R.mipmap.img_menu_btn_1,
                "The Dark Knight Rises", "http://www.imdb.com/title/tt1345836/"));

        // Sets the data behind this ListView
        listaNoticias.setAdapter(new AdaptadorItemNoticia(this, items));
    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
