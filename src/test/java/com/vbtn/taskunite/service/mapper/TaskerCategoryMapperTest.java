package com.vbtn.taskunite.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TaskerCategoryMapperTest {

    private TaskerCategoryMapper taskerCategoryMapper;

    @BeforeEach
    public void setUp() {
        taskerCategoryMapper = new TaskerCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(taskerCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(taskerCategoryMapper.fromId(null)).isNull();
    }
}
