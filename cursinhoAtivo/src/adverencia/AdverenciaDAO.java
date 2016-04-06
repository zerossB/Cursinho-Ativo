package adverencia;

/**
 * Class AdverenciaDAO responsável pela 
 * leitura/escrita de objetos Adverencia no BD
 *
 * @author JSQLGen
 */
public final class AdverenciaDAO extends dbaccess.DAO {

    //*****************************************
    //CREATE TABLE
    //*****************************************

    /** createTable - Cria a tabela Adverencia no BD
     * @param connection Conexão com BD
     * @throws java.sql.SQLException
     */
    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {
        String sql = "CREATE TABLE Adverencia ("
                   + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                   + "aluno INT NOT NULL,"
                   + "coord INT NOT NULL,"
                   + "data DATE NOT NULL,"
                   + "observacao VARCHAR(500) NOT NULL,"
                   + "CONSTRAINT PK_Adverencia PRIMARY KEY (id),"
                   + "CONSTRAINT FKA_Adverencia_Aluno FOREIGN KEY (aluno) REFERENCES Alunos ON DELETE CASCADE,"
                   + "CONSTRAINT FKA_Adverencia_Coord FOREIGN KEY (coord) REFERENCES Coordenador ON DELETE CASCADE"
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
     * @param adverenciaSave Adverencia a ser armazenado
     * @param statement PreparedStatement contendo SQL
     * @throws java.sql.SQLException
     */
    private static void obj2stmt(Adverencia adverenciaSave, java.sql.PreparedStatement statement) throws java.sql.SQLException {
        statement.setInt(1, adverenciaSave.getAluno().getId());
        statement.setInt(2, adverenciaSave.getCoord().getId());
        statement.setDate(3, new java.sql.Date(adverenciaSave.getData().getTime()));
        statement.setString(4, adverenciaSave.getObservacao());
    }

    /** insert - Este método insere no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param adverenciaInsert Adverencia a ser inserido
     * @throws java.sql.SQLException
     */
    public static void insert(java.sql.Connection connection, Adverencia adverenciaInsert) throws java.sql.SQLException {
        String sql = "INSERT INTO Adverencia (aluno,coord,data,observacao) "
                   + "VALUES (?,?,?,?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(adverenciaInsert, statement);
        statement.executeUpdate();
        statement.close();
        sql = "SELECT IDENTITY_VAL_LOCAL() FROM Adverencia";
        statement = connection.prepareStatement(sql);
        java.sql.ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            adverenciaInsert.setId(resultSet.getInt(1));
        }
        statement.close();
    }

    /** update - Este método atualiza no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param adverenciaUpdate Adverencia a ser atualizado
     * @throws java.sql.SQLException
     */
    public static void update(java.sql.Connection connection, Adverencia adverenciaUpdate) throws java.sql.SQLException {
        String sql = "UPDATE Adverencia SET "
                   + "aluno = ?,"
                   + "coord = ?,"
                   + "data = ?,"
                   + "observacao = ? "
                   + "WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(adverenciaUpdate, statement);
        statement.setInt(5, adverenciaUpdate.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** delete - Este método apaga do BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param adverenciaDelete Adverencia a ser apagado
     * @throws java.sql.SQLException
     */
    public static void delete(java.sql.Connection connection, Adverencia adverenciaDelete) throws java.sql.SQLException {
        String sql = "DELETE FROM Adverencia WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, adverenciaDelete.getId());
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
    private static Adverencia rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {
        Adverencia adverenciaLoad = new Adverencia();
        adverenciaLoad.setId(resultSet.getInt("id"));
        try {
            adverenciaLoad.setAluno(alunos.AlunosDAO.loadById(connection, resultSet.getInt("aluno")));
        } catch (java.sql.SQLException e) {
            adverenciaLoad.setAluno(new alunos.Alunos());
        }
        try {
            adverenciaLoad.setCoord(coordenador.CoordenadorDAO.loadById(connection, resultSet.getInt("coord")));
        } catch (java.sql.SQLException e) {
            adverenciaLoad.setCoord(new coordenador.Coordenador());
        }
        adverenciaLoad.setData(resultSet.getDate("data"));
        adverenciaLoad.setObservacao(resultSet.getString("observacao"));
        return adverenciaLoad;
    }

    /** load - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return objeto Adverencia || null caso não encontrar
     * @throws java.sql.SQLException
     */
    private static Adverencia load(java.sql.Connection connection, String condition) throws java.sql.SQLException {
        if(!condition.isEmpty()){
            String sql = "SELECT id,aluno,coord,data,observacao "
                       + "FROM Adverencia "
                       + "WHERE "+condition;
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            Adverencia adverenciaLoad;
            if (resultSet.next()) {
                adverenciaLoad = rs2obj(connection, resultSet);
            } else {
                adverenciaLoad = null;
            }
            statement.close();
            return adverenciaLoad;
        } else {
            return null;
        }
    }

    /** loadList - Carrega lista de objetos Adverencia
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<Adverencia> loadList(java.sql.Connection connection, String condition) {
        java.util.List<Adverencia> list = new java.util.ArrayList<Adverencia>();
        try {
            String sql = "SELECT id,aluno,coord,data,observacao "
                       + "FROM Adverencia "
                       + (condition.isEmpty()?"":"WHERE "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Adverencia adverenciaLoad = rs2obj(connection, resultSet);
                list.add(adverenciaLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }

    /** loadView - Carrega visão de atributos de objetos Adverencia
     * @param connection Conexão com BD
     * @param attributesList Atributos listados
     * @param condition condição WHERE
     * @param order Ordem da lista
     * @return lista de atributo
     */
    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {
        String sql = "SELECT " + attributesList + " "
                   + "FROM Adverencia "
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
     * @param id campo identificador de Adverencia
     * @return objeto Adverencia || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Adverencia loadById(java.sql.Connection connection, Integer id) throws java.sql.SQLException {
        return load(connection, "id = "+id);
    }

    //*****************************************
    //EXISTS Object BY
    //*****************************************

    /** existsById - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Adverencia
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsById(java.sql.Connection connection, Integer id) {
        java.util.List<Adverencia> l = loadList(connection, "id="+id);
        return !l.isEmpty();
    }

    //*****************************************
    //LOAD Attribute List
    //*****************************************

    /** loadIdList - Carrega lista de id de objetos Adverencia
     * @param connection Conexão com BD
     * @return List contendo Id
     */
    public static java.util.List<String> loadIdList(java.sql.Connection connection) {
        return loadView(connection, "id", "", "id");
    }

    //*****************************************
    //LOAD Object List
    //*****************************************

    /** loadList - Carrega lista de objetos Adverencia
     * @param connection Conexão com BD
     * @return List contendo os objetos
     */
    public static java.util.List<Adverencia> loadList(java.sql.Connection connection) {
        return loadList(connection, "");
    }

    //*****************************************
    //LOAD Object View
    //*****************************************

    /** loadView - Carrega visão de atributos de objetos Adverencia
     * @param connection Conexão com BD
     * @return lista com visão de atributos
     */
    public static java.util.List loadView(java.sql.Connection connection) {
        String sql = "SELECT "
                   + "Adverencia.id,"
                   + "Alunos.matricula,"
                   + "Coordenador.matricula,"
                   + "Adverencia.data,"
                   + "Adverencia.observacao "
                   + "FROM Adverencia, Alunos, Coordenador "
                   + "WHERE Adverencia.aluno = Alunos.id "
                   + "AND Adverencia.coord = Coordenador.id ";
        return execQueryF(connection, sql);
    }

}
