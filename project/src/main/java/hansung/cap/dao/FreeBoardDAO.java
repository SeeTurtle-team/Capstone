package hansung.cap.dao;

import java.util.List;

import hansung.cap.project.FreeBoardVO;

public interface FreeBoardDAO {
	public List<FreeBoardVO> QueryAll();
	public void insert(FreeBoardVO vo);
	public List<FreeBoardVO> Search(String s);
	public List<FreeBoardVO> SearchTitle(String s);
	public List<FreeBoardVO> SearchUser(String s);
	public FreeBoardVO Read(int a);
	public void deleteFree(int a);
	public void modify(FreeBoardVO vo);
	public List<FreeBoardVO> paging(int paging);
	public int countBoard(FreeBoardVO vo);
	
	//페이징 관련
	public List<FreeBoardVO> listPage(int displayPost, int postNum) throws Exception;
	public List<FreeBoardVO> searchTitle(int displayPost, int postNum, String key) throws Exception;
	public List<FreeBoardVO> searchUser(int displayPost, int postNum, String key) throws Exception;
	public List<FreeBoardVO> searchAll(int displayPost, int postNum, String key) throws Exception;
}
