package org.t1708e.taskunite.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.t1708e.taskunite.web.rest.TestUtil;

public class TaskerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tasker.class);
        Tasker tasker1 = new Tasker();
        tasker1.setId(1L);
        Tasker tasker2 = new Tasker();
        tasker2.setId(tasker1.getId());
        assertThat(tasker1).isEqualTo(tasker2);
        tasker2.setId(2L);
        assertThat(tasker1).isNotEqualTo(tasker2);
        tasker1.setId(null);
        assertThat(tasker1).isNotEqualTo(tasker2);
    }
}
