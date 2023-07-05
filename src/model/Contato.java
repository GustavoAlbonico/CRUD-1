package model;

public class Contato {
    private Integer id;
   private String nome;

    public Contato(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Contato(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
