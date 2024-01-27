package repository;

import dto.UserRequestDTO;
import entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserCardRepository extends JpaRepository<UserCard, String> {

    List<UserCard> findUserCardById(long userId);
    UserCard save(UserRequestDTO userRequestDTO);
    @Modifying
    @Query("UPDATE USER_CARD SET status = ? WHERE card_id = ?")
    Optional<UserCard> modifyCardStatusByCardId(String status, long cardId);
}
