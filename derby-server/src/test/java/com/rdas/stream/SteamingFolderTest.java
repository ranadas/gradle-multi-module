package com.rdas.stream;

import com.monitorjbl.xlsx.StreamingReader;
import com.rdas.stream.impl.XExcelFileReader;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


//https://poi.apache.org/spreadsheet/how-to.html#sxssf
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
public class SteamingFolderTest {
    private static final Logger logger = LoggerFactory.getLogger(SteamingFolderTest.class);

    @Value("${integrationtests.localExcelFolder}")
    private String rootPath;

    @Ignore
    public void walkFolder2() throws Exception {
        Files.walk(Paths.get(rootPath))
                .filter(file -> file.toString().endsWith(".xlsx"))
                .distinct()
                .forEach(SteamingFolderTest::processFile);
    }

    private static void processFile(Path path) {
        try {
            XExcelFileReader fileReader = new XExcelFileReader(path.toString());
            System.out.println(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void walkFolder() throws Exception {

        Files.walk(Paths.get(rootPath))
                //.filter(file-> !file.toFile().isFile())
                .filter(file -> file.toString().endsWith(".xlsx"))
                //.map(p -> p.getParent().getParent())
                .distinct()
                .forEach(SteamingFolderTest::processPath)
                //.forEach(System.out::println);
                ;
    }

    private static void processPath(Path path) {
        System.out.println(path.toString());
        try {
            //InputStream is = new FileInputStream(new File("/path/to/workbook.xlsx"));
            InputStream is = new FileInputStream(path.toFile());
            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
                    .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                    .open(is);
            workbook.forEach(sheet-> {
                //logger.info(""+ Chalk.on("\nSheet Name {}\n").blue().underline(), sheet.getSheetName());
                logger.info("Sheet name : {} ", sheet.getSheetName());
                //System.out.println(sheet.getSheetName());
//                for (Row r : sheet) {
//                    for (Cell c : r) {
//                        logger.info("\t\tCell Value {} ", c.getStringCellValue());
//                        //System.out.println(c.getStringCellValue());
//                    }
//                }
            });
            //for (Sheet sheet : workbook){}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Ignore
    public void anotherWay() throws Exception {
        Files.walk(Paths.get(rootPath))
                //.filter(Files::isDirectory)
                .forEach(SteamingFolderTest::printIfArtifactVersionDirectory);
    }

    private static void printIfArtifactVersionDirectory(Path path) {
        File f = path.toAbsolutePath().toFile();
        List<String> filePaths = Arrays.asList(f.list(new MyExtFilenameFilter()));

        if (!filePaths.isEmpty()) {
            System.out.println(path.getParent());
        }
    }

    public static class MyExtFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".xlsx");
        }

    }
}
