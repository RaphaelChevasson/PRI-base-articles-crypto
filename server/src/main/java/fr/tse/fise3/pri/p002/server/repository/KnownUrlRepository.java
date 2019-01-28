package fr.tse.fise3.pri.p002.server.repository;

import fr.tse.fise3.pri.p002.server.model.KnownUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KnownUrlRepository extends JpaRepository<KnownUrl, String> {
    Optional<KnownUrl> findByUrlName(String urlName);
}
