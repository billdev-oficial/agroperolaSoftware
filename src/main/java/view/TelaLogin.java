package view;

import dao.UsuarioDAO;
import ui.TelaPrincipal;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private final JTextField campoUsuario = new JTextField();
    private final JPasswordField campoSenha = new JPasswordField();

    public TelaLogin() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320, 180);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painel.add(new JLabel("Usuário:"));
        painel.add(campoUsuario);

        painel.add(new JLabel("Senha:"));
        painel.add(campoSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(e -> {
            String user = campoUsuario.getText().trim();
            String pass = new String(campoSenha.getPassword());
            if (new UsuarioDAO().autenticar(user, pass)) {
                JOptionPane.showMessageDialog(this, "Login efetuado!");
                dispose();
                new TelaPrincipal();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Usuário ou senha inválidos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        painel.add(new JLabel()); // espaçamento
        painel.add(btnEntrar);

        add(painel);
        setVisible(true);
    }
}
