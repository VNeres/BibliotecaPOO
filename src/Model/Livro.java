package Model;

public class Livro {

    private int Id;
    private String Nome;
    private String Autor;
    private String Ano;
    private String Editora;
    private String Quantidade;
    

    public Livro() {
    }

    public Livro(int Id, String Nome, String Autor, String Ano, String Editora, String Quantidade) {
        this.Id = Id;
        this.Nome = Nome;
        this.Autor = Autor;
        this.Ano = Ano;
        this.Editora = Editora;
        this.Quantidade = Quantidade;
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

    public String getAno() {
        return Ano;
    }

    public void setAno(String Ano) {
        this.Ano = Ano;
    }

    public String getEditora() {
        return Editora;
    }

    public void setEditora(String Editora) {
        this.Editora = Editora;
    }

    public String getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(String Quantidade) {
        this.Quantidade = Quantidade;
    }
    
}
