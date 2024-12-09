/*******************************************************************************************
 Autor: Sayumi Mizogami Santana
 Componente Curricular: EXA 863 - MI Programação
 Concluido em: 08/12/2024
 Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 *******************************************************************************************/
package projeto.backend.model;

public class Notificacoes {
    private String mensagemNotificada;
    private boolean lido;


    public Notificacoes(String mensagemNotificada) {
        this.mensagemNotificada = mensagemNotificada;
    }

    public String getMensagemNotificada() {
        return mensagemNotificada;
    }

    public void setMensagemNotificada(String mensagemNotificada) {
        this.mensagemNotificada = mensagemNotificada;
    }

    @Override
    public String toString() {
        return "Notificacoes{" +
                "mensagemNotificada='" + mensagemNotificada + '\'' +
                '}';
    }
}
