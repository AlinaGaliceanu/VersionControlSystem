package vcs;

import utils.OperationType;

import java.util.ArrayList;

public final class StatusOperation extends VcsOperation {
    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        vcs.getOutputWriter().write("On branch: " + vcs.getCurrentBranch().getName() + "\n");
        vcs.getOutputWriter().write("Staged changes:" + "\n");
       for (int i = 0; i < vcs.staging.size(); ++i) {
           vcs.getOutputWriter().write("\t" + vcs.staging.get(i) + "\n");
       }
        return 0;
    }
}
