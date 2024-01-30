package repository;

import entity.Member;
import entity.UserCard;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<UserCard, String> {
    Member findUserById(long id);
}