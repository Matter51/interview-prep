package lru;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LRUTest {

    private LRUCache<String> lruCache;

    @BeforeEach
    public void setup() {
        this.lruCache = new LRUCache<>(3);
    }

    @Test
    public void testPutAndGetEntries() {
        // Arrange
        String entry1 = "entry1";
        String entry2 = "entry2";
        String entry3 = "entry3";

        // Act
        lruCache.put(entry1);
        lruCache.put(entry2);
        lruCache.put(entry3);

        // Assert
        assertThat(lruCache.get(entry1.hashCode())).isEqualTo(entry1);
        assertThat(lruCache.get(entry2.hashCode())).isEqualTo(entry2);
        assertThat(lruCache.get(entry3.hashCode())).isEqualTo(entry3);
    }

    @Test
    public void testPutDuplicateEntities() {
        // Arrange
        String entry1 = "entry1";

        // Act
        lruCache.put(entry1);
        lruCache.put(entry1);

        // Assert
        assertThat(lruCache.get(entry1.hashCode())).isEqualTo(entry1);
        assertThat(lruCache.getCacheSize()).isEqualTo(1);
    }

    @Test
    public void testPutMoreEntitiesThanSizeAllows() {
        // Arrange
        String entry1 = "entry1";
        String entry2 = "entry2";
        String entry3 = "entry3";
        String entry4 = "entry4";

        // Act
        lruCache.put(entry1);
        lruCache.put(entry2);
        lruCache.put(entry3);
        lruCache.put(entry4);

        // Assert
        assertThat(lruCache.get(entry1.hashCode())).isEqualTo(null);
        assertThat(lruCache.getCacheSize()).isEqualTo(3);
        assertThat(lruCache.get(entry4.hashCode())).isEqualTo(entry4);
    }


    @Test
    public void testGettingUpdatesRecentlyUsedStatus() {
        // Arrange
        String entry1 = "entry1";
        String entry2 = "entry2";
        String entry3 = "entry3";
        String entry4 = "entry4";

        // Act
        lruCache.put(entry1);
        lruCache.put(entry2);
        lruCache.put(entry3);
        lruCache.get(entry1.hashCode());
        lruCache.put(entry4);

        // Assert
        assertThat(lruCache.get(entry1.hashCode())).isEqualTo(entry1);
        assertThat(lruCache.getCacheSize()).isEqualTo(3);
        assertThat(lruCache.get(entry2.hashCode())).isEqualTo(null);
    }
}
