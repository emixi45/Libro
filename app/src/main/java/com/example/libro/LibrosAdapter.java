package com.example.libro;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.LibrosViewHolder> {


    private List<Libro> libroList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public LibrosAdapter(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public LibrosViewHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
        View itemLibro = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro,parent,false);
        return new LibrosViewHolder(itemLibro);
    }
    @Override
    public void onBindViewHolder(@NonNull LibrosViewHolder holder, @SuppressLint("RecyclerView") final int position){
        holder.nombre.setText(libroList.get(position).getNombre());
        holder.autor.setText(libroList.get(position).getAutor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(libroList.get(position));
            }
        });
    }


    @Override
    public int getItemCount(){
        return libroList.size();
    }

    class LibrosViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView autor;

        LibrosViewHolder (@NonNull View itemView){
            super(itemView);
            nombre = itemView.findViewById(R.id.txtNombre);
            autor = itemView.findViewById(R.id.txtAutor);
        }
    }
    interface OnItemClickListener {
        void onItemClick(Libro libro);
    }
    public List<Libro> getLibroList() {
        return this.libroList;
    }

    public void setLibroList(List<Libro> libros){
        libroList = libros;
        notifyDataSetChanged();
    }

}
