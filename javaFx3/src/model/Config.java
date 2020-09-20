package model;

public class Config {

	private String url = "localhost";
	private String port = "3306";
	private String bdd = "vide_grenier";
	private String mdp = "root";
	private String pswd = "";

//one
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getBdd() {
		return bdd;
	}
	public void setBdd(String bdd) {
		this.bdd = bdd;
	}
}
