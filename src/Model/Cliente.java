package Model;

public class Cliente {

    private int Id;
    private String Nome;
    private String Telefone;
    private String Rg;
    private String DataNascimento;

    public Cliente() {
    }

    public Cliente(int Id, String Nome, String Telefone, String Rg, String DataNascimento) {
        this.Id = Id;
        this.Nome = Nome;
        this.Telefone = Telefone;
        this.Rg = Rg;
        this.DataNascimento = DataNascimento;
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

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getRg() {
        return Rg;
    }

    @Override
    public String toString() {
        return "Cliente{" + "Id=" + Id + ", Nome=" + Nome + ", Telefone=" + Telefone + ", Rg=" + Rg + ", DataNascimento=" + DataNascimento + '}';
    }

    public void setRg(String Rg) {
        this.Rg = Rg;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String DataNascimento) {
        this.DataNascimento = DataNascimento;
    }

}
