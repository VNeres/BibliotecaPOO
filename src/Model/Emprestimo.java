package Model;

import java.util.Date;


public class Emprestimo {
    private int Id;
    private int idLivro;
    private int idCliente;
    private Date DataEmprestimo;
    private String DataDevolucao;
    private String livro;
    private String cliente;

    public Emprestimo() {
    }

    public Emprestimo(int Id, int idLivro, int idCliente,  Date DataEmprestimo, String DataDevolucao) {
        this.Id = Id;
        this.idLivro = idLivro;
        this.idCliente = idCliente;
        this.DataEmprestimo = DataEmprestimo;
        this.DataDevolucao = DataDevolucao;
    }

    public Emprestimo(int Id, String livro, String cliente, Date DataEmprestimo, String DataDevolucao) {
        this.Id = Id;
        this.DataEmprestimo = DataEmprestimo;
        this.DataDevolucao = DataDevolucao;
        this.livro = livro;
        this.cliente = cliente;
    }

    
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDataEmprestimo() {
        return DataEmprestimo;
    }

    public void setDataEmprestimo(Date DataEmprestimo) {
        this.DataEmprestimo = DataEmprestimo;
    }

    public String getDataDevolucao() {
        return DataDevolucao;
    }

    public void setDataDevolucao(String DataDevolucao) {
        this.DataDevolucao = DataDevolucao;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    
    
}
