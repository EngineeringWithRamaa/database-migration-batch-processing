package com.engineeringwithramaa.databasebackup.batch;

import com.engineeringwithramaa.databasebackup.model.backup_db.BUser;
import com.engineeringwithramaa.databasebackup.model.users_db.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DBProcessor implements ItemProcessor<User, BUser> {

    @Override
    public BUser process(User user) throws Exception {
        BUser bUser = new BUser();
        bUser.setId(user.getId());
        bUser.setName(user.getName());
        bUser.setDepartment(user.getDepartment());
        bUser.setSalary(user.getSalary());

        return bUser;
    }
}
