package jdbc;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleJDBCRepository {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private Statement st = null;

    private static final String CREATE_USER_SQL = "INSERT INTO myusers(firstname, lastname, age) VALUES (?,?,?)";
    private static final String UPDATE_USER_SQL = "UPDATE myusers SET firstname = ?, lastname = ?, age = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM myusers WHERE id = ? ";
    private static final String FIND_USER_BY_ID_SQL = "SELECT * FROM myusers WHERE id = ?";
    private static final String FIND_USER_BY_NAME_SQL = "SELECT * FROM myusers WHERE firstname = ?";
    private static final String FIND_ALL_USER_SQL = "SELECT * FROM myusers";

    public Long createUser(User user) {
        Long id = null;
        try (Connection connection = CustomDataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, user.getFirstName());
            preparedStatement.setObject(2, user.getLastName());
            preparedStatement.setObject(3, user.getAge());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    public User findUserById(Long userId) {
        try(Connection connection = CustomDataSource.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_SQL)){
            User user = new User();
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = build()
            }
        }
    }

    public User findUserByName(String userName) {
    }

    public List<User> findAllUser() {
    }

    public User updateUser() {
    }

    private void deleteUser(Long userId) {
    }
}
