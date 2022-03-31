package Dao;

import Enum.Success;
import Model.User;
import Model.UserAuthorize;
import Model.UserRegistration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final static Connection connection = DataBaseConnection.connect ();

    public static Success authorize ( UserAuthorize userAuthorize )
    {
        String SQL = "SELECT count(*) as count FROM users WHERE password = '" + userAuthorize.getPassword () + "' " +
                     "AND (login = '" + userAuthorize.getLoginOrEmail () + "' or email = '" + userAuthorize.getLoginOrEmail () + "')";

        try (ResultSet resultSet = connection.createStatement ().executeQuery ( SQL ))
        {
            resultSet.next ();

            if ( resultSet.getInt ( "count" ) != 0 )
            {
                return Success.OK;
            }
        } catch (SQLException e)
        {
            System.out.println ( e.getMessage () );
        }
        return Success.NOT_FOUND;
    }

    public static Success registration ( UserRegistration userRegistration )
    {
        String SQL = "insert into users (login, password, email) values (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement ( SQL ))
        {
            preparedStatement.setString ( 1, userRegistration.getLogin () );
            preparedStatement.setString ( 2, userRegistration.getPassword () );
            preparedStatement.setString ( 3, userRegistration.getEmail () );

            if ( preparedStatement.executeUpdate () > 0 )
            {
                return Success.OK;
            }
        } catch (SQLException e)
        {
            System.out.println ( e.getMessage () );
        }
        return Success.FAIL;
    }

    private static User deleteUser ( Long id )
    {
        String SQL = "delete from users where id = ?";
        User user = getById ( id );

        try (PreparedStatement preparedStatement = connection.prepareStatement ( SQL ))
        {
            preparedStatement.setLong ( 1, id );

            return preparedStatement.executeUpdate () > 0 ? user : null;
        } catch (SQLException e)
        {
            System.out.println ( e.getMessage () );
        }
        return null;
    }

    private static User getById ( Long id )
    {
        User user = null;

        String SQL = "select * from users where id = " + id;

        try (ResultSet resultSet = connection.createStatement ().executeQuery ( SQL ))
        {
            resultSet.next ();

            user = new User (
                    resultSet.getLong ( "id" ),
                    resultSet.getString ( "full_name" ),
                    resultSet.getLong ( "user_age" ),
                    resultSet.getString ( "inn" )
            );
        } catch (SQLException e)
        {
            System.out.println ( e.getMessage () );
        }

        return user;
    }

    private static User updateUser ( User user )
    {
        String SQL = "update users set full_name = ?, user_age = ?, inn = ? where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement ( SQL ))
        {
            preparedStatement.setString ( 1, user.getFullName () );
            preparedStatement.setLong ( 2, user.getAge () );
            preparedStatement.setString ( 3, user.getInn () );
            preparedStatement.setLong ( 4, user.getId () );

            return preparedStatement.executeUpdate () > 0 ? user : null;
        } catch (SQLException e)
        {
            System.out.println ( e.getMessage () );
        }
        return null;
    }

    private static User saveUser ( User user )
    {
        String SQL = "insert into users (full_name, user_age, inn) values (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement ( SQL ))
        {
            preparedStatement.setString ( 1, user.getFullName () );
            preparedStatement.setLong ( 2, user.getAge () );
            preparedStatement.setString ( 3, user.getInn () );

            return preparedStatement.executeUpdate () > 0 ? user : null;
        } catch (SQLException e)
        {
            System.out.println ( e.getMessage () );
        }
        return null;
    }

    private static List<User> getAllUsers ()
    {
        List<User> users = new ArrayList<> ();
        String SQL = "select * from users u";
        try (ResultSet resultSet = connection.createStatement ().executeQuery ( SQL ))
        {
            while (resultSet.next ())
            {
                users.add ( new User (
                        resultSet.getLong ( "id" ),
                        resultSet.getString ( "full_name" ),
                        resultSet.getLong ( "user_age" ),
                        resultSet.getString ( "inn" )
                ) );
            }
        } catch (SQLException e)
        {
            System.out.println ( e.getMessage () );
        }

        return users;
    }
}