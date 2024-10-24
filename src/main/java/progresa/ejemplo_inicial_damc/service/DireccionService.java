package progresa.ejemplo_inicial_damc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.ejemplo_inicial_damc.dao.DireccionRepository;
import progresa.ejemplo_inicial_damc.entity.Direccion;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    public Optional<Direccion> getByCalle(String calle){
        return direccionRepository.findByCalle(calle);
    }

    public boolean existsByCalle(String calle){
        return direccionRepository.existsByCalle(calle);
    }

    public List<Direccion> list(){
        return direccionRepository.findAll();
    }
    public Optional<Direccion> getOne(long id){
        return direccionRepository.findById(id);
    }
    public void save(Direccion direccion){
        direccionRepository.save(direccion);
    }
    public void delete(long id){
        direccionRepository.deleteById(id);
    }


}
