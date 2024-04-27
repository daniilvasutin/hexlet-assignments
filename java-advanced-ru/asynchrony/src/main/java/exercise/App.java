package exercise;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

class App {

    // BEGIN

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    public static CompletableFuture<String> unionFiles(String file1, String file2, String dest) {

        System.out.println("First file read..");
        CompletableFuture<String> futureFile1 = CompletableFuture.supplyAsync(() -> {

            String file1S;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            try {
                file1S = Files.readString(getFullPath(file1));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return file1S;
        });
        System.out.println("First END read");

        System.out.println("Second file read..");
        CompletableFuture<String> futureFile2 = CompletableFuture.supplyAsync(() -> {

            String file2S;

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            try {
                file2S = Files.readString(getFullPath(file2));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return file2S;
        });
        System.out.println("Second END read..");

        System.out.println("Combine two files...");
        CompletableFuture<String> twoFiles = futureFile1.thenCombine(futureFile2, (workFile1, workFile2) -> {
            var result = workFile1 + workFile2;

            try {
                Files.writeString(getFullPath(dest), result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }).exceptionally(ex -> {
            System.out.println("Oh you get exeption: " + ex.getMessage());
            return null;
        });

        return twoFiles;

    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        var destPath = Files.createFile(getFullPath("dest.txt")).toString();

        var result = unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt", destPath);
        result.get();
        // END
    }
}

