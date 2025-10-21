package com.arep.loadBalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Invoker {
    private static final String NAVEGADOR = "Mozilla/5.0";

    static final List<String> SERVIDORES_DISPONIBLES = Arrays.asList(
                                                                "http://ec2-13-218-231-248.compute-1.amazonaws.com:4567",
                                                                "http://ec2-52-23-235-185.compute-1.amazonaws.com:4567"
                                                    );
    static int indiceActual = 0;



    /**
     * Realiza una petición HTTP GET a uno de los servidores disponibles usando estrategia de turnos rotatorios.
     *
     * @param operacion La operación matemática a realizar.
     * @param lista     Los números sobre los cuales se aplicará la operación.
     * @param numero    El número adicional para usar en la operación.
     * @return La respuesta del servidor como texto.
     * @throws IOException Si hay un error al conectarse o leer la respuesta.
     */
    public static String invoke(String operacion, String lista, String numero) throws IOException {
        URL direccion = obtenerDireccionServidor(operacion, lista, numero);

        HttpURLConnection conexion = (HttpURLConnection) direccion.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("User-Agent", NAVEGADOR);

        int codigoRespuesta = conexion.getResponseCode();
        System.out.println("Código de respuesta GET :: " + codigoRespuesta);
        StringBuffer resultado = new StringBuffer();

        if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
            BufferedReader lector = new BufferedReader(new InputStreamReader(
                    conexion.getInputStream()));
            String lineaLeida;
            while ((lineaLeida = lector.readLine()) != null) {
                resultado.append(lineaLeida);
            }
            lector.close();
            System.out.println(resultado.toString());
        } else {
            System.out.println("La petición GET falló");
        }
        System.out.println("Petición GET completada");
        return resultado.toString();
    }

    /**
     * Retorna la dirección del servidor siguiente según la estrategia de turnos rotatorios.
     *
     * @param operacion La operación matemática a realizar.
     * @param lista     Los números sobre los cuales se aplicará la operación.
     * @param numero    El número adicional para usar en la operación.
     * @return Un objeto URL con la dirección completa para la petición.
     * @throws MalformedURLException Si la dirección construida no es válida.
     */
    public static URL obtenerDireccionServidor(String operacion, String lista, String numero) throws MalformedURLException {
        String direccionBase = SERVIDORES_DISPONIBLES.get(indiceActual);
        indiceActual = (indiceActual + 1) % SERVIDORES_DISPONIBLES.size();
        return new URL(direccionBase + "/" + operacion + "?list=" + lista + "&value=" + numero);
    }


}