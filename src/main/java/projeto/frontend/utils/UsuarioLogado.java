package projeto.frontend.utils;

import projeto.backend.model.Usuario;

public class UsuarioLogado {
    private static Usuario usuarioLogado;

    public static void login(Usuario usuario){
        usuarioLogado = usuario;
    }

    public static void sairConta(){
        usuarioLogado = null;
    }
    public static boolean isLogado(){
        return usuarioLogado != null;
    }

    public static Usuario getUsuarioLogado(){
        return usuarioLogado;
    }
}
