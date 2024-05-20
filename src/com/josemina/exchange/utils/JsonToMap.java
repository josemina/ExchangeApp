package com.josemina.exchange.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JsonToMap {
        public static Map<String, String> readJsonFile(String fileName) throws IOException {
        StringBuilder jsonContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            FileWriter writer = new FileWriter("logs.txt");
            writer.write(e.getMessage());
            writer.close();
        }
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
        return gson.fromJson(jsonContent.toString(), type);
    }
}
