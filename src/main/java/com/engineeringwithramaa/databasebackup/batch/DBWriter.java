package com.engineeringwithramaa.databasebackup.batch;

import com.engineeringwithramaa.databasebackup.model.backup_db.BUser;
import com.engineeringwithramaa.databasebackup.repository.backup_db.BUserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<BUser>{
    @Autowired
    private BUserRepository bUserRepository;
    @Override
    public void write(List<? extends BUser> list) throws Exception {
        bUserRepository.saveAll(list);
    }
}