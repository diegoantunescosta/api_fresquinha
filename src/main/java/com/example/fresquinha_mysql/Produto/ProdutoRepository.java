package com.example.fresquinha_mysql.Produto;


import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Integer>{

    void deleteById(Long id);

    
}
