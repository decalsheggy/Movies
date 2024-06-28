package com.example.movies;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsHolder> {
    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsHolder holder, int position) {
        Review review = reviews.get(position);
        holder.tvNameReview.setText(review.getAuthorName());
        holder.tvTextReview.setText(review.getReview());

        String reviewType = review.getType();

        if (review != null) {
            int background;
            switch (reviewType) {
                case "Негативный":
                    background = R.drawable.review_red;
                    break;
                case "Нейтральный":
                    background = R.drawable.review_orange;
                    break;
                default:
                    background = R.drawable.review_green;
                    break;
            }
            Drawable color = ContextCompat.getDrawable(holder.itemView.getContext(), background);
            holder.lLReview.setBackground(color);
        } else holder.lLReview.setBackgroundColor(R.drawable.review_red);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ReviewsHolder extends RecyclerView.ViewHolder {
        private final TextView tvNameReview;
        private final TextView tvTextReview;
        private final LinearLayout lLReview;

        public ReviewsHolder(@NonNull View itemView) {
            super(itemView);
            tvNameReview = itemView.findViewById(R.id.tvNameReview);
            tvTextReview = itemView.findViewById(R.id.tvTextReview);
            lLReview = itemView.findViewById(R.id.lLReview);
        }
    }
}
