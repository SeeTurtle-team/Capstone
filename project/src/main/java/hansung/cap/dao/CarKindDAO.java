package hansung.cap.dao;

import java.util.List;
import java.util.Map;

import hansung.cap.project.CarKindVO;
import hansung.cap.project.FreeBoardVO;
import hansung.cap.project.listVO;


public interface CarKindDAO {
	public List<CarKindVO> Querrycar(String msg);  //차 이름으로 검색
	public List<CarKindVO> QuerryMaker(String msg);  // 제조사로 검색
	public List<CarKindVO> QuerryAll();
	public List<CarKindVO> paging(int paging);
	public int countBoard(CarKindVO vo);
	
	//페이징 관련
	public List<CarKindVO> listPage(int displayPost, int postNum) throws Exception;
	public List<CarKindVO> searchKind(int displayPost, int postNum, String key) throws Exception;
	public List<CarKindVO> searchMaker(int displayPost, int postNum, String key) throws Exception;
}
