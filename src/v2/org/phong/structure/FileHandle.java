package v2.org.phong.structure;


import v2.org.phong.macro.CreationDistribution;
import v2.org.phong.macro.DesiredAccess;
import v2.org.phong.macro.FlagsAndAttributes;
import v2.org.phong.macro.ShareMode;
import v2.org.phong.structure.ex.SECURITY_ATTRIBUTES;

public class FileHandle extends Handle {
	
	private String fileName;
	private DesiredAccess desiredAccess;
	private ShareMode shareMode;
	private SECURITY_ATTRIBUTES securityAttributes;
	private CreationDistribution creationDistribution;
	private FlagsAndAttributes flagsAndAttributes;
	private FileHandle templateFile;
	
	public FileHandle() {
		// TODO Auto-generated constructor stub
	}
	
	public FileHandle(String fName, DesiredAccess dAccess, ShareMode sMode, 
			SECURITY_ATTRIBUTES sAttr, CreationDistribution cDistribution, 
			FlagsAndAttributes flagAndAttr, FileHandle tFile) {
		this.setFileName(fName);
		this.setDesiredAccess(dAccess);
		this.setShareMode(sMode);
		this.setSecurityAttributes(sAttr);
		this.setCreationDistribution(cDistribution);
		this.setFlagsAndAttributes(flagAndAttr);
		this.setTemplateFile(tFile);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public DesiredAccess getDesiredAccess() {
		return desiredAccess;
	}

	public void setDesiredAccess(DesiredAccess desiredAccess) {
		this.desiredAccess = desiredAccess;
	}

	public ShareMode getShareMode() {
		return shareMode;
	}

	public void setShareMode(ShareMode shareMode) {
		this.shareMode = shareMode;
	}

	public SECURITY_ATTRIBUTES getSecurityAttributes() {
		return securityAttributes;
	}

	public void setSecurityAttributes(SECURITY_ATTRIBUTES securityAttributes) {
		this.securityAttributes = securityAttributes;
	}

	public CreationDistribution getCreationDistribution() {
		return creationDistribution;
	}

	public void setCreationDistribution(CreationDistribution creationDistribution) {
		this.creationDistribution = creationDistribution;
	}

	public FlagsAndAttributes getFlagsAndAttributes() {
		return flagsAndAttributes;
	}

	public void setFlagsAndAttributes(FlagsAndAttributes flagsAndAttributes) {
		this.flagsAndAttributes = flagsAndAttributes;
	}

	public FileHandle getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(FileHandle templateFile) {
		this.templateFile = templateFile;
	}

}