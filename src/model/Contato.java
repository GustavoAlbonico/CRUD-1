package model;

public class Contato {
    private Integer id;
    private ContatoEnum tipoContato;
    private String descricao;
    private Empresa empresa;

//    public Contato(Integer id, ContatoEnum tipoContato, String descricao, Empresa empresa) {
//        this.id = id;
//        this.tipoContato = tipoContato;
//        this.descricao = descricao;
//        this.empresa = empresa;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContatoEnum getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(ContatoEnum tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
