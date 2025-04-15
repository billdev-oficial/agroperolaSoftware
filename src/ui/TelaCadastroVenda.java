package ui;

import dao.PessoaDAO;
import dao.VendaDAO;
import model.Pessoa;
import model.Venda;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TelaCadastroVenda extends JFrame {

    public TelaCadastroVenda() {
        setTitle("Registrar Venda");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Lista de pessoas
        JLabel lblPessoa = new JLabel("Pessoa:");
        JComboBox<Pessoa> comboPessoas = new JComboBox<>();

        List<Pessoa> pessoas = new PessoaDAO().listarTodas();
        for (Pessoa p : pessoas) {
            comboPessoas.addItem(p);
        }

        JLabel lblValor = new JLabel("Valor (R$):");
        JTextField txtValor = new JTextField();

        JLabel lblVencimento = new JLabel("Dias até vencimento:");
        JTextField txtDias = new JTextField("7");

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> {
            Pessoa pessoa = (Pessoa) comboPessoas.getSelectedItem();
            if (pessoa == null) {
                JOptionPane.showMessageDialog(this, "Nenhuma pessoa selecionada.");
                return;
            }

            try {
                double valor = Double.parseDouble(txtValor.getText().trim());
                int dias = Integer.parseInt(txtDias.getText().trim());
                LocalDate data = LocalDate.now();
                LocalDate venc = data.plusDays(dias);

                Venda v = new Venda(pessoa.getId(), valor, data, venc);
                new VendaDAO().salvar(v);
                JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar venda: " + ex.getMessage());
            }
        });

        add(lblPessoa);
        add(comboPessoas);
        add(lblValor);
        add(txtValor);
        add(lblVencimento);
        add(txtDias);
        add(new JLabel());
        add(btnSalvar);
    }
}