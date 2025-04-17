package view;

import dao.UsuarioDAO;
import utils.Atualizador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

    private final JTextField campoUsuario = new JTextField();
    private final JPasswordField campoSenha = new JPasswordField();

    public TelaLogin() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 2, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painel.add(new JLabel("Usuário:"));
        painel.add(campoUsuario);
        painel.add(new JLabel("Senha:"));
        painel.add(campoSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(this::fazerLogin);
        painel.add(new JLabel());
        painel.add(btnEntrar);

        add(painel);
        setVisible(true);
    }

    private void fazerLogin(ActionEvent e) {
        String usuario = campoUsuario.getText().trim();
        String senha = new String(campoSenha.getPassword());

        UsuarioDAO dao = new UsuarioDAO();
        if (!dao.autenticar(usuario, senha)) {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ao logar com sucesso, checa atualização
        if (Atualizador.verificarAtualizacao()) {
            int opc = JOptionPane.showConfirmDialog(
                    this,
                    "Há uma nova versão disponível. Deseja atualizar agora?",
                    "Atualização disponível",
                    JOptionPane.YES_NO_OPTION
            );
            if (opc == JOptionPane.YES_OPTION) {
                Atualizador.atualizarAplicativo(this);
                return; // encerra aqui; usuário reiniciará após atualização
            }
        }

        dispose();
        new ui.TelaPrincipal().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaLogin::new);
    }
}
