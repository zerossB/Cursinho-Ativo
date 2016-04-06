package materia.materiaaula;
/**
 *
 * @author JSQLGen
 */
public class MateriaAulaTab extends javax.swing.JPanel {
    private final materia.MateriaForm parent;
    private final java.sql.Connection connection;
    private final materia.Materia materiaOwner;
    private final java.util.List<MateriaAula> list;

    /** Construtor do form MateriaAulaTab
     * @param parent
     * @param connection
     * @param materiaOwner 
     */
    public MateriaAulaTab(materia.MateriaForm parent, java.sql.Connection connection, materia.Materia materiaOwner) {
        this.parent = parent;
        this.connection = connection;
        this.materiaOwner = materiaOwner;
        this.list = materiaOwner.getAulas();
        initComponents();
        refresh();
    }

    /** Atualiza tabela */
    private void refresh(){
        javax.swing.table.DefaultTableModel mTable = (javax.swing.table.DefaultTableModel)tTable.getModel();
        int lineSel = tTable.getSelectedRow();
        while(mTable.getRowCount()>0) mTable.removeRow(0);
        for(MateriaAula materiaAula: list) {
            java.util.List<Object> line = new java.util.ArrayList<Object>();
            line.add(materiaAula.getId());
            line.add(materiaAula.getCodigo());
            line.add(materiaAula.getCodMateria());
            line.add(materiaAula.getNomeAula());
            line.add(materiaAula.getDescriAula());
            mTable.addRow(line.toArray());
        }
        if(lineSel>=0 && lineSel<list.size()) tTable.setRowSelectionInterval(lineSel, lineSel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        sTable = new javax.swing.JScrollPane();
        tTable = new javax.swing.JTable();
        pButtons = new javax.swing.JPanel();
        bAdd = new javax.swing.JButton();
        bRem = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();
        setLayout(new java.awt.BorderLayout());
        tTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Id", "Codigo", "CodMateria", "NomeAula", "DescriAula"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tTableMouseClicked(evt);
            }
        });
        sTable.setViewportView(tTable);
        add(sTable, java.awt.BorderLayout.CENTER);
        pButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        bAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_new-16.png"))); // NOI18N
        bAdd.setMnemonic('N');
        bAdd.setText("Adicionar");
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });
        pButtons.add(bAdd);
        bRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_delete-16.png"))); // NOI18N
        bRem.setMnemonic('R');
        bRem.setText("Remover");
        bRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRemActionPerformed(evt);
            }
        });
        pButtons.add(bRem);
        bEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_open-16.png"))); // NOI18N
        bEdit.setMnemonic('E');
        bEdit.setText("Editar");
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });
        pButtons.add(bEdit);
        add(pButtons, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        MateriaAula materiaAula = new MateriaAula();
        materiaAula = MateriaAulaForm.showInputDialog(parent, connection, materiaAula);
        if(materiaAula!=null){
            try{
                if(materiaOwner.getId()!=null) MateriaAulaDAO.insert(connection, materiaAula, materiaOwner);
                list.add(materiaAula);
                refresh();
            } catch(java.sql.SQLException e){
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Falha na gravação do banco de dados!\n"+e.getMessage(),"Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bAddActionPerformed

    private void bRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemActionPerformed
        if(tTable.getSelectedRow()>=0){
            MateriaAula materiaAula = list.get(tTable.getSelectedRow());
            if(javax.swing.JOptionPane.showConfirmDialog(this, "Deseja excluir o MateriaAula "+materiaAula.getId()+"?","Questão",javax.swing.JOptionPane.OK_CANCEL_OPTION)==javax.swing.JOptionPane.OK_OPTION){
                try{
                    if(materiaOwner.getId()!=null) MateriaAulaDAO.delete(connection, materiaAula);
                    list.remove(tTable.getSelectedRow());
                    refresh();
                } catch(java.sql.SQLException e){
                    e.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, "Falha na exclusão do banco de dados!\n"+e.getMessage(),"Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_bRemActionPerformed

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        if(tTable.getSelectedRow()>=0){
            MateriaAula materiaAula = list.get(tTable.getSelectedRow());
            materiaAula = MateriaAulaForm.showInputDialog(parent, connection, materiaAula);
            if(materiaAula!=null){
                try{
                    if(materiaOwner.getId()!=null) MateriaAulaDAO.update(connection, materiaAula, materiaOwner);
                    refresh();
                } catch(java.sql.SQLException e){
                    e.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, "Falha na gravação do banco de dados!\n"+e.getMessage(),"Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_bEditActionPerformed

    private void tTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTableMouseClicked
        if(evt.getClickCount()>=2){
            bEditActionPerformed(null);
        }
    }//GEN-LAST:event_tTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bRem;
    private javax.swing.JPanel pButtons;
    private javax.swing.JScrollPane sTable;
    private javax.swing.JTable tTable;
    // End of variables declaration//GEN-END:variables
}
