package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Alocacao;
import br.jus.trt23.contavinculada.entities.Colaborador;
import br.jus.trt23.contavinculada.entities.ContaVinculada;
import br.jus.trt23.contavinculada.entities.Contrato;
import br.jus.trt23.contavinculada.entities.EncargoAliquota;
import br.jus.trt23.contavinculada.entities.Faturamento;
import br.jus.trt23.contavinculada.entities.FaturamentoItem;
import br.jus.trt23.contavinculada.entities.FaturamentoItemEvento;
import br.jus.trt23.contavinculada.entities.Fiscal;
import br.jus.trt23.contavinculada.entities.Liberacao;
import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import br.jus.trt23.contavinculada.entities.PostoDeTrabalho;
import br.jus.trt23.contavinculada.entities.Retencao;
import br.jus.trt23.contavinculada.entities.Salario;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import br.jus.trt23.contavinculada.validators.ValidadorUtil;
import br.jus.trt23.webacesso.util.UsuarioSessao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;

@Named
@Dependent
@Getter
@Setter
@Default
public class ContratoController extends AbstractController<Contrato> {

    public ContratoController() {
        super(Contrato.class);
    }

    private Fiscal fiscalNovo;
    private EncargoAliquota aliquotaNova;
    private Faturamento faturamentoNovo;
    private FaturamentoItem faturamentoItemNovo;
    private ContaVinculada contaNova;
    private PostoDeTrabalho postoNovo;
    private Contrato aditivoNovo;
    private Alocacao alocacaoNova;
    private Salario remuneracaoNova;
    private LocalDate referenciaInicio;
    private LocalDate referenciaFim;
    private List<Colaborador> colaboradoresPorContrato;
    private String liberacaoParecer;

    @Inject
    private ColaboradorController colaboradorController;
    @Inject
    private FaturamentoItemEventoController faturamentoItemEventoController;
    @Inject
    private AlocacaoController alocacaoController;
    @Inject
    private LiberacaoController liberacaoController;
    @Inject
    private RetencaoController retencaoController;
    @Inject
    private UsuarioSessao usuarioSessao;

    public String prepareFiscalNovo() {
        setFiscalNovo(new Fiscal());
        return "FiscalNovo";
    }

    public String prepareAliquotaNova() {
        setAliquotaNova(new EncargoAliquota());
        getAliquotaNova().setVigenciaIgual(getSelected());
        return "AliquotaNova";
    }

    public String prepareFaturamentoNovo() throws Exception {
        setFaturamentoNovo(new Faturamento());
        getFaturamentoNovo().setVigenteDesde(referenciaInicio);
        getFaturamentoNovo().setVigenteAte(referenciaFim);
        getFaturamentoNovo().setReferenciaInicio(referenciaInicio);
        getFaturamentoNovo().setReferenciaFim(referenciaFim);
        getFaturamentoNovo().setContrato(getSelected());
        if (!ValidadorUtil.validate(getFaturamentoNovo())) {
            return "Edit";
        }
        inicializaFaturamento();
        getSelected().addFaturamentos(getFaturamentoNovo());
        saveOrCreate();
        return "FaturamentoEdit";
    }

    public String prepareContaNova() {
        setContaNova(new ContaVinculada());
        return "ContaNova";
    }

    public String preparePostoNovo() {
        setPostoNovo(new PostoDeTrabalho());
        getPostoNovo().setVigenciaIgual(getSelected());
        return "PostoNovo";
    }

    public String prepareAditivoNovo() {
        setAditivoNovo(new Contrato());
        return "AditivoNovo";
    }

    public String prepareAlocacaoNova() {
        setAlocacaoNova(new Alocacao());
        return "AlocacaoNova";
    }

    public String prepareRemuneracaoNova() {
        setRemuneracaoNova(new Salario());
        return "RemuneracaoNova";
    }

    public String prepareAditivoEdit(Contrato aditivo) {
        setSelected(aditivo);
        return "AditivoEdit";
    }

    public String preparePostoEdit() {
        Object obj = JsfUtil.findComponent("postoDeTrabalhoDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setPostoNovo((PostoDeTrabalho) dt.getRowData());
        }
        return "PostoEdit";
    }

    public String prepareContaEdit() {
        Object obj = JsfUtil.findComponent("contaVinculadaDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setContaNova((ContaVinculada) dt.getRowData());
        }
        return "ContaEdit";
    }

    public String prepareAliquotaEdit() {
        Object obj = JsfUtil.findComponent("encargoAliquotaDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setAliquotaNova((EncargoAliquota) dt.getRowData());
        }
        return "AliquotaEdit";
    }

    public String prepareFiscalEdit() {
        Object obj = JsfUtil.findComponent("fiscalDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setFiscalNovo((Fiscal) dt.getRowData());
        }
        return "FiscalEdit";
    }

    public String prepareFaturamentoEdit() {
        Object obj = JsfUtil.findComponent("faturamentoDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setFaturamentoNovo((Faturamento) dt.getRowData());
        }
        return "FaturamentoEdit";
    }

    public String prepareAlocacaoEdit() {
        Object obj = JsfUtil.findComponent("alocacaoDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setAlocacaoNova((Alocacao) dt.getRowData());
        }
        return "AlocacaoEdit";
    }

    public String prepareRemuneracaoEdit() {
        Object obj = JsfUtil.findComponent("remuneracaoDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setRemuneracaoNova((Salario) dt.getRowData());
        }
        return "RemuneracaoEdit";
    }

    public String prepareFaturamentoItemEdit() {
        Object obj = JsfUtil.findComponent("faturamentoItemDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setFaturamentoItemNovo((FaturamentoItem) dt.getRowData());
        }
        return "FaturamentoItemEdit";
    }

    public String saveOrCreatePostoDeTrabalho() throws Exception {
        String msg;
        try {
            selected.addPostosDeTrabalho(postoNovo);
            saveOrCreate();
            msg = getResponseCreated("PostoDeTrabalho");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public String saveOrCreateEncargoAliquota() throws Exception {
        String msg;
        try {
            selected.addAliquotas(aliquotaNova);
            saveOrCreate();
            msg = getResponseCreated("EncargoAliquota");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public String saveOrCreateContaVinculada() throws Exception {
        String msg;
        try {
            selected.AddContasVinculadas(contaNova);
            saveOrCreate();
            msg = getResponseCreated("ContaVinculada");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public String saveOrCreateFiscal() throws Exception {
        String msg;
        try {
            selected.addFiscais(fiscalNovo);
            saveOrCreate();
            msg = getResponseCreated("Fiscal");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public String saveOrCreateFaturamento() throws Exception {
        String msg;
        try {
            selected.addFaturamentos(faturamentoNovo);
            saveOrCreate();
            msg = getResponseCreated("Faturamento");
            JsfUtil.addSuccessMessage(msg);
            return null;
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public String saveOrCreateAlocacao() throws Exception {
        String msg;
        try {
            getPostoNovo().addAlocacoes(alocacaoNova);
            saveOrCreate();
            msg = getResponseCreated("PostoDeTrabalho_Alocacao");
            JsfUtil.addSuccessMessage(msg);
            return "PostoEdit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public String saveOrCreateRemuneracao() throws Exception {
        String msg;
        try {
            getPostoNovo().addRemuneracaoes(remuneracaoNova);
            saveOrCreate();
            msg = getResponseCreated("PostoDeTrabalho_Remuneracao");
            JsfUtil.addSuccessMessage(msg);
            return "PostoEdit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    @Override
    protected String getMessagePrefix() {
        return "Contrato";
    }

    public List<Colaborador> completeColaboradoresParaOContrato(String criteria,
            Contrato contrato) throws Exception {
        //TODO: Deve ser configurado para que não se permita selecionar o
        //      mesmo colaborador para titular e substituto.
        if (contrato.getContratado() instanceof PessoaJuridica) {
            PessoaJuridica pj = (PessoaJuridica) getSelected().getContratado();
            return colaboradorController.complete(criteria, pj);
        }
        throw new Exception("Contratado não é pessoa jurídica e não suporta colaboradores.");
    }

    private void inicializaFaturamento() throws Exception {
        FaturamentoItem faturamentoItem;
        FaturamentoItemEvento eventoPadrao = faturamentoItemEventoController.getFaturamentoItemEventoPadrao();
        List<Alocacao> alocacoesAtivasNoPeriodo;
        List<Alocacao> alocacoesOrdenadasPorVigencia;
        int alocacaoPos;
        for (PostoDeTrabalho posto : getSelected().getPostosDeTrabalho()) {
            alocacaoPos = 0;
            alocacoesAtivasNoPeriodo = alocacaoController.
                    findVigenteParaOPostoDeTrabalho(posto,
                            faturamentoNovo.getReferenciaInicio(),
                            faturamentoNovo.getReferenciaFim());
            if (alocacoesAtivasNoPeriodo.size() > 0) {
                alocacoesOrdenadasPorVigencia = alocacoesAtivasNoPeriodo.stream().sorted((a1, a2) -> a1.getVigenteDesde().compareTo(a2.getVigenteDesde())).collect(Collectors.toList());

                for (int i = 0; i < getFaturamentoNovo().getDiasEntreReferencias(); i++) {
                    //se o dia de faturamento não estiver na alocacao posicionada
                    //avança para a próxima
                    while (!(alocacoesOrdenadasPorVigencia.get(alocacaoPos).getVigenteDesde().compareTo(referenciaInicio.plusDays(i)) <= 0
                            && alocacoesOrdenadasPorVigencia.get(alocacaoPos).getVigenteAte().compareTo(referenciaInicio.plusDays(i)) >= 0)) {
                        if (alocacaoPos < alocacoesOrdenadasPorVigencia.size() - 1) {
                            alocacaoPos++;
                        } else {
                            break;
                        }
                    }
                    if (alocacaoPos >= alocacoesOrdenadasPorVigencia.size()) {
                        break;
                    }
                    faturamentoItem = new FaturamentoItem();
                    faturamentoItem.setVigenteDesde(referenciaInicio.plusDays(i));
                    faturamentoItem.setDia(referenciaInicio.plusDays(i));
                    faturamentoItem.setPostoDeTrabalho(posto);
                    faturamentoItem.setTitular(alocacoesOrdenadasPorVigencia.get(alocacaoPos).getTitular());
                    faturamentoItem.setSubstituto(alocacoesOrdenadasPorVigencia.get(alocacaoPos).getSubstituto());
                    faturamentoItem.setFaturamento(faturamentoNovo);
                    faturamentoItem.setEvento(eventoPadrao);
                    faturamentoNovo.addItens(faturamentoItem);
                }
            }
        }
    }

    public List<FaturamentoItem> getFaturamentoItemPorPostoDeTrabalho(PostoDeTrabalho postoDeTrabalho) {
        if (getFaturamentoNovo() != null) {
            return getFaturamentoNovo().getItens().stream().
                    filter(f -> f.getPostoDeTrabalho().equals(postoDeTrabalho)).
                    collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public int sortByPostoDeTrabalhoDia(Object o1, Object o2) throws Exception {
        if (o1 instanceof FaturamentoItem && o2 instanceof FaturamentoItem) {
            FaturamentoItem fi1, fi2;
            fi1 = (FaturamentoItem) o1;
            fi2 = (FaturamentoItem) o2;
            int compareResult = fi1.getPostoDeTrabalho().toString().compareToIgnoreCase(
                    fi2.getPostoDeTrabalho().toString());
            if (compareResult == 0) {
                return fi1.getDia().compareTo(fi2.getDia());
            } else {
                return compareResult;
            }
        }
        throw new Exception("Método destinado a comparações entre FaturamentoItem");
    }

    public void retencaoRowSelect(SelectEvent event) {
        List<Retencao> retencoesComLiberacoes = new ArrayList<>();
        retencaoController.getSelectedItems().stream().filter((ret) -> (null != ret.getLiberacao())).forEach((ret) -> {
            retencoesComLiberacoes.add(ret);
        });
        retencaoController.getSelectedItems().removeAll(retencoesComLiberacoes);
    }

    public String processarLiberacoes() {
        liberacaoParecer = null;
        return "LiberacaoNova";
    }

    public String saveOrCreateLiberacoes() throws Exception {
        Liberacao liberacao;
        for (Retencao retencao : retencaoController.getSelectedItems()) {
            liberacao = new Liberacao();
            liberacao.setRetencao(retencao);
            liberacao.setParecerLiberacao(liberacaoParecer);
            liberacao.setVigenteDesde(liberacaoController.getFacade().getDateOnServer());
            liberacao.setLiberadoPor(usuarioSessao.getLogin());
            retencao.setLiberacao(liberacao);
            liberacaoController.saveOrCreate(liberacao);
        }
        retencaoController.getSelectedItems().clear();
        return "FaturamentoEdit";
    }

    public String duplicarFaturamento() throws CloneNotSupportedException {
        Object obj = JsfUtil.findComponent("faturamentoDT");
        Faturamento faturamentoClone;
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setFaturamentoNovo((Faturamento) dt.getRowData());
            faturamentoClone = (Faturamento) getFaturamentoNovo().clone();
            if (null != faturamentoClone.getRetencoes() && faturamentoClone.getRetencoes().size() > 0) {
                faturamentoClone.setRetencoes(new ArrayList<>());
            }
            faturamentoClone.setVigenteDesde(faturamentoClone.getReferenciaInicio());
            faturamentoClone.setVigenteAte(faturamentoClone.getReferenciaFim());
            faturamentoClone.setContrato(getSelected());

            setFaturamentoNovo(faturamentoClone);
            getSelected().addFaturamentos(getFaturamentoNovo());
            return "FaturamentoEdit";
        }
        return "";
    }
}
