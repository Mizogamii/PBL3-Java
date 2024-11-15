/*******************************************************************************************
 Autor: Sayumi Mizogami Santana
 Componente Curricular: EXA 863 - MI Programação
 Concluido em: 20/10/2024
 Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 *******************************************************************************************/
package projeto.backend.model;

import java.util.Date;
import java.util.Objects;

/**
 * A classe recibo representa um recibo gerado após a compra de um ingresso.
 * Armazena informações sobre o usuário que realizou a compra, o tipo de pagamento,
 * a data da compra, o nome do evento, a data do evento e o preço do ingresso.
 * @see Pagamento
 */
public class Recibo {
    private String loginUsuario;
    private Pagamento.TipoPagamento tipoPagamento;
    private Date dataDaCompra;
    private String nomeDoEvento;
    private Date dataDoEvento;
    private double preco;

    /**
     * Construtor para criar um recibo com todos os detalhes da transação de compra de ingresso.
     *
     * @param loginUsuario Login do usuário que realizou a compra.
     * @param tipoPagamento Tipo de pagamento utilizado na compra.
     * @param dataDaCompra Data em que a compra foi realizada.
     * @param nomeDoEvento Nome do evento para o qual o ingresso foi comprado.
     * @param dataDoEvento Data do evento.
     * @param preco Preço do ingresso.
     */
    public Recibo(String loginUsuario, Pagamento.TipoPagamento tipoPagamento, Date dataDaCompra, String nomeDoEvento, Date dataDoEvento, double preco) {
        this.loginUsuario = loginUsuario;
        this.tipoPagamento = tipoPagamento;
        this.dataDaCompra = dataDaCompra;
        this.nomeDoEvento = nomeDoEvento;
        this.dataDoEvento = dataDoEvento;
        this.preco = preco;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public Pagamento.TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(Pagamento.TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Date getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(Date dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    public String getNomeDoEvento() {
        return nomeDoEvento;
    }

    public void setNomeDoEvento(String nomeDoEvento) {
        this.nomeDoEvento = nomeDoEvento;
    }

    public Date getDataDoEvento() {
        return dataDoEvento;
    }

    public void setDataDoEvento(Date dataDoEvento) {
        this.dataDoEvento = dataDoEvento;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Compara este recibo com outro objeto para verificar se são iguais.
     * Dois recibos são considerados iguais se todos os atributos são iguais.
     *
     * @param o o objeto a ser comparado.
     * @return {@code true} se os objetos são iguais, {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recibo recibo)) return false;
        return Double.compare(getPreco(), recibo.getPreco()) == 0 && Objects.equals(getLoginUsuario(), recibo.getLoginUsuario()) && getTipoPagamento() == recibo.getTipoPagamento() && Objects.equals(getDataDaCompra(), recibo.getDataDaCompra()) && Objects.equals(getNomeDoEvento(), recibo.getNomeDoEvento()) && Objects.equals(getDataDoEvento(), recibo.getDataDoEvento());
    }

    /**
     * Retorna o código hash deste recibo, baseado em todos os atributos.
     *
     * @return o código hash do recibo.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getLoginUsuario(), getTipoPagamento(), getDataDaCompra(), getNomeDoEvento(), getDataDoEvento(), getPreco());
    }

    /**
     * Retorna uma representação textual do recibo, incluindo login do usuário, tipo de pagamento,
     * data da compra, nome do evento, data do evento e preço do ingresso.
     *
     * @return String com as informações do recibo.
     */
    @Override
    public String toString() {
        return "Recibo: \n" +
                "Login: " + loginUsuario +
                "\nTipo de pagamento: " + tipoPagamento +
                "\nData da compra: " + dataDaCompra +
                "\nEvento: " + nomeDoEvento +
                "\nData do evento: " + dataDoEvento +
                "\nValor do ingresso: " + preco;
    }
}
