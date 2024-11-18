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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
/**
 * A classe {@code Pagamento} representa a operação de pagamento de um ingresso,
 * incluindo o tipo de pagamento utilizado, a data do pagamento, o ingresso comprado e o login do usuário que efetuou a compra.
 * <p>Suporta diversos tipos de pagamento, como crédito, boleto, Pix e débito, e permite a geração de um recibo contendo
 * os detalhes do pagamento.</p>
 *
 * @see Ingresso
 * @see Recibo
 */

public class Pagamento {
    /**
     * Enumeração que define os tipos de pagamento disponíveis.
     */
    public enum TipoPagamento {
        BOLETO, CREDITO, DEBITO, PIX;
    }

    private TipoPagamento tipoPagamento;
    private Date data;
    private Ingresso ingresso;
    private String loginUsuario;

    /**
     * Construtor que cria uma instância de Pagamento com os parâmetros especificados.
     *
     * @param tipoPagamento Tipo de pagamento selecionado para a compra.
     * @param data Data em que o pagamento foi realizado.
     * @param ingresso Ingresso associado ao pagamento.
     * @param loginUsuario Login do usuário que realizou a compra.
     */
    public Pagamento(TipoPagamento tipoPagamento, Date data, Ingresso ingresso, String loginUsuario){
        this.tipoPagamento = tipoPagamento;
        this.data = data;
        this.ingresso = ingresso;
        this.loginUsuario = loginUsuario;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    /**
     * Gera um recibo contendo as informações do pagamento, incluindo o login do usuário, tipo de pagamento,
     * data da compra, nome do evento, data do evento e preço do ingresso.
     *
     * @param loginUsuario Login do usuário que realizou a compra.
     * @param tipoPagamento Tipo de pagamento utilizado.
     * @param dataDaCompra Data em que a compra foi efetuada.
     * @param nomeDoEvento Nome do evento associado ao ingresso.
     * @param dataDoEvento Data do evento.
     * @param preco Preço do ingresso.
     * @return Recibo contendo as informações da compra.
     */
    public Recibo gerarRecibo(String loginUsuario, TipoPagamento tipoPagamento, Date dataDaCompra, String nomeDoEvento, Date dataDoEvento, double preco){
        Recibo recibo = new Recibo(loginUsuario, tipoPagamento, dataDaCompra, nomeDoEvento, dataDoEvento, preco);
        return recibo;
    }
    /**
     * Compara este objeto Pagamento com o objeto especificado para verificar se são iguais.
     * A comparação é feita com base nos atributos de tipo de pagamento, data e ingresso.
     *
     * @param o Objeto a ser comparado.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagamento compra)) return false;
        return Objects.equals(getTipoPagamento(), compra.getTipoPagamento()) && Objects.equals(getData(), compra.getData()) && Objects.equals(getIngresso(), compra.getIngresso());
    }
    /**
     * Retorna o código hash baseado nos atributos do pagamento.
     *
     * @return Código hash do pagamento.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTipoPagamento(), getData(), getIngresso());
    }

    /**
     * Retorna uma representação em {@code String} do pagamento, exibindo o login do usuário,
     * tipo de pagamento, data da compra, nome do evento do ingresso e a data do evento.
     *
     * @return Representação em {@code String} do pagamento.
     */
    @Override
    public String toString() {
        return "Recibo "+
                "\\nLogin: " + loginUsuario +
                "\\nTipo de pagamento: " + tipoPagamento +
                "\\nData da compra: " + data +
                "\\nIngresso comprado: " + ingresso.getEvento().getNome() +
                "\\nData do evento: " + ingresso.getEvento().getData();
    }

}
