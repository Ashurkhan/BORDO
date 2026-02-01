package han.com.kg.bordoMal.service.impl;

import han.com.kg.bordoMal.dto.request.AdVoteRequest;
import han.com.kg.bordoMal.exception.NotFoundException;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.AdVote;
import han.com.kg.bordoMal.model.AdVoteType;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.repository.AdRepository;
import han.com.kg.bordoMal.repository.AdVoteRepository;
import han.com.kg.bordoMal.service.AdVoteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdVoteServiceImpl implements AdVoteService {
    private final AdVoteRepository adVoteRepository;
    private final AdRepository adRepository;

    public AdVoteServiceImpl(AdVoteRepository adVoteRepository, AdRepository adRepository) {
        this.adVoteRepository = adVoteRepository;
        this.adRepository = adRepository;
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
