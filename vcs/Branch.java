package vcs;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String name;
    private List<Commit> commits = new ArrayList<>();

    public Branch(String name, List<Commit> commits) {
        this.name = name;
        this.setCommits(commits);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }
}
