package professor;
/**
 *
 * @author JSQLGen
 */
public class ProfessorTab extends javax.swing.JDialog {
    private final java.sql.Connection connection;
    private final java.util.List list;

    /** Construtor do form ProfessorTab
     * @param parent
     * @param connection
     */
    public ProfessorTab(java.awt.Window parent, java.sql.Connection connection) {
        super(parent);
        this.connection = connection;
        initComponents();
        list = new java.util.ArrayList();
        refresh();
        this.setVisible(true);
    }

    /** Exibe caixa de diálogo
     * @param parent Janela mãe
     * @param connection Conexão com o BD
     */
    public static void showDialog(java.awt.Window parent, java.sql.Connection connection){
        new ProfessorTab(parent, connection);
    }

    /** Atualiza tabela */
    private void refresh() {
        javax.swing.table.DefaultTableModel mTable = (javax.swing.table.DefaultTableModel)tTable.getModel();
        int lineSel = tTable.getSelectedRow();
        list.removeAll(list);
        list.addAll(ProfessorDAO.loadView(connection));
        while(mTable.getRowCount()>0) mTable.removeRow(0);
        for(int i = 0; i < list.size(); i++) {
            java.util.List line = (java.util.List)list.get(i);
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
        bPrint = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tabela de Professor");
        setModal(true);
        tTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Id", "Matricula", "Foto", "Nome", "Periodo", "Rg", "Cpf", "Telefone", "Celular", "Cep", "Rua", "Bairro", "Cidade", "Complemento", "Materia", "Tipo", "Validade", "Ativo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
        getContentPane().add(sTable, java.awt.BorderLayout.CENTER);
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
        bPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_print-16.png"))); // NOI18N
        bPrint.setMnemonic('P');
        bPrint.setText("Imprimir");
        bPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrintActionPerformed(evt);
            }
        });
        pButtons.add(bPrint);
        getContentPane().add(pButtons, java.awt.BorderLayout.SOUTH);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-800)/2, (screenSize.height-600)/2, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        Professor professor = new Professor();
        professor = ProfessorForm.showInputDialog(this, connection, professor);
        if(professor!=null) {
            try {
                ProfessorDAO.insert(connection, professor);
                refresh();
            } catch(java.sql.SQLException e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Falha na gravação do banco de dados!\n"+e.getMessage(),"Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bAddActionPerformed

    private void bRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemActionPerformed
        if(tTable.getSelectedRow()>=0) {
            try {
                Professor professor = ProfessorDAO.loadById(connection, (Integer)tTable.getValueAt(tTable.getSelectedRow(), 0));
                if(javax.swing.JOptionPane.showConfirmDialog(this, "Deseja excluir o Professor "+professor.getId()+"?","Questão",javax.swing.JOptionPane.OK_CANCEL_OPTION)==javax.swing.JOptionPane.OK_OPTION) {
                    ProfessorDAO.delete(connection, professor);
                    list.remove(tTable.getSelectedRow());
                    refresh();
                }
            } catch(java.sql.SQLException e){
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Falha na exclusão do banco de dados!\n"+e.getMessage(),"Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bRemActionPerformed

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        if(tTable.getSelectedRow()>=0) {
            try {
                Professor professor = ProfessorDAO.loadById(connection, (Integer)tTable.getValueAt(tTable.getSelectedRow(), 0));
                professor = ProfessorForm.showInputDialog(this, connection, professor);
                if(professor!=null) {
                    ProfessorDAO.update(connection, professor);
                    refresh();
                }
            } catch(java.sql.SQLException e){
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Falha na gravação do banco de dados!\n"+e.getMessage(),"Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bEditActionPerformed

    private void tTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTableMouseClicked
        if(evt.getClickCount()>=2){
            bEditActionPerformed(null);
        }
    }//GEN-LAST:event_tTableMouseClicked

    private void bPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrintActionPerformed
        javax.swing.table.DefaultTableModel mTable = (javax.swing.table.DefaultTableModel)tTable.getModel();
        String print = "";
        print += "<html>";
        print += "<body>";
        print += "<table border=1 width=100%>";
        print += "<caption>Tabela de Professor</caption>";
        print += "<tr>";
        for(int j=0;j<mTable.getColumnCount();j++)
            print += "<th>"+mTable.getColumnName(j);
        for(int i=0;i<mTable.getRowCount();i++){
            print += "<tr>";
            for(int j=0;j<mTable.getColumnCount();j++)
                print += "<td>"+mTable.getValueAt(i,j);
        }
        print += "</table>";
        print += "</body>";
        print += "</html>";
        new toolbox.print.PrintHTML(print);
    }//GEN-LAST:event_bPrintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bPrint;
    private javax.swing.JButton bRem;
    private javax.swing.JPanel pButtons;
    private javax.swing.JScrollPane sTable;
    private javax.swing.JTable tTable;
    // End of variables declaration//GEN-END:variables
}
