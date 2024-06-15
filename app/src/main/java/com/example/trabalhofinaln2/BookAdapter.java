package com.example.trabalhofinaln2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Livro> livros;
    private OnItemClickListener listener;

    public BookAdapter(List<Livro> livros, OnItemClickListener listener) {
        this.livros = livros;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Livro livro);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_livro, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Livro livro = livros.get(position);
        holder.tituloTextView.setText(livro.getTitulo());
        holder.bind(livro, listener);
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tituloTextView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.boooktitle);
        }

        public void bind(final Livro livro, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(livro);
                }
            });
        }
    }
}
