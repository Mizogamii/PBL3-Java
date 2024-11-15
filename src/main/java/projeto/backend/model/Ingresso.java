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

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Ingresso {
    private Evento evento;
    private double preco;
    private boolean ativo;
    private String idIngresso;
    private Pagamento compra;

    /**
     * Construtor
     * @param evento Evento associado ao ingresso.
     * @param preco Preço do ingresso.
     */
    public Ingresso(Evento evento, double preco) {
        this.evento = evento;
        this.preco = preco;
        this.ativo = true;
        this.idIngresso = UUID.randomUUID().toString();
    }

    //Getters e setters
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isAtivo(){
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getIdIngresso() {
        return idIngresso;
    }

    /**
     * Cancela o ingresso, se a data do evento for posterior à data atual.
     * @return Retorna  true se o ingresso foi cancelado com sucesso, false se o ingresso não pode ser cancelado.
     */
    public boolean cancelar(){
        boolean result;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 9);
        Date data = calendar.getTime();
        result = data.after(evento.getData());
        if(result){
            this.ativo = true;
            return false;
        }else{
            this.ativo = false;
            return true;
        }
    }

    /**
     * Compara dois ingressos para verificar se são iguais, com base no evento, preço, assento e status ativo.
     * @param o Objeto a ser comparado com o ingresso atual.
     * @return Retorna true se os ingressos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingresso ingresso)) return false;
        return Double.compare(preco, ingresso.preco) == 0 && ativo == ingresso.ativo && Objects.equals(evento, ingresso.evento);
    }

    /**
     * Retorna o código hash baseado nos atributos do ingresso.
     * @return Retorna Código hash do ingresso.
     */
    @Override
    public int hashCode() {
        return Objects.hash(evento, preco, ativo);
    }

    @Override
    public String toString() {
        return "\nNome do evento: " + evento.getNome() +
                "\nPreco: " + preco +
                "\nEvento disponível: " + ativo;
    }
}


