package ar.edu.davinci.dv_ds_20261c_g29.excepcion;

public class RecursoNoEncontradoExcepcion extends RuntimeException {

    // Se lanza cuando no se encuentra un recurso en la base de datos
    public RecursoNoEncontradoExcepcion(String mensaje) {
        super(mensaje);
    }
}