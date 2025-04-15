package ui;

import com.formdev.flatlaf.FlatDarkLaf;
import dao.PessoaDAO;
import model.Pessoa;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroPessoa extends JFrame {

    public TelaCadastroPessoa() {
        // Aplicando FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("Cadastrar Pessoa");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String telefone = txtTelefone.getText().trim();

            if (nome.isEmpty() || telefone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            Pessoa p = new Pessoa(nome, telefone);
            new PessoaDAO().salvar(p);
            JOptionPane.showMessageDialog(this, "Pessoa cadastrada com sucesso!");
            dispose();
        });

        add(lblNome);
        add(txtNome);
        add(lblTelefone);
        add(txtTelefone);
        add(new JLabel()); // espaço vazio
        add(btnSalvar);
    }
}
