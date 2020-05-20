package net.home.parser.clazz;

import net.home.wrapper.ClassWrapper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ClassParser {
    private ClassParser() {
    }

    public static ClassWrapper parse(File file) throws IOException {
        return parse(file.toPath());
    }

    public static ClassWrapper parse(String path) throws IOException {
        return parse(Paths.get(path));
    }

    public static ClassWrapper parse(Path path) throws IOException {
        List<Byte> content = getContent(path);
        return getClassWrapper(content);
    }

    private static List<Byte> getContent(Path path) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(path.toFile()))) {
            List<Byte> content = new ArrayList<>();
            for (int b = is.read(); b != -1; b = is.read()) {
                content.add((byte) b);
            }
            return content;
        }
    }

    private static ClassWrapper getClassWrapper(List<Byte> content) {
        return null;
    }
}
