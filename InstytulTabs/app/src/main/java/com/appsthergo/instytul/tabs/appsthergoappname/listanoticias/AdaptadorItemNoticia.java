package com.appsthergo.instytul.tabs.appsthergoappname.listanoticias;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.appsthergo.instytul.tabs.appsthergoappname.R;

import libreria.conexion.CargarImagen;
import libreria.sistema.App;

public class AdaptadorItemNoticia extends BaseAdapter {

    private Context context;
    private List<ItemNoticia> items;
    private ArrayList id_imagenes;

    public AdaptadorItemNoticia(Context context, List<ItemNoticia> items) {
        this.context = context;
        this.items = items;
        id_imagenes=new ArrayList();
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getIdImagen(int position){
        return id_imagenes.size();
       // return (int)id_imagenes.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.lista_noticias, parent, false);
        }

        // Set data into the view.
        ImageView imagen = (ImageView) rowView.findViewById(R.id.listaNoticias_imagen);
        TextView titulo = (TextView) rowView.findViewById(R.id.listaNoticias_titulo);

        ItemNoticia item = this.items.get(position);
        titulo.setText(item.getTitle());
        titulo.setTextColor(Color.parseColor(App.modulo_noticias_colorTexto));
        CargarImagen cargar=new CargarImagen(context,item.getUrl_imagen(),imagen);
        cargar.setAjustar(true);
        cargar.execute();

        return rowView;
    }

}