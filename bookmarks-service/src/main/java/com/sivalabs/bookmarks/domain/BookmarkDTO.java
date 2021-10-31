package com.sivalabs.bookmarks.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BookmarkDTO {
    private Long id;
    private String title;
    private String url;
    private Integer upVotes = 0;
    private Integer downVotes = 0;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BookmarkDTO toBookmarkDTO(Bookmark bookmark, BookmarkVotes votes) {
        BookmarkDTO dto = new BookmarkDTO();
        dto.setId(bookmark.id);
        dto.setTitle(bookmark.title);
        dto.setUrl(bookmark.url);
        dto.setCreatedAt(bookmark.createdAt);
        dto.setUpdatedAt(bookmark.updatedAt);
        if (votes != null) {
            dto.setUpVotes(votes.getUpVotes());
            dto.setDownVotes(votes.getDownVotes());
        }
        return dto;
    }
}
