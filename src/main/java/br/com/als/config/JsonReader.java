package br.com.als.config;

import br.com.als.classes.acos.model.AcoModel;
import br.com.als.classes.perfis.PerfilModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonReader {

    private static Gson gson = new GsonBuilder().create();

    public static PerfilModel readPerfil(String path) throws IOException {
        try (Reader reader = new FileReader(path)) {
            return gson.fromJson(reader, PerfilModel.class);
        }
    }

    public static AcoModel readAco(String path) throws IOException {
        try (Reader reader = new FileReader(path)) {
            return gson.fromJson(reader, AcoModel.class);
        }
    }
}
