package net.vpg;

import net.vpg.vjson.SerializableObject;
import net.vpg.vjson.value.JSONArray;
import net.vpg.vjson.value.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MavenRepoAnalyzer {
    public static final Pattern ENTRY_PATTERN = Pattern.compile("<a href=\"(.+)\" title=\"\\1\">\\1</a>\\s+(\\d{4}-\\d{2}-\\d{2}|-)\\s+(\\d+|-)\\s+?");
    public static final String BASE_URL = "https://repo1.maven.org/maven2/";
    public static final AtomicInteger i = new AtomicInteger();
    public static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1000);

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        List<MavenRepoEntry> entries = getEntriesRecursive("");
        while (i.get() != 0) {
            System.out.println(i.get());
            Thread.sleep(1000);
        }
        try (PrintStream stream = new PrintStream("maven.json")) {
            stream.println(new JSONArray().addAll(entries).deserialize());
        }
    }

    public static List<MavenRepoEntry> getEntries(String location) {
        return WebUtil.getString(BASE_URL, location)
            .lines()
            .map(ENTRY_PATTERN::matcher)
            .filter(Matcher::matches)
            .map(m -> new MavenRepoEntry(m.group(1), m.group(2), m.group(3)))
            .collect(Collectors.toList());
    }

    public static List<MavenRepoEntry> getEntriesRecursive(String location) {
        List<MavenRepoEntry> entries = getEntries(location);
        entries.forEach(entry -> {
            if (entry.isDirectory()) {
                entry.setContents(location);
            }
        });
        return entries;
    }

    public static class MavenRepoEntry implements SerializableObject {
        final String name;
        final String lastModified;
        final int sizeInBytes;
        List<MavenRepoEntry> contents = null;

        public MavenRepoEntry(String name, String lastModified, String sizeInBytes) {
            this.name = name.replaceAll("/", "");
            this.lastModified = lastModified;
            this.sizeInBytes = sizeInBytes.equals("-") ? 0 : Integer.parseInt(sizeInBytes);
        }

        public boolean isDirectory() {
            return sizeInBytes == 0;
        }

        public List<MavenRepoEntry> getContents() {
            return contents;
        }

        public void setContents(String baseLocation) {
            executor.execute(() -> {
                i.incrementAndGet();
                try {
                    contents = getEntriesRecursive(baseLocation + name + "/");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error in contents of " + baseLocation + name + "/");
                    setContents(baseLocation);
                } finally {
                    i.decrementAndGet();
                }
            });
        }

        @Override
        public JSONObject toObject() {
            return new JSONObject().put(name, Objects.requireNonNullElse(contents, sizeInBytes));
        }
    }
}
