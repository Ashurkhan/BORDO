package han.com.kg.bordoMal.service.impl;

import han.com.kg.bordoMal.exception.NotFoundException;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.AdView;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.repository.AdRepository;
import han.com.kg.bordoMal.repository.AdViewRepository;
import han.com.kg.bordoMal.repository.UserRepository;
import han.com.kg.bordoMal.service.AdViewService;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdViewServiceImpl implements AdViewService {
    private final AdRepository adRepository;
    private final AdViewRepository adViewRepository;
    private final UserRepository userRepository;

    public AdViewServiceImpl(AdRepository adRepository, AdViewRepository adViewRepository, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.adViewRepository = adViewRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void addView(Long adId, Long viewerId) {
        Ad ad=adRepository.findById(adId).orElseThrow(()-> new NotFoundException("ad not found"));

        User viewer=null;
        if(viewer!=null){
            viewer=userRepository.findById(viewerId).orElseThrow(()-> new NotFoundException("User not found"));
        }
        AdView view= new AdView();
        view.setAd(ad);
        view.setViewer(viewer);

        adViewRepository.save(view);
    }
}
