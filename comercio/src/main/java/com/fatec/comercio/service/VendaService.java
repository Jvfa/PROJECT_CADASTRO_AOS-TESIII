package com.fatec.comercio.service;

import com.fatec.comercio.forms.ItemVendaForm;
import com.fatec.comercio.forms.VendaForm;
import com.fatec.comercio.models.*;
import com.fatec.comercio.repository.ClienteRepository;
import com.fatec.comercio.repository.ProdutoRepository;
import com.fatec.comercio.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public VendaService(VendaRepository vendaRepository, ClienteRepository clienteRepository,
            ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Venda> buscarTodasVendas() {
        return vendaRepository.findAll();
    }

    public Venda buscarVendaPorId(Integer id) {
        return vendaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Venda salvarVenda(VendaForm form) {
        Cliente cliente = clienteRepository.findById(form.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + form.getClienteId()));

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDatavenda(new Date());

        List<VendaProduto> itens = new ArrayList<>();
        for (ItemVendaForm itemForm : form.getItens()) {
            Produto produto = produtoRepository.findById(itemForm.getProdutoId())
                    .orElseThrow(
                            () -> new RuntimeException("Produto não encontrado com ID: " + itemForm.getProdutoId()));

            BigDecimal estoqueAtual = produto.getQuantidade();
            BigDecimal quantidadeComprada = itemForm.getQuantidade();

            if (estoqueAtual.compareTo(quantidadeComprada) < 0) {
                // Se o estoque for menor que a quantidade comprada, lança exceção
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNomeproduto());
            }

            produto.setQuantidade(estoqueAtual.subtract(quantidadeComprada));
            produtoRepository.save(produto); // Salva o produto com estoque abatido

            VendaProduto vendaProduto = new VendaProduto();

            // Você precisa criar e setar o ID composto
            VendaProdutoId vendaProdutoId = new VendaProdutoId();
            vendaProdutoId.setCodprodutofk(produto.getCodproduto());
            // O codvendafk será setado pelo JPA/Hibernate por causa do Cascade
            vendaProduto.setId(vendaProdutoId);

            vendaProduto.setVenda(venda);
            vendaProduto.setProduto(produto);
            vendaProduto.setQuantidadeVendida(itemForm.getQuantidade());
            vendaProduto.setValorVenda(produto.getValor());

            itens.add(vendaProduto);
        }

        venda.setItens(itens);
        return vendaRepository.save(venda);
    }
    
    @Transactional
    public void apagarVenda(Integer id) {
        // Encontra a venda ou lança uma exceção
        Venda venda = vendaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venda não encontrada com ID: " + id));

        // Itera sobre os itens da venda para reabastecer o estoque
        for (VendaProduto item : venda.getItens()) {
            Produto produto = item.getProduto();
            BigDecimal quantidadeVendida = item.getQuantidadeVendida();
            
            BigDecimal estoqueAtual = produto.getQuantidade();
            
            // Adiciona a quantidade de volta ao estoque
            BigDecimal novoEstoque = estoqueAtual.add(quantidadeVendida);
            produto.setQuantidade(novoEstoque);
            
            // Salva o produto com o estoque atualizado
            produtoRepository.save(produto);
        }

        // Deleta a venda (o CascadeType.ALL se encarrega de apagar os VendaProduto)
        vendaRepository.delete(venda);
    }
}
