package com.engineeringwithramaa.databasebackup.repository.users_db;

import com.engineeringwithramaa.databasebackup.model.users_db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
