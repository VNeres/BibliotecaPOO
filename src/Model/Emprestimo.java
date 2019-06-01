package Model;

import java.util.Date;


public class Emprestimo {
    private int Id;
    private int idLivro;
    private int idCliente;
    private Date DataEmprestimo;
    private Date DataDevolucao;

    public Emprestimo() {
    }

    public Emprestimo(int Id, int idLivro, int idCliente, Date DataEmprestimo, Date DataDevolucao) {
        this.Id = Id;
        this.idLivro = idLivro;
        this.idCliente = idCliente;
        this.DataEmprestimo = new Date();
        this.DataDevolucao = DataDevolucao;
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

    public Date getDataDevolucao() {
        return DataDevolucao;
    }

    public void setDataDevolucao(Date DataDevolucao) {
        this.DataDevolucao = DataDevolucao;
    }


    
}
