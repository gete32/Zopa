package service;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

abstract class AbstractServiceTest {

    URL getUrl(final String name) {
        URL url = this.getClass().getClassLoader().getResource(name);
        assertNotNull(url);
        return url;
    }
}
