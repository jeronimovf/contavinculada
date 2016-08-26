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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.selectonemenu.SelectOneMenu;

@Named
@ViewScoped
@Getter
@Setter
public class ContratoController extends AbstractController<Contrato> {

    @Inject
    ColaboradorController colaboradorController;
    @Inject
    AlocacaoController alocacaoController;
    @Inject
    FaturamentoItemEventoController faturamentoItemEventoController;

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
    private LocalDate faturamentoCompetencia;
    private PostoDeTrabalho postoSelecionado;

    @Override
    protected void prepareDlg() {
        prepareFiscalNovo();
        prepareAliquotaNova();
        prepareContaNova();
        preparePostoNovo();
        prepareAditivoNovo();
        setPostoSelecionado(new PostoDeTrabalho());
        setFaturamentoItemNovo(new FaturamentoItem());
    }

    public String prepareFiscalNovo() {
        setFiscalNovo(new Fiscal());
        return "fiscalNovo";
    }

    public String prepareAliquotaNova() {
        setAliquotaNova(new EncargoAliquota());
        return "aliquotaNova";
    }

    public String prepareFaturamentoNovo() throws Exception {
        setFaturamentoNovo(new Faturamento());
        getFaturamentoNovo().setVigenteDesde(faturamentoCompetencia.withDayOfMonth(1));
        inicializaFaturamento();
        return "faturamentoNovo";
    }

    public String prepareContaNova() {
        setContaNova(new ContaVinculada());
        return "contaNova";
    }

    public String preparePostoNovo() {
        setPostoNovo(new PostoDeTrabalho());
        return "postoNovo";
    }

    public String prepareAditivoNovo() {
        setAditivoNovo(new Contrato());
        return "aditivoNovo";
    }

    public String prepareAlocacaoNova() {
        setAlocacaoNova(new Alocacao());
        getAlocacaoNova().setPostoDeTrabalho(getPostoNovo());
        return "alocacaoNova";
    }

    public String prepareRemuneracaoNova() {
        setRemuneracaoNova(new Salario());
        return "remuneracaoNova";
    }

    public String prepareAditivoEdit(Contrato aditivo) {
        setSelected(aditivo);
        return "aditivoEdit";
    }

    public String preparePostoEdit() {
        Object obj = JsfUtil.findComponent("postoDeTrabalhoDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setPostoNovo((PostoDeTrabalho) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "postoEdit";
    }

    public String prepareContaEdit() {
        Object obj = JsfUtil.findComponent("contaVinculadaDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setContaNova((ContaVinculada) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "contaEdit";
    }

    public String prepareAliquotaEdit() {
        Object obj = JsfUtil.findComponent("encargoAliquotaDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setAliquotaNova((EncargoAliquota) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "aliquotaEdit";
    }

    public String prepareFiscalEdit() {
        Object obj = JsfUtil.findComponent("fiscalDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setFiscalNovo((Fiscal) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "fiscalEdit";
    }

    public String prepareFaturamentoEdit() {
        Object obj = JsfUtil.findComponent("faturamentoDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setFaturamentoNovo((Faturamento) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "faturamentoEdit";
    }

    public String prepareAlocacaoEdit() {
        Object obj = JsfUtil.findComponent("alocacaoDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setAlocacaoNova((Alocacao) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "alocacaoEdit";
    }

    public String prepareRemuneracaoEdit() {
        Object obj = JsfUtil.findComponent("remuneracaoDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setRemuneracaoNova((Salario) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "remuneracaoEdit";
    }

    public String prepareFaturamentoItemEdit() {
        Object obj = JsfUtil.findComponent("faturamentoItemDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setFaturamentoItemNovo((FaturamentoItem) dt.getRowData());
        }
        setActiveAction(EActiveAction.EDIT);
        return "faturamentoItemEdit";
    }

    public String saveOrCreatePostoDeTrabalho() throws Exception {
        String msg;
        try {
            selected.getPostosDeTrabalho().add(postoNovo);
            postoNovo.setContrato(selected);
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
            selected.getAliquotas().add(aliquotaNova);
            aliquotaNova.setContrato(selected);
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
            selected.getContasVinculadas().add(contaNova);
            contaNova.setContrato(selected);
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
            selected.getFiscais().add(fiscalNovo);
            fiscalNovo.setContrato(selected);
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
            selected.getFaturamentos().add(faturamentoNovo);
            faturamentoNovo.setContrato(selected);
            saveOrCreate();
            prepareFaturamentoNovo();
            msg = getResponseCreated("Faturamento");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public String saveOrCreateAlocacao() throws Exception {
        String msg;
        try {
            getPostoNovo().getAlocacoes().add(getAlocacaoNova());
            getAlocacaoNova().setPostoDeTrabalho(getPostoNovo());
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
            getPostoNovo().getRemuneracoes().add(getRemuneracaoNova());
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
        List<Colaborador> colabs;
        if (getSelected().getContratado() instanceof PessoaJuridica) {
            PessoaJuridica pj = (PessoaJuridica) getSelected().getContratado();
            colabs = colaboradorController.complete(criteria, pj);
            if (getAlocacaoNova().getTitular() != null) {
                colabs.remove(getAlocacaoNova().getTitular());
            }
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

    public List<FaturamentoItem> getFaturamentoPorPostoDeTrabalho() {
        Object obj = JsfUtil.findComponent("postoSelecionado");
        if (obj instanceof SelectOneMenu) {
            SelectOneMenu som = (SelectOneMenu) obj;
            if (som.getValue() instanceof PostoDeTrabalho) {
                setPostoSelecionado((PostoDeTrabalho) som.getValue());
                return getFaturamentoNovo().getItens().stream().
                        filter(f -> f.getPostoDeTrabalho() == getPostoSelecionado()).
                        collect(Collectors.toList());
            }

        }
        return new ArrayList<>();
    }

}
