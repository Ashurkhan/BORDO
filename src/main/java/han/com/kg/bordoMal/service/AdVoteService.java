package han.com.kg.bordoMal.service;

import han.com.kg.bordoMal.dto.request.AdVoteRequest;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.User;

public interface AdVoteService {
    void vote(Ad ad,User user, AdVoteRequest request);
    long getLikesCount(Ad ad);
    long getDislikesCount(Ad ad);
}
