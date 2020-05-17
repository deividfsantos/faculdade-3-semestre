package com.trabalhomips;

import com.trabalhomips.business.Decoder;
import com.trabalhomips.business.Encoder;
import com.trabalhomips.file.Reader;
import com.trabalhomips.file.Writer;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println();
            Scanner input = new Scanner(System.in);
            System.out.println("Digite a opção desejada: ");
            System.out.println("1-Encode");
            System.out.println("2-Decode");
            System.out.println("3-Sair");
            System.out.print("Digite a opção: ");
            int op = Integer.parseInt(input.nextLine());
            System.out.println();
            Reader reader = new Reader();
            Writer writer = new Writer();
            System.out.println("Digite o caminho do arquivo: ");
            String filePath = input.nextLine();
            List<String> allLines = reader.readFile(filePath);
            switch (op) {
                case 3:
                    System.exit(0);
                case 1:
                    Encoder encoder = new Encoder();
                    List<String> encodedLines = encoder.encodeFile(allLines);
                    writer.writeFile(encodedLines, "-encoded.asm", filePath);
                    break;
                case 2:
                    Decoder decoder = new Decoder();
                    List<String> decodedLines = decoder.decodeFile(allLines);
                    writer.writeFile(decodedLines, "-decoded.asm", filePath);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}

