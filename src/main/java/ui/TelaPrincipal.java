package ui;

import javax.swing.*;
import java.awt.*;
import ui.TelaCadastroPessoa;
import ui.TelaCadastroVenda;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema de Vendas Fiado");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1, 10, 10));
        setSize(400, 250);
        setLocationRelativeTo(null);

        JButton btnCadastrarPessoa = new JButton("Cadastrar Pessoa");
        btnCadastrarPessoa.addActionListener(e -> new TelaCadastroPessoa());

        JButton btnRegistrarVenda = new JButton("Registrar Venda");
        btnRegistrarVenda.addActionListener(e -> new TelaCadastroVenda());

        add(btnCadastrarPessoa);
        add(btnRegistrarVenda);

        setVisible(true);
    }
}
