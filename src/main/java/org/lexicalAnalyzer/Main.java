package org.lexicalAnalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
/**
 * Author: Emilio Sánchez Enriquez
 * Date: 12/11/2023
 * Project: Lexical Analyzer
 */
public class Main {
    public static void main(String[] args) {

        FileSystemView desktop = FileSystemView.getFileSystemView();
        String desktopPath = desktop.getHomeDirectory().getAbsolutePath();
        JFileChooser fileChooser = new JFileChooser(desktopPath);
        List<TokenPair> tokens = new ArrayList<>();

        String fileName = "";
        String filePath = "";
        String output = "";

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            fileName = selectedFile.getName();
        } else {
            System.exit(0);
        }

        try {
            Reader file = new BufferedReader(new FileReader(filePath));

            LexicalAnalyzer lexer = new LexicalAnalyzer(file);

            while (true) {
                Token token = lexer.yylex();

                if (token.getTokenType() == TokenType.EOF) {
                    break;
                }
                tokens.add(new TokenPair(token.getTokenType().toString(), token.getLexeme()));
            }

            PrintWriter writer = new PrintWriter(new FileWriter(desktopPath + "/tokens_" + fileName));

            for (TokenPair pair : tokens) {
                output = pair.toString();
                writer.println(output);
            }

            writer.close();

            System.out.println("Data saved: " + filePath + "\\tokens_" + fileName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}