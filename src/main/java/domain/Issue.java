package domain;

import java.util.Collections;
import java.util.Set;

public class Issue<Label, assignee> {
    private int id;
    private String name;
    private boolean close;
    private String author;
    private Set<String> labelIssue;
    private Set<String> assignee;
    private Set<String> projects;
    private Set<String> milestones;
    private Set<String> tags;


    public Issue(int id, String name, boolean close, String author, Set<String> labelIssue, Set<String> assignee, Set<String> projects, Set<String> milestones, Set<String> tags) {
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

    public Set <String> getLabelIssue() {
        return labelIssue;
    }

    public void setLabelIssue(Set <String>  labelIssue) {
        this.labelIssue = labelIssue;
    }

    public Set  <String> getAssignee() {
        return assignee;
    }

    public void setAssignee(Set <String> assignee) {
        this.assignee = assignee;
    }

    public Set <String> getProjects() {
        return projects;
    }

    public void setProjects(Set <String> projects) {
        this.projects = projects;
    }

    public Set <String> getMilestones() {
        return milestones;
    }

    public void setMilestones(Set <String> milestones) {
        this.milestones = milestones;
    }

    public Set <String> getTags() {
        return tags;
    }

    public void setTags(Set <String> tags) {
        this.tags = tags;
    }
}

