package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import GraphIOModels.GraphData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonUtils {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writeGraphsToJson(List<GraphData> graphs, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(graphs, writer);
            System.out.println("✅ Saved graphs to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
