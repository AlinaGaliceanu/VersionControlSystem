package vcs;

import filesystem.FileSystemSnapshot;

public class Commit {
    private String Message;
    private int id;
    private FileSystemSnapshot fileSystemSnapshot;
   public Commit(String message, int id) {
       this.Message = message;
       this.id = id;
   }

   public String getMessage() {
       return Message;
   }

   public void setMessage(String message) {
       this.Message = message;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FileSystemSnapshot getFileSystemSnapshot() {
        return fileSystemSnapshot;
    }

    public void setFileSystemSnapshot(FileSystemSnapshot fileSystemSnapshot) {
        this.fileSystemSnapshot = fileSystemSnapshot;
    }
}
