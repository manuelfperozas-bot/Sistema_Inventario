package back_end;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Precio_Dolar {

    private String urlApi = "https://ve.dolarapi.com/v1/dolares/oficial";
    private double Precio;

    public Precio_Dolar() {

        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlApi))
                    .header("Accept", "application/json")
                    .GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {

                JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

                this.Precio = json.get("promedio").getAsDouble();

            }else {

                System.out.println("Error al consultar la API. Código de estado: " + response.statusCode());

            }

        }catch (Exception e){

            System.out.println("Ocurrió un error al conectar con el servidor: " + e.getMessage());
            e.printStackTrace();

        }

    }

    public double getPrecio() {
        return Precio;
    }

}
