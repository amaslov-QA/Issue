package repository;

import domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Repository {
    private List<Issue> items = new ArrayList<>();

    public Collection<Issue> getAll() {
        return this.items;
    }

    public boolean addAll(Collection<? extends Issue> items) {
        return this.items.addAll(items);
    }


    public Issue getById(int id) {
        for (Issue item : items) {
            if (item.getId() == id) {
                return item;
            }

        }
        return null;

    }

    public boolean add(Issue item) {
        return items.add(item);
    }

    public boolean remove(Issue item) {
        return items.remove(item);
    }

    public boolean removeAll(Collection<? extends Issue> items) {
        return this.items.removeAll(items);
    }

    public void closeById(int id) {
        Issue[] result = new Issue[0];
        for (Issue issues : getAll()) {

            if (issues.getId() == id && !issues.isClose()) {
                issues.setClose(true);
            }

        }
    }

    public void openById(int id) {
        Issue[] result = new Issue[0];
        for (Issue issues : getAll()) {

            if (issues.getId() == id && issues.isClose()) {
                issues.setClose(false);
            }

        }


    }
}

