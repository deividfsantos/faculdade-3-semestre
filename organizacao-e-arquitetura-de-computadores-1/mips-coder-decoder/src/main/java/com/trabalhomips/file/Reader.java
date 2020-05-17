package com.trabalhomips.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Reader {
    public List<String> readFile(String path) throws IOException {
        final Path of = Path.of(path);
        System.out.println("Leitura do arquivo iniciada no local: \n" + of);
        return Files.readAllLines(of);
    }
}
