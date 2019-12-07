package com.vbtn.taskunite.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vbtn.taskunite.web.rest.TestUtil;

public class AdminProfitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminProfit.class);
        AdminProfit adminProfit1 = new AdminProfit();
        adminProfit1.setId(1L);
        AdminProfit adminProfit2 = new AdminProfit();
        adminProfit2.setId(adminProfit1.getId());
        assertThat(adminProfit1).isEqualTo(adminProfit2);
        adminProfit2.setId(2L);
        assertThat(adminProfit1).isNotEqualTo(adminProfit2);
        adminProfit1.setId(null);
        assertThat(adminProfit1).isNotEqualTo(adminProfit2);
    }
}
