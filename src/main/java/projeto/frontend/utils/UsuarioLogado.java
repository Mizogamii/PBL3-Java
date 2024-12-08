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
package projeto.frontend.utils;

import projeto.backend.model.Usuario;

/**
 * Classe utilitária para gerenciar o usuário logado na aplicação.
 * Permite realizar login, logout e verificar o estado de autenticação.
 */
public class UsuarioLogado {
    /** Referência ao usuário atualmente logado na aplicação. */
    private static Usuario usuarioLogado;

    /**
     * Realiza o login de um usuário na aplicação.
     *
     * @param usuario Instância do usuário que será autenticado.
     */
    public static void login(Usuario usuario){
        usuarioLogado = usuario;
    }

    /**
     * Realiza o logout do usuário atual, removendo a autenticação.
     */
    public static void sairConta(){
        usuarioLogado = null;
    }

    /**
     * Verifica se existe um usuário logado na aplicação.
     *
     * @return {@code true} se há um usuário logado, {@code false} caso contrário.
     */
    public static boolean isLogado(){
        return usuarioLogado != null;
    }


    /**
     * Retorna o usuário atualmente logado na aplicação.
     *
     * @return Instância do {@link Usuario} logado ou {@code null} se nenhum usuário estiver autenticado.
     */
    public static Usuario getUsuarioLogado(){
        return usuarioLogado;
    }
}
