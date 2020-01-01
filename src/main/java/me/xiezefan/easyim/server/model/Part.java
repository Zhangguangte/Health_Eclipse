package me.xiezefan.easyim.server.model;

public class Part {
    private String id;
    private String partName;
    private String relatedOrgans;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getRelatedOrgans() {
		return relatedOrgans;
	}
	public void setRelatedOrgans(String relatedOrgans) {
		this.relatedOrgans = relatedOrgans;
	}
	@Override
	public String toString() {
		return "Part [id=" + id + ", partName=" + partName + ", relatedOrgans=" + relatedOrgans + "]";
	}


 
}
