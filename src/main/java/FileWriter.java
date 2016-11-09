import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.CharSink;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by JayaramanJ on 9-11-2016.
 */
public class FileWriter {

    public static void main(String[] args) {
        writeTofile();
    }

    public static void writeTofile() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom", System.getProperty("line.separator"), "Jack");
        names.add("Bob");
        names.add(System.getProperty("line.separator"));
        names.add("Rob");
        File file = new File("c:/Tools/test.txt");
        CharSink sink = Files.asCharSink(file, Charsets.UTF_8);
        try {
            sink.writeLines(names, " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
