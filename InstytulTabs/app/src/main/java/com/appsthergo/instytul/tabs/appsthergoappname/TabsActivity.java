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
import android.widget.TabHost;

import com.appsthergo.instytul.tabs.appsthergoappname.frames.FrameNoticias;

import libreria.complementos.Util;
import libreria.conexion.Conexion;
import libreria.sistema.App;
import libreria.sistema.AppConfig;
import libreria.sistema.AppMeta;


public class TabsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tabs);
        App.establecerBarraAccion(this, AppConfig.txt_modulo_noticias);
        establecerTabs();
        establecerApariencia();

        tabNoticias();
        registrarInstalacion();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void establecerApariencia() {
        ((LinearLayout) findViewById(R.id.tabNoticias)).setBackground(new ColorDrawable(Color.parseColor(AppConfig.modulo_noticias_colorFondo)));
        ((LinearLayout) findViewById(R.id.tabInstitucional)).setBackground(new ColorDrawable(Color.parseColor(AppConfig.modulo_institucional_colorFondo)));
        ((LinearLayout) findViewById(R.id.tabEncuestas)).setBackground(new ColorDrawable(Color.parseColor(AppConfig.modulo_encuestas_colorFondo)));
        ((LinearLayout) findViewById(R.id.tabPqr)).setBackground(new ColorDrawable(Color.parseColor(AppConfig.modulo_pqr_colorFondo)));
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
                if (tabId == "tab1") {
                    App.establecerBarraAccion(TabsActivity.this, AppConfig.txt_modulo_noticias);
                    tabNoticias();
                }
                if (tabId == "tab2")
                    App.establecerBarraAccion(TabsActivity.this, AppConfig.txt_modulo_institucional);
                if (tabId == "tab3")
                    App.establecerBarraAccion(TabsActivity.this, AppConfig.txt_modulo_encuestas);
                if (tabId == "tab4")
                    App.establecerBarraAccion(TabsActivity.this, AppConfig.txt_modulo_pqr);
            }
        });


        // tabs.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor(AppConfig.modulo_noticias_colorFondo));
        // tabs.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor(AppConfig.modulo_institucional_colorFondo));
        // tabs.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor(AppConfig.modulo_encuestas_colorFondo));
        // tabs.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor(AppConfig.modulo_pqr_colorFondo));
    }




    private void tabNoticias(){
        FrameNoticias frameNoticias=new FrameNoticias(this,R.id.tabNoticias);
        frameNoticias.iniciar();
    }







    private void registrarInstalacion(){

        if(!Conexion.verificar(this))
            return;

        String regInstalacion="instalacion_"+App.obtenerIdDispositivo(this);

        if(AppMeta.findByClave(this, regInstalacion)!=null)
            return;

        String fecha= Util.obtenerFechaActual();;

        AppMeta meta=new AppMeta(this);

        meta.setClave(regInstalacion);
        meta.setValor(fecha);
        meta.save();

        String[][] datos = new String[3][2];
        datos[0][0] = "key_app";
        datos[0][1] = App.keyApp;
        datos[1][0] = "clave";
        datos[1][1] = regInstalacion;
        datos[2][0] = "valor";
        datos[2][1] =  fecha;

        Conexion.conectar(App.URL_META_REGISTRAR, datos);
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
