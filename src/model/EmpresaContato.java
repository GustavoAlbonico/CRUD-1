package model;

public class EmpresaContato {
    private Integer id;
    private String perfil;
    private Empresa empresa;
    private Contato contato;

    public EmpresaContato(Integer id, String perfil, Empresa empresa, Contato contato) {
        this.id = id;
        this.perfil = perfil;
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
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
