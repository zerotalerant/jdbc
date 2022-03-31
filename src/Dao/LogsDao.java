package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogsDao {

    private final Connection connection = DataBaseConnection.connect ();

    public boolean saveLogs ( Long userId )
    {
        String SQL = "INSERT INTO user_logs (user_id, counter, create_date) " +
                     "VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = connection.prepareStatement ( SQL );
            preparedStatement.setLong ( 1, userId );
            preparedStatement.setLong ( 2, 0 );
            preparedStatement.setDate ( 3, new Date ( 79L ) );

            return preparedStatement.executeUpdate () > 0;
        } catch (SQLException e)
        {
            System.out.println ( e.getMessage () );
        } finally
        {
            try
            {
                preparedStatement.close ();

                connection.close ();
            } catch (SQLException e)
            {
                System.out.println ( e.getMessage () );
            }
        }
        return false;
    }

    public boolean updateLogs ( Long userId )
    {
        String SQL = "UPDATE user_logs " +
                     "SET counter = (select t.counter from user_logs t where t.user_id = ?) + 1, " +
                     "update_date = now() " +
                     "WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement ( SQL ))
        {
            preparedStatement.setLong ( 1, userId );
            preparedStatement.setLong ( 2, userId );

            return preparedStatement.executeUpdate () > 0;
        } catch (SQLException e)
        {
            System.out.println ( e.getMessage () );
        }
        return false;

    }
}