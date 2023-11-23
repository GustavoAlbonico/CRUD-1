package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private Integer id;
    private String nome;
    private String logo;
    private String site;
    private Ambiente ambiente;
    private List<EmpresaContato> listaContato = new ArrayList<>();

    public Empresa(Integer id, String nome, String logo, String site, Ambiente ambiente) {
        this.id = id;
        this.nome = nome;
        this.logo = logo;
        this.site = site;
        this.ambiente = ambiente;
    }

    //construtor para campo opcional "site" no banco
    public Empresa(Integer id, String nome, String logo, Ambiente ambiente) {
        this.id = id;
        this.nome = nome;
        this.logo = logo;
        this.ambiente = ambiente;
    }
    public Empresa(){}

    public void adicionarContato (EmpresaContato empresaContato){
        listaContato.add(empresaContato);

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public List<EmpresaContato> getListaContato() {
        return listaContato;
    }

    public void setListaContato(List<EmpresaContato> listaContato) {
        this.listaContato = listaContato;
    }
}
