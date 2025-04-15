package app;

import dao.PessoaDAO;
import model.Pessoa;

import java.util.List;

public class TestePessoa {
    public static void main(String[] args) {
        PessoaDAO dao = new PessoaDAO();

        // Teste: adicionar uma nova pessoa
        Pessoa novaPessoa = new Pessoa("João da Silva", "11999998888");
        dao.salvar(novaPessoa);

        // Listar todas as pessoas cadastradas
        List<Pessoa> pessoas = dao.listarTodas();
        for (Pessoa p : pessoas) {
            System.out.println(p.getId() + ": " + p.getNome() + " - " + p.getTelefoneFormatado());
        }
    }
}