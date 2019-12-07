package com.vbtn.taskunite.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AdminTransactionMapperTest {

    private AdminTransactionMapper adminTransactionMapper;

    @BeforeEach
    public void setUp() {
        adminTransactionMapper = new AdminTransactionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(adminTransactionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adminTransactionMapper.fromId(null)).isNull();
    }
}
