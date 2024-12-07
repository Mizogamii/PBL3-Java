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
