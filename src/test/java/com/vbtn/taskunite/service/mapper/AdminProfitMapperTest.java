package com.vbtn.taskunite.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AdminProfitMapperTest {

    private AdminProfitMapper adminProfitMapper;

    @BeforeEach
    public void setUp() {
        adminProfitMapper = new AdminProfitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(adminProfitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adminProfitMapper.fromId(null)).isNull();
    }
}
