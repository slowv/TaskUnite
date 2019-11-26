package org.t1708e.taskunite.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.t1708e.taskunite.web.rest.TestUtil;

public class StatisticDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatisticDTO.class);
        StatisticDTO statisticDTO1 = new StatisticDTO();
        statisticDTO1.setId(1L);
        StatisticDTO statisticDTO2 = new StatisticDTO();
        assertThat(statisticDTO1).isNotEqualTo(statisticDTO2);
        statisticDTO2.setId(statisticDTO1.getId());
        assertThat(statisticDTO1).isEqualTo(statisticDTO2);
        statisticDTO2.setId(2L);
        assertThat(statisticDTO1).isNotEqualTo(statisticDTO2);
        statisticDTO1.setId(null);
        assertThat(statisticDTO1).isNotEqualTo(statisticDTO2);
    }
}
