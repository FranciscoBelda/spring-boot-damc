package progresa.ejemplo_inicial_damc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.ejemplo_inicial_damc.dao.ImagenRepository;
import progresa.ejemplo_inicial_damc.entity.Imagen;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImagenService {
    @Autowired
    ImagenRepository imagenRepository;

    public List<Imagen> lista(){
        return imagenRepository.findAll();
    }
    public Optional<Imagen> getById(long id){
        return imagenRepository.findById(id);
    }
    public Optional<Imagen> getByUrl(String url){
        return imagenRepository.findByUrl(url);
    }
    public void save(Imagen imagen){
        imagenRepository.save(imagen);
    }
    public void delete(long id){
        imagenRepository.deleteById(id);
    }
    public boolean existsById(long id){
        return imagenRepository.existsById(id);
    }
    public boolean existsByUrl(String url){
        return imagenRepository.existsByUrl(url);
    }
}
