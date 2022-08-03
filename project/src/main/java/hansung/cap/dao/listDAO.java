package hansung.cap.dao;

import hansung.cap.project.MemberVO;
import hansung.cap.project.listVO;
import java.util.List;
import java.util.Map;

public interface listDAO {
	public List<listVO> QueryAll();
	public List<listVO> QueryModel(String model);
	public List<listVO> QueryTime(String time);
	public List<listVO> paging(int paging);
	public List<listVO> searchName(String msg);
	public listVO selectOne(int a);
	public int countBoard(listVO vo);
	public int ScountBoard1(String msg);
	public int ScountBoard2(String msg);
}
