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
import br.jus.trt23.contavinculada.entities.PessoaJuridica;
import br.jus.trt23.contavinculada.entities.PostoDeTrabalho;
import br.jus.trt23.contavinculada.entities.Salario;
import br.jus.trt23.contavinculada.enums.EActiveAction;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;

@Named
@FlowScoped("contratoflow")
@Getter
@Setter
public class ContratoFlowController extends AbstractController<Contrato> {

    @Inject
    ColaboradorController colaboradorController;
    @Inject
    AlocacaoController alocacaoController;
    @Inject
    FaturamentoItemEventoController faturamentoItemEventoController;

    public ContratoFlowController() {
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
    private LocalDate faturamentoCompetencia;
    private List<Colaborador> colaboradoresPorContrato;


    @Override
    protected void prepareDlg() {
        prepareFiscalNovo();
        prepareAliquotaNova();
        prepareContaNova();
        preparePostoNovo();
        prepareAditivoNovo();
    }

    public String prepareFiscalNovo() {
        setFiscalNovo(new Fiscal());
        return "FiscalNovo";
    }

    public String prepareAliquotaNova() {
        setAliquotaNova(new EncargoAliquota());
        return "AliquotaNova";
    }

    public String prepareFaturamentoNovo() throws Exception {
        setFaturamentoNovo(new Faturamento());
        getFaturamentoNovo().setVigenteDesde(faturamentoCompetencia.withDayOfMonth(1));
        inicializaFaturamento();
        return "FaturamentoNovo";
    }

    public String prepareContaNova() {
        setContaNova(new ContaVinculada());
        return "ContaNova";
    }

    public String preparePostoNovo() {
        setPostoNovo(new PostoDeTrabalho());
        return "PostoNovo";
    }

    public String prepareAditivoNovo() {
        setAditivoNovo(new Contrato());
        return "AditivoNovo";
    }

    public String prepareAlocacaoNova() {
        setAlocacaoNova(new Alocacao());
        getAlocacaoNova().setPostoDeTrabalho(getPostoNovo());
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
        setActiveAction(EActiveAction.EDIT);
        return "PostoEdit";
    }

    public String prepareContaEdit() {
        Object obj = JsfUtil.findComponent("contaVinculadaDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setContaNova((ContaVinculada) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "ContaEdit";
    }

    public String prepareAliquotaEdit() {
        Object obj = JsfUtil.findComponent("encargoAliquotaDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setAliquotaNova((EncargoAliquota) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "AliquotaEdit";
    }

    public String prepareFiscalEdit() {
        Object obj = JsfUtil.findComponent("fiscalDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setFiscalNovo((Fiscal) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "FiscalEdit";
    }

    public String prepareFaturamentoEdit() {
        Object obj = JsfUtil.findComponent("faturamentoDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setFaturamentoNovo((Faturamento) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "FaturamentoEdit";
    }

    public String prepareAlocacaoEdit() {
        Object obj = JsfUtil.findComponent("alocacaoDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setAlocacaoNova((Alocacao) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "AlocacaoEdit";
    }

    public String prepareRemuneracaoEdit() {
        Object obj = JsfUtil.findComponent("remuneracaoDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setRemuneracaoNova((Salario) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "RemuneracaoEdit";
    }

    public String prepareFaturamentoItemEdit() {
        Object obj = JsfUtil.findComponent("faturamentoItemDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setFaturamentoItemNovo((FaturamentoItem) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "FaturamentoItemEdit";
    }

    public String saveOrCreatePostoDeTrabalho() throws Exception {
        String msg;
        try {
            if (getActiveAction().equals(EActiveAction.NEW)) {
                selected.getPostosDeTrabalho().add(postoNovo);
                postoNovo.setContrato(selected);
            }
            saveOrCreate();
            preparePostoNovo();
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
            if (getActiveAction().equals(EActiveAction.NEW)) {
                selected.getAliquotas().add(aliquotaNova);
                aliquotaNova.setContrato(selected);
            }
            saveOrCreate();
            prepareAliquotaNova();
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
            if (getActiveAction().equals(EActiveAction.NEW)) {
                selected.getContasVinculadas().add(contaNova);
                contaNova.setContrato(selected);
            }
            saveOrCreate();
            prepareContaNova();
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
            if (getActiveAction().equals(EActiveAction.NEW)) {
                selected.getFiscais().add(fiscalNovo);
                fiscalNovo.setContrato(selected);
            }
            saveOrCreate();
            prepareFiscalNovo();
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
            if (getActiveAction().equals(EActiveAction.NEW)) {
                selected.getFaturamentos().add(faturamentoNovo);
                faturamentoNovo.setContrato(selected);
            }
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
            if (getActiveAction().equals(EActiveAction.NEW)) {
                getPostoNovo().getAlocacoes().add(getAlocacaoNova());
                getAlocacaoNova().setPostoDeTrabalho(getPostoNovo());
            }

            saveOrCreate();
            prepareAlocacaoNova();
            msg = getResponseCreated("PostoDeTrabalho_Alocacao");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public String saveOrCreateRemuneracao() throws Exception {
        String msg;
        try {
            if (getActiveAction().equals(EActiveAction.NEW)) {
                getPostoNovo().getRemuneracoes().add(getRemuneracaoNova());
            }

            saveOrCreate();
            prepareRemuneracaoNova();
            msg = getResponseCreated("PostoDeTrabalho_Remuneracao");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
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

    public List<Colaborador> completeColaboradores(String criteria) throws Exception {
        //TODO: Deve ser configurado para que não se permita selecionar o
        //      mesmo colaborador para titular e substituto.

        if (getSelected().getContratado() instanceof PessoaJuridica) {
            PessoaJuridica pj = (PessoaJuridica) getSelected().getContratado();
            return colaboradorController.complete(criteria, pj);
        }
        throw new Exception("Contratado não é pessoa jurídica e não suporta colaboradores.");
    }

    private void inicializaFaturamento() throws Exception {
        getFaturamentoNovo().setCompetencia(faturamentoCompetencia);
        getFaturamentoNovo().setItens(new ArrayList<>());
        FaturamentoItem faturamentoItem;
        FaturamentoItemEvento eventoPadrao = faturamentoItemEventoController.getFaturamentoItemEventoPadrao();
        Alocacao alocacaoAtiva;
        for (PostoDeTrabalho posto : getSelected().getPostosDeTrabalho()) {
            alocacaoAtiva = alocacaoController.findVigenteParaOPostoDeTrabalho(posto);
            for (int i = 1; i <= getFaturamentoCompetencia().lengthOfMonth(); i++) {
                faturamentoItem = new FaturamentoItem();
                faturamentoItem.setVigenteDesde(faturamentoCompetencia.withDayOfMonth(i));
                faturamentoItem.setDia(faturamentoCompetencia.withDayOfMonth(i));
                faturamentoItem.setPostoDeTrabalho(posto);
                faturamentoItem.setTitular(alocacaoAtiva.getTitular());
                faturamentoItem.setSubstituto(alocacaoAtiva.getSubstituto());
                faturamentoItem.setFaturamento(faturamentoNovo);
                faturamentoItem.setEvento(eventoPadrao);
                faturamentoNovo.getItens().add(faturamentoItem);
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

    public List<Colaborador> getColaboradoresPorContrato() throws Exception {
        if (this.colaboradoresPorContrato == null) {
            setColaboradoresPorContrato(completeColaboradores(""));
        }
        return this.colaboradoresPorContrato;
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
}
