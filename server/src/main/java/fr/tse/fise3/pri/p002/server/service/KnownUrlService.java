package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.model.KnownUrl;
import fr.tse.fise3.pri.p002.server.repository.KnownUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KnownUrlService {

    @Autowired
    private KnownUrlRepository knownUrlRepository;

    Optional<KnownUrl> findUrlByName(String urlName) {
        return knownUrlRepository.findByUrlName(urlName);
    }

    public void saveUrl(KnownUrl knownUrl) {
        knownUrlRepository.save(knownUrl);
    }


}
