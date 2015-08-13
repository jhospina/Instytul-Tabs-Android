package com.appsthergo.instytul.tabs.appsthergoappname.listanoticias;

/**
 * Created by Jhon on 12/08/2015.
 */
public class ItemNoticia {

        private int image;
        private String title;
        private String url;

        public ItemNoticia() {
            super();
        }

        public ItemNoticia(int image, String title, String url) {
            super();
            this.image = image;
            this.title = title;
            this.url = url;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

}
