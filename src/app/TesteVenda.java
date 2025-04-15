package app;

import dao.PessoaDAO;
import dao.VendaDAO;
import model.Pessoa;
import model.Venda;

import java.time.LocalDate;
import java.util.List;

public class TesteVenda {
    public static void main(String[] args) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        VendaDAO vendaDAO = new VendaDAO();

        // Selecionar a primeira pessoa cadastrada
        List<Pessoa> pessoas = pessoaDAO.listarTodas();
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }

        Pessoa pessoa = pessoas.get(0);
        System.out.println("Pessoa selecionada: " + pessoa);

        // Adicionar uma venda para essa pessoa
        Venda novaVenda = new Venda(
                pessoa.getId(),
                150.00,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );
        vendaDAO.salvar(novaVenda);
        System.out.println("Venda adicionada com sucesso!");

        // Listar todas as vendas dessa pessoa
        List<Venda> vendas = vendaDAO.listarPorPessoa(pessoa.getId());
        for (Venda v : vendas) {
            System.out.println(v);
        }
    }
}