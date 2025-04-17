package utils;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Atualizador {

    // URL para o arquivo de versão (raw) no GitHub
    private static final String URL_VERSAO =
            "https://raw.githubusercontent.com/billdev-oficial/agroperolaSoftware/main/versao.txt";

    // URL para o ZIP da release no GitHub (supondo que você já publica uma release com esse nome)
    private static final String URL_ZIP =
            "https://github.com/billdev-oficial/agroperolaSoftware/releases/latest/download/agroperolaSoftware.zip";

    // Lê a versão local do arquivo versao.txt (que deve estar no mesmo diretório do .jar)
    public static String lerVersaoLocal() {
        try {
            return Files.readString(Path.of("versao.txt")).trim();
        } catch (IOException e) {
            return "0.0.0";  // fallback se não encontrar
        }
    }

    // Lê a versão publicada no GitHub
    public static String lerVersaoOnline() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(URL_VERSAO).openStream()))) {
            return in.readLine().trim();
        } catch (IOException e) {
            return null;
        }
    }

    // Verifica se há atualização
    public static boolean verificarAtualizacao() {
        String local = lerVersaoLocal();
        String online = lerVersaoOnline();
        return online != null && !online.isEmpty() && !online.equals(local);
    }

    // Baixa o ZIP e descompacta no diretório atual
    public static void atualizarAplicativo(JFrame parent) {
        SwingWorker<Void,Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Download do ZIP
                try (InputStream in = new URL(URL_ZIP).openStream()) {
                    Files.copy(in, Path.of("update.zip"), StandardCopyOption.REPLACE_EXISTING);
                }

                // Descompactar
                try (ZipInputStream zis = new ZipInputStream(new FileInputStream("update.zip"))) {
                    ZipEntry entry;
                    while ((entry = zis.getNextEntry()) != null) {
                        Path path = Paths.get(entry.getName());
                        if (entry.isDirectory()) {
                            Files.createDirectories(path);
                        } else {
                            Files.createDirectories(path.getParent());
                            try (OutputStream out = Files.newOutputStream(path)) {
                                byte[] buffer = new byte[4096];
                                int len;
                                while ((len = zis.read(buffer)) > 0) {
                                    out.write(buffer, 0, len);
                                }
                            }
                        }
                        zis.closeEntry();
                    }
                }

                // Atualizar versao.txt
                String novaVersao = lerVersaoOnline();
                if (novaVersao != null) {
                    Files.writeString(Path.of("versao.txt"), novaVersao, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                }

                // Remover ZIP temporário
                Files.deleteIfExists(Path.of("update.zip"));

                return null;
            }

            @Override
            protected void done() {
                JOptionPane.showMessageDialog(parent,
                        "Atualização concluída!\nReinicie o aplicativo para usar a versão mais recente.",
                        "Atualização", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        worker.execute();
    }
}