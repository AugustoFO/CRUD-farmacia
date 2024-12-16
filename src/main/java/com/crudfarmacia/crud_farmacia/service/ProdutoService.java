package com.crudfarmacia.crud_farmacia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.crudfarmacia.crud_farmacia.model.Produto;
import com.crudfarmacia.crud_farmacia.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> gerarRelatorioDeFaltaDeEstoque(@Param("limiteEstoque") Integer limiteEstoque) {
		
        return produtoRepository.findByQtdEstoqueProdutoLessThan(limiteEstoque);
    }

}
