/**
 * Calcula el n-ésimo número de la secuencia de Lucas y muestra el resultado en la página web.
 *
 * @param {string} posicion - La posición en la secuencia de Lucas que se desea calcular.
 */
function calcularLucas() {
    let posicion = document.getElementById("numero").value;

    const peticionHttp = new XMLHttpRequest();
    peticionHttp.onload = function () {
        let respuesta = JSON.parse(this.responseText);
        let textoMostrar = `Operación: <strong>${respuesta.operation}</strong><br><br>Entrada: <strong>${respuesta.input}</strong><br><br>Resultado: <strong>${respuesta.output}</strong>`;
        document.getElementById("getrespmsg").innerHTML = textoMostrar;
        document.getElementById("getrespmsg").style.display = "block";
    }
    peticionHttp.open("GET", "/lucasseq?value=" + posicion);
    peticionHttp.send();
}
