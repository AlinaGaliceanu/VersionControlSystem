package vcs;

import utils.OperationType;

import java.util.ArrayList;

public final class RollbackOperation extends VcsOperation{
    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs){
        vcs.staging.clear();
        vcs.setActiveSnapshot(vcs.getHead().getFileSystemSnapshot().cloneFileSystem());
        return 0;
    }
}
