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

import projeto.backend.model.Evento;
import projeto.backend.model.Ingresso;
import projeto.backend.model.Usuario;
import projeto.backend.repository.ArmazenamentoDados;
import projeto.backend.repository.LeituraDados;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerUsuario {
    List<Evento> eventos = ArmazenamentoDados.eventos;
    //CADASTRO DE USUÁRIO
    /**
     *
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param nome Nome do usuário.
     * @param cpf CPF do usuário.
     * @param email E-mail do usuário.
     * @return Retorna usuário cadastrado.
     */

    public Usuario cadastrarUsuario(String login, String senha, String nome, String cpf, String email) {
        if (verificacaoLogin(login) == null) {
            Usuario usuario = new Usuario(login, senha, nome, cpf, email);
            ArmazenamentoDados.salvarNoRepositorio(usuario, "usuarioDados", usuario.getCpf() + ".json");
            return usuario;
        } else {
            throw new IllegalArgumentException("Erro ao cadastrar usuário. Verifique as informações e tente novamente.");
        }
    }

    /**
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param nome Nome do usuário.
     * @param cpf CPF do usuário.
     * @param email E-mail do usuário.
     * @param admin Indica se o usuário é um administrador.
     * @return Retorna administrador cadastrado.
     */

    public Usuario cadastrarUsuarioAdmin(String login, String senha, String nome, String cpf, String email, boolean admin) {
        if (verificacaoLogin(login) == null) {
            Usuario usuario = new Usuario(login, senha, nome, cpf, email, admin);
            ArmazenamentoDados.salvarNoRepositorio(usuario, "usuarioDados", usuario.getCpf() + ".json");
            return usuario;
        } else {
            throw new IllegalArgumentException("Erro ao cadastrar usuário. Verifique as informações e tente novamente.");
        }
    }

    //LOGIN DO USUÁRIO
    /**
     * Realiza o login de um usuário.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @return Retorna o usuário que fez login.
     */
    public Usuario fazerLogin(String login, String senha) {
        Usuario usuario = verificacaoLogin(login);
        if (usuario != null) {
            if (usuario.getSenha().equals(senha)) {
                return usuario;
            } else {
                throw new IllegalArgumentException("ERRO! Senha incorreta!");
            }
        } else {
            throw new IllegalArgumentException("ERRO! Login inexistente!");
        }
    }

    //EDIÇÃO DE DADOS
    /**
     * Edita o nome que está no login do usuário.
     * @param usuario Usuário a ser modificado.
     * @param newLogin O novo login escolhido pelo usuaário.
     */
    public void editarLogin(Usuario usuario, String newLogin) {
        if (verificacaoLogin(newLogin) == null) {
            usuario.setLogin(newLogin);
            File arquivoUsuario = geradorDeCaminho("Repositorio/usuarioDados", usuario.getCpf() + ".json");
            LeituraDados.ler(Usuario.class, arquivoUsuario.getPath());
            ArmazenamentoDados.salvarNoRepositorio(usuario, "usuarioDados", usuario.getCpf() + ".json");
        } else {
            throw new IllegalArgumentException("Dados incorretos, tente novamente!");
        }
    }

    /**
     * Edita a senha do usuário.
     * @param usuario Usuário a ser modificado.
     * @param newSenha Nova senha escolhida pelo usuário.
     */
    public void editarSenha(Usuario usuario, String newSenha){
        usuario.setSenha(newSenha);
        ArmazenamentoDados.salvarNoRepositorio(usuario, "usuarioDados", usuario.getCpf() + ".json");
    }

    /**
     * Edita o nome do usuário.
     * @param usuario Usuário a ser modificado.
     * @param newNome Nova nome escolhido pelo usuário.
     */
    public void editarNome(Usuario usuario, String newNome){
        usuario.setNome(newNome);
        ArmazenamentoDados.salvarNoRepositorio(usuario, "usuarioDados", usuario.getCpf() + ".json");
    }

    /**
     * Edita o email do usuário.
     * @param usuario Usuário a ser modificado.
     * @param newEmail Nova email escolhido pelo usuário.
     */
    public void editarEmail(Usuario usuario, String newEmail){
        usuario.setEmail(newEmail);
        ArmazenamentoDados.salvarNoRepositorio(usuario, "usuarioDados", usuario.getCpf() + ".json");
    }
    //MÉTODOS PARA USO INTERNO
    /**
     * /Gera o caminho do arquivo para a leitura de dados
     * @param caminhoInicial Caminho do repositório e o nome da pasta em que o arquivo json se encontra.
     * @param nomeArquivo Nome do arquivo json a ser buscado.
     * @return Retona o caminho do arquivo json.
     */
    public static File geradorDeCaminho(String caminhoInicial, String nomeArquivo){
        String caminho = caminhoInicial;
        File arquivoUsuario = new File(caminho, nomeArquivo);
        return arquivoUsuario;
    }


    /**
     * Verifica se existe um usuario com o mesmo login, uma vez que o login deveria ser algo único.
     * @param login Login do usuário
     * @return Retorna o usuário caso o login exista e null caso não.
     */
    public Usuario verificacaoLogin(String login) {
        File arquivosJson[];
        File diretorio = new File("Repositorio/usuarioDados");
        arquivosJson = diretorio.listFiles();

        if(!diretorio.isDirectory() || !diretorio.exists() || arquivosJson == null || arquivosJson.length == 0){
            return null;
        }else{
            for(int i = 0; i < arquivosJson.length; i++) {
                File arquivo = arquivosJson[i];

                if(arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".json")) {
                    Usuario usuario = LeituraDados.ler(Usuario.class, arquivo.getPath());
                    if(usuario != null && usuario.getLogin().equals(login)) {
                        return usuario;
                    }
                }
            }
        }
        return null;
    }

    public List<Ingresso> listarComprasRealizadas(Usuario usuario){
        List<Ingresso> comprasFeitas = new ArrayList<>(ArmazenamentoDados.listarComprasObjeto(usuario.getCpf() + ".json"));
        return comprasFeitas;
    }

    public List<Ingresso> listarEventosParticipados(Usuario usuario){
        List<String> eventosPassados = ArmazenamentoDados.listarEventosPassados();
        List<Ingresso> ingressosComprados = listarComprasRealizadas(usuario);
        List<Ingresso> ingressosEventosParticipados = new ArrayList<>();

        for(Ingresso ingresso: ingressosComprados){
            if(eventosPassados.contains(ingresso.getEvento().getIdEvento())){
                ingressosEventosParticipados.add(ingresso);
            }
        }
        System.out.println(ingressosEventosParticipados);
        return ingressosEventosParticipados;
    }

    public void atualizarRecibo(Usuario usuario){
        File arquivosJson[];
        File diretorio = new File("Repositorio/usuarioDados");
        arquivosJson = diretorio.listFiles();
        Date dataAtual = new Date();
        System.out.println(dataAtual);
        if(diretorio.isDirectory() || diretorio.exists() || arquivosJson != null || arquivosJson.length > 0){
            for (int i = 0; i < arquivosJson.length; i++) {
                File arquivo = arquivosJson[i];

                if(arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".json")) {
                    Ingresso ingresso = LeituraDados.ler(Ingresso.class, arquivo.getPath());
                    System.out.println(ingresso.getDataEvento());
                    if (ingresso != null && ingresso.getDataEvento().before(dataAtual)) {
                        ingresso.setAtivo(false);
                        ArmazenamentoDados.salvarNoRepositorio(usuario, "Repositorio/usuarioDados", usuario.getCpf()+".json");
                    }
                }
            }
        }
    }
}
