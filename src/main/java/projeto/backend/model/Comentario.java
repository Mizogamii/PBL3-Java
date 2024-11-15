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

/**
 * Classe para representar o comentáeio feito pelo usuário sobre um evento.
 */
public class Comentario {
    private String login;
    private String nomeEvento;
    private String coment;

    /**
     * @param login Login do usuário que fez o comentário.
     * @param nomeEvento Nome do evento relacionado ao .
     * @param coment Rexto do comentário feito pelo usuário.
     */
    public Comentario(String login, String nomeEvento, String coment) {
        this.login = login;
        this.nomeEvento = nomeEvento;
        this.coment = coment;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String nomeUsuario) {
        this.login = nomeUsuario;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }


}

