package hansung.cap.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import hansung.cap.project.MemberVO;
import hansung.cap.project.listVO;
import org.springframework.stereotype.Repository;

@Repository
public class listDAOimp implements listDAO {
	@Inject // �������踦 �ڵ����� ����(JAVA���� ����) ==@autowired (Spring���� ����)
    private SqlSession sqlSession;
   
   
    private static final String namespace = "hansung.cap.mapper.listMapper";
                                            //memberMapper.xml�� namespace��
   /*
    @Override
    public void insertList(listVO vo) {
        sqlSession.insert(namespace+".insertList", vo);
    }
    */
    @Override
    public List<listVO> QueryAll() {
    			
    	return sqlSession.selectList(namespace+".queryAll");
    }
    
    @Override
    public List<listVO> All(){
    	return sqlSession.selectList(namespace+".All");
    }
    
    @Override
    public List<listVO> QueryModel(String model){
    	return sqlSession.selectList(namespace+".queryModel",model);
    }
    
    @Override
    public List<listVO> QueryTime(String time){
    	return sqlSession.selectList(namespace+".queryTime",time);
    }
    
    public List<listVO> paging(int paging) {
		
    	return sqlSession.selectList(namespace+".paging", paging);
    }

    @Override
    public List<listVO> searchName(String msg) {
    	
		return sqlSession.selectList(namespace+".searchName",msg);
    }
    
    @Override
    public listVO selectOne(int a) {
    	return sqlSession.selectOne(namespace+".selectOne",a);
    }
    
    @Override
    public listVO selectImg(String url) {
    	return sqlSession.selectOne(namespace+".selectUrl",url);
    }
    
    @Override
    public int countBoard(listVO vo) {
    	int result = sqlSession.selectOne(namespace+".countBoard", vo);
		return result;
    }
    
    @Override
	public List<listVO> listPage(int displayPost, int postNum) throws Exception{
		HashMap data = new HashMap();
		
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		
		return sqlSession.selectList(namespace+".listPage",data);
	}
	
	@Override
	public List<listVO> listPageTime(int displayPost, int postNum) throws Exception{
		HashMap data = new HashMap();
		
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		
		return sqlSession.selectList(namespace+".listPagetime",data);
	}
	
	@Override
	public List<listVO> searchModel(int displayPost, int postNum,String key) throws Exception{
		HashMap data = new HashMap();
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		data.put("key",key);

		return sqlSession.selectList(namespace+".searchSelect",data);
	}
	
	@Override
	public List<listVO> searchTime(int displayPost, int postNum, String key) throws Exception{
		HashMap data = new HashMap();
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		data.put("key",key);

		return sqlSession.selectList(namespace+".time",data);
	}
}
