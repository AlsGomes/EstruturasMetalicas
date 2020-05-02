package br.com.als.config;

import br.com.als.classes.perfis.PerfilModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonWriter {

    private static final String PATH = "perfis/L50.8x50.8x3.2.json";
    private static Gson gson = new GsonBuilder().create();

    public static void createJson() throws IOException {

        try (Writer writer = new FileWriter(PATH, false)) {
            PerfilModel perfil = new PerfilModel();
            gson.toJson(perfil, writer);
        }
    }
}
