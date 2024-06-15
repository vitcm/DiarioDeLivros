package com.example.trabalhofinaln2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private BooksDbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbManager = new BooksDbManager(this);
        List<Livro> livros = dbManager.getBooks();

        bookAdapter = new BookAdapter(livros, new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Livro livro) {
                Intent intent = new Intent(SecondActivity.this, FourthActivity.class);
                intent.putExtra("livroId", livro.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(bookAdapter);

        ImageButton cadastrarButton = findViewById(R.id.cadastrarButton);
        cadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Livro> livros = dbManager.getBooks();
        bookAdapter = new BookAdapter(livros, new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Livro livro) {
                Intent intent = new Intent(SecondActivity.this, FourthActivity.class);
                intent.putExtra("livroId", livro.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(bookAdapter);
    }
}