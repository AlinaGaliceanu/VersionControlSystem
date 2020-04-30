package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;
import java.util.List;

public class VcsInvalidOperation extends VcsOperation{

    public VcsInvalidOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    @Override
    public int execute(Vcs vcs) {
        return ErrorCodeManager.VCS_BAD_CMD_CODE;
    }
}
