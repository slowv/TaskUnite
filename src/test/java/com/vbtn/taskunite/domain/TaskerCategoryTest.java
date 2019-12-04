package com.vbtn.taskunite.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vbtn.taskunite.web.rest.TestUtil;

public class TaskerCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskerCategory.class);
        TaskerCategory taskerCategory1 = new TaskerCategory();
        taskerCategory1.setId(1L);
        TaskerCategory taskerCategory2 = new TaskerCategory();
        taskerCategory2.setId(taskerCategory1.getId());
        assertThat(taskerCategory1).isEqualTo(taskerCategory2);
        taskerCategory2.setId(2L);
        assertThat(taskerCategory1).isNotEqualTo(taskerCategory2);
        taskerCategory1.setId(null);
        assertThat(taskerCategory1).isNotEqualTo(taskerCategory2);
    }
}
