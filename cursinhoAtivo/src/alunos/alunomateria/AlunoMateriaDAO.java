package alunos.alunomateria;

/**
 * Class AlunoMateriaDAO responsável pela 
 * leitura/escrita de objetos AlunoMateria no BD
 *
 * @author JSQLGen
 */
public final class AlunoMateriaDAO extends dbaccess.DAO {

    //*****************************************
    //CREATE TABLE
    //*****************************************

    /** createTable - Cria a tabela AlunoMateria no BD
     * @param connection Conexão com BD
     * @throws java.sql.SQLException
     */
    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {
        String sql = "CREATE TABLE AlunoMateria ("
                   + "alunosOwner INT NOT NULL,"
                   + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                   + "materia INT NOT NULL,"
                   + "CONSTRAINT PK_AlunoMateria PRIMARY KEY (id),"
                   + "CONSTRAINT FKC_AlunoMateria_AlunosOwner FOREIGN KEY (alunosOwner) REFERENCES Alunos ON DELETE CASCADE,"
                   + "CONSTRAINT FKA_AlunoMateria_Materia FOREIGN KEY (materia) REFERENCES Materia ON DELETE CASCADE"
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
     * @param alunoMateriaSave AlunoMateria a ser armazenado
     * @param statement PreparedStatement contendo SQL
     * @throws java.sql.SQLException
     * @param alunosOwner Alunos owner
     */
    private static void obj2stmt(AlunoMateria alunoMateriaSave, alunos.Alunos alunosOwner, java.sql.PreparedStatement statement) throws java.sql.SQLException {
        statement.setInt(1, alunosOwner.getId());
        statement.setInt(2, alunoMateriaSave.getMateria().getId());
    }

    /** insert - Este método insere no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param alunoMateriaInsert AlunoMateria a ser inserido
     * @param alunosOwner Alunos owner
     * @throws java.sql.SQLException
     */
    public static void insert(java.sql.Connection connection, AlunoMateria alunoMateriaInsert, alunos.Alunos alunosOwner) throws java.sql.SQLException {
        String sql = "INSERT INTO AlunoMateria (alunosOwner,materia) "
                   + "VALUES (?,?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(alunoMateriaInsert, alunosOwner, statement);
        statement.executeUpdate();
        statement.close();
        sql = "SELECT IDENTITY_VAL_LOCAL() FROM AlunoMateria";
        statement = connection.prepareStatement(sql);
        java.sql.ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            alunoMateriaInsert.setId(resultSet.getInt(1));
        }
        statement.close();
    }

    /** update - Este método atualiza no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param alunoMateriaUpdate AlunoMateria a ser atualizado
     * @param alunosOwner Alunos owner
     * @throws java.sql.SQLException
     */
    public static void update(java.sql.Connection connection, AlunoMateria alunoMateriaUpdate, alunos.Alunos alunosOwner) throws java.sql.SQLException {
        String sql = "UPDATE AlunoMateria SET "
                   + "alunosOwner = ?,"
                   + "materia = ? "
                   + "WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(alunoMateriaUpdate, alunosOwner, statement);
        statement.setInt(3, alunoMateriaUpdate.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** delete - Este método apaga do BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param alunoMateriaDelete AlunoMateria a ser apagado
     * @throws java.sql.SQLException
     */
    public static void delete(java.sql.Connection connection, AlunoMateria alunoMateriaDelete) throws java.sql.SQLException {
        String sql = "DELETE FROM AlunoMateria WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, alunoMateriaDelete.getId());
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
    private static AlunoMateria rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {
        AlunoMateria alunoMateriaLoad = new AlunoMateria();
        alunoMateriaLoad.setId(resultSet.getInt("id"));
        try {
            alunoMateriaLoad.setMateria(materia.MateriaDAO.loadById(connection, resultSet.getInt("materia")));
        } catch (java.sql.SQLException e) {
            alunoMateriaLoad.setMateria(new materia.Materia());
        }
        return alunoMateriaLoad;
    }

    /** load - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return objeto AlunoMateria || null caso não encontrar
     * @throws java.sql.SQLException
     */
    private static AlunoMateria load(java.sql.Connection connection, String condition) throws java.sql.SQLException {
        if(!condition.isEmpty()){
            String sql = "SELECT id,materia "
                       + "FROM AlunoMateria "
                       + "WHERE "+condition;
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            AlunoMateria alunoMateriaLoad;
            if (resultSet.next()) {
                alunoMateriaLoad = rs2obj(connection, resultSet);
            } else {
                alunoMateriaLoad = null;
            }
            statement.close();
            return alunoMateriaLoad;
        } else {
            return null;
        }
    }

    /** loadList - Carrega lista de objetos AlunoMateria
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<AlunoMateria> loadList(java.sql.Connection connection, String condition) {
        java.util.List<AlunoMateria> list = new java.util.ArrayList<AlunoMateria>();
        try {
            String sql = "SELECT id,materia "
                       + "FROM AlunoMateria "
                       + (condition.isEmpty()?"":"WHERE "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                AlunoMateria alunoMateriaLoad = rs2obj(connection, resultSet);
                list.add(alunoMateriaLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }

    /** loadView - Carrega visão de atributos de objetos AlunoMateria
     * @param connection Conexão com BD
     * @param attributesList Atributos listados
     * @param condition condição WHERE
     * @param order Ordem da lista
     * @return lista de atributo
     */
    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {
        String sql = "SELECT " + attributesList + " "
                   + "FROM AlunoMateria "
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
     * @param id campo identificador de AlunoMateria
     * @return objeto AlunoMateria || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static AlunoMateria loadById(java.sql.Connection connection, Integer id) throws java.sql.SQLException {
        return load(connection, "id = "+id);
    }

    //*****************************************
    //EXISTS Object BY
    //*****************************************

    /** existsById - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de AlunoMateria
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsById(java.sql.Connection connection, Integer id) {
        java.util.List<AlunoMateria> l = loadList(connection, "id="+id);
        return !l.isEmpty();
    }

    //*****************************************
    //LOAD Attribute List
    //*****************************************

    /** loadIdList - Carrega lista de id de objetos AlunoMateria
     * @param connection Conexão com BD
     * @return List contendo Id
     */
    public static java.util.List<String> loadIdList(java.sql.Connection connection) {
        return loadView(connection, "id", "", "id");
    }

    //*****************************************
    //LOAD Object List
    //*****************************************

    /** loadList - Retorna Lista de objetos AlunoMateria por Alunos
     * @param connection Conexão com BD
     * @param alunosOwner Alunos
     * @return List contendo os objetos
     */
    public static java.util.List<AlunoMateria> loadList(java.sql.Connection connection, alunos.Alunos alunosOwner) {
        return loadList(connection, "alunosOwner = " + alunosOwner.getId());
    }

    //*****************************************
    //LOAD Object View
    //*****************************************

}
