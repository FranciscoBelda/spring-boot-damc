package progresa.ejemplo_inicial_damc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="direccion")
@Getter
@Setter
@NoArgsConstructor
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonIgnore
    private Long id;

    @Column
    private String calle;

    @Column
    private String numero;

    @OneToOne
    @JsonBackReference
    private Restaurante restaurante;

    public Direccion(String calle, String numero) {
        this.calle = calle;
        this.numero = numero;
    }
}
