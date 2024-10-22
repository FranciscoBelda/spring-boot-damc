package progresa.ejemplo_inicial_damc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import progresa.ejemplo_inicial_damc.entity.Categoria;

import java.util.Optional;

@RepositoryRestResource
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByCategoria(String categoria);

    boolean existsByCategoria(String categoria);
}
