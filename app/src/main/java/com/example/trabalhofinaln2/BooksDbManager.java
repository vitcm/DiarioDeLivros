package com.example.trabalhofinaln2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BooksDbManager {
    private BooksDbHelper dbHelper;

    public BooksDbManager(Context context) {
        dbHelper = new BooksDbHelper(context);
    }

    // Método para adicionar um livro
    public void addBook(Livro livro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", livro.getTitulo());
        values.put("autor", livro.getAutor());
        values.put("dataFimLeitura", livro.getDataFimLeitura());
        values.put("nota", livro.getNota());
        db.insert("livros", null, values);
        db.close();
    }

    // Método para atualizar um livro
    public void updateBook(Livro livro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", livro.getTitulo());
        values.put("autor", livro.getAutor());
        values.put("dataFimLeitura", livro.getDataFimLeitura());
        values.put("nota", livro.getNota());
        db.update("livros", values, "id = ?", new String[]{String.valueOf(livro.getId())});
        db.close();
    }

    // Método para deletar um livro
    public void deleteBook(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("livros", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Método para obter todos os livros
    public List<Livro> getBooks() {
        List<Livro> livros = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("livros", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                String autor = cursor.getString(cursor.getColumnIndexOrThrow("autor"));
                String dataFimLeitura = cursor.getString(cursor.getColumnIndexOrThrow("dataFimLeitura"));
                float nota = cursor.getFloat(cursor.getColumnIndexOrThrow("nota"));

                Livro livro = new Livro(id, titulo, autor, dataFimLeitura, nota);
                livros.add(livro);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return livros;
    }

    // Método para obter um livro por ID
    public Livro getBookById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("livros", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
            String autor = cursor.getString(cursor.getColumnIndexOrThrow("autor"));
            String dataFimLeitura = cursor.getString(cursor.getColumnIndexOrThrow("dataFimLeitura"));
            float nota = cursor.getFloat(cursor.getColumnIndexOrThrow("nota"));

            Livro livro = new Livro(id, titulo, autor, dataFimLeitura, nota);
            cursor.close();
            db.close();
            return livro;
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return null;
    }
}
