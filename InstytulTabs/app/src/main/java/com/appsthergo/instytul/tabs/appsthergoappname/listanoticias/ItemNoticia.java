package com.appsthergo.instytul.tabs.appsthergoappname.listanoticias;

/**
 * Created by Jhon on 12/08/2015.
 */
public class ItemNoticia {

        private String url_imagen;
        private String title;
        private int id_noticia;

        public ItemNoticia() {
            super();
        }

        public ItemNoticia(int id_noticia, String title,String image) {
            super();
            this.id_noticia=id_noticia;
            this.url_imagen = image;
            this.title = title;
        }

        public String getUrl_imagen() {
            return url_imagen;
        }

        public void setUrl_imagen(String url_imagen) {
            this.url_imagen = url_imagen;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    public int getId_noticia() {
        return id_noticia;
    }

    public void setId_noticia(int id_noticia) {
        this.id_noticia = id_noticia;
    }
}
