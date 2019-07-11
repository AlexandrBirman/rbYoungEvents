/*package dbService.dao;

import dbService.entity.Vacancy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleDAO implements AutoCloseable{
    private Connection connection;

    public SimpleDAO() {
        connection = ConnectionProvider.getConnection();
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

//    public ResultSet executeSelectQuery(String sql) {
//        ResultSet resultSet = null;
//        try (Statement statement = connection.createStatement()){
//            resultSet = statement.executeQuery(sql);
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resultSet;
//    }
//"select * from events where url = \'" + vacancy.getUrl() + "\'"
    public void insertVacancy(Vacancy vacancy, String keyword) {
        try (PreparedStatement statement = connection.prepareStatement("insert into events(eventName," +
                " url, idType) values(?, ?, 1)", Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, vacancy.getName());
            statement.setString(2, vacancy.getUrl());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                vacancy.setId(rs.getInt(1));
            }
            try (PreparedStatement st = connection.prepareStatement("insert into eventVals(idEvent," +
                    " keyword) values(?, ?)")){
                st.setInt(1, vacancy.getId());
                st.setString(2, keyword);
                st.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void insertType(String chatId, int typeId) {
        try(PreparedStatement statement = connection.prepareStatement("insert into userTypes(chatId, typeId)" +
                " values(?, ?)")) {
            statement.setString(1, chatId);
            statement.setInt(2, typeId);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertInterests(String chatId, String keyword) {
        try(PreparedStatement statement = connection.prepareStatement("insert into userLike(chatId, LikedWord)" +
                " values(?, ?)")) {
            statement.setString(1, chatId);
            statement.setString(2, keyword);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> selectKeyWords(String chatId) {
        List<String> list = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from userLike where chatId = \'" + chatId + "\'");
            while(rs.next())
                list.add(rs.getString(3));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Integer> selectTypes(String chatId) {
        List<Integer> list = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from userTypes where chatId = \'" + chatId + "\'");
            while(rs.next())
                list.add(rs.getInt(2));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Integer> selectEventIds(String keyWord) {
        List<Integer> list = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from eventVals where keyword = \'" + keyWord + "\'");
            while(rs.next())
                list.add(rs.getInt(2));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Vacancy> selectVacanciesForPerson(String chatId) {
        List<Vacancy> list = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT events.eventName, events.url FROM events, userLike," +
                    " userTypes, eventVals WHERE userLike.likedWord = eventVals.keyword AND" +
                    " eventVals.idEvent = events.eventId AND events.idType = userTypes.typeId AND" +
                    " userLike.chatId = userTypes.chatId AND userLike.chatId = \'" + chatId + "\'");
            while(rs.next()) {
                Vacancy vacancy = new Vacancy(rs.getString(1), rs.getString(2));
                list.add(vacancy);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteTypesByChatId(String chatId) {
        try(Statement statement = connection.createStatement()) {
            statement.execute("delete from userTypes where chatId = \'" + chatId + "\'");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInterestsByChatId(String chatId) {
        try(Statement statement = connection.createStatement()) {
            statement.execute("delete from userLike where chatId = \'" + chatId + "\' and" +
                    " likedword IN (\'Аналитика\', \'Бизнес\', \'Дизайн\')");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLanguagesByChatId(String chatId) {
        try(Statement statement = connection.createStatement()) {
            statement.execute("delete from userLike where chatId = \'" + chatId + "\' and" +
                    " likedword IN (\'Java\', \'C++\', \'Python\', \'PHP\', \'JavaScript\')");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/
