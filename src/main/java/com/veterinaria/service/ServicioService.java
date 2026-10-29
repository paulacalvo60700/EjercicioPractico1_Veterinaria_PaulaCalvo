package com.veterinaria.service;
import com.veterinaria.domain.Servicio;
import com.veterinaria.repository.ServicioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.veterinaria.repository.ServicioRepository;
import com.veterinaria.repository.TipoServicioRepository;
import com.veterinaria.repository.CitaRepository;

@Service
public class ServicioService {
    private final ServicioRepository repositorio;
    private final TipoServicioRepository tipos;
    private final CitaRepository citas;
    public ServicioService(ServicioRepository repositorio, TipoServicioRepository tipos, CitaRepository citas) { this.repositorio = repositorio; this.tipos = tipos; this.citas = citas; }
    public List<Servicio> listar() { return repositorio.findAll(); }
    public Optional<Servicio> buscar(Integer id) { return repositorio.findById(id); }
    
    public void guardar(Servicio dato) { dato.setTipoServicio(tipos.findById(dato.getTipoServicio().getId()).orElseThrow()); repositorio.save(dato); }
    public void eliminar(Integer id) { if (citas.existeServicio(id)) throw new IllegalStateException("El servicio tiene citas asociadas"); repositorio.deleteById(id); }

}
