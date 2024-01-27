package repository;

import entity.Member;
import entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<UserCard, String> {
    Member findUserById(long id);
}
