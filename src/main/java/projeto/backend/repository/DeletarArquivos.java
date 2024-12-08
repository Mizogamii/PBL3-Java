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

import java.io.File;

/**
 * Deleta os arquivos.
 */
public class DeletarArquivos {
    public static void apagarJson(String nomeDiretorio) {
        File diretorio = new File(nomeDiretorio);
        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles();
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    if (arquivo.isDirectory()) {
                        apagarJson(arquivo.getPath());
                    }else if(arquivo.isFile() && arquivo.getName().endsWith(".json")) {
                        arquivo.delete();
                    }
                }
            }
        }
    }
}
