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

class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoreViewHolder> {

    private ArrayList<SkillIq> scores;

    public ScoresAdapter() {
        scores = ApiUtil.getInstance().scores;

    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        SkillIq score = scores.get(position);
        holder.bind(score);
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public static class ScoreViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvDetails;
        private ImageView imgBadge;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            imgBadge = itemView.findViewById(R.id.imgBadge);
        }

        public void bind(@NonNull SkillIq score) {
            tvName.setText(score.getName());
            String details = score.getScore() + " skill IQ score, " + score.getCountry();
            tvDetails.setText(details);

            Picasso.get().load(score.getBadgeUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .resize(200,150)
                    .into(imgBadge);
        }
    }
}
