package hansung.cap.dao;

import java.util.HashMap;
import java.util.List;


import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import hansung.cap.project.FreeBoardVO;
import hansung.cap.project.QnAVO;
import org.springframework.stereotype.Repository;

@Repository
public class QnADAOImp implements QnADAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "hansung.cap.mapper.QnAMapper";
	
	@Override
	public void enrollQnA(QnAVO vo) {
		sqlSession.insert(namespace+".insertQnA",vo);
	}
	
	@Override
	public List<QnAVO> QueryAll(){
		return sqlSession.selectList(namespace+".queryAll");
	}
	
	@Override
	public List<QnAVO> search(String s){
		return sqlSession.selectList(namespace+".search",s);
	}
	
	@Override
	public List<QnAVO> searchTitle(String s){
		return sqlSession.selectList(namespace+".searchTitle",s);
	}
	
	@Override
	public List<QnAVO> searchUser(String s){
		return sqlSession.selectList(namespace+".searchUser",s);
	}
	@Override
	public QnAVO read(int s){
		return sqlSession.selectOne(namespace+".read",s);
	}
	
	@Override
	public void delete(int s) {
		sqlSession.delete(namespace+".delete",s);
	}
	
	@Override
	public void modify(QnAVO vo) {
		sqlSession.update(namespace+".modify",vo);
	}
	
	public List<QnAVO> paging(int paging){
		return sqlSession.selectList(namespace+".paging", paging);
	}
	
	@Override
	public int countBoard(QnAVO vo) {
		int result = sqlSession.selectOne(namespace+".countBoard", vo);
		return result;
	}
	
	@Override
	public List<QnAVO> listPage(int displayPost, int postNum) throws Exception{
		HashMap data = new HashMap();
		
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		
		return sqlSession.selectList(namespace+".listPage",data);
	}
	
	@Override
	public List<QnAVO> searchTitle(int displayPost, int postNum,String key) throws Exception{
		HashMap data = new HashMap();
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		data.put("key",key);

		return sqlSession.selectList(namespace+".searchSelect",data);
	}
	
	@Override
	public List<QnAVO> searchUser(int displayPost, int postNum, String key) throws Exception{
		HashMap data = new HashMap();
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		data.put("key",key);

		return sqlSession.selectList(namespace+".user",data);
	}
	
	@Override
	public List<QnAVO> searchAll(int displayPost, int postNum, String key) throws Exception{
		HashMap data = new HashMap();
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		data.put("key",key);

		return sqlSession.selectList(namespace+".All",data);
	}
}
