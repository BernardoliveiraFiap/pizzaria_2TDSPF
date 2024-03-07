package br.com.fiap.pizzaria_2TDSPF.resource;


import br.com.fiap.pizzaria_2TDSPF.entity.Cliente;
import br.com.fiap.pizzaria_2TDSPF.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteRepository repo;

    @GetMapping
    public List<Cliente> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Cliente findById(@PathVariable Long id){
        return repo.findById( id ).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Cliente save (@RequestBody Cliente cliente) {
        return repo.save( cliente );
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        Cliente cliente = repo.findById( id ).get();
        repo.delete(cliente);
    }

}
