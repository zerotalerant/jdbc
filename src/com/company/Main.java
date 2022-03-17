package com.company;

import org.postgresql.core.SqlCommandType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5433/";
    private static final String user = "postgres";
    private static final String password = "master";


    public static void main ( String[] args )
    {

        List<Country> countryList = getCountry ();
    }

    public static List<Country> getCountry ()
    {
        String sql = "SELECT id, country_name, kod_strany FROM country";
        Scanner scanner = new Scanner ( System.in );
        List<Country> countries = new ArrayList<> ();
        System.out.print ( "--------Insert Info--------\n" );
        System.out.print ( "\tID: " );
        int id = scanner.nextInt ();
        System.out.print ( "\tCountry name: " );
        String name = scanner.next ();
        System.out.print ( "\tCountry code: " );
        int kod = scanner.nextInt ();
        String insert = "insert into countries values (?, ?, ?);";
        Connection conn = null;

        try
        {
            conn = DriverManager.getConnection ( url, user, password );
            PreparedStatement preparedStatement = conn.prepareStatement ( insert );
            System.out.println ( "Успешно законектились!" );
            preparedStatement.setInt ( 1, id );
            preparedStatement.setString ( 2, name );
            preparedStatement.setInt ( 3, kod );
            ResultSet rs = preparedStatement.executeQuery ();
        } catch (SQLException e)
        {
            System.out.println ( e.getMessage () );
        } finally
        {
            try
            {
                conn.close ();
            } catch (SQLException ex)
            {
                ex.printStackTrace ();
            }
        }
        return countries;
    }


}
