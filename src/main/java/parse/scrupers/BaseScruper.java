package parse.scrupers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public abstract  class BaseScruper {

    protected String getJsonString(String url) throws IOException {
        String urlString = url;
        URL urlObject = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == 404) {
            throw new IllegalArgumentException();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    abstract List<String> getReferences(String content) throws IOException;

    public abstract List<String> getData() throws IOException;

}
