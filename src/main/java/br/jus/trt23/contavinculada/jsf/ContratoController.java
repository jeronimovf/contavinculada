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
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;

@Named
@Dependent
@Getter
@Setter
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
    private LocalDate faturamentoCompetencia;
    private List<Colaborador> colaboradoresPorContrato;

    @Inject
    ColaboradorController colaboradorController;
    @Inject
    FaturamentoItemEventoController faturamentoItemEventoController;
    @Inject
    AlocacaoController alocacaoController;

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
        getFaturamentoNovo().setCompetencia(faturamentoCompetencia);
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
                faturamentoNovo.addItens(faturamentoItem);
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
}
