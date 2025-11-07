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

    public VendaService(VendaRepository vendaRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
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
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + itemForm.getProdutoId()));

            VendaProduto vendaProduto = new VendaProduto();

            vendaProduto.setVenda(venda);
            vendaProduto.setProduto(produto);
            vendaProduto.setQuantidadeVendida(itemForm.getQuantidade());
            vendaProduto.setValorVenda(produto.getValor());

            itens.add(vendaProduto);
        }

        venda.setItens(itens);
        return vendaRepository.save(venda);
    }
}
