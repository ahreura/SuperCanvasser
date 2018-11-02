package team830.SuperCanvasser.Campaign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team830.SuperCanvasser.SuperCanvasserApplication;

import java.util.List;

@Service
public class CampaignService implements CampaignInterface {
    @Autowired
    private CampaignRepo campaignRepo;
    private static final Logger log = LoggerFactory.getLogger(SuperCanvasserApplication.class);

    @Override
    public Campaign editCampaign(Campaign campaign) {
        log.debug("Executing edit campaign - service");
        return campaignRepo.save(campaign);
    }

    @Override
    public List<Campaign> findAll() {
        log.debug("Executing find all campaign - service");
        return campaignRepo.findAll();
    }

    @Override
    public Campaign addCampaign(Campaign campaign) {
        log.debug("Executing add campaign - service");
        return campaignRepo.save(campaign);
    }

    @Override
    public Campaign findBy_Id(String id) {
        log.debug("Executing find campaign by id - service");
        return campaignRepo.findBy_id(id);
    }

}
