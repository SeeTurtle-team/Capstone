package hansung.cap.project;

public class listVO {
	public int seq;
	public String model;
	public String time;
	public String imgUrl;
	
	
	
	public listVO() {
		this.seq =0;
		this.model ="";
		this.time ="";
		this.imgUrl="";
		
	}
	
	public listVO(int seq, String model, String time, String imgUrl){
		this.seq = seq;
		this.model = model;
		this.time = time;
		this.imgUrl = imgUrl;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}



	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
	
}
