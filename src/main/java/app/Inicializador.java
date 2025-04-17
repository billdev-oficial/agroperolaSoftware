package app;

import utils.conexao;

import java.sql.Connection;
import java.sql.Statement;

public class Inicializador {

    /** Cria todas as tabelas (pessoa, venda, usuarios) se ainda não existirem */
    public static void criarTabelas() {
        String sqlPessoa = """
            CREATE TABLE IF NOT EXISTS pessoa (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                telefone TEXT NOT NULL
            );
            """;
        String sqlVenda = """
            CREATE TABLE IF NOT EXISTS venda (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                pessoa_id INTEGER NOT NULL,
                valor REAL NOT NULL,
                data TEXT NOT NULL,
                vencimento TEXT NOT NULL,
                pago INTEGER NOT NULL DEFAULT 0,
                FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
            );
            """;
        String sqlUsuarios = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT NOT NULL UNIQUE,
                senha TEXT NOT NULL
            );
            """;

        try (Connection conn = conexao.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlPessoa);
            stmt.execute(sqlVenda);
            stmt.execute(sqlUsuarios);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
