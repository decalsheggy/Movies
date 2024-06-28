package com.example.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder> {
    private List<Trailer> trailers = new ArrayList<>();//добавляем на нее сеттер
    private OnClickListenerTrailer onClickListenerTrailer;

    public void setOnClickListenerTrailer(OnClickListenerTrailer onClickListenerTrailer) {
        this.onClickListenerTrailer = onClickListenerTrailer;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();//после установки значений запускается метод изменения данных
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Из макета создаем view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
        return new TrailersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        holder.tvTrailer.setText(trailer.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListenerTrailer != null) {
                    onClickListenerTrailer.setOnClickTrailer(trailer);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    static class TrailersViewHolder extends RecyclerView.ViewHolder {//холдер получает нашу вьюшку
        private final TextView tvTrailer;

        public TrailersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTrailer = itemView.findViewById(R.id.tvTrailer);
        }
    }

    interface OnClickListenerTrailer {
        void setOnClickTrailer(Trailer trailer);
    }
}
