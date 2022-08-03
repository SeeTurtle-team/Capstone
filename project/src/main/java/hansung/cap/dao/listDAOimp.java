package hansung.cap.dao;
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
    public int countBoard(listVO vo) {
    	int result = sqlSession.selectOne(namespace+".countBoard", vo);
		return result;
    }
    @Override
    public int ScountBoard1(String msg) {
    	int result = sqlSession.selectOne(namespace+".ScountBoard1", msg);
		return result;
    }
    @Override
    public int ScountBoard2(String msg) {
    	int result = sqlSession.selectOne(namespace+".ScountBoard2", msg);
		return result;
    }
    


}
