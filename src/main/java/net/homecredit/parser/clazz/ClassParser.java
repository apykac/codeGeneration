package net.homecredit.parser.clazz;

import net.homecredit.entity.clazz.ClassEntity;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ClassParser {
    public ClassParser() {
    }

    public ClassEntity parse(File file) throws IOException {
        return parse(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
    }

    public ClassEntity parse(Path path) throws IOException {
        return parse(Files.readAllLines(path, StandardCharsets.UTF_8));
    }

    public ClassEntity parse(String path) throws IOException {
        return parse(Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
    }

    private ClassEntity parse(List<String> content) {
        return null;
    }
}
