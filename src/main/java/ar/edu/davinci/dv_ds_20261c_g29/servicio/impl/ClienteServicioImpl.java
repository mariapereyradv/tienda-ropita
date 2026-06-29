package ar.edu.davinci.dv_ds_20261c_g29.servicio.impl;

import ar.edu.davinci.dv_ds_20261c_g29.dominio.entidad.Cliente;
import ar.edu.davinci.dv_ds_20261c_g29.excepcion.RecursoNoEncontradoExcepcion;
import ar.edu.davinci.dv_ds_20261c_g29.repositorio.ClienteRepositorio;
import ar.edu.davinci.dv_ds_20261c_g29.servicio.interfaz.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepositorio.findAll();
    }

    @Override
    public Cliente obtenerPorId(Long id) {
        return clienteRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Cliente no encontrado con id: " + id));
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente) {
        obtenerPorId(id);
        cliente.setId(id);
        return clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminar(Long id) {
        obtenerPorId(id);
        clienteRepositorio.deleteById(id);
    }
}