/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Julio
 */
public class ListaPessoasModel extends AbstractListModel<String> {
    private List<String> nomes = new ArrayList<String>();
    
    public ListaPessoasModel(){
        super();
    }
    
    public ListaPessoasModel(List<String> dados){
        nomes.addAll(dados);
    }
    
    @Override
    public int getSize() {
        return nomes.size();
    }

    @Override
    public String getElementAt(int index) {
        return nomes.get(index);
    }
    
    public void add(String s) {
        nomes.add(s);
        fireIntervalAdded(this, nomes.size(), nomes.size());
    }
}
