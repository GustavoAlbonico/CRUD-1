package main.java.com.crud.model;

public class EmpresaContato {
    private Integer id;
    private String descricao;
    private Empresa empresa;
    private Contato contato;

    public EmpresaContato(Integer id, String descricao, Empresa empresa, Contato contato) {
        this.id = id;
        this.descricao = descricao;
        this.empresa = empresa;
        this.contato = contato;
    }

    public EmpresaContato(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
