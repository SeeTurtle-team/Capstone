package hansung.cap.project;

public class listVO {
	public String carNum; //�� ��ȣ
	public String carKind; //�� ��
	public String carColor; //�� ��
	public String carMaster; //�� ����
	public String inTime; // �Խ� �ð�
	public String outTime;// ������ �ð�
	
	
	public listVO() {
		this.carNum ="";
		this.carKind ="";
		this.carColor ="";
		this.carMaster ="";
		this.inTime ="";
		this.outTime="";
		
	}
	
	public listVO(String carNum, String carKind, String carColor, String carMaster, String inTime , String outTime) {
		this.carNum =carNum;
		this.carKind =carKind;
		this.carColor =carColor;
		this.carMaster =carMaster;
		this.inTime =inTime;
		this.outTime=outTime;
		
	}
	
	
	
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getCarKind() {
		return carKind;
	}
	public void setCarKind(String carKind) {
		this.carKind = carKind;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public String getCarMaster() {
		return carMaster;
	}
	public void setCarMaster(String carMaster) {
		this.carMaster = carMaster;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
}
