package com.task.ui.component.Home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.task.R;
import com.task.data.remote.dto.Scooter;
import com.task.ui.base.listeners.RecyclerItemListener;

import java.util.List;

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

public class ScootersAdapter extends RecyclerView.Adapter<ScooterViewHolder> {
    private final List<Scooter> scooters;
    private RecyclerItemListener onItemClickListener;

    public ScootersAdapter(RecyclerItemListener onItemClickListener, List<Scooter> scooters) {
        this.onItemClickListener = onItemClickListener;
        this.scooters = scooters;
    }

    @Override
    public ScooterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scooter_data, parent, false);
        return new ScooterViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ScooterViewHolder holder, int position) {
        holder.bind(position, scooters.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return scooters.size();
    }


}

