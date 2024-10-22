package progresa.ejemplo_inicial_damc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import progresa.ejemplo_inicial_damc.entity.Direccion;

import java.util.Optional;

@RepositoryRestResource
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    Optional<Direccion> findByCalle(String calle);

    boolean existsByCalle(String calle);
}
