package com.sivalabs.votes.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "votes")
public class Vote extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_id_generator")
    @SequenceGenerator(
        name = "vote_id_generator",
        sequenceName = "vote_id_seq",
        allocationSize = 10)
    public Long id;

    @Column(name = "bookmark_id", nullable = false, unique = true)
    @NotNull(message = "BookmarkId cannot be empty")
    public Long bookmarkId;

    @Column(name = "up_votes")
    public Integer upVotes = 0;

    @Column(name = "down_votes")
    public Integer downVotes = 0;

    @Column(name="created_at", updatable = false)
    public LocalDateTime createdAt;

    @Column(name="updated_at", insertable = false)
    public LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
