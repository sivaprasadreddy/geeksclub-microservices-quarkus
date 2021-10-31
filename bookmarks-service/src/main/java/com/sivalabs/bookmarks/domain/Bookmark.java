package com.sivalabs.bookmarks.domain;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bookmarks")
public class Bookmark extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookmark_id_generator")
    @SequenceGenerator(
        name = "bookmark_id_generator",
        sequenceName = "bookmark_id_seq",
        allocationSize = 10)
    public Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Title cannot be empty")
    public String title;

    @Column(nullable = false)
    @NotEmpty(message = "URL cannot be empty")
    public String url;


    @Column(name="created_at", updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    public LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    public Bookmark() {
    }

    public Bookmark(Long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }
}
