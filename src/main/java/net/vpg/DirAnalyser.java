package net.vpg;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DirAnalyser {
    public static void main(String[] args) {
        List<File> files = listFiles(new File("F:"));
        List<List<File>> collect = files.stream()
            .collect(Collectors.groupingBy(File::getName))
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .map(Map.Entry::getValue)
            .filter(l -> l.size() > 1)
            .collect(Collectors.toList());
        System.out.println(collect.stream().mapToInt(List::size).sum() + " duplicate files found");
        System.out.println("Top directories:");
        collect.stream()
            .flatMap(List::stream)
            .map(File::getParent)
            .distinct()
            .forEach(dir -> {
                System.out.println(dir);
                collect.stream()
                    .flatMap(List::stream)
                    .filter(f -> f.getParent().equals(dir))
                    .peek(s -> {
//                        if (dir.endsWith("\\Sent")) s.delete();
                    })
                    .filter(File::exists)
                    .map(File::toString)
                    .map(s -> "---> "+s)
                    .sorted()
                    .forEach(System.out::println);
            });
        collect.forEach((dupes) -> {
            System.out.println(dupes.get(0).getName());
            dupes.forEach(dupe -> System.out.println("-> " + dupe));
        });
    }

    private static List<File> listFiles(File dir) {
        List<File> files = new ArrayList<>();
        File[] filesArr = dir.listFiles();
        if (filesArr == null) {
            files.add(dir);
            return files;
        }
        for (File file : filesArr) {
//            System.out.println("Seeing: " + file);
            if (file.isDirectory()) {
                files.addAll(listFiles(file));
            } else {
                files.add(file);
            }
        }
        return files;
    }
}
