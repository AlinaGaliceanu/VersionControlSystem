package vcs;

import utils.OperationType;

import java.util.ArrayList;
import java.util.List;

public final class LogOperation extends VcsOperation{
    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        List<Commit> commits = vcs.getCurrentBranch().getCommits();
        int size = commits.size();
        for (int i = 0; i < size; ++i) {
            vcs.getOutputWriter().write("Commit id: " +  commits.get(i).getId() + "\n");
            vcs.getOutputWriter().write("Message: " + commits.get(i).getMessage() + "\n");

            if (i != (size -1)) {
                vcs.getOutputWriter().write("\n");
            }
        }
        return 0;
    }
}
