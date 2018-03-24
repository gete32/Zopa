package service;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class AbstractServiceTest {

    protected URL getUrl(final String name) {
        URL url = this.getClass().getClassLoader().getResource(name);
        assertNotNull(url);
        return url;
    }
}
