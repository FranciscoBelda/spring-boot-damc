package progresa.ejemplo_inicial_damc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDto {
    @NotBlank
    private String nombre;

    private DireccionDto direccion;

    private Set<ImagenDto> imagenes;

    private String categoria;
}
