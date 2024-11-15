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
package projeto.backend.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Faz a leitura dos dados para possíveis buscas e listagens.
 */
public class LeituraDados {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> T ler(Class<T> classeTipo, String caminho) {
        try (Reader leitura = new FileReader(caminho)) {
            return gson.fromJson(leitura, classeTipo);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
