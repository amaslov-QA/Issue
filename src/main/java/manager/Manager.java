package manager;

import domain.Issue;
import repository.Repository;

import java.util.*;
import java.util.function.Predicate;

public class Manager {
    private Repository repo = new Repository();

    public Manager(Repository repo) {  this.repo = repo; }
    public Manager() {    }

    Collection<Issue> issues = repo.findAll();

    public void addIssue(Issue issue) {
        repo.save(issue);
    }


    public Collection<Issue> openedIssues() {
        Collection<Issue> result = new LinkedList<>();
        for (Issue issue: issues) {
            if (issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public Collection<Issue> closedIssues() {
        Collection<Issue> result = new LinkedList<>();
        for (Issue issue: issues) {
            if (!issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }


    public Collection<Issue> findBy(Predicate<Issue> filter) {
        Collection<Issue> result = new LinkedList<>();
        for (Issue issue: issues) {
            if (filter.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

    public Collection<Issue> filterByAuthor(String text) {

        return findBy(issue -> issue.getAuthor().equals(text));

    }

    public Collection<Issue> filterByAssignee(String text) {
        return findBy(issue -> issue.getAssignee().contains(text));
    }

    public Collection<Issue> filterByLabel(String text) {
        return findBy(issue -> issue.getLabel().contains(text));
    }


    public void toOpenIssue(int id) {
        for (Issue issue: issues) {
            if (!issue.isOpen() && issue.getId() == id) {
                issue.setOpen(true);
            }
        }
    }

    public void toCloseIssue(int id) {
        for (Issue issue: issues) {
            if (issue.isOpen() && issue.getId() == id) {
                issue.setOpen(false);
            }
        }
    }
}