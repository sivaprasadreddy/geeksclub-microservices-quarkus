package com.sivalabs.bookmarks;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {
    public static void main(String... args) {
        System.out.println("Running bookmarks-service");
        Quarkus.run(args);
    }
}
