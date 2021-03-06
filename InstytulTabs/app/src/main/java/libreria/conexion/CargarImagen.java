package libreria.conexion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.appsthergo.instytul.tabs.appsthergoappname.R;
import com.appsthergo.instytul.tabs.appsthergoappname.listanoticias.ItemNoticia;

import libreria.sistema.App;

/**
 * Created by Jhon on 26/03/2015.
 */
public class CargarImagen extends AsyncTask<String, Void, Bitmap> {

    private String URL;
    private ViewGroup viewGroup;
    private ImageView imagen;
    private Context contexto;
    private boolean ajustar=false;

    /**
     * Carga una imagen traida de una URL
     *
     * @param contexto  El contexto
     * @param URL       LA url de la Imagen
     * @param viewGroup El objeto vista donde se mostrara la imagen
     */
    public CargarImagen(Context contexto, String URL, ViewGroup viewGroup) {
        this.contexto = contexto;
        this.URL = URL;
        this.viewGroup = viewGroup;
        this.imagen = null;
    }

    /**
     * Carga una imagen traida de una URL
     *
     * @param contexto  El contexto
     * @param URL       LA url de la Imagen
     * @param imagen La imagen donde se cargara
     */
    public CargarImagen(Context contexto, String URL, ImageView imagen) {
        this.contexto = contexto;
        this.URL = URL;
        this.viewGroup = null;
        this.imagen = imagen;
    }


    public void setAjustar(boolean ajustar){
        this.ajustar=ajustar;
    }


    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        Bitmap imagen = Conexion.descargarImagen(this.URL);
        return imagen;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);

        Drawable img = new BitmapDrawable(contexto.getResources(), result);


        if(viewGroup!=null)
            viewGroup.setBackground(img);


        if(imagen!=null) {
            imagen.setBackground(img);
            if(ajustar) {
                LinearLayout parent = (LinearLayout) imagen.getParent();
                imagen.getLayoutParams().width = Math.round((App.W_IMAGEN_NOTICIA * parent.getHeight()) / App.H_IMAGEN_NOTICIA);
                imagen.requestLayout();
                if (this.URL == null)
                    imagen.setImageResource(R.mipmap.img_menu_btn_2);
            }
        }


    }

}

