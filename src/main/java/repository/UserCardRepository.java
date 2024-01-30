package repository;

import dto.UserRequestDTO;
import entity.Status;
import entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserCardRepository extends JpaRepository<UserCard, String> {

    List<UserCard> findUserCardListById(long userId);

    UserCard findUserCardById(long userId);
    UserCard save(UserRequestDTO userRequestDTO);
    @Modifying
    @Query("UPDATE USER_CARD SET status = ? WHERE card_id = ?")
    Optional<UserCard> modifyCardStatusByCardId(Status status, long cardId);
}
