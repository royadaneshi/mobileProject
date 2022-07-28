package com.edufire.dic3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edufire.dic3.Models.Definitions;

import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionAdapter.ViewHolder> {

    private List<Definitions> definitionsList;

    public DefinitionAdapter(List<Definitions> definitionsList) {
        this.definitionsList = definitionsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.defenition_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.definition.setText(definitionsList.get(position).getDefinition());
        holder.example.setText(definitionsList.get(position).getExample());
    }

    @Override
    public int getItemCount() {
        return definitionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView example;
        TextView definition;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            example = itemView.findViewById(R.id.text_deffinition_example);
            definition = itemView.findViewById(R.id.txt_definition);
        }
    }
}
