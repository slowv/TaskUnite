package com.vbtn.taskunite.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vbtn.taskunite.web.rest.TestUtil;

public class TaskerCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskerCategoryDTO.class);
        TaskerCategoryDTO taskerCategoryDTO1 = new TaskerCategoryDTO();
        taskerCategoryDTO1.setId(1L);
        TaskerCategoryDTO taskerCategoryDTO2 = new TaskerCategoryDTO();
        assertThat(taskerCategoryDTO1).isNotEqualTo(taskerCategoryDTO2);
        taskerCategoryDTO2.setId(taskerCategoryDTO1.getId());
        assertThat(taskerCategoryDTO1).isEqualTo(taskerCategoryDTO2);
        taskerCategoryDTO2.setId(2L);
        assertThat(taskerCategoryDTO1).isNotEqualTo(taskerCategoryDTO2);
        taskerCategoryDTO1.setId(null);
        assertThat(taskerCategoryDTO1).isNotEqualTo(taskerCategoryDTO2);
    }
}
