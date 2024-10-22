package progresa.ejemplo_inicial_damc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.ejemplo_inicial_damc.dao.CategoriaRepository;
import progresa.ejemplo_inicial_damc.entity.Categoria;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> list(){
        return categoriaRepository.findAll();
    }
    public Optional<Categoria> getById(long id){
        return categoriaRepository.findById(id);
    }
    public Optional<Categoria> getByCategoria(String categoria){
        return categoriaRepository.findByCategoria(categoria);
    }
    public void save(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    public void delete(long id){
        categoriaRepository.deleteById(id);
    }
    public boolean existsByCategoria(String categoria){
        return categoriaRepository.existsByCategoria(categoria);
    }
    public boolean existsById(long id){
        return categoriaRepository.existsById(id);
    }
}
