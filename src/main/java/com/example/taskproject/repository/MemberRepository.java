package com.example.taskproject.repository;

import com.example.taskproject.entity.Member;
import com.example.taskproject.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<UserCard, String> {
    Member findUserById(long id);
}