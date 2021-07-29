package br.com.zup.casadocodigo.casadocodigo.form;

public class LivroForm {
    private Long Id;
    private String Titulo;

    public LivroForm(Long id, String titulo) {
        Id = id;
        Titulo = titulo;
    }

    public Long getId() {
        return Id;
    }

    public String getTitulo() {
        return Titulo;
    }
}
