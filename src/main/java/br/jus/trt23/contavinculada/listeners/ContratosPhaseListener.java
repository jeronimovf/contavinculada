/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.listeners;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author j129-9
 */
public class ContratosPhaseListener implements PhaseListener {

    public static final int INDENTSIZE = 2;

    @Override
    public void afterPhase(PhaseEvent event) {
        if (event.getPhaseId().equals(PhaseId.RESTORE_VIEW)
                || event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
            String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            switch (viewId) {
                case "/calendarioferiado/index.xhtml":
                    StringBuilder tree = new StringBuilder();
                    tree.append(printComponentTree(FacesContext.getCurrentInstance().getViewRoot(), 0));
                    System.out.print(tree.toString());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    public String printComponentTree(UIComponent comp, Integer indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(printComponentInfo(comp, indent));
        Integer cont = indent;

        List complist = comp.getChildren();
        if (complist.size() > 0) {
            cont++;
        }
        for (int i = 0; i < complist.size(); i++) {
            if (complist.get(i) instanceof UIComponentBase) {
                UIComponentBase uicomb = (UIComponentBase) complist.get(i);
                sb.append(printComponentTree(uicomb, cont));
            } else {
                UIComponent uicom = (UIComponent) complist.get(i);
                sb.append(printComponentTree(uicom, cont));
            }

        }
        return sb.toString();
    }

    public String printComponentInfo(UIComponent comp, Integer indent) {
        StringBuilder sb = new StringBuilder();
        if (comp.getId() == null) {
            sb.append("UIViewRoot" + " " + "(").append(comp.getClass().getName()).append(")");
        } else {
            sb.append("|\n\r").append(printIndent(indent)).append(comp.getId()).append(" " + "(").append(comp.getClass().getName()).append(")");
        }
        return sb.toString();
    }

    public String printIndent(Integer indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            for (int j = 0; j < INDENTSIZE; j++) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
