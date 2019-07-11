package util;

import java.io.*;
import java.nio.file.FileSystem;
import java.util.*;

public class ListsUtil {
    private final String LISTS_FOLDER = "src" +
            File.separator + "main" + File.separator + "resources" +
            File.separator + "lists";

    private Map<String, List<String>> lists;

    private ListsUtil() {
        lists = new HashMap<>();
        File folder = new File(LISTS_FOLDER);
        for(File file : folder.listFiles()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file)));
                List<String> list = new ArrayList<>();
                String line = reader.readLine();
                while(line != null) {
                    list.add(line);
                    line = reader.readLine();
                }

                lists.put(file.getName(), list);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static ListsUtil listsUtil;

    static {
        listsUtil = new ListsUtil();
    }

    public static List<String> getList(String name) {
        return listsUtil.lists.getOrDefault(name, Collections.emptyList());
    }
}
