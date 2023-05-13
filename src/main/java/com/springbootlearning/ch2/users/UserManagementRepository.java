package com.springbootlearning.ch2.users;

import com.springbootlearning.ch2.users.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<UserAccount, Long> {
}
