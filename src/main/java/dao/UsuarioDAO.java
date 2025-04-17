package dao;

import model.Usuario;
import utils.conexao;

import java.sql.*;

public class UsuarioDAO {

    /** Retorna true se encontrou usuário+senha correspondentes */
    public boolean autenticar(String usuario, String senha) {
        String sql = "SELECT 1 FROM usuarios WHERE usuario = ? AND senha = ?";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Persiste um novo usuario (a ser usado apenas pelo admin em SQL ou futuro CRUD admin) */
    public void salvarUsuario(Usuario u) {
        String sql = "INSERT INTO usuarios (usuario, senha) VALUES (?, ?)";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getUsuario());
            stmt.setString(2, u.getSenha());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
