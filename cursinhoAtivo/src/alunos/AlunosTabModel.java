/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alunos;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author G
 */
public class AlunosTabModel extends AbstractTableModel {

    private List<Alunos> alunos;
    private static final String tabColumn[] = {
        "Matricula", "Nome", "Periodo", "Cidade", "Ativo"
    };

    public AlunosTabModel(List<Alunos> alunos) {
        this.alunos = alunos;
    }

    @Override
    public int getRowCount() {
        return alunos.size();
    }

    @Override
    public int getColumnCount() {
        return tabColumn.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Boolean.class;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return tabColumn[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return alunos.get(rowIndex).getMatricula();
            case 1:
                return alunos.get(rowIndex).getNome();
            case 2:
                return alunos.get(rowIndex).getPeriodo();
            case 3:
                return alunos.get(rowIndex).getCidade();
            case 4:
                return alunos.get(rowIndex).getAtivo();
        }
        return null;
    }
}
