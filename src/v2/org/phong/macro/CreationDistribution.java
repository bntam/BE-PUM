package v2.org.phong.macro;

public class CreationDistribution {
	int CREATE_NEW = 1;
	int CREATE_ALWAYS = 2;
	int OPEN_EXISTING = 3;
	int OPEN_ALWAYS = 4;
	int TRUNCATE_EXISTING = 5;

	private int creationDistribution;

	public int getCreationDistribution() {
		return creationDistribution;
	}

	public void setCreationDistributionWithCreateNew() {
		this.creationDistribution = CREATE_NEW;
	}

	public void setCreationDistributionWithCreateAlways() {
		this.creationDistribution = CREATE_ALWAYS;
	}

	public void setCreationDistributionWithOpenExisting() {
		this.creationDistribution = OPEN_EXISTING;
	}

	public void setCreationDistributionWithOpenAlways() {
		this.creationDistribution = OPEN_ALWAYS;
	}

	public void setCreationDistributionWithTruncateExisting() {
		this.creationDistribution = TRUNCATE_EXISTING;
	}
}
