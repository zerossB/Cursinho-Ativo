package alunos;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author JSQLGen
 */
public class AlunosForm extends javax.swing.JDialog {

    private com.towel.swing.img.JImagePanel ipImage;

    private final java.sql.Connection connection;
    private Alunos alunos;

    /**
     * Construtor do form AlunosForm
     *
     * @param parent Janela mãe
     * @param connection Conexão com o BD
     * @param alunos Objeto a ser editado
     */
    public AlunosForm(java.awt.Window parent, java.sql.Connection connection, Alunos alunos) {
        super(parent);
        this.connection = connection;
        this.alunos = alunos;
        initComponents();
        obj2form();
        if (tFoto.getText().equals("") && tValidade.getText().equals("")) {
            tFoto.setText(getClass().getResource("/toolbox/images/estudiante.png").getPath());
            tMatricula.setText(getNumeroMatricula());
            tValidade.setText(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) + "");
        }
        try {
            ipImage = new com.towel.swing.img.JImagePanel(loadImage(tFoto.getText()));
            pFoto.add(ipImage);
            pFoto.validate();
        } catch (IOException ex) {
            Logger.getLogger(AlunosForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(true);
    }

    private String getNumeroMatricula() {
        String matri = "";
        java.util.List<Alunos> list = AlunosDAO.loadList(connection);

        matri += java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        matri += "03";
        matri += String.format("%04d", list.size() + 1);

        return matri;
    }

    /**
     * Carrega Foto do Aluno
     *
     * @param file Caminho absoluto da foto
     * @return BufferedImage
     * @throws java.io.IOException
     */
    private static java.awt.image.BufferedImage loadImage(String file) throws java.io.IOException {
        return javax.imageio.ImageIO.read(new java.io.File(file));
    }

    /**
     * Exibe caixa de diálogo para preenchimento dos campos
     *
     * @param parent Janela mãe
     * @param connection Conexão com o BD
     * @param alunos Objeto a ser editado
     * @return the alunos
     */
    public static Alunos showInputDialog(java.awt.Window parent, java.sql.Connection connection, Alunos alunos) {
        return new AlunosForm(parent, connection, alunos).alunos;
    }

    /**
     * Transfere os dados do objeto para o formulário
     */
    private void obj2form() {
        tId.setText(alunos.getId() == null ? "" : alunos.getId().toString());
        tMatricula.setText("" + (alunos.getMatricula() == null ? "" : alunos.getMatricula()));
        tFoto.setText(alunos.getFoto());
        tNome.setText(alunos.getNome());
        cPeriodo.setSelectedItem(alunos.getPeriodo());
        tRg.setText("" + (alunos.getRg() == null ? "" : alunos.getRg()));
        tCpf.setText(alunos.getCpf() == null ? "" : alunos.getCpf());
        tTelefone.setText(alunos.getTelefone());
        tCelular.setText(alunos.getCelular());
        tCep.setText(alunos.getCep());
        tRua.setText(alunos.getRua());
        tBairro.setText(alunos.getBairro());
        tCidade.setText(alunos.getCidade());
        tComplemento.setText(alunos.getComplemento());
        cUF.setSelectedItem(alunos.getUF());
        tValidade.setText(alunos.getValidade());
        cAtivo.setSelected(alunos.getAtivo());
    }

    /**
     * Transfere os dados do formulário para o objeto
     */
    private void form2obj() {
        alunos.setId(tId.getText());
        alunos.setMatricula(tMatricula.getText());
        alunos.setFoto(tFoto.getText());
        alunos.setNome(tNome.getText());
        alunos.setPeriodo(cPeriodo.getSelectedItem().toString());
        alunos.setRg(tRg.getText());
        alunos.setCpf(tCpf.getText());
        alunos.setTelefone(tTelefone.getText());
        alunos.setCelular(tCelular.getText());
        alunos.setCep(tCep.getText());
        alunos.setRua(tRua.getText());
        alunos.setBairro(tBairro.getText());
        alunos.setCidade(tCidade.getText());
        alunos.setComplemento(tComplemento.getText());
        alunos.setUF(cUF.getSelectedItem().toString());
        alunos.setValidade(tValidade.getText());
        alunos.setAtivo(cAtivo.isSelected());
    }

    /**
     * valida os dados do formulário
     */
    private String validateForm() {
        String fieldError = "";
        if (Integer.parseInt(tMatricula.getText()) < 0) {
            fieldError += "Matricula\n";
        }
        if (tFoto.getText().length() < 1) {
            fieldError += "Foto\n";
        }
        if (tNome.getText().length() < 1) {
            fieldError += "Nome\n";
        }
        if (cPeriodo.getSelectedItem() == null) {
            fieldError += "Periodo\n";
        }
        if (Integer.parseInt(tRg.getText()) < 0) {
            fieldError += "Rg\n";
        }
        if (tCpf.getText().length() < 1) {
            fieldError += "Cpf\n";
        }
        if (tTelefone.getText().length() < 1) {
            fieldError += "Telefone\n";
        }
        if (tCelular.getText().length() < 1) {
            fieldError += "Celular\n";
        }
        if (tCep.getText().length() < 1) {
            fieldError += "Cep\n";
        }
        if (tRua.getText().length() < 1) {
            fieldError += "Rua\n";
        }
        if (tBairro.getText().length() < 1) {
            fieldError += "Bairro\n";
        }
        if (tCidade.getText().length() < 1) {
            fieldError += "Cidade\n";
        }
        if (tComplemento.getText().length() < 1) {
            fieldError += "Complemento\n";
        }
        if (cUF.getSelectedItem() == null) {
            fieldError += "UF\n";
        }
        if (tValidade.getText().length() < 1) {
            fieldError += "Validade\n";
        }
        return fieldError;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tId = new javax.swing.JLabel();
        tFoto = new javax.swing.JFormattedTextField();
        fcFoto = new javax.swing.JFileChooser();
        pData = new javax.swing.JPanel();
        pFoto = new javax.swing.JPanel();
        tMatricula = new javax.swing.JFormattedTextField();
        tNome = new javax.swing.JFormattedTextField();
        cPeriodo = new javax.swing.JComboBox(Alunos.PERIODO);
        tRg = new javax.swing.JFormattedTextField();
        tCpf = new javax.swing.JFormattedTextField();
        tTelefone = new javax.swing.JFormattedTextField();
        tCelular = new javax.swing.JFormattedTextField();
        cAtivo = new javax.swing.JCheckBox();
        tCep = new javax.swing.JFormattedTextField();
        tBairro = new javax.swing.JFormattedTextField();
        cUF = new javax.swing.JComboBox(Alunos.UF);
        tRua = new javax.swing.JFormattedTextField();
        tCidade = new javax.swing.JFormattedTextField();
        tComplemento = new javax.swing.JFormattedTextField();
        tValidade = new javax.swing.JFormattedTextField();
        tbData = new javax.swing.JTabbedPane();
        pMaterias = new alunos.alunomateria.AlunoMateriaTab(this, connection, alunos);
        pButtons = new javax.swing.JPanel();
        bOk = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();

        tId.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        tId.setBorder(javax.swing.BorderFactory.createTitledBorder("Id"));

        tFoto.setBorder(javax.swing.BorderFactory.createTitledBorder("Foto"));

        fcFoto.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos de Imagens", "jpg", "jpeg", "png", "gif")
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de Alunos");
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pData.setBorder(javax.swing.BorderFactory.createTitledBorder("Inclusão/Alteração de Alunos"));

        pFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pFoto.setMinimumSize(new java.awt.Dimension(200, 150));
        pFoto.setPreferredSize(new java.awt.Dimension(200, 150));
        pFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pFotoMouseClicked(evt);
            }
        });
        pFoto.setLayout(new java.awt.BorderLayout());

        tMatricula.setEditable(false);
        tMatricula.setBorder(javax.swing.BorderFactory.createTitledBorder("Matricula"));
        tMatricula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        tMatricula.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        tNome.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));

        cPeriodo.setBorder(javax.swing.BorderFactory.createTitledBorder("Periodo"));

        tRg.setBorder(javax.swing.BorderFactory.createTitledBorder("Rg"));
        tRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        tRg.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        tCpf.setBorder(javax.swing.BorderFactory.createTitledBorder("Cpf"));
        try {
            tCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tCpf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tCpfFocusLost(evt);
            }
        });

        tTelefone.setBorder(javax.swing.BorderFactory.createTitledBorder("Telefone"));
        try {
            tTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tTelefone.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tCelular.setBorder(javax.swing.BorderFactory.createTitledBorder("Celular"));
        try {
            tCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #.####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tCelular.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cAtivo.setText("Ativo");

        tCep.setBorder(javax.swing.BorderFactory.createTitledBorder("Cep"));
        try {
            tCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tCep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tCepFocusLost(evt);
            }
        });

        tBairro.setBorder(javax.swing.BorderFactory.createTitledBorder("Bairro"));

        cUF.setBorder(javax.swing.BorderFactory.createTitledBorder("UF"));

        tRua.setBorder(javax.swing.BorderFactory.createTitledBorder("Rua"));

        tCidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Cidade"));

        tComplemento.setBorder(javax.swing.BorderFactory.createTitledBorder("Complemento"));

        tValidade.setEditable(false);
        tValidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Validade"));

        javax.swing.GroupLayout pDataLayout = new javax.swing.GroupLayout(pData);
        pData.setLayout(pDataLayout);
        pDataLayout.setHorizontalGroup(
            pDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pDataLayout.createSequentialGroup()
                        .addComponent(tMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pDataLayout.createSequentialGroup()
                        .addComponent(tCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tRg, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cAtivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDataLayout.createSequentialGroup()
                        .addComponent(tCep, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tRua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pDataLayout.createSequentialGroup()
                        .addComponent(cUF, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tValidade)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pDataLayout.setVerticalGroup(
            pDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDataLayout.createSequentialGroup()
                        .addComponent(pFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pDataLayout.createSequentialGroup()
                        .addGroup(pDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        tbData.addTab("Materias", new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_data-table-16.png")), pMaterias); // NOI18N

        pButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        bOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_calc-accept-16.png"))); // NOI18N
        bOk.setMnemonic('O');
        bOk.setText("Ok");
        bOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOkActionPerformed(evt);
            }
        });
        pButtons.add(bOk);

        bCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_calc-cancel-16.png"))); // NOI18N
        bCancel.setMnemonic('C');
        bCancel.setText("Cancelar");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });
        pButtons.add(bCancel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbData)
                    .addComponent(pButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(tbData, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(802, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOkActionPerformed
        String fieldError = validateForm();
        if (fieldError.length() == 0) {
            form2obj();
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_bOkActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        alunos = null;
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_bCancelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        bCancelActionPerformed(null);
    }//GEN-LAST:event_formWindowClosing

    private void pFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pFotoMouseClicked
        if (evt.getClickCount() >= 2) {
            if (fcFoto.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
                try {
                    tFoto.setText(fcFoto.getSelectedFile().getAbsolutePath());
                    ipImage.setImage(loadImage(tFoto.getText()));
                    ipImage.repaint();
                    ipImage.validate();
                    pFoto.validate();
                } catch (IOException ex) {
                    Logger.getLogger(AlunosForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_pFotoMouseClicked

    private void tCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tCepFocusLost
        try {
            final X509TrustManager cert = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs,
                        String authType)
                        throws java.security.cert.CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs,
                        String authType)
                        throws java.security.cert.CertificateException {
                }
            };

            //cria socket ssl
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{cert}, null);

            //ativa o socket para a requisicao
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            final HostnameVerifier hv = (String urlHostName, SSLSession session) -> true;

            //https://viacep.com.br/ws/14870470/json/
            URL url = new URL("https://viacep.com.br/ws/" + tCep.getText().replace("-", "").replace(".", "") + "/json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream content = connection.getInputStream();
            Gson gson = new Gson();

            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            StringBuffer html = new StringBuffer();
            String linha = "";
            while ((linha = reader.readLine()) != null) {
                html.append(linha);
            }

            model.CEP ceps = gson.fromJson(html.toString(), model.CEP.class);
            if (ceps != null || !ceps.toString().equals("")) {
                tRua.setText(ceps.getLogradouro());
                tBairro.setText(ceps.getBairro());
                tCidade.setText(ceps.getLocalidade());
                cUF.setSelectedItem(ceps.getUf());
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Erro ao Realizar consulta");
            }

        } catch (MalformedURLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getCause());
            Logger.getLogger(AlunosForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getCause());
            Logger.getLogger(AlunosForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException | NoSuchAlgorithmException ex) {
            Logger.getLogger(AlunosForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tCepFocusLost

    private void tCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tCpfFocusLost
        java.util.List<String> cpf = new java.util.ArrayList<>();
        char[] cpfs = tCpf.getText().replace(".", "").substring(0, 9).toCharArray();
        for(int i = 0; i <= 8; i++){
            cpf.add(cpfs[i]+"");
        }
        int[] primeiro = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] segundo = new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        int cpf1 = 0;
        int cpf2 = 0;
        int soma = 0;
        for (int i = 0; i <= 8; i++) {
            soma += Integer.parseInt(cpf.get(i) + "") * primeiro[i];
        }
        cpf1 = (soma * 10) % 11;        
        soma = 0;
        cpf.add(cpf1+"");
        for (int i = 0; i <= 9; i++) {
            soma += Integer.parseInt(cpf.get(i) + "") * segundo[i];
        }
        cpf2 = (soma * 10) % 11;
        String cpff = tCpf.getText().replace(".", "").replace("-", "");
        if(!cpff.substring(9, 10).equals(String.valueOf(cpf1)) && !cpff.substring(10, 11).equals(String.valueOf(cpf2))){
            javax.swing.JOptionPane.showMessageDialog(this, "CPF Invalido\nPor favor digite um CPF Válido!!");
        }
    }//GEN-LAST:event_tCpfFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bOk;
    private javax.swing.JCheckBox cAtivo;
    private javax.swing.JComboBox cPeriodo;
    private javax.swing.JComboBox cUF;
    private javax.swing.JFileChooser fcFoto;
    private javax.swing.JPanel pButtons;
    private javax.swing.JPanel pData;
    private javax.swing.JPanel pFoto;
    private javax.swing.JPanel pMaterias;
    private javax.swing.JFormattedTextField tBairro;
    private javax.swing.JFormattedTextField tCelular;
    private javax.swing.JFormattedTextField tCep;
    private javax.swing.JFormattedTextField tCidade;
    private javax.swing.JFormattedTextField tComplemento;
    private javax.swing.JFormattedTextField tCpf;
    private javax.swing.JFormattedTextField tFoto;
    private javax.swing.JLabel tId;
    private javax.swing.JFormattedTextField tMatricula;
    private javax.swing.JFormattedTextField tNome;
    private javax.swing.JFormattedTextField tRg;
    private javax.swing.JFormattedTextField tRua;
    private javax.swing.JFormattedTextField tTelefone;
    private javax.swing.JFormattedTextField tValidade;
    private javax.swing.JTabbedPane tbData;
    // End of variables declaration//GEN-END:variables
}
