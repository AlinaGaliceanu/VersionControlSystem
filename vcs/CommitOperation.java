package vcs;

import utils.ErrorCodeManager;
import utils.IDGenerator;
import utils.OperationType;

import java.util.ArrayList;
import java.util.List;

public final class CommitOperation extends VcsOperation {
    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super (type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        if (vcs.staging.size() == 0) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        } else {
            String str = operationArgs.get(1);
            if (operationArgs.size() > 3) {
                for (int i = 2; i < operationArgs.size(); ++i) {
                    str = str + " " + operationArgs.get(i);
                }
            }
            Commit commit = new Commit(str, IDGenerator.generateCommitID());
            commit.setFileSystemSnapshot(vcs.getActiveSnapshot());
            List<Commit> aux = vcs.getCurrentBranch().getCommits();
            aux.add(commit);
            vcs.getCurrentBranch().setCommits(aux);
            vcs.staging.clear();
            vcs.setActiveSnapshot(vcs.getActiveSnapshot().cloneFileSystem());
            vcs.setHead(commit);
        }
        return 0;
    }
}
