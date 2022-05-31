package com.example.fresquinha_mysql.Produto;
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
@RequestMapping(path = "/produtos") //Especifica o nome do recurso REST
public class ProdutoController {

    // Instância do repositório para usarmos os métodos CRUD
    @Autowired
    private ProdutoRepository produtoRepository;

    // GET: Retorna todas os fornecedores
    @GetMapping
    public @ResponseBody Iterable<Produto> consultarProduto() {
        return produtoRepository.findAll();
    }


    //POST: Incluir uma Produto
    @PostMapping
    public @ResponseBody Produto incluirProduto(@RequestBody Produto produto) {
        Produto novoProduto = new Produto();
        novoProduto.setName(produto.getName());
        novoProduto.setTipo(produto.getTipo());
        return produtoRepository.save(novoProduto);
    } 

    //Get - obter uma categoria por ID
    @GetMapping(value = "{id}")
    public @ResponseBody Optional<Produto> obterProdOptional (@PathVariable Integer id) {
        return produtoRepository.findById(id);
    }

    //Delete: Excluir uma categoria pelo id

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> excluirProduto(@PathVariable Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // PUT: Alteração dos dados de uma categoria
    
    @PutMapping(value = "{id}")
    public ResponseEntity<Object> alterarProduto(@PathVariable Integer id,
        @RequestBody Produto produto) {
            Optional<Produto> prod = produtoRepository.findById(id);
            if (prod.isPresent()) {
                produto.setId(id);
                produtoRepository.save(produto);
            }else {
                produtoRepository.save(produto);
            }
            return ResponseEntity.status(201).build();
        }
        

}