package com.example.gads_leaderboard;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.HourViewHolder> {

    private ArrayList<Hour> hours;

    public HoursAdapter() {
        hours = ApiUtil.getInstance().hours;
        //
    }


    @NonNull
    @Override
    public HourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new HourViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, int position) {
        Hour h = hours.get(position);
        holder.bind(h);
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public static class HourViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvDetails;
        private ImageView imgBadge;

        public HourViewHolder (@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            imgBadge = itemView.findViewById(R.id.imgBadge);
        }

        public void bind(@NonNull Hour hour) {
            tvName.setText(hour.getName());
            String details = hour.getHours() + " learning hours, " + hour.getCountry();
            tvDetails.setText(details);

            Picasso.get().load(hour.getBadgeUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .resize(150,150)
                    .into(imgBadge);
        }
    }

}
