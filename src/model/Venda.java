package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {
    private int id;
    private int pessoaId;
    private double valor;
    private LocalDate data;
    private LocalDate vencimento;
    private boolean pago;

    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Construtor para novos registros (sem ID ainda)
    public Venda(int pessoaId, double valor, LocalDate data, LocalDate vencimento) {
        this.pessoaId = pessoaId;
        this.valor = valor;
        this.data = data;
        this.vencimento = vencimento;
        this.pago = false;
    }

    // Construtor para vendas salvas no banco (com ID)
    public Venda(int id, int pessoaId, double valor, LocalDate data, LocalDate vencimento, boolean pago) {
        this.id = id;
        this.pessoaId = pessoaId;
        this.valor = valor;
        this.data = data;
        this.vencimento = vencimento;
        this.pago = pago;
    }

    public int getId() {
        return id;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public boolean isPago() {
        return pago;
    }

    public String getDataFormatada() {
        return data.format(FORMATADOR);
    }

    public String getVencimentoFormatado() {
        return vencimento.format(FORMATADOR);
    }

    @Override
    public String toString() {
        return "Venda de R$" + valor + " - Data: " + getDataFormatada() + " - Vencimento: " + getVencimentoFormatado();
    }
}