package br.com.als.config;

import br.com.als.classes.perfis.PerfilModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonWriter {

    private static final String PATH = "perfis/%s.json";
    private static Gson gson = new GsonBuilder().create();

    public static void createJson(PerfilModel perfilCalculo) throws IOException {

        try (Writer writer = new FileWriter(String.format(PATH, perfilCalculo.getNomePerfil()), false)) {
            PerfilModel perfil = new PerfilModel();
            gson.toJson(perfil, writer);
        }
    }
}
