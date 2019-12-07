package com.vbtn.taskunite.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vbtn.taskunite.web.rest.TestUtil;

public class AdminTransactionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminTransactionDTO.class);
        AdminTransactionDTO adminTransactionDTO1 = new AdminTransactionDTO();
        adminTransactionDTO1.setId(1L);
        AdminTransactionDTO adminTransactionDTO2 = new AdminTransactionDTO();
        assertThat(adminTransactionDTO1).isNotEqualTo(adminTransactionDTO2);
        adminTransactionDTO2.setId(adminTransactionDTO1.getId());
        assertThat(adminTransactionDTO1).isEqualTo(adminTransactionDTO2);
        adminTransactionDTO2.setId(2L);
        assertThat(adminTransactionDTO1).isNotEqualTo(adminTransactionDTO2);
        adminTransactionDTO1.setId(null);
        assertThat(adminTransactionDTO1).isNotEqualTo(adminTransactionDTO2);
    }
}
