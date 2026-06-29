package ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Cliente;

import java.util.List;

public interface ClienteServicio {

    List<Cliente> obtenerTodos();

    Cliente obtenerPorId(Long id);

    Cliente guardar(Cliente cliente);

    Cliente actualizar(Long id, Cliente cliente);

    void eliminar(Long id);
}