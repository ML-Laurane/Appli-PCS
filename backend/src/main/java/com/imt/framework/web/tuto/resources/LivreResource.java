package com.imt.framework.web.tuto.resources;

import com.imt.framework.web.tuto.entities.Livre;
import com.imt.framework.web.tuto.repositories.LivreRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class LivreResource {

    private final LivreRepository livreRepository;

    public LivreResource(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    // GET /books?maxPrice=...
    @GetMapping
    public Iterable<Livre> getBooks(
            @RequestParam(value = "maxPrice", required = false) Double maxPrice) {

        if (maxPrice != null) {
            return livreRepository.getBooksWithMaxPrice(maxPrice);
        }
        return livreRepository.findAll();
    }

    // POST /books
    @PostMapping
    public void createBook(@NotNull @RequestBody Livre livre) {
        livreRepository.save(livre);
    }

    // PATCH /books/{id}
    @PatchMapping("/{id}")
    public void updateBook(
            @PathVariable Integer id,
            @NotNull @RequestBody Livre livre) throws Exception {

        Optional<Livre> l = livreRepository.findById(id);

        if (l.isEmpty()) {
            throw new Exception("Livre inconnu");
        }

        Livre livreToUpdate = l.get();
        livreToUpdate.setAuteur(livre.getAuteur());
        livreToUpdate.setPrice(livre.getPrice());
        livreToUpdate.setTitre(livre.getTitre());

        livreRepository.save(livreToUpdate);
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        livreRepository.deleteById(id);
    }
}
