package net.vpg;

import net.vpg.vjson.value.JSONObject;
import net.vpg.vjson.value.JSONValue;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PokeAPICache {
    public static final String BASE_URL = "https://pokeapi.co/api/v2/";

    public static void main(String[] args) {
        WebUtil.getData(BASE_URL)
            .toMap()
            .keySet()
            .forEach(endpoint -> {
                try (PrintStream output = new PrintStream("src/main/resources/raw/" + endpoint + ".json")) {
                    output.println("[");
                    AtomicInteger i = new AtomicInteger();
                    int count = WebUtil.getData(BASE_URL, endpoint + "/?limit=1").getInt("count");
                    WebUtil.getData(BASE_URL, endpoint + "/?limit=" + count)
                        .getArray("results")
                        .stream()
                        .map(JSONValue::toObject)
                        .map(obj -> obj.getString("url"))
                        .peek(url -> System.out.println("Progress: " + i.incrementAndGet() + "/" + count + " | GET /" + url.replace(BASE_URL, "")))
                        .parallel()
                        .map(WebUtil::getData)
                        .collect(Collectors.toList())
                        .stream()
                        .sequential()
                        .sorted(Comparator.comparingInt(result -> result.getInt("id")))
                        .map(JSONObject::toString)
                        .map(s -> s + ",")
                        .forEach(output::println);
                    output.println("{}]");
                    System.out.println(endpoint + " cached");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        System.out.println("Caching complete");
    }
}
