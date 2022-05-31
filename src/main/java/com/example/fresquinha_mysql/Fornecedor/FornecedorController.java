package com.example.fresquinha_mysql.Fornecedor;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





@CrossOrigin(maxAge = 3600)
@Controller
// Indetifica que a classe é um controlador REST. 
@RequestMapping(path = "/fornecedores") //Especifica o nome do recurso REST
public class FornecedorController {

    // Instância do repositório para usarmos os métodos CRUD
    @Autowired
    private FornecedorRepository categoriaRepository;

    // GET: Retorna todas os fornecedores
    @GetMapping
    public @ResponseBody Iterable<Fornecedor> consultarCategorias() {
        return categoriaRepository.findAll();
    }


    //POST: Incluir uma categoria
    @PostMapping
    public @ResponseBody Fornecedor incluirCategoria(@RequestBody Fornecedor categoria) {
        Fornecedor novaCategoria = new Fornecedor();
        novaCategoria.setName(categoria.getName());       
        novaCategoria.setPhone(categoria.getPhone());
        return categoriaRepository.save(novaCategoria);
    } 

    //Get - obter uma categoria por ID
    @GetMapping(value = "{id}")
    public @ResponseBody Optional<Fornecedor> obterCateOptional (@PathVariable Integer id) {
        return categoriaRepository.findById(id);
    }

    //Delete: Excluir uma categoria pelo id

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> excluirCategoria (@PathVariable Integer id) {
        Optional<Fornecedor> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // PUT: Alteração dos dados de uma categoria
    
    @PutMapping(value = "{id}")
    public ResponseEntity<Object> alterarCategoria(@PathVariable Integer id,
        @RequestBody Fornecedor categoria) {
            Optional<Fornecedor> cat = categoriaRepository.findById(id);
            if (cat.isPresent()) {
                categoria.setId(id);
                categoriaRepository.save(categoria);
            }else {
                categoriaRepository.save(categoria);
            }
            return ResponseEntity.status(201).build();
        }
        

}