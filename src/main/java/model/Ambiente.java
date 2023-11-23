package main.java.model;

public class Ambiente {

    private Integer id;
    private String nome;
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private Cidade cidade;
    private Categoria categoria;

    public Ambiente(Integer id, String nome, String cep,String rua, String numero, String bairro, Cidade cidade, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.categoria = categoria;
    }

    public Ambiente(Integer id, String nome, String cep,String rua, String bairro, Cidade cidade, Categoria categoria) {
        this(id,nome,cep,rua,null,bairro,cidade,categoria);
    }

    public Ambiente() {
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Ambiente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cep='" + cep + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade=" + cidade +
                ", categoria=" + categoria +
                '}';
    }
}
