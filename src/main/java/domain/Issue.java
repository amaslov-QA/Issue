package domain;

import java.util.Collections;
import java.util.Set;

public class Issue {
    private int id;
    private String name;
    private boolean close;
    private String author;
    private Set labelIssue;
    private Set assignee;
    private Set projects;
    private Set milestones;
    private Set tags;


    public Issue(int id, String name, boolean close, String author, Set labelIssue, Set assignee, Set projects, Set milestones, Set tags) {
        this.id = id;
        this.name = name;
        this.close = close;
        this.author = author;
        this.labelIssue = labelIssue;
        this.assignee = assignee;
        this.projects = projects;
        this.milestones = milestones;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set getLabelIssue() {
        return labelIssue;
    }

    public void setLabelIssue(Set labelIssue) {
        this.labelIssue = labelIssue;
    }

    public Set getAssignee() {
        return assignee;
    }

    public void setAssignee(Set assignee) {
        this.assignee = assignee;
    }

    public Set getProjects() {
        return projects;
    }

    public void setProjects(Set projects) {
        this.projects = projects;
    }

    public Set getMilestones() {
        return milestones;
    }

    public void setMilestones(Set milestones) {
        this.milestones = milestones;
    }

    public Set getTags() {
        return tags;
    }

    public void setTags(Set tags) {
        this.tags = tags;
    }
}

