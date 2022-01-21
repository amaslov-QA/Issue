package manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.Issue;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssuesManagerTest {
    private Manager manager = new Manager();

    Issue issues1 = new Issue(1, "Iss1", 10, 45, true, "Lena", new HashSet<>(List.of("status", "theme:top", "theme:top")), new HashSet<>(List.of("Oleg")));
    Issue issues2 = new Issue(2, "Iss2", 12, 12, false, "Marina", new HashSet<>(List.of("status:blocked", "status:new", "theme")), new HashSet<>(List.of("Vic")));
    Issue issues3 = new Issue(3, "Iss3", 11, 48, true, "Max", new HashSet<>(List.of("component", "type:bug", "type:bug")), new HashSet<>(List.of("KOP")));
    Issue issues4 = new Issue(4, "Iss4", 22, 30, false, "Den", new HashSet<>(List.of("type:task", "type:bug")), new HashSet<>(List.of("Vic")));
    Issue issues5 = new Issue(5, "Iss5", 21, 8, false, "Den", new HashSet<>(List.of("type:bug")), new HashSet<>(List.of("Vic")));

    @BeforeEach
    public void setUp() {
        manager.addIssue(issues1);
        manager.addIssue(issues2);
        manager.addIssue(issues3);
        manager.addIssue(issues4);
        manager.addIssue(issues5);
    }

    @Test
    void shouldShowOpenedIssues() {
        Collection<Issue> actual = manager.openedIssues();
        Collection<Issue> expected = List.of(issues1, issues3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowClosedIssues() {
        Collection<Issue> actual = manager.closedIssues();
        Collection<Issue> expected = List.of(issues2, issues4, issues5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowAuthorIssues() {
        Collection<Issue> actual = manager.filterByAuthor("Lena");
        Collection<Issue> expected = List.of(issues1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowNothingAuthor() {
        Collection<Issue> actual = manager.filterByAuthor("lena");
        Collection<Issue> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowAssigneeIssues() {
        Collection<Issue> actual = manager.filterByAssignee("Vic");
        Collection<Issue> expected = List.of(issues2, issues4, issues5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowNothingAssignee() {
        Collection<Issue> actual = manager.filterByAssignee("vic");
        Collection<Issue> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowLabelIssues() {
        Collection<Issue> actual = manager.filterByLabel("type:bug");
        Collection<Issue> expected = List.of(issues3, issues4, issues5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowNothingLabel() {
        Collection<Issue> actual = manager.filterByLabel("type");
        Collection<Issue> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowOpenIssues() {
        manager.toOpenIssue(2);
        Collection<Issue> actual = manager.openedIssues();
        Collection<Issue> expected = List.of(issues1, issues2, issues3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowOpenOpenedIssues() {
        manager.toOpenIssue(1);
        Collection<Issue> actual = manager.openedIssues();
        Collection<Issue> expected = List.of(issues1, issues3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowCloseIssues() {
        manager.toCloseIssue(3);
        Collection<Issue> actual = manager.closedIssues();
        Collection<Issue> expected = List.of(issues2, issues3, issues4, issues5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowCloseClosedIssues() {
        manager.toCloseIssue(2);
        Collection<Issue> actual = manager.closedIssues();
        Collection<Issue> expected = List.of(issues2, issues4, issues5);
        assertEquals(expected, actual);
    }
}