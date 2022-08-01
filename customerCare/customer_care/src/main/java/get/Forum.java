package get;

public class Forum {
	
	private String topic;
	private String description;
	private String datetime;
	private String name;
	private int cid;
	private int fid;
	
	Forum(String topic,String description,int cid,int fid, String datetime, String name){
		
		this.topic = topic;
		this.description = description;
		this.cid = cid;
		this.fid = fid;
		this.datetime = datetime;
		this.name = name;
		
	}
	
	public String getTopic() {
		return topic;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getCid() {
		return cid;
	}
	
	public int getFid() {
		return fid;
	}
	
	public String getDatetime() {
		return datetime;
	}
	
	public String getName() {
		return name;
	}
	
}
