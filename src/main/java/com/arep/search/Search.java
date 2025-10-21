package com.arep.search;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 * The Search class provides static methods for calculating Lucas sequence numbers.
 * It returns the nth number in the Lucas sequence based on the provided position.
 */
public class Search {
    /**
     * Calculates the Lucas number at the given position using iterative approach.
     *
     * @param posicionTexto A string representing the position in the Lucas sequence.
     * @return A JSON string containing the operation name, position, and the calculated Lucas number.
     */
    public static String calcularLucasIterativo(String posicionTexto) {
        int posicion = Integer.parseInt(posicionTexto);
        
        if (posicion == 0) {
            return crearRespuestaJson("lucasIterativo", posicionTexto, 2);
        }
        if (posicion == 1) {
            return crearRespuestaJson("lucasIterativo", posicionTexto, 1);
        }
        
        int primerNumero = 2;
        int segundoNumero = 1;
        int resultado = 0;
        
        for (int i = 2; i <= posicion; i++) {
            resultado = primerNumero + segundoNumero;
            primerNumero = segundoNumero;
            segundoNumero = resultado;
        }

        return crearRespuestaJson("lucasIterativo", posicionTexto, resultado);
    }

    /**
     * Calculates the Lucas number at the given position using recursive approach.
     *
     * @param posicionTexto A string representing the position in the Lucas sequence.
     * @return A JSON string containing the operation name, position, and the calculated Lucas number.
     */
    public static String calcularLucasRecursivo(String posicionTexto) {
        int posicion = Integer.parseInt(posicionTexto);
        
        int valorFinal = obtenerNumeroLucas(posicion);

        return crearRespuestaJson("lucasRecursivo", posicionTexto, valorFinal);
    }

    /**
     * Recursive helper method for calculating Lucas numbers.
     *
     * @param indice The position in the Lucas sequence.
     * @return The Lucas number at the given position.
     */
    private static int obtenerNumeroLucas(int indice) {
        if (indice == 0) {
            return 2;
        }
        if (indice == 1) {
            return 1;
        }
        
        return obtenerNumeroLucas(indice - 1) + obtenerNumeroLucas(indice - 2);
    }

    /**
     * Creates a JSON string containing the operation details and result.
     *
     * @param tipoOperacion The operation name.
     * @param entrada The input position as string.
     * @param valorResultado The calculated Lucas number.
     * @return A JSON string containing the operation details and result.
     */
    public static String crearRespuestaJson(String tipoOperacion, String entrada, int valorResultado) {
        JSONObject objetoJson = new JSONObject();
        objetoJson.put("operation", tipoOperacion);
        objetoJson.put("input", entrada);
        objetoJson.put("output", valorResultado);

        return objetoJson.toString();
    }

}