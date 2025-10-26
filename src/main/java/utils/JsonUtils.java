package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonUtils {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> T readJson(String path, Class<T> clazz) throws IOException {
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, clazz);
        }
    }

    public static <T> T[] readJsonArray(String path, Class<T[]> clazz) throws IOException {
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, clazz);
        }
    }

    public static void writeJson(String path, Object data) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        }
    }
}
