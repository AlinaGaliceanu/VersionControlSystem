package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;
import java.util.List;

public final class CheckoutOperation extends  VcsOperation {
    public CheckoutOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        if (vcs.staging.size() != 0) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }

        List<Branch> branches = vcs.getBranch();
        boolean okB = false;
        for (Branch b : branches) {
            if (b.getName().equals(operationArgs.get(0))) {
                okB = true;
                int size = b.getCommits().size();
                if (size > 0) {
                    vcs.setHead(b.getCommits().get(size - 1));
                }
                vcs.setCurrentBranch(b);
            }
        }

        boolean okC = false;
        List<Commit> commits = vcs.getCurrentBranch().getCommits();
        int id = 0;
//        int tmp = operationArgs.get(0).charAt(0);
//        System.out.println(operationArgs.get(0));
//        if (tmp > 0 && tmp  < 100) {
        if (operationArgs.get(0).equals("-c")) {
            for (Commit c : commits) {
                if (operationArgs.get(1).equals(c.getId() + "")) {
                    okC = true;
                    vcs.setHead(c);
                    vcs.setActiveSnapshot(c.getFileSystemSnapshot().cloneFileSystem());
//                    vcs.getActiveSnapshot().cloneFileSystem();
                    id = c.getId();
                }
            }
        if (!okC) {
            return ErrorCodeManager.VCS_BAD_PATH_CODE;
        }

        List<Commit> aux = new ArrayList<>();
        for (Commit c : commits) {
            if (c.getId() <= id) {
                aux.add(c);
            }
        }
        vcs.getCurrentBranch().setCommits(aux);
        }
            if (!okB && !okC) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }

//        if (!okB && )
        return 0;
    }
}
