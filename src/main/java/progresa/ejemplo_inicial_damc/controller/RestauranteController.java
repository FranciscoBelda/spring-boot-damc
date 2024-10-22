package progresa.ejemplo_inicial_damc.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progresa.ejemplo_inicial_damc.dto.ImagenDto;
import progresa.ejemplo_inicial_damc.dto.Mensaje;
import progresa.ejemplo_inicial_damc.dto.RestauranteDto;
import progresa.ejemplo_inicial_damc.entity.Categoria;
import progresa.ejemplo_inicial_damc.entity.Direccion;
import progresa.ejemplo_inicial_damc.entity.Imagen;
import progresa.ejemplo_inicial_damc.entity.Restaurante;
import progresa.ejemplo_inicial_damc.service.CategoriaService;
import progresa.ejemplo_inicial_damc.service.RestauranteService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/restaurante")
@CrossOrigin(origins = "*")
public class RestauranteController {

    @Autowired
    RestauranteService restauranteService;
    CategoriaService categoriaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Restaurante>> List(){
        List<Restaurante> list = restauranteService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(
            @PathVariable("id") long id){
        if(!restauranteService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("no existe"),
                    HttpStatus.NOT_FOUND);
        }
        if(restauranteService.getOne(id).isPresent()){
            Restaurante restaurante =
                    restauranteService.getOne(id).get();
            return new ResponseEntity<>(restaurante, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensaje("no existe"),
                HttpStatus.NOT_FOUND);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<?> getByNombre(
            @PathVariable("nombre") String nombre){
        if(!restauranteService.existsByNombre(nombre)){
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        if(restauranteService.getByNombre(nombre).isPresent()){
            Restaurante restaurante = restauranteService.getByNombre(nombre).get();
            return new ResponseEntity<>(restaurante, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody RestauranteDto restauranteDto){
        if(StringUtils.isBlank(restauranteDto.getNombre()))
            return new ResponseEntity<>(
                    new Mensaje("El nombre es obligatorio"),
                    HttpStatus.BAD_REQUEST);

        if(restauranteService.existsByNombre(restauranteDto.getNombre()))
            return new ResponseEntity<>(
                    new Mensaje("El nombre ya existe"),
                    HttpStatus.BAD_REQUEST);

        Restaurante restaurante = new Restaurante();
        restaurante.setNombre(restauranteDto.getNombre());
        //restaurante.setCalle(restauranteDto.getCalle());

        // Imágenes
        Set<Imagen> imagenes = new HashSet<>();
        for (ImagenDto imagen:restauranteDto.getImagenes()) {
            if(StringUtils.isBlank(imagen.getUrl()))
                return new ResponseEntity<>(
                        new Mensaje("La url de la imagen es obligatoria."),
                        HttpStatus.BAD_REQUEST);
            Imagen newImagen = new Imagen(imagen.getUrl());
            newImagen.setRestaurante(restaurante);
            imagenes.add(newImagen);
        }
        restaurante.setListaImagenes(imagenes);


        // AÑADIR DIRECCIÓN
        Direccion direccion = new Direccion();
        // Controlar que la calle no sea vacía
        if (StringUtils.isBlank(
                restauranteDto.getDireccion().getCalle()))
            return new ResponseEntity<>(
                    new Mensaje("La calle no puede ser vacía."),
                    HttpStatus.BAD_REQUEST);
        direccion.setCalle(restauranteDto.getDireccion().getCalle());
        direccion.setNumero(restauranteDto.getDireccion().getNumero());

        direccion.setRestaurante(restaurante);
        restaurante.setDireccion(direccion);

        // Categoría
        Categoria categoria = null;
        if (categoriaService.getByCategoria(
                restauranteDto.getCategoria()).isPresent()){
            categoria = categoriaService.getByCategoria(restauranteDto
                    .getCategoria()).get();
        }else {
            if(StringUtils.isBlank(restauranteDto.getCategoria())){
                return new ResponseEntity<>(
                        new Mensaje("El nombre de la categoría es obligatorio."),
                        HttpStatus.BAD_REQUEST);
            }
            categoria = new Categoria(restauranteDto.getCategoria());
        }

        categoria.getListaRestaurantes().add(restaurante);
        restaurante.setCategoria(categoria);

        restauranteService.save(restaurante);
        return new ResponseEntity<>(new Mensaje("Restaurante creado"),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        if (!restauranteService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"),
                    HttpStatus.NOT_FOUND);
        restauranteService.delete(id);
        return new ResponseEntity<>(
                new Mensaje("Restaurante eliminado"), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") long id,
            @RequestBody RestauranteDto restauranteDto){
        if (!restauranteService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"),
                    HttpStatus.NOT_FOUND);
        if (restauranteService.existsByNombre(
                restauranteDto.getNombre())&&
        restauranteService.getByNombre(
                restauranteDto.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("El nombre ya existe"),
                    HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(restauranteDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"),
                    HttpStatus.BAD_REQUEST);

        Restaurante restaurante =
                restauranteService.getOne(id).get();
        restaurante.setNombre(restauranteDto.getNombre());
        //restaurante.setCalle(restauranteDto.getCalle());

        // Imágenes
        Set<Imagen> imagenes = new HashSet<>();
        for (ImagenDto imagen:restauranteDto.getImagenes()) {
            if(StringUtils.isBlank(imagen.getUrl()))
                return new ResponseEntity<>(
                        new Mensaje("La url de la imagen es obligatoria."),
                        HttpStatus.BAD_REQUEST);

            Imagen newImagen = new Imagen(imagen.getUrl());
            newImagen.setRestaurante(restaurante);
            imagenes.add(newImagen);
        }

        restaurante.setListaImagenes(imagenes);


        // DIRECCIÓN
        // Controlar que la calle no sea vacía
        if (StringUtils.isBlank(
                restauranteDto.getDireccion().getCalle()))
            return new ResponseEntity<>(
                    new Mensaje("La calle no puede ser vacía."),
                    HttpStatus.BAD_REQUEST);
        restaurante.getDireccion().setCalle(
                restauranteDto.getDireccion().getCalle());
        restaurante.getDireccion().setNumero(
                restauranteDto.getDireccion().getNumero());

        // Categoría
        Categoria categoria = null;
        if (categoriaService.getByCategoria(
                restauranteDto.getCategoria()).isPresent()){
            categoria = categoriaService.getByCategoria(restauranteDto
                    .getCategoria()).get();
        }else {
            if(StringUtils.isBlank(restauranteDto.getCategoria())){
                return new ResponseEntity<>(
                        new Mensaje("El nombre de la categoría es obligatorio."),
                        HttpStatus.BAD_REQUEST);
            }
            categoria = new Categoria(restauranteDto.getCategoria());
        }

        categoria.getListaRestaurantes().add(restaurante);
        restaurante.setCategoria(categoria);

        restauranteService.save(restaurante);
        return new ResponseEntity<>(new Mensaje("Restaurante actualizado"),
                HttpStatus.OK);
    }
}

