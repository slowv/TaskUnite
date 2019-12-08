package com.vbtn.taskunite.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vbtn.taskunite.web.rest.TestUtil;

public class AdminTransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminTransaction.class);
        AdminTransaction adminTransaction1 = new AdminTransaction();
        adminTransaction1.setId(1L);
        AdminTransaction adminTransaction2 = new AdminTransaction();
        adminTransaction2.setId(adminTransaction1.getId());
        assertThat(adminTransaction1).isEqualTo(adminTransaction2);
        adminTransaction2.setId(2L);
        assertThat(adminTransaction1).isNotEqualTo(adminTransaction2);
        adminTransaction1.setId(null);
        assertThat(adminTransaction1).isNotEqualTo(adminTransaction2);
    }
}
