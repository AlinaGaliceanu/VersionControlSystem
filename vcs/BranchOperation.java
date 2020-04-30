package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;
import java.util.List;

public final class BranchOperation extends VcsOperation {
    public BranchOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        List<Branch> list = vcs.getBranch();
        boolean ok = true;
        for (Branch b : list) {
           if(b.getName().equals(operationArgs.get(0))) {
               ok = false;
               return ErrorCodeManager.VCS_BAD_CMD_CODE;
           }
        }
        if (ok) {
//            vcs.setCurrentBranch();
            Branch aux = new Branch(operationArgs.get(0), vcs.getCurrentBranch().getCommits());
            vcs.setBranch(aux);
//            vcs.setCurrentBranch(aux);
        }
        return 0;
    }
}
