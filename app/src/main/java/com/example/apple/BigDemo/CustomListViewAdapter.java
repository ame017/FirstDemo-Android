package com.example.apple.BigDemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomListViewAdapter extends ArrayAdapter<ListViewModel> {

    private int resource;

//    public CustomListViewAdapter (Context context, int layoutId, List<ListViewModel> list) {
//
//    }


    public CustomListViewAdapter(@NonNull Context context, int resource, @NonNull List<ListViewModel> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //取出模型
        ListViewModel model = getItem(position);
        View cell;
        ViewHolder viewHolder;
        if (convertView == null) {
            cell = LayoutInflater.from(getContext()).inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = cell.findViewById(R.id.cell_imageView);
            viewHolder.textView = cell.findViewById(R.id.cell_textView);
            cell.setTag(viewHolder);
        }else {
            cell = convertView;
            viewHolder = (ViewHolder) cell.getTag();
        }
        viewHolder.textView.setText(model.textContent);
        Glide.with(cell).load(model.getUrl()).into(viewHolder.imageView);
        return cell;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
