package com.appsthergo.instytul.tabs.appsthergoappname.frames;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.appsthergo.instytul.tabs.appsthergoappname.R;

import libreria.complementos.Mensaje;
import libreria.complementos.Util;
import libreria.conexion.Conexion;
import libreria.conexion.DescargarNoticias;
import libreria.sistema.App;
import libreria.tipos_contenido.Noticias;

/**
 * Created by Jhon on 14/08/2015.
 */
public class FrameNoticias {

    Activity activity;
    LinearLayout contenedor;


    public FrameNoticias(Activity activity, int id_contenedor) {
        this.activity = activity;
        this.contenedor = (LinearLayout) activity.findViewById(id_contenedor);
    }

    public void iniciar() {

        contenedor.setBackground(new ColorDrawable(Color.parseColor(App.modulo_noticias_colorFondo)));


        App.noticias_cargadas = 0;

        if (!App.noticias_descargadas) {

            if (!Conexion.verificar(activity)) {
                Mensaje.alerta(activity, App.txt_info_titulo_sin_conexion, App.txt_info_descripcion_sin_conexion);
                cargarNoticias();
                App.noticias_descargadas = true;
            } else {
                ProgressDialog progress = new ProgressDialog(activity);
                progress.setMessage(App.txt_info_cargando);
                new DescargarNoticias(progress, activity, R.id.listView_noticias).execute();
            }

        } else {
            cargarNoticias();
            App.noticias_descargadas = true;
        }

        //activity.startService(new Intent(this, ServicioNoticias.class));

        //Envia información de actividad al servidor
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                Conexion.registrarActividad(activity, Noticias.iden);
            }
        });
        hilo.start();

        cargarMasNoticias();

    }


    public void cargarNoticias() {
        Noticias noticias = new Noticias(activity);
        noticias.cargar(R.id.listView_noticias);
    }


    public void cargarMasNoticias(){

        ListView lista = (ListView) activity.findViewById(R.id.listView_noticias);
        lista.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view,
                                 int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                //Algorithm to check if the last item is visible or not
                final int lastItem = firstVisibleItem + visibleItemCount;
                if (lastItem == totalItemCount) {
                    // you have reached end of list, load more data
                    if (Conexion.verificar(activity)) {
                        if (!App.descarga_iniciada) {
                            App.descarga_iniciada = true;
                           Mensaje.toast(activity,App.txt_info_cargando);
                            new DescargarNoticias(activity, R.id.listView_noticias).execute();
                        }
                    }else{
                        cargarNoticias();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //blank, not using this
            }
        });

    }

}
