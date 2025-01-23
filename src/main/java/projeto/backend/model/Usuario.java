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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Usuario {
    protected String login;
    private String senha;
    private String nome;
    private String cpf;
    private String email;
    private boolean admin;
    private List<Ingresso> ingressos;
    private List<Recibo> recibos;
    private List<Notificacoes> notificacoes;

    /**
     * Construtor
     *
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param nome  Nome do usuário.
     * @param cpf   CPF do usuário.
     * @param email Email do usuário.
     * @param admin Boolean para verificar o tipo do usuário(admin ou comum)
     */
    public Usuario(String login, String senha, String nome, String cpf, String email, boolean admin) {
        if (!cpfVerificacao(cpf)) {
            throw new IllegalArgumentException("ERRO! Digite o CPF correto!");
        }

        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.admin = admin;
    }

    public Usuario(String login, String senha, String nome, String cpf, String email) {
        if (!cpfVerificacao(cpf)) {
            throw new IllegalArgumentException("ERRO! Digite o CPF correto!");
        }
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.ingressos = new ArrayList<>();
        this.recibos = new ArrayList<>();
        this.notificacoes = new ArrayList<>();
    }

    //Getters e setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(cpfVerificacao(cpf)){
            this.cpf = cpf;
        }else{
            throw new IllegalArgumentException("ERRO! Digite o CPF correto!");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public List<Recibo> getCompras() {
        return recibos;
    }

    public void setCompras(List<Recibo> compras) {
        this.recibos = compras;
    }

    public void setNotificacoes(List<Notificacoes> notificacoes) {
        this.notificacoes = notificacoes;
    }

    //Verifica o CPF(quantidade de números e se é composta apenas por números)
    public boolean cpfVerificacao(String cpfVerif) {
        return cpfVerif.length() == 11 && cpfVerif.matches("[0-9]+");
    }

    public void adicionarNotificacoes(Notificacoes notificacao) {
        if (this.notificacoes == null) {
            this.notificacoes = new ArrayList<>();
        }
        this.notificacoes.add(notificacao);
    }

    public List<Notificacoes> getNotificacoes() {
        if (this.notificacoes == null) {
            this.notificacoes = new ArrayList<>();
        }
        return this.notificacoes;
    }

    /**
     * Compara dois usuários com base no login e CPF.
     *
     * @return Retorna true se os usuários forem iguais, false caso contrário.
     * @param o Objeto a ser comparado.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(getLogin(), usuario.getLogin()) && Objects.equals(getCpf(), usuario.getCpf());
    }

    /**
     * Gera o código hash baseado no login e CPF do usuário.
     * @return Código hash do usuário.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getCpf());
    }

    @Override
    public String toString() {
        Gson gson = new  Gson();
        return gson.toJson(this);
    }
}