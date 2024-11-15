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
package projeto.backend.controller;

import projeto.backend.model.*;
import projeto.backend.repository.ArmazenamentoDados;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerCompra {
    List<Evento> eventos = ArmazenamentoDados.eventos;
    //INGRESSOS E COMPRA
    /**
     * @param usuario Usuário que está comprando o ingresso.
     * @param evento Evento para o qual o ingresso está sendo criado.
     * @param preco Preço do ingresso.
     * @return Ingresso criado.
     */
    public Ingresso criarIngresso(Usuario usuario, Evento evento, double preco) {
        Ingresso ingresso = new Ingresso(evento, preco);
        if (usuario.getIngressos() == null) {
            usuario.setIngressos(new ArrayList<>());
        }
        usuario.getIngressos().add(ingresso);
        ingresso.setAtivo(false);
        return ingresso;
    }

    /**
     * Atualiza a quantidade de assentos disponíveis para um evento.
     * @param evento Evento cuja quantidade de assentos será atualizada.
     */
    public void atualizarQuantidadeAssentoDisponivel(Evento evento) {
        if (evento.getQuantidadeAssentosDisponiveis() > 0) {
            evento.setQuantidadeAssentosDisponiveis(evento.getQuantidadeAssentosDisponiveis() - 1);
            ArmazenamentoDados.salvarNoRepositorio(evento, "eventosDados", evento.getIdEvento() + ".json");
        }
    }

    /**
     *
     * @param usuario Usuário que está comprando o ingresso.
     * @param nomeEvento Nome do evento.
     * @param preco Preço do ingresso.
     * @param tipoPagamento Tipo de pagamento utilizado.
     * @param dataDaCompra Data da compra.
     * @param loginUsuario Login do usuário.
     * @return Retorna o ingresso comprado.
     */
    public Ingresso fazerIngresso(Usuario usuario, String nomeEvento, double preco, Pagamento.TipoPagamento tipoPagamento, Date dataDaCompra, String loginUsuario) {
        for (Evento evento : eventos) {
            if (evento.getNome().equals(nomeEvento) && evento.getData().after(dataDaCompra) && evento.quantidadeEvento()) {
                Ingresso ingresso = criarIngresso(usuario, evento, preco);
                atualizarQuantidadeAssentoDisponivel(evento);
                comprarIngresso(tipoPagamento, dataDaCompra, ingresso, loginUsuario, usuario);
                ArmazenamentoDados.salvarNoRepositorio(usuario, "usuarioDados", usuario.getCpf() + ".json");
                return ingresso;
            }
        }
        return null;
    }

    /**
     *
     * Processa o pagamento de um ingresso e gera um recibo.
     *
     * @param tipoPagamento Tipo de pagamento utilizado.
     * @param dataDaCompra Data da compra.
     * @param ingresso Ingresso que está sendo comprado.
     * @param loginUsuario Login do usuário.
     * @param usuario Usuário que está comprando o ingresso.
     * @return Retorna o recibo gerado para a compra.
     */
    public Recibo comprarIngresso(Pagamento.TipoPagamento tipoPagamento, Date dataDaCompra, Ingresso ingresso, String loginUsuario, Usuario usuario) {
        Pagamento pagamento = new Pagamento(tipoPagamento, dataDaCompra, ingresso, loginUsuario);
        Recibo recibo = pagamento.gerarRecibo(loginUsuario, tipoPagamento, dataDaCompra, ingresso.getEvento().getNome(), ingresso.getEvento().getData(), ingresso.getPreco());
        usuario.getCompras().add(recibo);

        return recibo;
    }

    /**
     * Manda confirmação por email
     * @param recibo Recibo da compra
     * @param email Email do usuário que realizou a compra
     * @return Retorna a confirmação
     */
    public String confirmaçãoDaCompra(Recibo recibo, String email){
        return "Email: " + email + "\n" + recibo.toString();
    }

    /**
     * Cancela a compra de um ingresso.
     *
     * @param usuario Usuário que está cancelando a compra.
     * @param ingresso Ingresso que está sendo cancelado.
     * @return Retorna true caso o cancelamento for bem sucedido, false caso contrário.
     */
    public boolean cancelarCompra(Usuario usuario, Ingresso ingresso) {
        boolean cancelamento = ingresso.cancelar();
        Evento evento = ingresso.getEvento();
        if (cancelamento) {
            usuario.getIngressos().removeIf(cancIngresso -> cancIngresso.equals(ingresso));
            evento.setQuantidadeAssentosDisponiveis(evento.getQuantidadeAssentosDisponiveis() + 1);
            ArmazenamentoDados.salvarNoRepositorio(usuario, "usuarioDados", usuario.getCpf() + ".json");
        }
        return cancelamento;
    }

    /**
     * Lista os ingressos comprados pelo usuário.
     * @param usuario Objeto usuário.
     * @return Retorna lista de ingressos comprados pelo usuário.
     */
    public List<String> listarIngressosComprados(Usuario usuario) {
        List<String> ingressosComprados = ArmazenamentoDados.listarCompras();
        return ingressosComprados;
    }
}
