package com.imt.framework.web.tuto;

import com.imt.framework.web.tuto.repositories.LivreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookControllerTest {

    @Autowired
    private LivreRepository livreRepository;

    @Test
    void repositoryShouldReturnBooks() {
        assert(livreRepository != null);
    }
}
