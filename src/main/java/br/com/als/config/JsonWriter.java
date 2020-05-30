package br.com.als.config;

import br.com.als.classes.perfis.PerfilModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonWriter {

    private static final String PATH = "perfis/%s.json";

    public void createJson(PerfilModel perfilCalculo) {
        try {
            Gson gson = new GsonBuilder().create();
            Writer writer = new FileWriter(String.format(PATH, perfilCalculo.getNomePerfil()), false);
            gson.toJson(perfilCalculo, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
