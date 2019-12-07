package com.vbtn.taskunite.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vbtn.taskunite.web.rest.TestUtil;

public class AdminProfitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminProfitDTO.class);
        AdminProfitDTO adminProfitDTO1 = new AdminProfitDTO();
        adminProfitDTO1.setId(1L);
        AdminProfitDTO adminProfitDTO2 = new AdminProfitDTO();
        assertThat(adminProfitDTO1).isNotEqualTo(adminProfitDTO2);
        adminProfitDTO2.setId(adminProfitDTO1.getId());
        assertThat(adminProfitDTO1).isEqualTo(adminProfitDTO2);
        adminProfitDTO2.setId(2L);
        assertThat(adminProfitDTO1).isNotEqualTo(adminProfitDTO2);
        adminProfitDTO1.setId(null);
        assertThat(adminProfitDTO1).isNotEqualTo(adminProfitDTO2);
    }
}
