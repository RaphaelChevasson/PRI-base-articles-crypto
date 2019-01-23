package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.model.Keyword;
import fr.tse.fise3.pri.p002.server.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;


    @Transactional(readOnly = false)
    public Keyword findOrCreateByName(String name) {
        Optional<Keyword> optionalKeyword = keywordRepository.findByKeywordName(name);

        if (optionalKeyword.isPresent()) {
            return optionalKeyword.get();
        }

        Keyword keyword = new Keyword();
        keyword.setKeywordName(name);
        return keywordRepository.saveAndFlush(keyword);
    }


}
