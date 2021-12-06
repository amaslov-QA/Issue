package repository;

import org.junit.jupiter.api.Test;
import domain.Issue;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private Repository repository = new Repository();

    private Issue first = new Issue(1, "First", true, "Komarov", Set.of("bug"), Set.of("No one"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issue second = new Issue(2, "Second", false, "Komarov", Set.of("bug"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issue third = new Issue(3, "Third", true, "Komarov", Set.of("bug"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issue fourth = new Issue(4, "Fourth", false, "Petrov", Set.of("documentation"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issue fifth = new Issue(5, "Fifth", true, "Petrov", Set.of("bug"), Set.of("Sidorov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issue sixth = new Issue(6, "Sixth", true, "Sidorov", Set.of("bug"), Set.of("Komarov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));

    @Test
    void getAll() {
        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issue[] expected = new Issue[]{first, second, third, fourth, fifth};
        Issue[] actual = repository.getAll().toArray(new Issue[0]);

        assertArrayEquals(expected, actual);
    }


    @Test
    void add() {
        repository.add(first);
        repository.add(second);

        Issue[] expected = new Issue[]{first, second};
        Issue[] actual = repository.getAll().toArray(new Issue[0]);

        assertArrayEquals(expected, actual);
    }

    @Test
    void remove() {
        repository.addAll(List.of(first, second, third, fourth, fifth));
        repository.remove(second);

        Issue[] expected = new Issue[]{first, third, fourth, fifth};
        Issue[] actual = repository.getAll().toArray(new Issue[0]);

        assertArrayEquals(expected, actual);
    }

}