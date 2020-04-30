package vcs;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import javafx.scene.layout.BackgroundRepeat;
import utils.IDGenerator;
import utils.OperationType;
import utils.OutputWriter;
import utils.Visitor;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.List;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    public List<String> staging = new ArrayList<>();
    private Branch currentBranch;
    private List<Branch> branch = new ArrayList<>();
    private  Commit head;
    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);
        Commit firstCommit = new Commit("First commit", IDGenerator.generateCommitID());
        firstCommit.setFileSystemSnapshot(activeSnapshot.cloneFileSystem());
//        firstCommit.getFileSystemSnapshot().cloneFileSystem();
        List<Commit> commits = new ArrayList<>();
        commits.add(firstCommit);
        Branch masterBranch = new Branch("master", commits);
        branch.add(masterBranch);
        currentBranch = masterBranch;
        branch.add(masterBranch);
        head = firstCommit;

        //TODO other initialisations
    }



    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        //TODO
        return vcsOperation.execute(this);
//        return 0;
    }

    //TODO methods through which vcs operations interact with this

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    public Branch getCurrentBranch() {
        return currentBranch;
    }

    public void setCurrentBranch(Branch currentBranch) {
        this.currentBranch = currentBranch;
    }

    public List<Branch> getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch.add(branch);
    }

    public FileSystemSnapshot getActiveSnapshot() {
        return activeSnapshot;
    }

    public void setActiveSnapshot(FileSystemSnapshot activeSnapshot) {
        this.activeSnapshot = activeSnapshot;
    }

    public Commit getHead() {
        return head;
    }

    public void setHead(Commit head) {
        this.head = head;
    }
}
