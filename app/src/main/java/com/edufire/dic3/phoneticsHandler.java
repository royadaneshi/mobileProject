package com.edufire.dic3;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edufire.dic3.Models.Phonetics;

import java.io.IOException;
import java.util.List;

public class phoneticsHandler extends RecyclerView.Adapter<phoneticsHandler.ViewHolder> {

    private List<Phonetics> phonetics;
    MediaPlayer mediaPlayer;
    Context context;


    public phoneticsHandler(Context context,List<Phonetics> phonetics) {
        this.context = context;
        this.phonetics = phonetics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phonetic_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.phonetic.setText(phonetics.get(position).getText());
        holder.audioUrl = phonetics.get(position).getAudio();
        Log.d(holder.phonetic.getText().toString() + holder.audioUrl, "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return phonetics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView phonetic;
        ImageView audioPlayer;
        String audioUrl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            phonetic = itemView.findViewById(R.id.ph_item_text);
            audioPlayer = itemView.findViewById(R.id.ph_item_sound);
            audioPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playAudio(audioUrl);
                }
            });
        }
    }

    private void playAudio(String url){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);

        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, "Audio started playing..", Toast.LENGTH_SHORT).show();
    }
}
