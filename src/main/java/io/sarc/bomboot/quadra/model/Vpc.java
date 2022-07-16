package io.sarc.bomboot.quadra.model;

public class Vpc {
	private String id;
	private String CidrBlock;
	private boolean defaultVpc;
	private String dhcpOptionsId;
	private String state;
	private String tagName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDhcpOptionsId() {
		return dhcpOptionsId;
	}
	public void setDhcpOptionsId(String dhcpOptionsId) {
		this.dhcpOptionsId = dhcpOptionsId;
	}
	public String getCidrBlock() {
		return CidrBlock;
	}
	public void setCidrBlock(String cidrBlock) {
		CidrBlock = cidrBlock;
	}
	public boolean isDefaultVpc() {
		return defaultVpc;
	}
	public void setDefaultVpc(boolean defaultVpc) {
		this.defaultVpc = defaultVpc;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}