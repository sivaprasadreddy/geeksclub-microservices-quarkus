package com.sivalabs.bookmarks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookmarkVotes {
    private Long bookmarkId;
    private Integer upVotes;
    private Integer downVotes;
}
