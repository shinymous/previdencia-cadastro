package com.previdencia.cadastro.service;

import com.previdencia.cadastro.dto.AcaoDTO;
import com.previdencia.cadastro.dto.ContaAcaoDTO;
import com.previdencia.cadastro.dto.ContaDTO;
import com.previdencia.cadastro.dto.ContaInfoDTO;
import com.previdencia.cadastro.exception.EntidadeNaoEcontradaException;
import com.previdencia.cadastro.exception.SaldoInsuficienteException;
import com.previdencia.cadastro.model.entity.Conta;
import com.previdencia.cadastro.model.entity.ContaAcao;
import com.previdencia.cadastro.model.entity.Deposito;
import com.previdencia.cadastro.model.entity.Saque;
import com.previdencia.cadastro.model.repository.ContaRepository;
import com.previdencia.cadastro.model.repository.DepositoRepository;
import com.previdencia.cadastro.model.repository.SaqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private DepositoRepository depositoRepository;
    @Autowired
    private SaqueRepository saqueRepository;

    public ContaAcaoDTO depositar(Long id, BigDecimal valor){
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEcontradaException("conta", id));
        Deposito deposito = new Deposito();
        deposito.setContaId(conta.getId());
        deposito.setValor(valor);
        deposito.setData(LocalDateTime.now());
        return new ContaAcaoDTO(depositoRepository.save(deposito), this.obterSaldoPorConta(deposito.getContaId()));
    }


    public ContaAcaoDTO sacar(Long id, BigDecimal valor){
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEcontradaException("conta", id));
        Saque saque = new Saque();
        saque.setValor(valor);
        saque.setContaId(conta.getId());
        saque.setData(LocalDateTime.now());
        this.validaSaque(saque);
        return new ContaAcaoDTO(saqueRepository.save(saque), this.obterSaldoPorConta(saque.getContaId()));
    }

    private void validaSaque(Saque saque){
        BigDecimal saldo = this.obterSaldoPorConta(saque.getContaId());
        if(saldo.compareTo(saque.getValor()) < 0){
            throw new SaldoInsuficienteException(saque.getValor());
        }
    }

    private BigDecimal obterSaldoPorConta(Long contaId){
        BigDecimal totalDeposito = depositoRepository.findSomaByConta(contaId).orElse(new BigDecimal("0.00"));
        BigDecimal totalSaque = saqueRepository.findSomaByConta(contaId).orElse(new BigDecimal("0.00"));
        return totalDeposito.subtract(totalSaque);
    }

    public ContaDTO informacoesDasContasPorUsuario(Long id){
        List<Conta> contas = contaRepository.findAllContaByUsuarioId(id);
        if(contas.isEmpty()){
            throw new EntidadeNaoEcontradaException(String.format("Conta não encontrada para usuário %s ", id));
        }

        //HISTÓRICO DE CONTA COMUM
        List<AcaoDTO> historicoComum = new ArrayList<>();


        //OBTEM SAQUES E DEPOSITOS HISTORICOS DE CONTA COMUM
        List<Saque> saquesHistoricoComum = saqueRepository.findAllByUsuarioIdAndContaComum(id);
        historicoComum.addAll(mapSaqueToAcaoDTO(saquesHistoricoComum));

        List<Deposito> depositosHistoricoComum = depositoRepository.findAllByUsuarioIdAndContaComum(id);
        historicoComum.addAll(mapDepositoToAcaoDTO(depositosHistoricoComum));

        ContaInfoDTO contaComum = new ContaInfoDTO();
        contaComum.setHistorico(historicoComum);

        BigDecimal totalSaqueComum = saquesHistoricoComum.stream()
                .map(ContaAcao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalDepositoComum = depositosHistoricoComum.stream()
                .map(ContaAcao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        contaComum.setSaldo(totalDepositoComum.subtract(totalSaqueComum));

        //HISTÓRICO DE CONTA EVENTUAL
        List<AcaoDTO> historicoEventual = new ArrayList<>();

        //OBTEM SAQUES E DEPOSITOS HISTORICOS DE CONTA EVENTUAL
        List<Saque> saquesHistoricoEventual = saqueRepository.findAllByUsuarioIdAndContaEventual(id);
        historicoEventual.addAll(mapSaqueToAcaoDTO(saquesHistoricoEventual));

        List<Deposito> depositosHistoricoEventual = depositoRepository.findAllByUsuarioIdAndContaEventual(id);
        historicoEventual.addAll(mapDepositoToAcaoDTO(depositosHistoricoEventual));

        ContaInfoDTO contaEventual = new ContaInfoDTO();
        contaEventual.setHistorico(historicoEventual);

        BigDecimal totalSaqueEventual = saquesHistoricoEventual.stream()
                .map(ContaAcao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalDepositoEventual = depositosHistoricoEventual.stream()
                .map(ContaAcao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        contaEventual.setSaldo(totalDepositoEventual.subtract(totalSaqueEventual));

        return new ContaDTO(contaComum, contaEventual);

    }

    private List<AcaoDTO> mapSaqueToAcaoDTO(List<Saque> saques){
        return saques.stream()
                .map(AcaoDTO::new)
                .collect(Collectors.toList());
    }

    private List<AcaoDTO> mapDepositoToAcaoDTO(List<Deposito> saques){
        return saques.stream()
                .map(AcaoDTO::new)
                .collect(Collectors.toList());
    }
}
