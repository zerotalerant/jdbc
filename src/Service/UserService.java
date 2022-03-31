package Service;

import Dao.LogsDao;
import Dao.UserDao;
import Model.User;
import Model.UserAuthorize;
import Model.UserRegistration;

public class UserService {
    private final static LogsDao logsDao = new LogsDao ();

    public static void main ( String[] args )
    {
        UserRegistration user = new UserRegistration ( "login", "pass", "email" );
        UserAuthorize userAuthorize = new UserAuthorize ( "email", "pass" );

          System.out.println ( UserDao.registration(user));
      //  System.out.println ( UserDao.authorize ( userAuthorize ) );
    }
}

