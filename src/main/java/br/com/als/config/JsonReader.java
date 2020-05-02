package br.com.als.config;

import br.com.als.classes.perfis.PerfilModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonReader {

    private static Gson gson = new GsonBuilder().create();

    public static PerfilModel read(String path) throws IOException {
        try (Reader reader = new FileReader(path)) {
            return gson.fromJson(reader, PerfilModel.class);
        }
    }
}
