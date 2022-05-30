package com.engineeringwithramaa.databasebackup.batch;

import com.engineeringwithramaa.databasebackup.model.users_db.User;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@StepScope
public class DBReader extends JdbcCursorItemReader<User>
                      implements ItemReader<User> {
    public DBReader(@Autowired DataSource userDbDataSource) {
        setDataSource(userDbDataSource);
        setSql("SELECT id, name, department, salary FROM users_table");
        setFetchSize(10000);
        setRowMapper(new UserRowMapper());
    }

    public class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user  = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setDepartment(rs.getString("department"));
            user.setSalary(rs.getLong("salary"));
            return user;
        }
    }
}
