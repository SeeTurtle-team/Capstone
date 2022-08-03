package hansung.cap.project;

public class FreeBoardVO {
	

	public int seq;
	public String title;
	public String content;
	public String userId;
	public String time;
	public byte[] image;
	
	public FreeBoardVO() {
		this.seq=0;
		this.title="";
		this.content="";
		this.userId="";
		this.time="";
		this.image=null;
	}
	
	public FreeBoardVO(int Seq,String title,String content,String userId,String time,byte[] image) {
		this.seq=Seq;
		this.title=title;
		this.content=content;
		this.userId=userId;
		this.time=time;
		this.image =image;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int Seq) {
		this.seq = Seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	

}
