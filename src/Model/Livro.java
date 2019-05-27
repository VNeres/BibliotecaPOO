package Model;

public class Livro {

    private int Id;
    private String Nome;
    private String Autor;
    private int Ano;
    private boolean isDisponivel;

    public Livro() {
    }

    public Livro(int Id, String Nome, String Autor, int Ano, boolean isDisponivel) {
        this.Id = Id;
        this.Nome = Nome;
        this.Autor = Autor;
        this.Ano = Ano;
        this.isDisponivel = isDisponivel;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int Ano) {
        this.Ano = Ano;
    }

    public boolean isIsDisponivel() {
        return isDisponivel;
    }

    public void setIsDisponivel(boolean isDisponivel) {
        this.isDisponivel = isDisponivel;
    }
    
    

}
