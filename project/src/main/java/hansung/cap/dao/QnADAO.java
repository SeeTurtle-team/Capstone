package hansung.cap.dao;

import java.util.List;

import hansung.cap.project.FreeBoardVO;
import hansung.cap.project.QnAVO;

public interface QnADAO {
	public void enrollQnA(QnAVO vo);
	public List<QnAVO> QueryAll();
	public List<QnAVO> search(String s);
	public List<QnAVO> searchTitle(String s);
	public List<QnAVO> searchUser(String s);
	public QnAVO read(int s);
	public void delete(int s);
	public void modify(QnAVO vo);
	public List<QnAVO> paging(int paging);
	public int countBoard(QnAVO vo);

	public List<QnAVO> listPage(int displayPost, int postNum) throws Exception;
	public List<QnAVO> searchTitle(int displayPost, int postNum, String key) throws Exception;
	public List<QnAVO> searchUser(int displayPost, int postNum, String key) throws Exception;
	public List<QnAVO> searchAll(int displayPost, int postNum, String key) throws Exception;
	
}
