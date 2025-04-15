package model;

public class Pessoa {
    private int id;
    private String nome;
    private String telefone;

    // Construtor para novos cadastros (sem ID ainda)
    public Pessoa(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    // Construtor para quando puxamos do banco (já tem ID)
    public Pessoa(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefoneFormatado() {
        String t = telefone.replaceAll("[^\\d]", ""); // remove tudo que não for número
        if (t.length() == 11) {
            return "(" + t.substring(0, 2) + ") " + t.substring(2, 7) + "-" + t.substring(7);
        } else if (t.length() == 10) {
            return "(" + t.substring(0, 2) + ") " + t.substring(2, 6) + "-" + t.substring(6);
        } else {
            return telefone; // caso não seja válido
        }
    }

    @Override
    public String toString() {
        return nome + " (" + getTelefoneFormatado() + ")";
    }
}
