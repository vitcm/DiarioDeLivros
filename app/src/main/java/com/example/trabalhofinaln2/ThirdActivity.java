package com.example.trabalhofinaln2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ThirdActivity extends AppCompatActivity {

    private EditText tituloInput;
    private EditText autorInput;
    private EditText dataFimInput;
    private EditText notaInput;
    private BooksDbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tituloInput = findViewById(R.id.tituloInput);
        autorInput = findViewById(R.id.autorInput);
        dataFimInput = findViewById(R.id.dataFimInput);
        notaInput = findViewById(R.id.notaInput);

        dbManager = new BooksDbManager(this);

        dataFimInput.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ThirdActivity.this,
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        String date = String.format("%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear);
                        dataFimInput.setText(date);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        ImageButton cadastrarLivroButton = findViewById(R.id.cadastrarLivroButton);
        cadastrarLivroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = tituloInput.getText().toString();
                String autor = autorInput.getText().toString();
                String dataFimLeitura = dataFimInput.getText().toString();
                String notaString = notaInput.getText().toString();
                if (titulo.isEmpty() || autor.isEmpty() || dataFimLeitura.isEmpty() || notaString.isEmpty()) {
                    Toast.makeText(ThirdActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    float nota = Float.parseFloat(notaString);

                    cadastrarLivro(titulo, autor, dataFimLeitura, nota);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("titulo", titulo);
                    resultIntent.putExtra("autor", autor);
                    resultIntent.putExtra("dataFimLeitura", dataFimLeitura);
                    resultIntent.putExtra("nota", nota);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }catch (Exception e) {
                    Toast.makeText(ThirdActivity.this, "Tivemos um problema no cadastro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cadastrarLivro(String titulo, String autor, String dataFimLeitura, float nota) {

        Livro livro = new Livro(0, titulo, autor, dataFimLeitura, nota);

        dbManager.addBook(livro);

        tituloInput.setText("");
        autorInput.setText("");
        dataFimInput.setText("");
        notaInput.setText("");
    }
}
