package com.example.fresquinha_mysql.Vendedor;
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
@RequestMapping(path = "/vendedores") //Especifica o nome do recurso REST
public class VendedorController {

    // Instância do repositório para usarmos os métodos CRUD
    @Autowired
    private VendedorRepository VendedorRepository;

    // GET: Retorna todas os fornecedores
    @GetMapping
    public @ResponseBody Iterable<Vendedor> consultarVendedor() {
        return VendedorRepository.findAll();
    }


    //POST: Incluir uma Vendedor
    @PostMapping
    public @ResponseBody Vendedor incluirVendedor(@RequestBody Vendedor Vendedor) {
        Vendedor novoVendedor = new Vendedor();
        novoVendedor.setName(Vendedor.getName());
        novoVendedor.setAdress(Vendedor.getAdress());
        return VendedorRepository.save(novoVendedor);
    } 

    //Get - obter uma categoria por ID
    @GetMapping(value = "{id}")
    public @ResponseBody Optional<Vendedor> obterProdOptional (@PathVariable Integer id) {
        return VendedorRepository.findById(id);
    }

    //Delete: Excluir uma categoria pelo id

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> excluirVendedor(@PathVariable Integer id) {
        Optional<Vendedor> Vendedor = VendedorRepository.findById(id);
        if (Vendedor.isPresent()) {
            VendedorRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // PUT: Alteração dos dados de uma categoria
    
    @PutMapping(value = "{id}")
    public ResponseEntity<Object> alterarVendedor(@PathVariable Integer id,
        @RequestBody Vendedor Vendedor) {
            Optional<Vendedor> vend = VendedorRepository.findById(id);
            if (vend.isPresent()) {
                Vendedor.setId(id);
                VendedorRepository.save(Vendedor);
            }else {
                VendedorRepository.save(Vendedor);
            }
            return ResponseEntity.status(201).build();
        }
        

}