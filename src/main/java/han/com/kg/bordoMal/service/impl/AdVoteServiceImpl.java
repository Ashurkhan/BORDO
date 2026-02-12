package han.com.kg.bordoMal.service.impl;

import han.com.kg.bordoMal.dto.request.AdVoteRequest;
import han.com.kg.bordoMal.model.*;
import han.com.kg.bordoMal.repository.AdRepository;
import han.com.kg.bordoMal.repository.AdVoteRepository;
import han.com.kg.bordoMal.service.AdVoteService;
import han.com.kg.bordoMal.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AdVoteServiceImpl implements AdVoteService {
    private static final Logger log = LoggerFactory.getLogger(AdVoteServiceImpl.class);

    private final AdVoteRepository adVoteRepository;
    private final AdRepository adRepository;
    private final NotificationService notificationService;

    public AdVoteServiceImpl(AdVoteRepository adVoteRepository, AdRepository adRepository, NotificationService notificationService) {
        this.adVoteRepository = adVoteRepository;
        this.adRepository = adRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void vote(Ad ad, User user, AdVoteRequest request) {

        AdVote existing = adVoteRepository.findByAdAndUser(ad, user).orElse(null);

        if (request.getRemove() != null && request.getRemove()) {
            // удалить конкретный голос
            if (existing != null && existing.getType() == request.getType()) {
                adVoteRepository.delete(existing);
            }
            return;
        }

        // обычный лайк/дизлайк
        if (existing != null) {
            if (existing.getType() == request.getType()) {
                adVoteRepository.delete(existing); // повторный клик убирает голос
            } else {
                existing.setType(request.getType()); // смена типа
                adVoteRepository.save(existing);
            }
        } else {
            AdVote vote = new AdVote();
            vote.setAd(ad);
            vote.setUser(user);
            vote.setType(request.getType());
            adVoteRepository.save(vote);
        }
        User receiver = ad.getSeller();
        if (receiver != null) {
            try {
                notificationService.notify(
                        receiver,
                        NotificationType.AD_LIKED,
                        "Ваше объявление получило новый голос",
                        ad.getId()
                );
            } catch (Exception e) {
                log.error("Failed to send vote notification for ad {}: {}", ad.getId(), e.getMessage(), e);
            }
        }
    }



    @Override
    public long getLikesCount(Ad ad) {
        return adVoteRepository.countByAdAndType(ad, AdVoteType.LIKE);
    }

    @Override
    public long getDislikesCount(Ad ad) {
        return adVoteRepository.countByAdAndType(ad, AdVoteType.DISLIKE);
    }
}
