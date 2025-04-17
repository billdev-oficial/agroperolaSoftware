package ui;

import utils.Atualizador;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema de Vendas Fiado");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnCadastrarPessoa = new JButton("Cadastrar Pessoa");
        btnCadastrarPessoa.addActionListener(e -> new TelaCadastroPessoa().setVisible(true));

        JButton btnRegistrarVenda = new JButton("Registrar Venda");
        btnRegistrarVenda.addActionListener(e -> new TelaCadastroVenda().setVisible(true));

        JButton btnAtualizar = new JButton("Verificar Atualizações");
        btnAtualizar.addActionListener(e -> {
            if (Atualizador.verificarAtualizacao()) {
                int opc = JOptionPane.showConfirmDialog(
                        this,
                        "Nova versão disponível. Atualizar agora?",
                        "Atualização",
                        JOptionPane.YES_NO_OPTION
                );
                if (opc == JOptionPane.YES_OPTION) {
                    Atualizador.atualizarAplicativo(this);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Você já está na versão mais recente.");
            }
        });

        add(btnCadastrarPessoa);
        add(btnRegistrarVenda);
        add(btnAtualizar);

        setVisible(true);
    }
}
