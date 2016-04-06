package presenca;

/**
 * Class PresencaDAO responsável pela 
 * leitura/escrita de objetos Presenca no BD
 *
 * @author JSQLGen
 */
public final class PresencaDAO extends dbaccess.DAO {

    //*****************************************
    //CREATE TABLE
    //*****************************************

    /** createTable - Cria a tabela Presenca no BD
     * @param connection Conexão com BD
     * @throws java.sql.SQLException
     */
    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {
        String sql = "CREATE TABLE Presenca ("
                   + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                   + "aluno INT NOT NULL,"
                   + "entrada DATE NOT NULL,"
                   + "saida DATE NOT NULL,"
                   + "atraso INT NOT NULL,"
                   + "observacao VARCHAR(500) NOT NULL,"
                   + "coordenador INT NOT NULL,"
                   + "CONSTRAINT PK_Presenca PRIMARY KEY (id),"
                   + "CONSTRAINT FKA_Presenca_Aluno FOREIGN KEY (aluno) REFERENCES Alunos ON DELETE CASCADE,"
                   + "CONSTRAINT FKA_Presenca_Coordenador FOREIGN KEY (coordenador) REFERENCES Coordenador ON DELETE CASCADE"
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
     * @param presencaSave Presenca a ser armazenado
     * @param statement PreparedStatement contendo SQL
     * @throws java.sql.SQLException
     */
    private static void obj2stmt(Presenca presencaSave, java.sql.PreparedStatement statement) throws java.sql.SQLException {
        statement.setInt(1, presencaSave.getAluno().getId());
        statement.setDate(2, new java.sql.Date(presencaSave.getEntrada().getTime()));
        statement.setDate(3, new java.sql.Date(presencaSave.getSaida().getTime()));
        statement.setInt(4, presencaSave.getAtraso());
        statement.setString(5, presencaSave.getObservacao());
        statement.setInt(6, presencaSave.getCoordenador().getId());
    }

    /** insert - Este método insere no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param presencaInsert Presenca a ser inserido
     * @throws java.sql.SQLException
     */
    public static void insert(java.sql.Connection connection, Presenca presencaInsert) throws java.sql.SQLException {
        String sql = "INSERT INTO Presenca (aluno,entrada,saida,atraso,observacao,coordenador) "
                   + "VALUES (?,?,?,?,?,?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(presencaInsert, statement);
        statement.executeUpdate();
        statement.close();
        sql = "SELECT IDENTITY_VAL_LOCAL() FROM Presenca";
        statement = connection.prepareStatement(sql);
        java.sql.ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            presencaInsert.setId(resultSet.getInt(1));
        }
        statement.close();
    }

    /** update - Este método atualiza no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param presencaUpdate Presenca a ser atualizado
     * @throws java.sql.SQLException
     */
    public static void update(java.sql.Connection connection, Presenca presencaUpdate) throws java.sql.SQLException {
        String sql = "UPDATE Presenca SET "
                   + "aluno = ?,"
                   + "entrada = ?,"
                   + "saida = ?,"
                   + "atraso = ?,"
                   + "observacao = ?,"
                   + "coordenador = ? "
                   + "WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(presencaUpdate, statement);
        statement.setInt(7, presencaUpdate.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** delete - Este método apaga do BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param presencaDelete Presenca a ser apagado
     * @throws java.sql.SQLException
     */
    public static void delete(java.sql.Connection connection, Presenca presencaDelete) throws java.sql.SQLException {
        String sql = "DELETE FROM Presenca WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, presencaDelete.getId());
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
    private static Presenca rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {
        Presenca presencaLoad = new Presenca();
        presencaLoad.setId(resultSet.getInt("id"));
        try {
            presencaLoad.setAluno(alunos.AlunosDAO.loadById(connection, resultSet.getInt("aluno")));
        } catch (java.sql.SQLException e) {
            presencaLoad.setAluno(new alunos.Alunos());
        }
        presencaLoad.setEntrada(resultSet.getDate("entrada"));
        presencaLoad.setSaida(resultSet.getDate("saida"));
        presencaLoad.setAtraso(resultSet.getInt("atraso"));
        presencaLoad.setObservacao(resultSet.getString("observacao"));
        try {
            presencaLoad.setCoordenador(coordenador.CoordenadorDAO.loadById(connection, resultSet.getInt("coordenador")));
        } catch (java.sql.SQLException e) {
            presencaLoad.setCoordenador(new coordenador.Coordenador());
        }
        return presencaLoad;
    }

    /** load - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return objeto Presenca || null caso não encontrar
     * @throws java.sql.SQLException
     */
    private static Presenca load(java.sql.Connection connection, String condition) throws java.sql.SQLException {
        if(!condition.isEmpty()){
            String sql = "SELECT id,aluno,entrada,saida,atraso,observacao,coordenador "
                       + "FROM Presenca "
                       + "WHERE "+condition;
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            Presenca presencaLoad;
            if (resultSet.next()) {
                presencaLoad = rs2obj(connection, resultSet);
            } else {
                presencaLoad = null;
            }
            statement.close();
            return presencaLoad;
        } else {
            return null;
        }
    }

    /** loadList - Carrega lista de objetos Presenca
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<Presenca> loadList(java.sql.Connection connection, String condition) {
        java.util.List<Presenca> list = new java.util.ArrayList<Presenca>();
        try {
            String sql = "SELECT id,aluno,entrada,saida,atraso,observacao,coordenador "
                       + "FROM Presenca "
                       + (condition.isEmpty()?"":"WHERE "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Presenca presencaLoad = rs2obj(connection, resultSet);
                list.add(presencaLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }

    /** loadView - Carrega visão de atributos de objetos Presenca
     * @param connection Conexão com BD
     * @param attributesList Atributos listados
     * @param condition condição WHERE
     * @param order Ordem da lista
     * @return lista de atributo
     */
    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {
        String sql = "SELECT " + attributesList + " "
                   + "FROM Presenca "
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
     * @param id campo identificador de Presenca
     * @return objeto Presenca || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Presenca loadById(java.sql.Connection connection, Integer id) throws java.sql.SQLException {
        return load(connection, "id = "+id);
    }

    //*****************************************
    //EXISTS Object BY
    //*****************************************

    /** existsById - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Presenca
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsById(java.sql.Connection connection, Integer id) {
        java.util.List<Presenca> l = loadList(connection, "id="+id);
        return !l.isEmpty();
    }

    //*****************************************
    //LOAD Attribute List
    //*****************************************

    /** loadIdList - Carrega lista de id de objetos Presenca
     * @param connection Conexão com BD
     * @return List contendo Id
     */
    public static java.util.List<String> loadIdList(java.sql.Connection connection) {
        return loadView(connection, "id", "", "id");
    }

    //*****************************************
    //LOAD Object List
    //*****************************************

    /** loadList - Carrega lista de objetos Presenca
     * @param connection Conexão com BD
     * @return List contendo os objetos
     */
    public static java.util.List<Presenca> loadList(java.sql.Connection connection) {
        return loadList(connection, "");
    }

    //*****************************************
    //LOAD Object View
    //*****************************************

    /** loadView - Carrega visão de atributos de objetos Presenca
     * @param connection Conexão com BD
     * @return lista com visão de atributos
     */
    public static java.util.List loadView(java.sql.Connection connection) {
        String sql = "SELECT "
                   + "Presenca.id,"
                   + "Alunos.matricula,"
                   + "Presenca.entrada,"
                   + "Presenca.saida,"
                   + "Presenca.atraso,"
                   + "Presenca.observacao,"
                   + "Coordenador.matricula "
                   + "FROM Presenca, Alunos, Coordenador "
                   + "WHERE Presenca.aluno = Alunos.id "
                   + "AND Presenca.coordenador = Coordenador.id ";
        return execQueryF(connection, sql);
    }

}
