package com.veterinaria.service;
import com.veterinaria.domain.Cita;
import com.veterinaria.repository.CitaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CitaService {
    private final CitaRepository repositorio;
    public CitaService(CitaRepository repositorio) { this.repositorio = repositorio; }
    public List<Cita> listar() { return repositorio.findAll(); }
    public Optional<Cita> buscar(Integer id) { return repositorio.findById(id); }
    
}
