package progresa.ejemplo_inicial_damc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String categoria;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    @JsonIgnore
    private Set<Restaurante> listaRestaurantes;

    public Categoria(String categoria) {
        this.categoria = categoria;
        listaRestaurantes = new HashSet<>();
    }
}
