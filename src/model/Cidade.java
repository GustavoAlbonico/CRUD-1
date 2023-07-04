package model;

public class Cidade {

    private Integer id;
    private String nome;
    private EstadoEnum UF;

    public Cidade(Integer id, String nome, EstadoEnum UF) {
        this.id = id;
        this.nome = nome;
        this.UF = UF;
    }

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

    public EstadoEnum getUF() {
        return UF;
    }

    public void setUF(EstadoEnum UF) {
        this.UF = UF;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", UF=" + UF +
                '}';
    }
}
