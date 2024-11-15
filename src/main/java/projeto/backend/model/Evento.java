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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Evento {
    private String nome;
    private String descricao;
    private int quantidadeAssentosDisponiveis;
    private Date data;
    private boolean ativo;
    private String idEvento;
    private List<Comentario> comentarios;

    /**
     * Contrutor
     *
     * @param nome      Nome do evento.
     * @param descricao Descrição do evento.
     * @param data      Data do evento.
     */
    public Evento(String nome, String descricao, int quantidadeAssentosDisponiveis, Date data) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeAssentosDisponiveis = quantidadeAssentosDisponiveis;
        this.data = data;

        SimpleDateFormat dataEdit = new SimpleDateFormat("dd-MM-yyyy");
        String codigo = nome + dataEdit.format(data);
        this.idEvento = codigo;
    }

    //Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Verifica se o evento ainda está ativo, ou seja, se a data atual é anterior à data do evento.
     *
     * @return Retorna true se o evento está ativo, false caso contrário.
     */
    public boolean isAtivo() {
        statusAtualizadoEvento();
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getQuantidadeAssentosDisponiveis() {
        return quantidadeAssentosDisponiveis;
    }

    public void setQuantidadeAssentosDisponiveis(int quantidadeAssentosDisponiveis) {
        this.quantidadeAssentosDisponiveis = quantidadeAssentosDisponiveis;
    }

    public boolean quantidadeEvento() {
        return this.quantidadeAssentosDisponiveis > 0;
    }

    public void statusAtualizadoEvento() {
        Date dataAtual = new Date();
        this.ativo = dataAtual.before(this.data);
    }

    /**
     * Compara dois eventos para verificar se são iguais, com base nos atributos nome, descrição, data e assentos disponíveis.
     * @param o Objeto a ser comparado com o evento atual
     * @return Retorna true se os eventos forem iguais e false caso não sejam.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento evento)) return false;
        return isAtivo() == evento.isAtivo() && Objects.equals(getNome(), evento.getNome()) && Objects.equals(getDescricao(), evento.getDescricao()) && Objects.equals(getData(), evento.getData());
    }

    /**
     * Retorna o código hash baseado nos atributos do evento.
     * @return Retorna código hash do evento.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getDescricao(), getData(), isAtivo());
    }

    /**
     * Retorna uma representação em String do evento, incluindo nome, descrição,
     * quantidade de assentos disponíveis, data, status ativo e ID do evento.
     * @return Retorna string com informações do evento.
     */
    @Override
    public String toString() {
        return "Evento{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidadeAssentosDiponiveis=" + quantidadeAssentosDisponiveis +
                ", data=" + data +
                ", ativo=" + ativo +
                ", idEvento='" + idEvento + '\'' +
                '}';
    }
}
