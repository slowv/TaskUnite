package org.t1708e.taskunite.service.impl;

import org.t1708e.taskunite.service.StatisticService;
import org.t1708e.taskunite.domain.Statistic;
import org.t1708e.taskunite.repository.StatisticRepository;
import org.t1708e.taskunite.service.dto.StatisticDTO;
import org.t1708e.taskunite.service.mapper.StatisticMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Statistic}.
 */
@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

    private final Logger log = LoggerFactory.getLogger(StatisticServiceImpl.class);

    private final StatisticRepository statisticRepository;

    private final StatisticMapper statisticMapper;

    public StatisticServiceImpl(StatisticRepository statisticRepository, StatisticMapper statisticMapper) {
        this.statisticRepository = statisticRepository;
        this.statisticMapper = statisticMapper;
    }

    /**
     * Save a statistic.
     *
     * @param statisticDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StatisticDTO save(StatisticDTO statisticDTO) {
        log.debug("Request to save Statistic : {}", statisticDTO);
        Statistic statistic = statisticMapper.toEntity(statisticDTO);
        statistic = statisticRepository.save(statistic);
        return statisticMapper.toDto(statistic);
    }

    /**
     * Get all the statistics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StatisticDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Statistics");
        return statisticRepository.findAll(pageable)
            .map(statisticMapper::toDto);
    }


    /**
     * Get one statistic by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StatisticDTO> findOne(Long id) {
        log.debug("Request to get Statistic : {}", id);
        return statisticRepository.findById(id)
            .map(statisticMapper::toDto);
    }

    /**
     * Delete the statistic by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Statistic : {}", id);
        statisticRepository.deleteById(id);
    }
}
