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
package projeto.backend.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import projeto.backend.model.Evento;
import projeto.backend.model.Ingresso;
import projeto.backend.model.Usuario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe responsável pelo armazenamento e gerenciamento de dados.
 */
public class ArmazenamentoDados {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Evento> eventos = new ArrayList<>();

    /**
     * Carrega todos os eventos salvos no repositório para a memória.
     * Os eventos são carregados a partir dos arquivos JSON encontrados.
     */
    public static void carregarEventos() {
        eventos.clear();
        List<File> arquivos = listarArquivos("eventosDados");
        for(File arquivo : arquivos){
            Evento evento = LeituraDados.ler(Evento.class, arquivo.getPath());
            if(evento != null){
                eventos.add(evento);
            }
        }
    }

    /**
     * Salva o objeto em atquivo Json.
     * @param objeto Objeto a ser salvo.
     * @param caminho Caminho do arquivo que será utilizado.
     * @throws IOException Para caso ocorram erros.
     */

    public static void salvarObjeto(Object objeto, String caminho) throws IOException {
        try (FileWriter inserir = new FileWriter(caminho)) {
            gson.toJson(objeto, inserir);
            inserir.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar objeto no caminho: " + caminho, e);
        }
    }


    /**
     * Salva um objeto no repositório
     * @param objeto Obejto a ser salvo
     * @param nomePasta Nome da pasta onde será salvo o objeto.
     * @param nomeDoArquivo Nome do arquivo json onde o objeto será salvo.
     */
    public static <T> void salvarNoRepositorio(T objeto, String nomePasta, String nomeDoArquivo) {
        try {
            File pastaDados = new File("Repositorio", nomePasta);
            if (!pastaDados.exists()) {
                pastaDados.mkdirs();
            }
            String caminho = pastaDados.getPath() + File.separator + nomeDoArquivo;
            salvarObjeto(objeto, caminho);
        } catch (IOException e) {
            System.err.println("Erro ao salvar no repositório: " + nomePasta + "/" + nomeDoArquivo);
            e.printStackTrace();
        }
    }


    /**
     * Lista os arquivos de dentro do repositório para futuras utilizações.
     * @param tipo Tipo do arquivo a ser listado.
     * @return Retorna a lista de arquivos encontrados.
     */
    public static List<File> listarArquivos(String tipo) {
        File diretorio = new File("Repositorio/" + tipo);
        File[] arquivosJson = diretorio.listFiles();
        List<File> arquivos = new ArrayList<>();

        if (arquivosJson != null) {
            for (File arquivo : arquivosJson) {
                if (arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".json")) {
                    arquivos.add(arquivo);
                }
            }
        }
        return arquivos;
    }

    /**
     * Lista todos os eventos ativos no sistema
     * @return Retorna uma lista de objetos de eventos ativos
     */
    public static List<Evento> listarEventos(){
        List<Evento> eventosDisponiveis = new ArrayList<>();
        List<File> arquivos = listarArquivos("eventosDados");

        for(File arquivo : arquivos){
            Evento evento = LeituraDados.ler(Evento.class, arquivo.getPath());
            if (evento != null && evento.isStatusEvento()) {
                eventosDisponiveis.add(evento);
            }
        }
        return eventosDisponiveis;
    }

    /**
     * Lista todos os eventos salvos no sistema, independentemente do status.
     *
     * @return Retorna uma lista de todos os eventos salvos.
     */
    public static List<Evento> listarTodosEventos(){
        List<Evento> todosEventos = new ArrayList<>();
        List<File> arquivos = listarArquivos("eventosDados");

        for(File arquivo : arquivos){
            Evento evento = LeituraDados.ler(Evento.class, arquivo.getPath());
                todosEventos.add(evento);
        }
        return todosEventos;
    }

    /**
     * Lista os ingressos comprados por um usuário específico.
     *
     * @param nomeArquivoUsuario Nome do arquivo JSON correspondente ao usuário.
     * @return Retorna uma lista de strings com informações essenciais dos ingressos.
     */
    public static List<String> listarCompras(String nomeArquivoUsuario) {
        List<String> ingressosComprados = new ArrayList<>();
        File diretorio = new File("Repositorio/usuarioDados");
        File arquivosJson[];
        arquivosJson = diretorio.listFiles();

        if (diretorio.isDirectory() && diretorio.exists() && arquivosJson != null && arquivosJson.length != 0) {
            for(File arquivo: arquivosJson) {
                if(arquivo.isFile() && arquivo.getName().equals(nomeArquivoUsuario)) {
                    Usuario usuario = LeituraDados.ler(Usuario.class, arquivo.getPath());
                    if(usuario != null && usuario.getIngressos()!= null) {
                        for (Ingresso ingresso : usuario.getIngressos()) {
                            String informacoesDoIngresso = "Evento: " + ingresso.getEvento().getNome() +
                                    "Descricao: " + ingresso.getEvento().getDescricao() +
                                    "Data: " + ingresso.getEvento().getData();
                            ingressosComprados.add(informacoesDoIngresso);
                        }
                    }
                    return ingressosComprados;
                }
            }
        }
        return ingressosComprados;
    }

    /**
     * Lista os ingressos comprados por um usuário como objetos do tipo Ingresso.
     *
     * @param nomeArquivoUsuario Nome do arquivo JSON correspondente ao usuário.
     * @return Retorna uma lista de objetos do tipo Ingresso.
     */
    public static List<Ingresso> listarComprasObjeto(String nomeArquivoUsuario){
        List<Ingresso> ingressosComprados = new ArrayList<>();
        List<File> arquivos = listarArquivos("usuarioDados");

        for(File arquivo: arquivos){
            if(arquivo.isFile() && arquivo.getName().equals(nomeArquivoUsuario)){
                Usuario usuario = LeituraDados.ler(Usuario.class, arquivo.getPath());
                if(usuario.getIngressos() != null){
                    for (Ingresso ingresso : usuario.getIngressos()) {
                        ingressosComprados.add(ingresso);
                    }
                    return ingressosComprados;
                }
            }
        }
        return ingressosComprados;
    }

    /**
     * Lista todos os eventos que já ocorreram (inativos).
     *
     * @return Retorna uma lista de identificadores de eventos passados.
     */
    public static List<String> listarEventosPassados(){
        List<String> eventosPassados = new ArrayList<>();
        List<File> arquivos = listarArquivos("eventosDados");

        for(File arquivo : arquivos){
            Evento evento = LeituraDados.ler(Evento.class, arquivo.getPath());
            if (evento != null && !evento.isStatusEvento()) {
                String idEventosPassados = evento.getIdEvento();
                eventosPassados.add(idEventosPassados);
            }
        }
        return eventosPassados;
    }
}

