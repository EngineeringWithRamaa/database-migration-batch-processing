package com.engineeringwithramaa.databasebackup.repository.backup_db;

import com.engineeringwithramaa.databasebackup.model.backup_db.BUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BUserRepository extends JpaRepository<BUser, Integer> {
}
