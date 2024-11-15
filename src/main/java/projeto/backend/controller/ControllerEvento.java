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

import projeto.backend.model.Comentario;
import projeto.backend.model.Evento;
import projeto.backend.model.Ingresso;
import projeto.backend.model.Usuario;
import projeto.backend.repository.ArmazenamentoDados;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerEvento {
    List<Evento> eventos = ArmazenamentoDados.eventos;
    //CADASTRO DE EVENTOS
    /**
     *
     * @param adm Usuário administrador que está cadastrando o evento.
     * @param nome Nome do evento.
     * @param descricao Descrição do evento.
     * @param quantidadeAssentos Quantidade de assentos disponíveis para o evento.
     * @param data Data do evento.
     * @return Retorna evento cadastrado.
     */
    public Evento cadastrarEvento(Usuario adm, String nome, String descricao, int quantidadeAssentos, Date data) {
        if (adm.isAdmin()) {
            Evento evento = new Evento(nome, descricao, quantidadeAssentos, data);
            evento.statusAtualizadoEvento();
            eventos.add(evento);
            ArmazenamentoDados.salvarNoRepositorio(evento, "eventosDados", evento.getIdEvento() + ".json");
            return evento;
        } else {
            throw new SecurityException("Somente administradores podem cadastrar eventos.");
        }
    }

    /**
     * Lista os eventos disponíveis no sistema.
     *
     * @return Retorna uma lista com os nomes dos eventos disponíveis.
     */

    public List<String> listarEventosDisponiveis() {
        List<Evento> eventos = ArmazenamentoDados.listarEventos();
        List<String> eventosDisp = new ArrayList<>();

        for(Evento evento: eventos){
            eventosDisp.add(evento.getNome());
        }
        return eventosDisp;
    }
    //FEEDBACK DO CLIENTE
    /**
     * Processa o comentário feito pelo usuário sovbre um evento participado por ele.
     * @param usuario Usuário participante.
     * @param evento Evento que participou.
     * @param nomeEvento Nome do evento que participou.
     * @param coment Comentário feito pelo usuário.
     * @return Retorna comentário ou erros.
     */
    public Comentario fazerComentario(Usuario usuario, Evento evento, String nomeEvento, String coment){
        boolean eventoCompradoPeloUsuario = false;
        for(Ingresso ingresso : usuario.getIngressos()) {
            if (ingresso.getEvento().getNome().equalsIgnoreCase(nomeEvento)) {
                eventoCompradoPeloUsuario = true;
            }
        }
        if(!listarEventosDisponiveis().contains(nomeEvento) && eventoCompradoPeloUsuario){
            Comentario comentario = new Comentario(usuario.getLogin(), nomeEvento, coment);
            if(evento.getComentarios() == null){
                evento.setComentarios(new ArrayList<>());
            }
            evento.getComentarios().add(comentario);
            ArmazenamentoDados.salvarNoRepositorio(evento, "eventosDados", evento.getIdEvento() + ".json");
            return comentario;
        }else{
            throw new IllegalArgumentException("Erro: Não é possível comentar neste evento. Verifique seus dados e tente novamente.");
        }
    }
}
