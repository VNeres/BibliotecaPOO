package Model;

import java.util.Date;


public class Emprestimo {
    private int ID;
    private String LivroEmprestado;
    private String EmprestadoPara;
    private Date DataEmprestimo = new Date();
    private Date DataDevolucao;

    public Emprestimo() {
    }

    public Emprestimo(int ID, String LivroEmprestado, String EmprestadoPara, Date DataEmprestimo, Date DataDevolucao) {
        this.ID = ID;
        this.LivroEmprestado = LivroEmprestado;
        this.EmprestadoPara = EmprestadoPara;
        this.DataEmprestimo = DataEmprestimo;
        this.DataDevolucao = DataDevolucao;
    }
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLivroEmprestado() {
        return LivroEmprestado;
    }

    public void setLivroEmprestado(String LivroEmprestado) {
        this.LivroEmprestado = LivroEmprestado;
    }

    public String getEmprestadoPara() {
        return EmprestadoPara;
    }

    public void setEmprestadoPara(String EmprestadoPara) {
        this.EmprestadoPara = EmprestadoPara;
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
