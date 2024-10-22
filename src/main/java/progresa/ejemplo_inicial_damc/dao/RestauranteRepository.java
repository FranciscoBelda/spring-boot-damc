package progresa.ejemplo_inicial_damc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import progresa.ejemplo_inicial_damc.entity.Restaurante;

import java.util.Optional;

@RepositoryRestResource
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    Optional<Restaurante> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

}
