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

    public BookAdapter(List<Livro> livros) {
        this.livros = livros;
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
    }
}
