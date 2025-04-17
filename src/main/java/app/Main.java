package app;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // 1) Aplica o tema antes de criar qualquer componente Swing
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // 2) Inicializa o banco (cria tabelas)
        Inicializador.criarTabelas();

        // 3) Abre a tela de login
        SwingUtilities.invokeLater(() -> new view.TelaLogin());
    }
}