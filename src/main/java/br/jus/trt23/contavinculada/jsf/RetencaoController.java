/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Colaborador;
import br.jus.trt23.contavinculada.entities.Faturamento;
import br.jus.trt23.contavinculada.entities.FaturamentoItem;
import br.jus.trt23.contavinculada.entities.FaturamentoItemEvento;
import br.jus.trt23.contavinculada.entities.RATItem;
import br.jus.trt23.contavinculada.entities.Retencao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author j129-9
 */
@Named
@Dependent
public class RetencaoController extends AbstractController<Retencao> {

    @Inject
    FaturamentoController faturamentoController;
    @Inject
    FaturamentoItemEventoController faturamentoItemEventoController;
    @Inject
    ColaboradorController colaboradorController;

    public RetencaoController() {
        super(Retencao.class);
    }

    public String processaFaturamento(Faturamento faturamento) throws Exception {
        if (null != faturamento) {
            FaturamentoItemEvento eventoPadrao
                    = faturamentoItemEventoController.getFaturamentoItemEventoPadrao();
            Set<Colaborador> colaboradoresNoFaturamento = new HashSet<>();
            Set<FaturamentoItem> faturamentoItens = new HashSet<>();
            List<FaturamentoItem> faturamentoItensNaRetencao = new ArrayList<>();
            Integer diasTitularidade;
            Integer diasSubstituicao;
            Integer diasTrabalhados;
            Stream<FaturamentoItem> sItens;
            Retencao retencao;

            sItens = faturamento.getItens().stream();

            //faz o union dos colaboradores que atuaram como titulares e
            //substitutos
            colaboradoresNoFaturamento.addAll(sItens.map(FaturamentoItem::getTitular).collect(Collectors.toSet()));

            sItens = faturamento.getItens().stream();

            colaboradoresNoFaturamento.addAll(sItens.map(FaturamentoItem::getSubstituto).collect(Collectors.toSet()));

            for (Colaborador col : colaboradoresNoFaturamento) {
                diasTitularidade = diasSubstituicao = diasTrabalhados = 0;

                //acumula a quantidade de dias trabalhados em titularidade
                faturamentoItens.clear();

                sItens = faturamento.getItens().stream();

                faturamentoItens.addAll(sItens.filter(p -> p.getTitular().equals(col)).collect(Collectors.toSet()));

                for (FaturamentoItem fi : faturamentoItens) {
                    if (fi.getEvento().getConsideraTrabalhadoParaTitular()) {
                        diasTitularidade++;
                        faturamentoItensNaRetencao.add(fi);
                    }
                }

                //acumula a quantidade de dias trabalhados em substituicao                
                faturamentoItens.clear();

                sItens = faturamento.getItens().stream();

                faturamentoItens.addAll(sItens.filter(p -> p.getSubstituto().equals(col)).collect(Collectors.toSet()));
                for (FaturamentoItem fi : faturamentoItens) {
                    if (fi.getEvento().getConsideraTrabalhadoParaSubstituto()) {
                        diasSubstituicao++;
                        faturamentoItensNaRetencao.add(fi);
                    }
                }

                diasTrabalhados = diasSubstituicao + diasTitularidade;

                if (diasTrabalhados > 0) {
                    for (RATItem item : faturamento.getContrato().getRat().getItens()) {
                        retencao = new Retencao();
                        retencao.setRatItem(item);
                        retencao.setSalario(
                                colaboradorController.findSalarioPlenamenteVigenteEntre(
                                        faturamento.getReferenciaInicio(),
                                        faturamento.getReferenciaFim(), col).getValor());
                        retencao.setDiasSubstituicao(diasSubstituicao);
                        retencao.setDiasTitularidade(diasTitularidade);
                        retencao.setFaturamentoItens(faturamentoItensNaRetencao);
                        create(retencao);
                    }
                }
            }
        }
        throw new Exception("Faturamento nulo.");
    }

    @Override
    protected String getMessagePrefix() {
        return "Retencao";
    }
}