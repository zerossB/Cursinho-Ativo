package coordenador;

/**
 * Class CoordenadorDAO responsável pela 
 * leitura/escrita de objetos Coordenador no BD
 *
 * @author JSQLGen
 */
public final class CoordenadorDAO extends dbaccess.DAO {

    //*****************************************
    //CREATE TABLE
    //*****************************************

    /** createTable - Cria a tabela Coordenador no BD
     * @param connection Conexão com BD
     * @throws java.sql.SQLException
     */
    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {
        String sql = "CREATE TABLE Coordenador ("
                   + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                   + "matricula INT UNIQUE NOT NULL,"
                   + "foto VARCHAR(200) NOT NULL,"
                   + "nome VARCHAR(200) NOT NULL,"
                   + "periodo VARCHAR(10) NOT NULL,"
                   + "rg INT UNIQUE NOT NULL,"
                   + "cpf VARCHAR(15) UNIQUE NOT NULL,"
                   + "telefone VARCHAR(15) NOT NULL,"
                   + "celular VARCHAR(15) NOT NULL,"
                   + "cep VARCHAR(12) NOT NULL,"
                   + "rua VARCHAR(100) NOT NULL,"
                   + "bairro VARCHAR(80) NOT NULL,"
                   + "cidade VARCHAR(80) NOT NULL,"
                   + "complemento VARCHAR(50) NOT NULL,"
                   + "validade VARCHAR(20) NOT NULL,"
                   + "ativo BOOLEAN NOT NULL,"
                   + "uf VARCHAR(2) NOT NULL,"
                   + "CONSTRAINT PK_Coordenador PRIMARY KEY (id)"
                   + ")";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();

        sql = "CREATE INDEX IDX_Coordenador_cep ON Coordenador (cep)";
        statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
    }

    //*****************************************
    //UPDATE
    //*****************************************

    /** obj2stmt - Transfere o Objeto para o PreparedStatement.
     * @param connection Conexão com BD
     * @param coordenadorSave Coordenador a ser armazenado
     * @param statement PreparedStatement contendo SQL
     * @throws java.sql.SQLException
     */
    private static void obj2stmt(Coordenador coordenadorSave, java.sql.PreparedStatement statement) throws java.sql.SQLException {
        statement.setInt(1, coordenadorSave.getMatricula());
        statement.setString(2, coordenadorSave.getFoto());
        statement.setString(3, coordenadorSave.getNome());
        statement.setString(4, coordenadorSave.getPeriodo());
        statement.setInt(5, coordenadorSave.getRg());
        statement.setString(6, coordenadorSave.getCpf());
        statement.setString(7, coordenadorSave.getTelefone());
        statement.setString(8, coordenadorSave.getCelular());
        statement.setString(9, coordenadorSave.getCep());
        statement.setString(10, coordenadorSave.getRua());
        statement.setString(11, coordenadorSave.getBairro());
        statement.setString(12, coordenadorSave.getCidade());
        statement.setString(13, coordenadorSave.getComplemento());
        statement.setString(14, coordenadorSave.getValidade());
        statement.setBoolean(15, coordenadorSave.getAtivo());
        statement.setString(16, coordenadorSave.getUf());
    }

    /** insert - Este método insere no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param coordenadorInsert Coordenador a ser inserido
     * @throws java.sql.SQLException
     */
    public static void insert(java.sql.Connection connection, Coordenador coordenadorInsert) throws java.sql.SQLException {
        String sql = "INSERT INTO Coordenador (matricula,foto,nome,periodo,rg,cpf,telefone,celular,cep,rua,bairro,cidade,complemento,validade,ativo,uf) "
                   + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(coordenadorInsert, statement);
        statement.executeUpdate();
        statement.close();
        sql = "SELECT IDENTITY_VAL_LOCAL() FROM Coordenador";
        statement = connection.prepareStatement(sql);
        java.sql.ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            coordenadorInsert.setId(resultSet.getInt(1));
        }
        statement.close();
    }

    /** update - Este método atualiza no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param coordenadorUpdate Coordenador a ser atualizado
     * @throws java.sql.SQLException
     */
    public static void update(java.sql.Connection connection, Coordenador coordenadorUpdate) throws java.sql.SQLException {
        String sql = "UPDATE Coordenador SET "
                   + "matricula = ?,"
                   + "foto = ?,"
                   + "nome = ?,"
                   + "periodo = ?,"
                   + "rg = ?,"
                   + "cpf = ?,"
                   + "telefone = ?,"
                   + "celular = ?,"
                   + "cep = ?,"
                   + "rua = ?,"
                   + "bairro = ?,"
                   + "cidade = ?,"
                   + "complemento = ?,"
                   + "validade = ?,"
                   + "ativo = ?,"
                   + "uf = ? "
                   + "WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(coordenadorUpdate, statement);
        statement.setInt(17, coordenadorUpdate.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** delete - Este método apaga do BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param coordenadorDelete Coordenador a ser apagado
     * @throws java.sql.SQLException
     */
    public static void delete(java.sql.Connection connection, Coordenador coordenadorDelete) throws java.sql.SQLException {
        String sql = "DELETE FROM Coordenador WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, coordenadorDelete.getId());
        statement.executeUpdate();
        statement.close();
    }

    //*****************************************
    //QUERY private
    //*****************************************

    /**
     * rs2obj - Transfere do ResultSet da Query SQL para um novo objeto
     * @param connection
     * @param resultSet to parse
     * @return novo objeto
     * @throws java.sql.SQLException 
     */
    private static Coordenador rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {
        Coordenador coordenadorLoad = new Coordenador();
        coordenadorLoad.setId(resultSet.getInt("id"));
        coordenadorLoad.setMatricula(resultSet.getInt("matricula"));
        coordenadorLoad.setFoto(resultSet.getString("foto"));
        coordenadorLoad.setNome(resultSet.getString("nome"));
        coordenadorLoad.setPeriodo(resultSet.getString("periodo"));
        coordenadorLoad.setRg(resultSet.getInt("rg"));
        coordenadorLoad.setCpf(resultSet.getString("cpf"));
        coordenadorLoad.setTelefone(resultSet.getString("telefone"));
        coordenadorLoad.setCelular(resultSet.getString("celular"));
        coordenadorLoad.setCep(resultSet.getString("cep"));
        coordenadorLoad.setRua(resultSet.getString("rua"));
        coordenadorLoad.setBairro(resultSet.getString("bairro"));
        coordenadorLoad.setCidade(resultSet.getString("cidade"));
        coordenadorLoad.setComplemento(resultSet.getString("complemento"));
        coordenadorLoad.setValidade(resultSet.getString("validade"));
        coordenadorLoad.setAtivo(resultSet.getBoolean("ativo"));
        coordenadorLoad.setUf(resultSet.getString("uf"));
        return coordenadorLoad;
    }

    /** load - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return objeto Coordenador || null caso não encontrar
     * @throws java.sql.SQLException
     */
    private static Coordenador load(java.sql.Connection connection, String condition) throws java.sql.SQLException {
        if(!condition.isEmpty()){
            String sql = "SELECT id,matricula,foto,nome,periodo,rg,cpf,telefone,celular,cep,rua,bairro,cidade,complemento,validade,ativo,uf "
                       + "FROM Coordenador "
                       + "WHERE "+condition;
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            Coordenador coordenadorLoad;
            if (resultSet.next()) {
                coordenadorLoad = rs2obj(connection, resultSet);
            } else {
                coordenadorLoad = null;
            }
            statement.close();
            return coordenadorLoad;
        } else {
            return null;
        }
    }

    /** loadList - Carrega lista de objetos Coordenador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<Coordenador> loadList(java.sql.Connection connection, String condition) {
        java.util.List<Coordenador> list = new java.util.ArrayList<Coordenador>();
        try {
            String sql = "SELECT id,matricula,foto,nome,periodo,rg,cpf,telefone,celular,cep,rua,bairro,cidade,complemento,validade,ativo,uf "
                       + "FROM Coordenador "
                       + (condition.isEmpty()?"":"WHERE "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coordenador coordenadorLoad = rs2obj(connection, resultSet);
                list.add(coordenadorLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }

    /** loadView - Carrega visão de atributos de objetos Coordenador
     * @param connection Conexão com BD
     * @param attributesList Atributos listados
     * @param condition condição WHERE
     * @param order Ordem da lista
     * @return lista de atributo
     */
    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {
        String sql = "SELECT " + attributesList + " "
                   + "FROM Coordenador "
                   + (condition.isEmpty() ? "" : "WHERE " + condition)
                   + (order.isEmpty() ? "" : "ORDER BY " + order);
        return execQueryF(connection, sql);
    }

    //*****************************************
    //QUERY public
    //*****************************************

    //*****************************************
    //LOAD Object BY
    //*****************************************

    /** loadById - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Coordenador
     * @return objeto Coordenador || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Coordenador loadById(java.sql.Connection connection, Integer id) throws java.sql.SQLException {
        return load(connection, "id = "+id);
    }

    /** loadByMatricula - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Coordenador
     * @return objeto Coordenador || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Coordenador loadByMatricula(java.sql.Connection connection, Integer matricula) throws java.sql.SQLException {
        return load(connection, "matricula = "+matricula);
    }

    /** loadByRg - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Coordenador
     * @return objeto Coordenador || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Coordenador loadByRg(java.sql.Connection connection, Integer rg) throws java.sql.SQLException {
        return load(connection, "rg = "+rg);
    }

    /** loadByCpf - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Coordenador
     * @return objeto Coordenador || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Coordenador loadByCpf(java.sql.Connection connection, String cpf) throws java.sql.SQLException {
        return load(connection, "cpf = '"+cpf+"'");
    }

    //*****************************************
    //EXISTS Object BY
    //*****************************************

    /** existsById - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Coordenador
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsById(java.sql.Connection connection, Integer id) {
        java.util.List<Coordenador> l = loadList(connection, "id="+id);
        return !l.isEmpty();
    }

    /** existsByMatricula - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Coordenador
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List<Coordenador> l = loadList(connection, "matricula="+matricula);
        return !l.isEmpty();
    }

    /** existsByRg - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Coordenador
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByRg(java.sql.Connection connection, Integer rg) {
        java.util.List<Coordenador> l = loadList(connection, "rg="+rg);
        return !l.isEmpty();
    }

    /** existsByCpf - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Coordenador
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByCpf(java.sql.Connection connection, String cpf) {
        java.util.List<Coordenador> l = loadList(connection, "cpf='"+cpf+"'");
        return !l.isEmpty();
    }

    //*****************************************
    //GET Unique Attribute BY 
    //*****************************************

    /** getMatriculaById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Coordenador
     * @return matricula || null caso não exista
     */
    public static Integer getMatriculaById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT matricula FROM Coordenador WHERE id="+id);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getRgById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Coordenador
     * @return rg || null caso não exista
     */
    public static Integer getRgById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT rg FROM Coordenador WHERE id="+id);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getCpfById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Coordenador
     * @return cpf || null caso não exista
     */
    public static String getCpfById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT cpf FROM Coordenador WHERE id="+id);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByMatricula - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Coordenador
     * @return id || null caso não exista
     */
    public static Integer getIdByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List l = execQuery(connection, "SELECT id FROM Coordenador WHERE matricula="+matricula);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getRgByMatricula - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Coordenador
     * @return rg || null caso não exista
     */
    public static Integer getRgByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List l = execQuery(connection, "SELECT rg FROM Coordenador WHERE matricula="+matricula);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getCpfByMatricula - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Coordenador
     * @return cpf || null caso não exista
     */
    public static String getCpfByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List l = execQuery(connection, "SELECT cpf FROM Coordenador WHERE matricula="+matricula);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByRg - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Coordenador
     * @return id || null caso não exista
     */
    public static Integer getIdByRg(java.sql.Connection connection, Integer rg) {
        java.util.List l = execQuery(connection, "SELECT id FROM Coordenador WHERE rg="+rg);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getMatriculaByRg - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Coordenador
     * @return matricula || null caso não exista
     */
    public static Integer getMatriculaByRg(java.sql.Connection connection, Integer rg) {
        java.util.List l = execQuery(connection, "SELECT matricula FROM Coordenador WHERE rg="+rg);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getCpfByRg - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Coordenador
     * @return cpf || null caso não exista
     */
    public static String getCpfByRg(java.sql.Connection connection, Integer rg) {
        java.util.List l = execQuery(connection, "SELECT cpf FROM Coordenador WHERE rg="+rg);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByCpf - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Coordenador
     * @return id || null caso não exista
     */
    public static Integer getIdByCpf(java.sql.Connection connection, String cpf) {
        java.util.List l = execQuery(connection, "SELECT id FROM Coordenador WHERE cpf='"+cpf+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getMatriculaByCpf - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Coordenador
     * @return matricula || null caso não exista
     */
    public static Integer getMatriculaByCpf(java.sql.Connection connection, String cpf) {
        java.util.List l = execQuery(connection, "SELECT matricula FROM Coordenador WHERE cpf='"+cpf+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getRgByCpf - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Coordenador
     * @return rg || null caso não exista
     */
    public static Integer getRgByCpf(java.sql.Connection connection, String cpf) {
        java.util.List l = execQuery(connection, "SELECT rg FROM Coordenador WHERE cpf='"+cpf+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    //*****************************************
    //LOAD Attribute List
    //*****************************************

    /** loadIdList - Carrega lista de id de objetos Coordenador
     * @param connection Conexão com BD
     * @return List contendo Id
     */
    public static java.util.List<String> loadIdList(java.sql.Connection connection) {
        return loadView(connection, "id", "", "id");
    }

    /** loadMatriculaList - Carrega lista de matricula de objetos Coordenador
     * @param connection Conexão com BD
     * @return List contendo Matricula
     */
    public static java.util.List<String> loadMatriculaList(java.sql.Connection connection) {
        return loadView(connection, "matricula", "", "matricula");
    }

    /** loadRgList - Carrega lista de rg de objetos Coordenador
     * @param connection Conexão com BD
     * @return List contendo Rg
     */
    public static java.util.List<String> loadRgList(java.sql.Connection connection) {
        return loadView(connection, "rg", "", "rg");
    }

    /** loadCpfList - Carrega lista de cpf de objetos Coordenador
     * @param connection Conexão com BD
     * @return List contendo Cpf
     */
    public static java.util.List<String> loadCpfList(java.sql.Connection connection) {
        return loadView(connection, "cpf", "", "cpf");
    }

    //*****************************************
    //LOAD Object List
    //*****************************************

    /** loadList - Carrega lista de objetos Coordenador
     * @param connection Conexão com BD
     * @return List contendo os objetos
     */
    public static java.util.List<Coordenador> loadList(java.sql.Connection connection) {
        return loadList(connection, "");
    }

    //*****************************************
    //LOAD Object View
    //*****************************************

    /** loadView - Carrega visão de atributos de objetos Coordenador
     * @param connection Conexão com BD
     * @return lista com visão de atributos
     */
    public static java.util.List loadView(java.sql.Connection connection) {
        String sql = "SELECT "
                   + "Coordenador.id,"
                   + "Coordenador.matricula,"
                   + "Coordenador.foto,"
                   + "Coordenador.nome,"
                   + "Coordenador.periodo,"
                   + "Coordenador.rg,"
                   + "Coordenador.cpf,"
                   + "Coordenador.telefone,"
                   + "Coordenador.celular,"
                   + "Coordenador.cep,"
                   + "Coordenador.rua,"
                   + "Coordenador.bairro,"
                   + "Coordenador.cidade,"
                   + "Coordenador.complemento,"
                   + "Coordenador.validade,"
                   + "Coordenador.ativo,"
                   + "Coordenador.uf "
                   + "FROM Coordenador ";
        return execQueryF(connection, sql);
    }

}
