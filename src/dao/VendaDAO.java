package dao;

import model.Venda;
import utils.conexao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    public void salvar(Venda venda) {
        String sql = "INSERT INTO venda (pessoa_id, valor, data, vencimento, pago) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venda.getPessoaId());
            stmt.setDouble(2, venda.getValor());
            stmt.setString(3, venda.getData().toString());
            stmt.setString(4, venda.getVencimento().toString());
            stmt.setInt(5, venda.isPago() ? 1 : 0);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar venda", e);
        }
    }

    public List<Venda> listarPorPessoa(int pessoaId) {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE pessoa_id = ?";
        try (Connection conn = conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pessoaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Venda v = new Venda(
                        rs.getInt("id"),
                        rs.getInt("pessoa_id"),
                        rs.getDouble("valor"),
                        LocalDate.parse(rs.getString("data")),
                        LocalDate.parse(rs.getString("vencimento")),
                        rs.getInt("pago") == 1
                );
                vendas.add(v);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vendas", e);
        }

        return vendas;
    }
}