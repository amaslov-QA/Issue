package manager;


import domain.Issue;
import repository.Repository;

public class Manager {
    public Repository repository = new Repository();
    public Issue[] items = new Issue[0];

    public Manager(Repository repository) {
        this.repository = repository;
    }


    public void save(Issue issues) {
        repository.add(issues);
    }


    public Issue[] searchClose() {
        Issue[] result = new Issue[0];
        for (Issue issues : repository.getAll()) {
            if (issues.isClose() == true) {
                Issue[] tmp = new Issue[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }

        }
        return result;
    }

    public Issue[] searchOpen() {
        Issue[] result = new Issue[0];
        for (Issue issues : repository.getAll()) {
            if (issues.isClose() == false) {
                Issue[] tmp = new Issue[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public Issue[] searchByAuthor(String author) {
        Issue[] result = new Issue[0];
        for (Issue issues : repository.getAll()) {
            if (issues.getAuthor() == author) {
                Issue[] tmp = new Issue[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public Issue[] searchByAssignee(String assignee) {
        Issue[] result = new Issue[0];
        for (Issue issues : repository.getAll()) {
            if (issues.getAssignee().contains(assignee)) {
                Issue[] tmp = new Issue[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public void closeById(int id) {
        Issue[] result = new Issue[0];
        for (Issue issues : repository.getAll()) {

            if (issues.getId() == id && !issues.isClose()) {
                issues.setClose(true);
            }

        }
    }

    public void openById(int id) {
        Issue[] result = new Issue[0];
        for (Issue issues : repository.getAll()) {

            if (issues.getId() == id && issues.isClose()) {
                issues.setClose(false);
            }

        }
    }

    public Issue[] searchByLabelIssue(String labelIssue) {
        Issue[] result = new Issue[0];
        for (Issue issues : repository.getAll()) {
            if (issues.getLabelIssue().contains(labelIssue)) {
                Issue[] tmp = new Issue[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public void removeById(int id) {

        Issue[] result = new Issue[0];
        for (Issue issues : repository.getAll()) {
            if (issues.getId() == id) {


                repository.remove(issues);
            }


        }
    }
}


