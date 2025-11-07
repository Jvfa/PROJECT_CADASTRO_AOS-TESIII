package com.fatec.comercio.service;

import com.fatec.comercio.models.Produto;
import com.fatec.comercio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Metodo para buscar todos os produtos
    public List<Produto> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }

    // Metodo para buscar um produto pelo ID
    public Produto buscarProdutoPorId(Integer id) {
        // O findById retorna um Optional, .orElse(null) retorna o produto ou nulo se não encontrar.
        return produtoRepository.findById(id).orElse(null);
    }

    // Metodo para salvar um novo produto
    public Produto salvarProduto(Produto produto) {
        // Validações e regras de negócio podem ser adicionadas aqui
        return produtoRepository.save(produto);
    }

    // Metodo para editar um produto existente
    public Produto editarProduto(Integer id, Produto produtoAtualizado) {
        // Verifica se o produto existe antes de tentar atualizar
        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produtoAtualizado.setCodproduto(id);
                    return produtoRepository.save(produtoAtualizado);
                }).orElse(null); // Retorna nulo se o produto não for encontrado
    }

    // Metodo para apagar um produto pelo ID
    public void apagarProduto(Integer id) {
        produtoRepository.deleteById(id);
    }
}
