package com.veterinaria.service;
import com.veterinaria.domain.TipoServicio;
import com.veterinaria.repository.TipoServicioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.veterinaria.repository.ServicioRepository;
import com.veterinaria.repository.TipoServicioRepository;

@Service
public class TipoServicioService {
    private final TipoServicioRepository repositorio;
    private final ServicioRepository servicios;
    public TipoServicioService(TipoServicioRepository repositorio, ServicioRepository servicios) { this.repositorio = repositorio; this.servicios = servicios; }
    public List<TipoServicio> listar() { var tipos = repositorio.findAll();
        for (var tipo : tipos) {
            tipo.setServicios(servicios.findAll().stream().filter(s -> s.getTipoServicio().getId().equals(tipo.getId())).toList());
        }
        return tipos; }
    public Optional<TipoServicio> buscar(Integer id) { return repositorio.findById(id); }
    
    public void guardar(TipoServicio dato) { repositorio.save(dato); }
    public void eliminar(Integer id) { if (servicios.existeTipo(id)) throw new IllegalStateException("El tipo tiene servicios asociados"); repositorio.deleteById(id); }

}
