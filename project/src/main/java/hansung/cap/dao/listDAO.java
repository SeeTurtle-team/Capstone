package hansung.cap.dao;

import hansung.cap.project.MemberVO;
import hansung.cap.project.listVO;
import java.util.List;
import java.util.Map;

public interface listDAO {
	public List<listVO> QueryAll();
	public List<listVO> All();
	public List<listVO> QueryModel(String model);
	public List<listVO> QueryTime(String time);
	public List<listVO> paging(int paging);
	public List<listVO> searchName(String msg);
	public listVO selectOne(int a);
	public listVO selectImg(String url);
	public int countBoard(listVO vo);

	public List<listVO> listPage(int displayPost, int postNum) throws Exception;
	public List<listVO> searchModel(int displayPost, int postNum, String key) throws Exception;
	public List<listVO> searchTime(int displayPost, int postNum, String key) throws Exception;
}
