package han.com.kg.bordoMal.repository;

import han.com.kg.bordoMal.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    Page<Message> findByChatIdAndDeletedFalse(Long chatId, Pageable pageable);
}
