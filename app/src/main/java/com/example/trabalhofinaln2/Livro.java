package com.example.trabalhofinaln2;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String dataFimLeitura;
    private float nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDataFimLeitura() {
        return dataFimLeitura;
    }

    public void setDataFimLeitura(String dataFimLeitura) {
        this.dataFimLeitura = dataFimLeitura;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Livro(int id, String titulo, String autor, String dataFimLeitura, float nota) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.dataFimLeitura = dataFimLeitura;
        this.nota = nota;
    }
}
