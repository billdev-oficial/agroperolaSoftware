package ui;

import com.formdev.flatlaf.FlatDarkLaf;  // Importando o tema FlatLaf

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        // Aplicando o FlatDarkLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("Sistema de Vendas Fiado");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centraliza
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Botões principais
        JButton btnCadastrarPessoa = new JButton("Cadastrar Pessoa");
        JButton btnRegistrarVenda = new JButton("Registrar Venda");

        // Ações dos botões
        btnCadastrarPessoa.addActionListener(e -> new TelaCadastroPessoa().setVisible(true));
        btnRegistrarVenda.addActionListener(e -> new TelaCadastroVenda().setVisible(true));

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        painel.add(btnCadastrarPessoa);
        painel.add(btnRegistrarVenda);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}