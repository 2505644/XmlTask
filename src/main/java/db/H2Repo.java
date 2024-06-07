package db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class H2Repo {
    private static final Logger LOG = LoggerFactory.getLogger(H2Repo.class);

    private Connection connection;

    public H2Repo(Connection connection) {
        this.connection = connection;
    }

    public void dropTable() {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                String sql = "DROP TABLE article IF EXISTS";
                LOG.info("Table was deleted. Response: " + statement.executeUpdate(sql));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createTable() {
        if (connection != null) {
            try {
                dropTable();
                Statement statement = connection.createStatement();

                String sqlCreateTable = "CREATE TABLE IF NOT EXISTS article (\n" +
                        "row_num bigint PRIMARY KEY AUTO_INCREMENT,\n" +
                        "id_art INT NOT NULL,\n" +
                        "name varchar(255) NOT NULL,\n" +
                        "code varchar(255) NOT NULL,\n" +
                        "username varchar(255) NOT NULL,\n" +
                        "guid varchar(255) NOT NULL\n" +
                        ");";


                LOG.info("Table was created. Response: " + statement.executeUpdate(sqlCreateTable));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insertValuesInTable(int numberOfValuesToInsert) {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                for (int i = 1; i <= numberOfValuesToInsert; i++) {
                    String insertSQL =
                            String.format("INSERT INTO article " +
                                            "(id_art, name, code, username, guid) " +
                                            "VALUES ('%d', '%s', '%s', '%s', '%s')",
                                    i, "name" + i, "code" + i, "username" + i, "guid" + i);

                    statement.addBatch(insertSQL);
                }
                LOG.info("Values inserted in table: " + statement.executeBatch().length);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet showDatabase(boolean getOnlyResultSet) {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                String sql = "SELECT id_art, name, code, username, guid  FROM article where rownum <= 10";
                ResultSet resultSet = statement.executeQuery(sql);

                if (getOnlyResultSet == true) {
                    return resultSet;
                }

                LOG.info("Show the table: ");
                while (resultSet.next()) {
                    LOG.info(String.format("id_art: %d, name: %s, code: %s, username: %s, guid: %s",
                            resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
                }
                return resultSet;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
