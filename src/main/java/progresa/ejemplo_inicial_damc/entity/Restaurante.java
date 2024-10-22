package progresa.ejemplo_inicial_damc.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "restaurante")
@Getter
@Setter
@NoArgsConstructor
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurante_id") //Clave ajena
    @JsonManagedReference
    // @PrimaryKeyJoinColumn // Clave primaria
    private Direccion direccion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurante")
    @JsonManagedReference
    private Set<Imagen> listaImagenes;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
}
