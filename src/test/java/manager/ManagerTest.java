package manager;

import org.junit.jupiter.api.Test;
import domain.Issue;
import repository.Repository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private Repository repository = new Repository();
    private Manager manager = new Manager(repository);

    private Issue first = new Issue(1, "First", true, "Komarov", Set.of("bug"), Set.of("No one"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issue second = new Issue(2, "Second", false, "Komarov", Set.of("bug"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issue third = new Issue(3, "Third", true, "Komarov", Set.of("bug"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issue fourth = new Issue(4, "Fourth", false, "Petrov", Set.of("documentation"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issue fifth = new Issue(5, "Fifth", true, "Petrov", Set.of("bug"), Set.of("Sidorov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issue sixth = new Issue(6, "Sixth", true, "Sidorov", Set.of("bug"), Set.of("Komarov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));


    @Test
    void shouldSearchClose() {
        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issue[] expected = new Issue[]{first, third, fifth};
        Issue[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchOpen() {
        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issue[] expected = new Issue[]{second, fourth};
        Issue[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthor() {
        String author = "Komarov";

        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issue[] expected = new Issue[]{first, second, third};
        Issue[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);

    }


    @Test
    void searchByAuthorIfOne() {
        String author = "Sidorov";

        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));

        Issue[] expected = new Issue[]{sixth};
        Issue[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);

    }

    @Test
    void searchByAuthorIfNoOne() {
        String author = "Marina";

        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);

    }


    @Test
    void searchByAssignee() {
        String assignee = "Petrov";

        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issue[] expected = new Issue[]{second, third, fourth};
        Issue[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAssigneeIfOne() {
        String assignee = "Komarov";

        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));

        Issue[] expected = new Issue[]{sixth};
        Issue[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAssigneeIfNoOne() {
        String assignee = "Marina";

        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }


    @Test
    void closeById() {
        int idNecessaryToClose = 2;
        repository.addAll(List.of(first, second, third, fourth, fifth));
        manager.closeById(idNecessaryToClose);

        Issue[] expected = new Issue[]{first, second, third, fifth};
        Issue[] actual = manager.searchClose();
        assertArrayEquals(expected, actual);
    }

    @Test
    void closeByIdIfAlreadyClosed() {
        int idNecessaryToClose = 1;
        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));
        manager.closeById(idNecessaryToClose);

        Issue[] expected = new Issue[]{first, third, fifth, sixth};
        Issue[] actual = manager.searchClose();
        assertArrayEquals(expected, actual);
    }

    @Test
    void openById() {
        int idNecessaryToOpen = 3;
        repository.addAll(List.of(first, second, third, fourth, fifth));
        manager.openById(idNecessaryToOpen);

        Issue[] expected = new Issue[]{second, third, fourth};
        Issue[] actual = manager.searchOpen();
        assertArrayEquals(expected, actual);
    }

    @Test
    void openByIdIfAlreadyOpen() {
        int idNecessaryToOpen = 4;
        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));
        manager.openById(idNecessaryToOpen);

        Issue[] expected = new Issue[]{second, fourth};
        Issue[] actual = manager.searchOpen();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByLabelIssue() {
        String labelIssue = "bug";

        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issue[] expected = new Issue[]{first, second, third, fifth};
        Issue[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void searchByLabelIssueIfOne() {
        String labelIssue = "documentation";

        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));

        Issue[] expected = new Issue[]{fourth};
        Issue[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchByLabelIssueIfNoOne() {
        String labelIssue = "question";

        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveById() {


        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));
        repository.remove(third);

        Issue[] expected = new Issue[]{first, second, fourth, fifth, sixth};
        Issue[] actual = repository.getAll().toArray(new Issue[0]);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveByIdWhichIsNot() {


        repository.addAll(List.of(first, second, fourth, fifth, sixth));
        repository.remove(third);

        Issue[] expected = new Issue[]{first, second, fourth, fifth, sixth};
        Issue[] actual = repository.getAll().toArray(new Issue[0]);

        assertArrayEquals(expected, actual);

    }


    @Test
    void shouldSearchCloseIfOneIn() {
        repository.addAll(List.of(first));

        Issue[] expected = new Issue[]{first};
        Issue[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchOpenIfOneIn() {
        repository.addAll(List.of(second));

        Issue[] expected = new Issue[]{second};
        Issue[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthorIOneIn() {
        String author = "Komarov";

        repository.addAll(List.of(second));

        Issue[] expected = new Issue[]{second};
        Issue[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);

    }

    @Test
    void searchByAssigneeIfOneIn() {
        String assignee = "Petrov";

        repository.addAll(List.of(second));

        Issue[] expected = new Issue[]{second};
        Issue[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }

    @Test
    void closeByIdIfOneIn() {
        int idNecessaryToClose = 1;

        repository.addAll(List.of(first));

        manager.closeById(idNecessaryToClose);

        Issue[] expected = new Issue[]{first};
        Issue[] actual = manager.searchClose();
        assertArrayEquals(expected, actual);
    }

    @Test
    void openByIdIfOneIn() {
        int idNecessaryToOpen = 2;

        repository.addAll(List.of(second));

        manager.openById(idNecessaryToOpen);

        Issue[] expected = new Issue[]{second};
        Issue[] actual = manager.searchOpen();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByLabelIssueIfOneIn() {
        String labelIssue = "bug";

        repository.addAll(List.of(first));

        Issue[] expected = new Issue[]{first};
        Issue[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveByIdIfOneIn() {

        repository.addAll(List.of(first));

        repository.remove(first);

        Issue[] expected = new Issue[]{};
        Issue[] actual = repository.getAll().toArray(new Issue[0]);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchCloseWhichNotIfOneIn() {
        repository.addAll(List.of(second));

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchOpenWhichNotIfOneIn() {
        repository.addAll(List.of(first));

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthorWhichNotIfOneIn() {
        String author = "Petrov";

        repository.addAll(List.of(second));

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);

    }

    @Test
    void searchByAssigneeWhichNotIfOneIn() {
        String assignee = "Petrov";

        repository.addAll(List.of(first));

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }

    @Test
    void closeByIdWhichNotIfOneIn() {
        int idNecessaryToClose = 2;

        repository.addAll(List.of(first));

        manager.closeById(idNecessaryToClose);

        Issue[] expected = new Issue[]{first};
        Issue[] actual = manager.searchClose();
        assertArrayEquals(expected, actual);
    }

    @Test
    void openByIdWhichNotIfOneIn() {
        int idNecessaryToOpen = 1;

        repository.addAll(List.of(second));

        manager.openById(idNecessaryToOpen);

        Issue[] expected = new Issue[]{second};
        Issue[] actual = manager.searchOpen();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByLabelIssueWhichNotIfOneIn() {
        String labelIssue = "bug";

        repository.addAll(List.of(fourth));

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveByIdWhichNotIfOneIn() {

        repository.addAll(List.of(first));

        repository.remove(second);

        Issue[] expected = new Issue[]{first};
        Issue[] actual = repository.getAll().toArray(new Issue[0]);

        assertArrayEquals(expected, actual);

    }


    @Test
    void shouldSearchCloseInEmpty() {

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchOpenInEmpty() {

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthorInEmpty() {
        String author = "Komarov";


        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);

    }

    @Test
    void searchByAssigneeInEmpty() {
        String assignee = "Petrov";


        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }

    @Test
    void closeByIdInEmpty() {
        int idNecessaryToClose = 2;

        manager.closeById(idNecessaryToClose);

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchClose();
        assertArrayEquals(expected, actual);
    }

    @Test
    void openByIdInEmpty() {
        int idNecessaryToOpen = 3;

        manager.openById(idNecessaryToOpen);

        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchOpen();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByLabelIssueInEmpty() {
        String labelIssue = "bug";


        Issue[] expected = new Issue[]{};
        Issue[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveByIdInEmpty() {


        repository.remove(third);

        Issue[] expected = new Issue[]{};
        Issue[] actual = repository.getAll().toArray(new Issue[0]);

        assertArrayEquals(expected, actual);

    }

}