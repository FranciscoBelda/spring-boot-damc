package progresa.ejemplo_inicial_damc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.ejemplo_inicial_damc.dao.RestauranteRepository;
import progresa.ejemplo_inicial_damc.entity.Restaurante;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RestauranteService {
    @Autowired
    RestauranteRepository restauranteRepository;

    public List<Restaurante> list(){
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> getOne(long id){
        return restauranteRepository.findById(id);
    }
    public Optional<Restaurante> getByNombre(String nombre){
        return restauranteRepository.findByNombre(nombre);
    }

    public void save(Restaurante restaurante){
        restauranteRepository.save(restaurante);
    }
    public void delete(long id){
        restauranteRepository.deleteById(id);
    }

    public boolean existsById(long id){
        return restauranteRepository.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return restauranteRepository.existsByNombre(nombre);
    }





}
