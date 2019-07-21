package parse.interfaces;

import java.io.IOException;
import java.util.List;

public interface HasAPI {
    String getJsonString() throws IOException;

    List<String> getReferences(String content) throws IOException;
}
