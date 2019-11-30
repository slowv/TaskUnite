package com.vbtn.taskunite.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.Duration;

/**
 * A Schedule.
 */
@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_from")
    private Instant from;

    @Column(name = "jhi_to")
    private Instant to;

    @Column(name = "duration")
    private Duration duration;

    @ManyToOne
    @JsonIgnoreProperties("schedules")
    private Tasker tasker;

    @ManyToOne
    @JsonIgnoreProperties("schedules")
    private Task task;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFrom() {
        return from;
    }

    public Schedule from(Instant from) {
        this.from = from;
        return this;
    }

    public void setFrom(Instant from) {
        this.from = from;
    }

    public Instant getTo() {
        return to;
    }

    public Schedule to(Instant to) {
        this.to = to;
        return this;
    }

    public void setTo(Instant to) {
        this.to = to;
    }

    public Duration getDuration() {
        return duration;
    }

    public Schedule duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Tasker getTasker() {
        return tasker;
    }

    public Schedule tasker(Tasker tasker) {
        this.tasker = tasker;
        return this;
    }

    public void setTasker(Tasker tasker) {
        this.tasker = tasker;
    }

    public Task getTask() {
        return task;
    }

    public Schedule task(Task task) {
        this.task = task;
        return this;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Schedule)) {
            return false;
        }
        return id != null && id.equals(((Schedule) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Schedule{" +
            "id=" + getId() +
            ", from='" + getFrom() + "'" +
            ", to='" + getTo() + "'" +
            ", duration='" + getDuration() + "'" +
            "}";
    }
}
