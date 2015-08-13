package com.appsthergo.instytul.tabs.appsthergoappname.listanoticias;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.appsthergo.instytul.tabs.appsthergoappname.R;

public class AdaptadorItemNoticia extends BaseAdapter {

    private Context context;
    private List<ItemNoticia> items;

    public AdaptadorItemNoticia(Context context, List<ItemNoticia> items) {
        this.context = context;
        this.items = items;
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
        ImageView ivItem = (ImageView) rowView.findViewById(R.id.listaNoticias_imagen);
        TextView tvTitle = (TextView) rowView.findViewById(R.id.listaNoticias_titulo);

        ItemNoticia item = this.items.get(position);
        tvTitle.setText(item.getTitle());
        ivItem.setImageResource(item.getImage());

        return rowView;
    }

}