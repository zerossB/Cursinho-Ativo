package presencaprof;

/**
 * Class PresencaProfDAO responsável pela 
 * leitura/escrita de objetos PresencaProf no BD
 *
 * @author JSQLGen
 */
public final class PresencaProfDAO extends dbaccess.DAO {

    //*****************************************
    //CREATE TABLE
    //*****************************************

    /** createTable - Cria a tabela PresencaProf no BD
     * @param connection Conexão com BD
     * @throws java.sql.SQLException
     */
    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {
        String sql = "CREATE TABLE PresencaProf ("
                   + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                   + "professor INT NOT NULL,"
                   + "entrada DATE NOT NULL,"
                   + "saida DATE NOT NULL,"
                   + "atraso INT NOT NULL,"
                   + "observacao VARCHAR(500) NOT NULL,"
                   + "coordenador INT NOT NULL,"
                   + "CONSTRAINT PK_PresencaProf PRIMARY KEY (id),"
                   + "CONSTRAINT FKA_PresencaProf_Professor FOREIGN KEY (professor) REFERENCES Professor ON DELETE CASCADE,"
                   + "CONSTRAINT FKA_PresencaProf_Coordenador FOREIGN KEY (coordenador) REFERENCES Coordenador ON DELETE CASCADE"
                   + ")";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
    }

    //*****************************************
    //UPDATE
    //*****************************************

    /** obj2stmt - Transfere o Objeto para o PreparedStatement.
     * @param connection Conexão com BD
     * @param presencaProfSave PresencaProf a ser armazenado
     * @param statement PreparedStatement contendo SQL
     * @throws java.sql.SQLException
     */
    private static void obj2stmt(PresencaProf presencaProfSave, java.sql.PreparedStatement statement) throws java.sql.SQLException {
        statement.setInt(1, presencaProfSave.getProfessor().getId());
        statement.setDate(2, new java.sql.Date(presencaProfSave.getEntrada().getTime()));
        statement.setDate(3, new java.sql.Date(presencaProfSave.getSaida().getTime()));
        statement.setInt(4, presencaProfSave.getAtraso());
        statement.setString(5, presencaProfSave.getObservacao());
        statement.setInt(6, presencaProfSave.getCoordenador().getId());
    }

    /** insert - Este método insere no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param presencaProfInsert PresencaProf a ser inserido
     * @throws java.sql.SQLException
     */
    public static void insert(java.sql.Connection connection, PresencaProf presencaProfInsert) throws java.sql.SQLException {
        String sql = "INSERT INTO PresencaProf (professor,entrada,saida,atraso,observacao,coordenador) "
                   + "VALUES (?,?,?,?,?,?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(presencaProfInsert, statement);
        statement.executeUpdate();
        statement.close();
        sql = "SELECT IDENTITY_VAL_LOCAL() FROM PresencaProf";
        statement = connection.prepareStatement(sql);
        java.sql.ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            presencaProfInsert.setId(resultSet.getInt(1));
        }
        statement.close();
    }

    /** update - Este método atualiza no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param presencaProfUpdate PresencaProf a ser atualizado
     * @throws java.sql.SQLException
     */
    public static void update(java.sql.Connection connection, PresencaProf presencaProfUpdate) throws java.sql.SQLException {
        String sql = "UPDATE PresencaProf SET "
                   + "professor = ?,"
                   + "entrada = ?,"
                   + "saida = ?,"
                   + "atraso = ?,"
                   + "observacao = ?,"
                   + "coordenador = ? "
                   + "WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(presencaProfUpdate, statement);
        statement.setInt(7, presencaProfUpdate.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** delete - Este método apaga do BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param presencaProfDelete PresencaProf a ser apagado
     * @throws java.sql.SQLException
     */
    public static void delete(java.sql.Connection connection, PresencaProf presencaProfDelete) throws java.sql.SQLException {
        String sql = "DELETE FROM PresencaProf WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, presencaProfDelete.getId());
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
    private static PresencaProf rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {
        PresencaProf presencaProfLoad = new PresencaProf();
        presencaProfLoad.setId(resultSet.getInt("id"));
        try {
            presencaProfLoad.setProfessor(professor.ProfessorDAO.loadById(connection, resultSet.getInt("professor")));
        } catch (java.sql.SQLException e) {
            presencaProfLoad.setProfessor(new professor.Professor());
        }
        presencaProfLoad.setEntrada(resultSet.getDate("entrada"));
        presencaProfLoad.setSaida(resultSet.getDate("saida"));
        presencaProfLoad.setAtraso(resultSet.getInt("atraso"));
        presencaProfLoad.setObservacao(resultSet.getString("observacao"));
        try {
            presencaProfLoad.setCoordenador(coordenador.CoordenadorDAO.loadById(connection, resultSet.getInt("coordenador")));
        } catch (java.sql.SQLException e) {
            presencaProfLoad.setCoordenador(new coordenador.Coordenador());
        }
        return presencaProfLoad;
    }

    /** load - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return objeto PresencaProf || null caso não encontrar
     * @throws java.sql.SQLException
     */
    private static PresencaProf load(java.sql.Connection connection, String condition) throws java.sql.SQLException {
        if(!condition.isEmpty()){
            String sql = "SELECT id,professor,entrada,saida,atraso,observacao,coordenador "
                       + "FROM PresencaProf "
                       + "WHERE "+condition;
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            PresencaProf presencaProfLoad;
            if (resultSet.next()) {
                presencaProfLoad = rs2obj(connection, resultSet);
            } else {
                presencaProfLoad = null;
            }
            statement.close();
            return presencaProfLoad;
        } else {
            return null;
        }
    }

    /** loadList - Carrega lista de objetos PresencaProf
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<PresencaProf> loadList(java.sql.Connection connection, String condition) {
        java.util.List<PresencaProf> list = new java.util.ArrayList<PresencaProf>();
        try {
            String sql = "SELECT id,professor,entrada,saida,atraso,observacao,coordenador "
                       + "FROM PresencaProf "
                       + (condition.isEmpty()?"":"WHERE "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PresencaProf presencaProfLoad = rs2obj(connection, resultSet);
                list.add(presencaProfLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }

    /** loadView - Carrega visão de atributos de objetos PresencaProf
     * @param connection Conexão com BD
     * @param attributesList Atributos listados
     * @param condition condição WHERE
     * @param order Ordem da lista
     * @return lista de atributo
     */
    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {
        String sql = "SELECT " + attributesList + " "
                   + "FROM PresencaProf "
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
     * @param id campo identificador de PresencaProf
     * @return objeto PresencaProf || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static PresencaProf loadById(java.sql.Connection connection, Integer id) throws java.sql.SQLException {
        return load(connection, "id = "+id);
    }

    //*****************************************
    //EXISTS Object BY
    //*****************************************

    /** existsById - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de PresencaProf
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsById(java.sql.Connection connection, Integer id) {
        java.util.List<PresencaProf> l = loadList(connection, "id="+id);
        return !l.isEmpty();
    }

    //*****************************************
    //LOAD Attribute List
    //*****************************************

    /** loadIdList - Carrega lista de id de objetos PresencaProf
     * @param connection Conexão com BD
     * @return List contendo Id
     */
    public static java.util.List<String> loadIdList(java.sql.Connection connection) {
        return loadView(connection, "id", "", "id");
    }

    //*****************************************
    //LOAD Object List
    //*****************************************

    /** loadList - Carrega lista de objetos PresencaProf
     * @param connection Conexão com BD
     * @return List contendo os objetos
     */
    public static java.util.List<PresencaProf> loadList(java.sql.Connection connection) {
        return loadList(connection, "");
    }

    //*****************************************
    //LOAD Object View
    //*****************************************

    /** loadView - Carrega visão de atributos de objetos PresencaProf
     * @param connection Conexão com BD
     * @return lista com visão de atributos
     */
    public static java.util.List loadView(java.sql.Connection connection) {
        String sql = "SELECT "
                   + "PresencaProf.id,"
                   + "Professor.matricula,"
                   + "PresencaProf.entrada,"
                   + "PresencaProf.saida,"
                   + "PresencaProf.atraso,"
                   + "PresencaProf.observacao,"
                   + "Coordenador.matricula "
                   + "FROM PresencaProf, Professor, Coordenador "
                   + "WHERE PresencaProf.professor = Professor.id "
                   + "AND PresencaProf.coordenador = Coordenador.id ";
        return execQueryF(connection, sql);
    }

}
