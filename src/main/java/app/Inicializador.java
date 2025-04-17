package app;

import utils.conexao;

import java.sql.Connection;
import java.sql.Statement;

public class Inicializador {
    public static void main(String[] args) {
        try (Connection conn = conexao.conectar(); Statement stmt = conn.createStatement()) {
            String criarPessoa = "CREATE TABLE IF NOT EXISTS pessoa (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "telefone TEXT NOT NULL)";
            stmt.execute(criarPessoa);

            String criarVenda = "CREATE TABLE IF NOT EXISTS venda (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "pessoa_id INTEGER NOT NULL," +
                    "valor REAL NOT NULL," +
                    "data TEXT NOT NULL," +
                    "vencimento TEXT NOT NULL," +
                    "pago INTEGER NOT NULL DEFAULT 0," +
                    "FOREIGN KEY (pessoa_id) REFERENCES pessoa(id))";
            stmt.execute(criarVenda);

            System.out.println("Banco de dados e tabelas criadas com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}