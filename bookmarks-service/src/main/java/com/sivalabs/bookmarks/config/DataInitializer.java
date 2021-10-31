package com.sivalabs.bookmarks.config;

import com.sivalabs.bookmarks.domain.Bookmark;
import com.sivalabs.bookmarks.domain.BookmarkRepository;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Transactional
@Slf4j
public class DataInitializer {

    @Inject
    BookmarkRepository bookmarkRepository;

    void onStart(@Observes StartupEvent ev) throws Exception {
        log.info("The application is starting...");
        this.importBookmarks("data/bookmarks.csv");
    }

    void onStop(@Observes ShutdownEvent ev) {
        log.info("The application is stopping...");
    }

    public void importBookmarks(String fileName) throws Exception {
        log.info("Importing bookmarks from file: {}", fileName);

        try (InputStream stream = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            final List<String> lines = readFromInputStream(stream);
            List<Bookmark> bookmarks = new ArrayList<>(lines.size());
            for (String line : lines) {
                String[] tokens = line.split("\\|");
                Bookmark bookmark = new Bookmark(null, tokens[1], tokens[0]);
                bookmarks.add(bookmark);
            }
            bookmarkRepository.persist(bookmarks);

            log.info("Imported {} bookmarks from file {}", lines.size(), fileName);
        }
    }

    private List<String> readFromInputStream(InputStream inputStream) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
