package com.trabalhomips.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Writer {
    public void writeFile(List<String> files, String suffix, String path) throws IOException {
        String pathWithoutExtension = path.replace(".asm", "");
        Path of = Path.of(pathWithoutExtension + suffix);
        Files.write(of, files);
        System.out.println("Escrita do arquivo concluida com sucesso no local: \n" + of);
    }
}
