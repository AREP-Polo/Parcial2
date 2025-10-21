package edu.arep.loadBalancer;

import static edu.arep.search.Search.binarySearch;
import static edu.arep.search.Search.linearSearch;
import static spark.Spark.*;
import static spark.Spark.get;

public class ProxyService {

    public static void main( String[] args )
    {
        port(obtenerNumeroPuerto());
        staticFiles.location("/public");

        get("/proxy", (solicitud, respuesta) -> {
            String tipoOperacion = solicitud.queryParams("operation");
            String listaElementos = solicitud.queryParams("list");
            String valorBuscado = solicitud.queryParams("value");

            return Invoker.invoke(tipoOperacion, listaElementos, valorBuscado);
        });

    }

    private static int obtenerNumeroPuerto() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}