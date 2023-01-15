import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.google.gson.Gson;
public class MVNDB {

	public static void main(String[] args) throws IOException, InterruptedException {
		 String jsonUrl = "http://universities.hipolabs.com/search?country=United+States";
	        HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(jsonUrl))
	                .build();
	        HttpResponse<String> response = client.send(request,
	                HttpResponse.BodyHandlers.ofString());
	        System.out.println(response.body());
	}

}
