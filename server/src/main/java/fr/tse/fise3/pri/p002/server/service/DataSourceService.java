package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.model.DataSource;
import fr.tse.fise3.pri.p002.server.repository.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DataSourceService {

    public static final String SOURCE_HAL = "hal";
    public static final String SOURCE_EPRINT = "eprint";

    @Autowired
    private DataSourceRepository dataSourceRepository;

    @Transactional(readOnly = false)
    public void saveDataSource(DataSource dataSource) {
        dataSourceRepository.save(dataSource);
    }

    @Transactional(readOnly = true)
    public Optional<DataSource> findByName(String name) {
        return dataSourceRepository.findById(name);
    }

    @Transactional(readOnly = true)
    public boolean dataSourceExistsByName(String name) {
        return dataSourceRepository.existsById(name);
    }

    @Transactional(readOnly = true)
    public DataSource getHalDataSource() {
        return findByName(SOURCE_HAL).orElseThrow(() -> new ResourceNotFoundException("Hal source doesn't exit"));
    }

    @Transactional(readOnly = true)
    public DataSource getEPrintDataSource() {
        return findByName(SOURCE_EPRINT).orElseThrow(() -> new ResourceNotFoundException("EPrint source doesn't exit"));
    }

    @Transactional(readOnly = true)
    public List<DataSource> findAllDataSources() {
        return dataSourceRepository.findAll();
    }


}
