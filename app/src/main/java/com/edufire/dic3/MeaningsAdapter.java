package com.edufire.dic3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edufire.dic3.Models.Meanings;

import java.util.List;

public class MeaningsAdapter extends RecyclerView.Adapter<MeaningsAdapter.ViewHolder> {

    private List<Meanings> meaningsList;
    private Context context;
    private DefinitionAdapter definitionAdapter;

    public MeaningsAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meaning_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pos.setText(meaningsList.get(position).getPartOfSpeech());
        holder.definitionRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        definitionAdapter = new DefinitionAdapter(meaningsList.get(position).getDefinitions());
        holder.definitionRecyclerView.setAdapter(definitionAdapter);
    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pos;
        RecyclerView definitionRecyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pos = itemView.findViewById(R.id.txt_meaning_pos);
            definitionRecyclerView = itemView.findViewById(R.id.rv_defenition);
        }
    }
}
