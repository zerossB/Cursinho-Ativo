package presenca;
/**
 *
 * @author JSQLGen
 */
public final class Presenca {


    /** Atributos */
    private Integer id;
    private alunos.Alunos aluno;
    private java.util.Date entrada;
    private java.util.Date saida;
    private Integer atraso;
    private String observacao;
    private coordenador.Coordenador coordenador;

    /** Construtor */
    public Presenca() {
        id = null;
        aluno = new alunos.Alunos();
        entrada = new java.util.Date();
        saida = new java.util.Date();
        atraso = 0;
        observacao = "";
        coordenador = new coordenador.Coordenador();
    }
    /** Metodos */

    /**
     * @return id
     */
    public Integer getId() { return id; }
    /**
     * @param id Id to set
     */
    public void setId(Integer id) { this.id = id; }
    /**
     * @param id - String id to set
     */
    public void setId(String id) { this.id = (id.equals("null") || id.isEmpty())?null:Integer.parseInt(id); }

    /**
     * @return aluno
     */
    public alunos.Alunos getAluno() { return aluno; }
    /**
     * @param aluno Aluno to set
     */
    public void setAluno(alunos.Alunos aluno) { this.aluno = aluno; }

    /**
     * @return Entrada
     */
    public java.util.Date getEntrada() { return entrada; }
    /**
     * @param pattern Formato de Entrada. Ex "yyyy-MM-dd" ou "dd/MM/yyyy"
     * @return Entrada Formatado
     */
    public String getEntradaF(String pattern) { return new java.text.SimpleDateFormat(pattern).format(entrada); }
    /**
     * @param entrada Entrada to set
     */
    public void setEntrada(java.util.Date entrada) { this.entrada = entrada; }
    /**
     * @param pattern Formato de Entrada. Ex "yyyy-MM-dd" ou "dd/MM/yyyy"
     * @param entrada - String Entrada to set
     */
    public void setEntradaF(String pattern, String entrada) { this.entrada = new java.text.SimpleDateFormat(pattern).parse(entrada, new java.text.ParsePosition(0)); }

    /**
     * @return Saida
     */
    public java.util.Date getSaida() { return saida; }
    /**
     * @param pattern Formato de Saida. Ex "yyyy-MM-dd" ou "dd/MM/yyyy"
     * @return Saida Formatado
     */
    public String getSaidaF(String pattern) { return new java.text.SimpleDateFormat(pattern).format(saida); }
    /**
     * @param saida Saida to set
     */
    public void setSaida(java.util.Date saida) { this.saida = saida; }
    /**
     * @param pattern Formato de Saida. Ex "yyyy-MM-dd" ou "dd/MM/yyyy"
     * @param saida - String Saida to set
     */
    public void setSaidaF(String pattern, String saida) { this.saida = new java.text.SimpleDateFormat(pattern).parse(saida, new java.text.ParsePosition(0)); }

    /**
     * @return atraso
     */
    public Integer getAtraso() { return atraso; }
    /**
     * @param atraso Atraso to set
     */
    public void setAtraso(Integer atraso) { this.atraso = atraso; }
    /**
     * @param atraso - String atraso to set
     */
    public void setAtraso(String atraso) { this.atraso = Integer.parseInt(atraso); }

    /**
     * @return observacao
     */
    public String getObservacao() { return observacao; }
    /**
     * @param observacao Observacao to set
     */
    public void setObservacao(String observacao) { this.observacao = (observacao.length()>500?observacao.substring(0,500):observacao).replace('\'','`'); }

    /**
     * @return coordenador
     */
    public coordenador.Coordenador getCoordenador() { return coordenador; }
    /**
     * @param coordenador Coordenador to set
     */
    public void setCoordenador(coordenador.Coordenador coordenador) { this.coordenador = coordenador; }
}
