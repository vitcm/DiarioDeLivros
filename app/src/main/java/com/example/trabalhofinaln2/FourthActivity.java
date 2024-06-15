package com.example.trabalhofinaln2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {
    private BooksDbManager dbManager;
    private TextView tituloPage;
    private EditText tituloTextView;
    private EditText autorTextView;
    private EditText dataFimLeituraTextView;
    private EditText notaTextView;
    private int livroId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        tituloPage = findViewById(R.id.tituloPagEdit);
        tituloTextView = findViewById(R.id.tituloInputEdit);
        autorTextView = findViewById(R.id.autorInputEdit);
        dataFimLeituraTextView = findViewById(R.id.dataFimInputEdit);
        notaTextView = findViewById(R.id.notaInputEdit);

        dbManager = new BooksDbManager(this);

        livroId = getIntent().getIntExtra("livroId", -1);
        if (livroId != -1) {
            Livro livro = dbManager.getBookById(livroId);
            if (livro != null) {
                tituloPage.setText(livro.getTitulo());
                tituloTextView.setText(livro.getTitulo());
                autorTextView.setText(livro.getAutor());
                dataFimLeituraTextView.setText(livro.getDataFimLeitura());
                notaTextView.setText(String.valueOf(livro.getNota()));
            }
        }
    }

    public void onEditButtonClick(View view) {
        String titulo = tituloTextView.getText().toString();
        String autor = autorTextView.getText().toString();
        String dataFimLeitura = dataFimLeituraTextView.getText().toString();
        float nota = Float.parseFloat(notaTextView.getText().toString());

        Livro livro = new Livro(livroId, titulo, autor, dataFimLeitura, nota);
        dbManager.updateBook(livro);
        Toast.makeText(this, "Livro atualizado com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onDeleteButtonClick(View view) {
        dbManager.deleteBook(livroId);
        Toast.makeText(this, "Livro apagado com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }
}
