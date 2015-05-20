package v2.org.phong.structure;

import java.io.File;
import java.util.LinkedList;

public class SearchHandle extends Handle {

	private File[] findFile;
	private LinkedList<FileHandle> findFileHandle;

	public SearchHandle() {
		// TODO Auto-generated constructor stub
	}

	public SearchHandle(File[] fFile, LinkedList<FileHandle> fFileHandle) {
		this.setFindFile(fFile);
		this.setFindFileHandle(fFileHandle);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	public File[] getFindFile() {
		return findFile;
	}

	public void setFindFile(File[] findFile) {
		this.findFile = findFile;
	}

	public LinkedList<FileHandle> getFindFileHandle() {
		return findFileHandle;
	}

	public void setFindFileHandle(LinkedList<FileHandle> findFileHandle) {
		this.findFileHandle = findFileHandle;
	}
}
