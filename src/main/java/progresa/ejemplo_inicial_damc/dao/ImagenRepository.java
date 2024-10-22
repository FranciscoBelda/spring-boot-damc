package progresa.ejemplo_inicial_damc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import progresa.ejemplo_inicial_damc.entity.Imagen;

import java.util.Optional;

@RepositoryRestResource
public interface ImagenRepository extends JpaRepository<Imagen, Long> {
    Optional<Imagen> findByUrl(String url);
    boolean existsByUrl(String url);
}
