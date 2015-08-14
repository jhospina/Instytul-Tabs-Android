package libreria.tipos_contenido;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableRow;

import com.appsthergo.instytul.tabs.appsthergoappname.VerNoticiaActivity;
import com.appsthergo.instytul.tabs.appsthergoappname.listanoticias.AdaptadorItemNoticia;
import com.appsthergo.instytul.tabs.appsthergoappname.listanoticias.ItemNoticia;

import java.util.ArrayList;
import java.util.List;

import libreria.complementos.Mensaje;
import libreria.complementos.Util;
import libreria.conexion.Conexion;
import libreria.sistema.App;
import libreria.sistema.ControladorBaseDatos;

/**
 * Created by Jhon on 26/03/2015.
 */
public class Noticias extends TipoContenido {

    public String urlImagen;
    public static final String iden = "noticias";

    public Noticias(Activity act) {
        super(act);
    }

    /**
     * Busca y obtiene una noticia dada por su numero ID
     *
     * @param activity La actividad actual
     * @param id       El id de la noticia a buscar
     * @return
     */
    public static Noticias buscar(Activity activity, int id) {
        ControladorBaseDatos dbc = new ControladorBaseDatos(activity, ControladorBaseDatos.nombreDB, null, 1);
        SQLiteDatabase db = dbc.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + ControladorBaseDatos.tabla_noticias + " where id='" + id + "'", null);

        if (c.moveToFirst()) {
            Noticias noticia = new Noticias(activity);
            noticia.setId(c.getInt(0));
            noticia.setTitulo(c.getString(2));
            noticia.setDescripcion(c.getString(3));
            noticia.setUrlImagen(c.getString(4));
            noticia.setFecha(c.getString(5));
            return noticia;
        }

        return null;
    }


    /**
     * Carga un listado de noticias en un listView dado por su Referencia
     *
     * @param layout (int) El layout donde se mostrara las noticias
     */
    public void cargar(int layout) {
        ControladorBaseDatos dbc = new ControladorBaseDatos(activity, ControladorBaseDatos.nombreDB, null, 1);
        SQLiteDatabase db = dbc.getReadableDatabase();

        ListView lista_noticias = (ListView) activity.findViewById(layout);
        lista_noticias.setBackground(new ColorDrawable(Color.parseColor(App.modulo_noticias_colorFondo)));

        Cursor noticias = db.rawQuery("SELECT * FROM " + ControladorBaseDatos.tabla_noticias + " ORDER BY id DESC LIMIT " + (App.noticias_cargadas) + "," + App.noticias_cantidad_a_cargar, null);

       List items = new ArrayList();

        if (noticias.moveToFirst()) {
            //Almacena los ids de las noticias
            final int[] ids=new int[noticias.getCount()];
            do {
                App.noticias_cargadas++;
                final int id_noticia = noticias.getInt(0);
                String titulo = noticias.getString(2);
                String URL_imagen = noticias.getString(4);
                ids[noticias.getPosition()]=id_noticia;

                //SI la Noticia tiene imagen
                if (URL_imagen.length() > 0 && Conexion.verificar(activity)) {
                    items.add(new ItemNoticia(id_noticia,titulo,URL_imagen));
                } else {
                    items.add(new ItemNoticia(id_noticia,titulo,null));
                }

            } while (noticias.moveToNext());


            //Carga el listado de noticias con el adaptador
            final AdaptadorItemNoticia adapter = new AdaptadorItemNoticia(activity, items);

            lista_noticias.setAdapter(adapter);


          lista_noticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  ItemNoticia noticia = (ItemNoticia) adapter.getItem(position);
                  Intent ver_noticias = new Intent(activity, VerNoticiaActivity.class);
                  ver_noticias.putExtra("id_noticia", ids[position]);
                  activity.startActivity(ver_noticias);
              }
          });

        } else {
               Mensaje.toast(activity, App.txt_info_no_hay_contenido_vuelve_mas_tarde);
        }


        db.close();
    }


    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

}
