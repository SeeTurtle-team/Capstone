package hansung.cap.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import hansung.cap.dao.CarKindDAO;
import hansung.cap.dao.qCommentDAO;
import hansung.cap.dao.FreeBoardDAO;
import hansung.cap.dao.MemberDAO;
import hansung.cap.dao.MemberDAOImp;
import hansung.cap.dao.QnADAO;
import hansung.cap.dao.fCommentDAO;
import hansung.cap.dao.listDAO;
import hansung.cap.dao.listDAOimp;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private listDAO lDao; //차량 리스트 DAO
	@Inject
	private MemberDAO mDao; //회원가입 및 로그인 DAO
	@Inject 
	private QnADAO qDao; //QnA DAO
	@Inject
	private CarKindDAO cDao; //차 데이터 확인 DAO
	@Inject
	private qCommentDAO qrDao; //QnA 리플 관련 DAO
	@Inject
	private fCommentDAO frDao; //Freeboard 리플 관련 DAO
	@Inject
	private FreeBoardDAO fDao;//자유 게시판 관련 DAO
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	//---------------------------------인덱스 화면-------------------------------------//
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("first page return");
		
		HttpSession session=httpServletRequest.getSession();
		
		String user_id=(String)session.getAttribute("userId");;
		System.out.println("----------------------------------"+user_id);
		model.addAttribute("id",user_id);
		
		String login;
		String option = httpServletRequest.getParameter("option");
		
		if(user_id==null) {
			System.out.println("로그인이 되지 않았습니다");
			login = "Login";
			model.addAttribute("login", "login");
		}
		else {
			login = "LogOut";
			System.out.println(user_id+"????");
			model.addAttribute("login",user_id);
			model.addAttribute("logOut","logOut");
		}
		
		if(option==null) {}
		else if(option.equals("logOut")){
			System.out.println("log Out!!!");
			session.invalidate();
			System.out.println("session delete");

			model.addAttribute("login","login");
			model.addAttribute("logOut","");
			return "index";
		}
		
		return "index";
	}
	
	//---------------------------------로그인 페이지-------------------------------------//
	@RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
	public String login(HttpServletRequest httpServletRequest, Model model, String id, String pw) {
		
		HttpSession session=httpServletRequest.getSession();
		
		System.out.println(id);
		String user_id=(String)session.getAttribute("userId");;
		System.out.println("----------------------------------"+user_id);
		if(user_id!=null) {  //로그인이 될 상황일시
			model.addAttribute("login",user_id);
			return "index";
		}
		
		MemberVO mVo=new MemberVO();
		
		//String option=httpServletRequest.getParameter("option");
		//System.out.println(httpServletRequest.getParameter("userId")+","+httpServletRequest.getParameter("userPw"));
		//System.out.println(option);
		System.out.println();
		
		if(id==null) {
			System.out.println("option null : loginpage loading");
		}
		
		else  {
			mVo.userId=id;
			mVo.userPw=pw;
			MemberVO login = new MemberVO();
			login=mDao.login(mVo);
			String signal = "no";
			if(login == null) {
				System.out.println("fail");
				model.addAttribute("failFlag",signal);
			}
			else {
				session.setAttribute("userId", mVo.userId);
				user_id=(String)session.getAttribute("userId");
				System.out.println(user_id);
				model.addAttribute("id",user_id);
				model.addAttribute("login",user_id);
				
				model.addAttribute("logOut","logOut");
				return "index";
			}
		}
		System.out.println(httpServletRequest.getParameter("id"));
		return "login";
	}
	
	//---------------------------------회원가입-------------------------------------//
	@RequestMapping(value = "/create", method = {RequestMethod.POST,RequestMethod.GET})
	public String create(HttpServletRequest httpServletRequest, Model model, String userId, String userPw, String userName, String userEmail, String userQuestion){
		HttpSession session=httpServletRequest.getSession();
		
		String user_id=(String)session.getAttribute("userId");;
		System.out.println("----------------------------------"+user_id);
		if(user_id!=null) {   //로그인이 되어 있을 시 
			return "index";
		}
		
		MemberVO mVo=new MemberVO();
		
		if(userId==null) {}
		else{
			mVo.userId=userId;
			mVo.userPw=userPw;
			mVo.userName=userName;
			mVo.userEmail=userEmail;
			mVo.userQuestion=userQuestion;
			int result = mDao.idCheck(mVo);
			
			if(mVo.userId.equals("manage1234")){
				model.addAttribute("fail","manage");
				return "create";
			}
			
			if(result == 0) {					//중복이 아니라면 쿼리에서 0 반환
				mDao.InsertId(mVo);
				String s = "login";
				model.addAttribute("loginAgain",s);
				System.out.println("create account success");
				return "login";
			}
			else if(result == 1) {				//중복이라면 쿼리에서 1 반환
				String r = "redundancy";
				model.addAttribute("redundancy", r);
				System.out.println("create account fail : rename id");
				return "create";
			}
		}
		
		return "create";
	}
	
	//---------------------------------비밀번호 찾는 페이지-------------------------------------//
	@RequestMapping(value = "/forgot", method = {RequestMethod.POST,RequestMethod.GET})
	public String forgot(HttpServletRequest httpServletRequest, Model model, String forgot_id, String forgot_email, String forgot_answer){
		HttpSession session=httpServletRequest.getSession();
		
		String user_id=(String)session.getAttribute("userId");;
		System.out.println("----------------------------------"+user_id);
		if(user_id!=null) {  //로그인 되어있을시
			return "index";
		}
		
		MemberVO mVo = new MemberVO();
		System.out.println("Post : "+forgot_id);
		System.out.println("Post : "+forgot_email);
		System.out.println("Post : "+forgot_answer);
		if(forgot_id==null) {
			
		}
		else {
			mVo.userId=forgot_id;
			mVo.userEmail=forgot_email;
			mVo.userQuestion=forgot_answer;
			
			String userPw=mDao.forgot(mVo);
			//System.out.println(userPw);
			
			if(userPw == null) {  //해당 답변에 해당하는 비밀번호가 없을 때
				System.out.println("fail");
				String signal="no";
				model.addAttribute("failFlag",signal);
				
			}
			else {  //비밀번호 알려주는 페이지로 return
				model.addAttribute("userPw", userPw);
				return "forgotRecord";
			}
		}
		
		return "forgot";
	}

	//---------------------------------CCTV 분석 페이지-------------------------------------//
	@RequestMapping(value = "/carList", method = RequestMethod.GET)
	public String carList(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("carList return");
		
		HttpSession session=httpServletRequest.getSession();
		listVO lVo=new listVO();
		int listSize = lDao.countBoard(lVo);	//리스트 글 개수
		int pageSize;							//필요한 페이지 수 및 마지막 페이지 번호
		
		if(listSize%10==0) {					//페이지 사이즈 계산
			pageSize = listSize/10;
		} else {
			pageSize = listSize/10 + 1;
		}
		int blockSize;							//블록(1 : 1~3, 2 : 2~4..)
		if(pageSize<3) {						//블록 사이즈 계산
			blockSize = 1;
		}
		else {
			blockSize = pageSize-2;
		}
		model.addAttribute("blockSize", blockSize);
		String nowBlock = httpServletRequest.getParameter("nowBlock");
		String user_id=(String)session.getAttribute("userId");;
		System.out.println("----------------------------------"+user_id);
		
		if(user_id==null) {
			return "login";
		}
		else {
			model.addAttribute("id",user_id);
			model.addAttribute("listSize", listSize);
			model.addAttribute("pageSize", pageSize);
		}
		
		String option = httpServletRequest.getParameter("option");
		String page = httpServletRequest.getParameter("page");
		
		if(page==null) {		//웹 페이지에서 넘겨준 값이 없으면 초기 페이지 값 1
			page = "1";
			nowBlock="1";
		}
		if(nowBlock==null) {
			nowBlock="1";
		}
		
		if(page!=null && option==null) {	//웹 페이지에서 넘겨준 값이 있으면 해당 페이지 값으로
			
		}
		else if(option.equals("first")) {	//페이징 첫번째로
			page = "1";
			nowBlock = "1";
		}
		else if(option.equals("last")) {	//페이징 마지막으로
			page = String.valueOf(pageSize);
			String snowBlock = Integer.toString(blockSize);
			nowBlock = snowBlock;
		}
		else if(option.equals("next")) {	//페이징 다음 블럭으로
			Integer ipage = Integer.parseInt(page);
			if(ipage>=pageSize) {
				String spage = Integer.toString(pageSize);
				page=spage;
			}
			Integer inowBlock = Integer.parseInt(nowBlock);
			if(inowBlock>=blockSize) {
				String snowBlock = Integer.toString(blockSize);
				nowBlock = snowBlock;
			}
		}
		else if(option.equals("back")) {	//페이징 이전 블럭으로
			Integer ipage = Integer.parseInt(page);
			if(ipage<=1) {
				page="1";
			}
			Integer inowBlock = Integer.parseInt(nowBlock);
			if(inowBlock<=1) {
				nowBlock = "1";
			}
		}
		
		else if(option.equals("search")) {  //검색 기능
			String text = httpServletRequest.getParameter("name");
			String sel = httpServletRequest.getParameter("sel");
			List<listVO> clist =  new ArrayList<listVO>();
			if(text.equals("")) {			//검색 값이 없으면
				model.addAttribute("page", page);
				model.addAttribute("nowBlock", nowBlock);
				Integer p = Integer.parseInt(page);
				List<listVO> list = new ArrayList<listVO>();
				int paging = listSize-(10*(p-1));		//sql에 넘겨줄 변수 계산
				list = lDao.paging(paging);
				model.addAttribute("list",list);
				return "carList";
			}
			if(sel.equals("model")) {  //모델 명으로 검색
				clist = lDao.QueryModel("%"+text+"%");
				listSize = lDao.ScountBoard1("%"+text+"%");
				if(listSize%10==0) {
					pageSize = listSize/10;
				} else {
					pageSize = listSize/10 + 1;
				}
			}
			else if(sel.equals("time")) {  //차량이 지나간 시간으로 검색
				clist = lDao.QueryTime("%"+text+"%");
				listSize = lDao.ScountBoard2("%"+text+"%");
				if(listSize%10==0) {
					pageSize = listSize/10;
				} else {
					pageSize = listSize/10 + 1;
				}
			}
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("list",clist);
			return "carList";
		}
		else if(option.equals("image")) {
			int a = Integer.parseInt(httpServletRequest.getParameter("seq"));
			
			System.out.println(a);
			List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
			System.out.println("여기서?");
			resultList = lDao.selectImg(a);
			System.out.println(resultList);
			
			System.out.println(resultList.size());
			Map<String,Object> resultMap = resultList.get(0);
			System.out.println(resultMap.size()+" size는 몇일까");
			for (Entry<String, Object> entry : resultMap.entrySet()) {
				System.out.println("[key]:" + entry.getKey() + ", [value]:" + entry.getValue());
			}
			
			System.out.println("여기가 문제일까");
			
			
		    byte[] arr = (byte[]) resultMap.get("base64");
		    
		    System.out.println(arr+" ????????????");
		    String base64ToString = new String(arr);

		    model.addAttribute("imgSrc",base64ToString);
		    
		    return "test";
		}
		else {
			
		}
		model.addAttribute("page", page);
		model.addAttribute("nowBlock", nowBlock);
		Integer p = Integer.parseInt(page);
		List<listVO> list = new ArrayList<listVO>();
		int paging = listSize-(10*(p-1));		//sql에 넘겨줄 변수 계산
		list = lDao.paging(paging);
		model.addAttribute("list",list);
		return "carList";
	}
	
	//---------------------------------QnA 페이지-------------------------------------//
		@RequestMapping(value = "/QnA", method = RequestMethod.GET)
		public String QnA(HttpServletRequest httpServletRequest, Model model) {
			System.out.println("QnAList return");
				
			HttpSession session=httpServletRequest.getSession();
				
			String user_id=(String)session.getAttribute("userId");;
			if(user_id==null) {
				return "login";
			}
			System.out.println("----------------------------------"+user_id);
			
			model.addAttribute("id",user_id);
			
			List<QnAVO> list = new ArrayList<QnAVO>();
			QnAVO qVo = new QnAVO();
			int listSize = qDao.countBoard(qVo);		//페이징
			int pageSize;
			if(listSize%10==0) {
				pageSize = listSize/10;
			} else {
				pageSize = listSize/10 + 1;
			}
			int blockSize;
			if(pageSize<3) {
				blockSize = 1;
			}
			else {
				blockSize = pageSize-2;
			}
			model.addAttribute("blockSize", blockSize);
			
			String nowBlock = httpServletRequest.getParameter("nowBlock");
			String option = httpServletRequest.getParameter("option");
			String page = httpServletRequest.getParameter("page");
			model.addAttribute("listSize", listSize);
			model.addAttribute("pageSize", pageSize);
			if(page==null) {		//웹 페이지에서 넘겨준 값이 없으면 초기 페이지 값 1
				page = "1";
				nowBlock="1";
			}
			if(option==null) {
				
			}
			else if(option.equals("first")) {
				page = "1";
				nowBlock = "1";
			}
			else if(option.equals("last")) {
				page = String.valueOf(pageSize);
				String snowBlock = Integer.toString(blockSize);
				nowBlock = snowBlock;
			}
			else if(option.equals("next")) {
				System.out.println("page : " + page);
				Integer ipage = Integer.parseInt(page);
				if(ipage>=pageSize) {
					String spage = Integer.toString(pageSize);
					page=spage;
				}
				Integer inowBlock = Integer.parseInt(nowBlock);
				System.out.println("blockSize : " + blockSize);
				if(inowBlock>=blockSize) {
					String snowBlock = Integer.toString(blockSize);
					nowBlock = snowBlock;
				}
			}
			else if(option.equals("back")) {
				Integer ipage = Integer.parseInt(page);
				if(ipage<=1) {
					page="1";
				}
				Integer inowBlock = Integer.parseInt(nowBlock);
				if(inowBlock<=1) {
					nowBlock = "1";
				}
			}
			else if(option.equals("search")) {  //검색
				String keyWord = httpServletRequest.getParameter("keyWord");
				String select = httpServletRequest.getParameter("select");
				if(keyWord.equals("")) {
					model.addAttribute("nowBlock", nowBlock);
					model.addAttribute("page", page);
					Integer p = Integer.parseInt(page);
					int paging = 10*(p-1);
					list = qDao.paging(paging);		
					model.addAttribute("list",list);
					return "QnA";
				}
				
				if(select.equals("title")) {  //제목으로 검색
					list = qDao.searchTitle("%"+keyWord+"%");
					listSize = qDao.ScountBoard1("%"+keyWord+"%");
					if(listSize%10==0) {
						pageSize = listSize/10;
					} else {
						pageSize = listSize/10 + 1;
					}
				}
				else if(select.equals("userId")) {  //작성자 아이디로 검색
					list = qDao.searchUser("%"+keyWord+"%");
					listSize = qDao.ScountBoard2("%"+keyWord+"%");
					if(listSize%10==0) {
						pageSize = listSize/10;
					} else {
						pageSize = listSize/10 + 1;
					}
				}
				else if(select.equals("multi")) { //제목 + 작성자로 검색
					list = qDao.search("%"+keyWord+"%");
					listSize = qDao.ScountBoard3("%"+keyWord+"%");
					if(listSize%10==0) {
						pageSize = listSize/10;
					} else {
						pageSize = listSize/10 + 1;
					}
				}				
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("list", list);
				return "SQnA";
			}
			else if(option.equals("read")) {  //글 열람
				System.out.println("QnA read");
				int a = Integer.parseInt(httpServletRequest.getParameter("seq"));
				QnAVO qVO = new QnAVO();
				qVO = qDao.read(a);
									
				if((user_id).equals("manage1234")) {  //관리자 아이디일시 QnA 답변란 제공
					model.addAttribute("manage",user_id);
				}
				
				String key = httpServletRequest.getParameter("del"); 
				
				if(key==null) {
					
				}
				else if(key.equals("delete")) { //답글 삭제
					int num = Integer.parseInt(httpServletRequest.getParameter("num"));
					qrDao.delete(num);
				}
				
				List<qCommentVO> qlist = new ArrayList<qCommentVO>();
				qlist = qrDao.CommentAll(a);  //QnA 답글 select
				
				if((qlist)!=null) {  //답글이 하나라도 있으면 적용
					model.addAttribute("rlist",qlist);
				}
				
				model.addAttribute("list",qVO);
				return "QnAView";
			}
			else if(option.equals("enroll")) {  //QnA 댓글 등록
				qCommentVO vo = new qCommentVO();
				vo.QnANum = Integer.parseInt(httpServletRequest.getParameter("seq"));
				vo.text = httpServletRequest.getParameter("text");
				vo.userId = user_id;
				vo.time = httpServletRequest.getParameter("time");
				qrDao.InsertComment(vo);
				
				int a = vo.QnANum;
				QnAVO qVO = new QnAVO();
				qVO = qDao.read(a);
				
				if((user_id).equals("manage1234")) {
					model.addAttribute("manage",user_id);
				}
				
				List<qCommentVO> qlist = new ArrayList<qCommentVO>();
				qlist = qrDao.CommentAll(a);
				
				model.addAttribute("rlist",qlist);
				model.addAttribute("list",qVO);
				
				return "QnAView";
			}
			else if(option.equals("goToenroll")) { //QnA 작성 칸으로 이동
				model.addAttribute("id",user_id);
				return "QnAWrite";
			}
			else if(option.equals("enrollQnA")) {  //QnA 글 등록
				qVo.title = httpServletRequest.getParameter("title");
				qVo.content = httpServletRequest.getParameter("content");
				qVo.userId = httpServletRequest.getParameter("writer");
				qVo.time = httpServletRequest.getParameter("time");
				
				qDao.enrollQnA(qVo);
				System.out.println("insert");
			}
			else if(option.equals("modify")) {  //QnA 글 수정페이지로 이동
				int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
				qVo = qDao.read(seq);
				qVo.setContent(qVo.getContent().replace("<br>", "\r\n"));
				model.addAttribute("list",qVo);
				model.addAttribute("id",user_id);
				return "QnAModify";
			}
			else if(option.equals("modifyQnA")) {  //QnA 글 수정 완료
				
				qVo.seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
				qVo.title = httpServletRequest.getParameter("title");
				qVo.content = httpServletRequest.getParameter("content");
				qVo.userId = httpServletRequest.getParameter("writer");
				qVo.time = httpServletRequest.getParameter("time");
				qDao.modify(qVo);
			}
			else if(option.equals("delQnA")) {  //QnA 글 삭제
				int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
				qDao.delete(seq);
				qrDao.deleteAll(seq);
			}
			list = qDao.QueryAll();
			model.addAttribute("nowBlock", nowBlock);
			model.addAttribute("page", page);
			Integer p = Integer.parseInt(page);
			int paging = 10*(p-1);
			list = qDao.paging(paging);		
			model.addAttribute("list",list);
			return "QnA";
		}

	//--------------------carModel페이지-------------------------//
	@RequestMapping(value = "/CarModel", method = RequestMethod.GET)
	public String carModel(HttpServletRequest httpServletRequest, Model model) {
		//많은 차들의 다양한 정보를 제공하는 페이지
		HttpSession session=httpServletRequest.getSession();
		String user_id=(String)session.getAttribute("userId"); ;
		model.addAttribute("id",user_id);
		
		if(user_id==null) {
			return "login";
		}
		List<CarKindVO> list = new ArrayList<CarKindVO>();
		list = cDao.QuerryAll();
		CarKindVO cVO = new CarKindVO();
		int listSize = cDao.countBoard(cVO);		//페이징
		int pageSize;
		if(listSize%6==0) {
			pageSize = listSize/6;
		} else {
			pageSize = listSize/6 +1;
		}
		int blockSize;
		if(pageSize<3) {
			blockSize = 1;
		}
		else {
			blockSize = pageSize-2;
		}
		model.addAttribute("blockSize", blockSize);
		model.addAttribute("listSize", listSize);
		model.addAttribute("pageSize", pageSize);
		
		String nowBlock = httpServletRequest.getParameter("nowBlock");
		String page = httpServletRequest.getParameter("page");
		String option = httpServletRequest.getParameter("option");
		if(page==null) {		//웹 페이지에서 넘겨준 값이 없으면 초기 페이지 값 1
			page = "1";
			nowBlock="1";
		}
		if(nowBlock==null) {
			nowBlock="1";
		}
		if(option==null) {
		}
		else if(option.equals("first")) {
			page = "1";
			nowBlock = "1";
		}
		else if(option.equals("last")) {
			page = String.valueOf(pageSize);
			String snowBlock = Integer.toString(blockSize);
			nowBlock = snowBlock;
		}
		else if(option.equals("next")) {
			Integer ipage = Integer.parseInt(page);
			if(ipage>=pageSize) {
				String spage = Integer.toString(pageSize);
				page=spage;
			}
			Integer inowBlock = Integer.parseInt(nowBlock);
			System.out.println("blockSize : " + blockSize);
			if(inowBlock>=blockSize) {
				String snowBlock = Integer.toString(blockSize);
				nowBlock = snowBlock;
			}
		}
		else if(option.equals("back")) {
			Integer ipage = Integer.parseInt(page);
			if(ipage<=1) {
				page="1";
			}
			Integer inowBlock = Integer.parseInt(nowBlock);
			if(inowBlock<=1) {
				nowBlock = "1";
			}
		}
		else if(option.equals("search")) {  //검색 기능
			System.out.println("검색 기능입니다");
			String searchText = httpServletRequest.getParameter("name");
			String sel =  httpServletRequest.getParameter("sel");
			if(searchText=="") {
				model.addAttribute("page", page);
				model.addAttribute("nowBlock", nowBlock);
				Integer p = Integer.parseInt(page);
				int paging = listSize-(6*(p-1));		//sql에 넘겨줄 변수 계산
				list = cDao.paging(paging);
				model.addAttribute("list",list);
				return "CarModel";
			}
			if(sel.equals("carkind")){ //차 이름으로 검색
				System.out.println("차 이름으로 검색");
				list = cDao.Querrycar("%"+searchText+"%");
				listSize = cDao.ScountBoard1("%"+searchText+"%");
				if(listSize%6==0) {
					pageSize = listSize/6;
				} else {
					pageSize = listSize/6 +1;
				}
			}
			else if(sel.equals("carmaker")) {  //제조사로 검색
				System.out.println("제조사로 검색");
				list = cDao.QuerryMaker("%"+searchText+"%");
				listSize = cDao.ScountBoard2("%"+searchText+"%");
				if(listSize%6==0) {
					pageSize = listSize/6;
				} else {
					pageSize = listSize/6 +1;
				}
			}
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("list",list);
			return "SCarModel";
		}
		model.addAttribute("page", page);
		model.addAttribute("nowBlock", nowBlock);
		Integer p = Integer.parseInt(page);
		int paging = listSize-(6*(p-1));		//sql에 넘겨줄 변수 계산
		list = cDao.paging(paging);
		model.addAttribute("list",list);
		return "CarModel";
	}	
	
	//----------------------------자유게시판------------------------------//
	@RequestMapping(value = "/free", method = RequestMethod.GET)
	public String free(HttpServletRequest httpServletRequest, Model model) {
		HttpSession session=httpServletRequest.getSession();
		String user_id=(String)session.getAttribute("userId"); ;
		model.addAttribute("id",user_id);
		
		if(user_id==null) {  //로그인 안되어있을시 로그인 페이지로 이동
			return "login";
		}
		List<fCommentVO> rlist = new ArrayList<fCommentVO>(); //리플 관련 리스트
		List<FreeBoardVO> list = new ArrayList<FreeBoardVO>(); //게시판 관련 리스트
		list = fDao.QueryAll();
		FreeBoardVO fVo = new FreeBoardVO();
		int listSize = fDao.countBoard(fVo);		//페이징
		int pageSize;
		if(listSize%10==0) {
			pageSize = listSize/10;
		} else {
			pageSize = listSize/10 + 1;
		}
		int blockSize;
		if(pageSize<3) {
			blockSize = 1;
		}
		else {
			blockSize = pageSize-2;
		}
		model.addAttribute("blockSize", blockSize);
		model.addAttribute("listSize", listSize);
		model.addAttribute("pageSize", pageSize);
		
		String nowBlock = httpServletRequest.getParameter("nowBlock");
		String page = httpServletRequest.getParameter("page");
		String option = httpServletRequest.getParameter("option");
		if(page==null) {		//웹 페이지에서 넘겨준 값이 없으면 초기 페이지 값 1
			page = "1";
			nowBlock="1";
		}
		if(option==null) {
			
		}
		else if(option.equals("first")) {
			page = "1";
			nowBlock="1";
		}
		else if(option.equals("last")) {
			page = String.valueOf(pageSize);
			String snowBlock = Integer.toString(blockSize);
			nowBlock = snowBlock;
		}
		else if(option.equals("next")) {
			Integer ipage = Integer.parseInt(page);
			if(ipage>=pageSize) {
				String spage = Integer.toString(pageSize);
				page=spage;
			}
			Integer inowBlock = Integer.parseInt(nowBlock);
			if(inowBlock>=blockSize) {
				String snowBlock = Integer.toString(blockSize);
				nowBlock = snowBlock;
			}
		}
		else if(option.equals("back")) {
			Integer ipage = Integer.parseInt(page);
			if(ipage<=1) {
				page="1";
			}
			Integer inowBlock = Integer.parseInt(nowBlock);
			if(inowBlock<=1) {
				nowBlock = "1";
			}
		}
		else if(option.equals("search")) {  //freeboard 검색
			String s = httpServletRequest.getParameter("text");
			String select = httpServletRequest.getParameter("select");
			if(s.equals("")) {
				model.addAttribute("nowBlock", nowBlock);
				model.addAttribute("page", page);
				Integer p = Integer.parseInt(page);
				int paging = 10*(p-1);
				list = fDao.paging(paging);
				model.addAttribute("list",list);
				return "Free";
			}
			
			if(select.equals("title")) { //제목으로 검색
				list = fDao.SearchTitle("%"+s+"%");
				listSize = fDao.ScountBoard1("%"+s+"%");
				if(listSize%10==0) {
					pageSize = listSize/10;
				} else {
					pageSize = listSize/10 + 1;
				}
			}
			else if(select.equals("userId")) {  //작성자로 검색
				list = fDao.SearchUser("%"+s+"%");
				listSize = fDao.ScountBoard1("%"+s+"%");
				if(listSize%10==0) {
					pageSize = listSize/10;
				} else {
					pageSize = listSize/10 + 1;
				}
			}
			else if(select.equals("multi")) {  // 둘 다 검색
				list = fDao.Search("%"+s+"%");
				listSize = fDao.ScountBoard1("%"+s+"%");
				if(listSize%10==0) {
					pageSize = listSize/10;
				} else {
					pageSize = listSize/10 + 1;
				}
			}
			model.addAttribute("pageSize", pageSize);			
			model.addAttribute("list",list);
			return "SFree";
		}
		else if(option.equals("view")) {   //freeboard 보기
			int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
			FreeBoardVO fVO = new FreeBoardVO();
			fVO = fDao.Read(seq);
			System.out.println(seq);
			rlist = frDao.querry(seq);
			int size = rlist.size();
			
			System.out.println(size);
			model.addAttribute("id",user_id);
			model.addAttribute("size",size);
			model.addAttribute("rlist",rlist);
			model.addAttribute("list",fVO);
			return "freeView";
		}
		else if(option.equals("comment")) {  //댓글 쓰기
			fCommentVO fCVO = new fCommentVO();
			fCVO.userId=user_id;
			fCVO.text = httpServletRequest.getParameter("comment");
			fCVO.freeNum = Integer.parseInt(httpServletRequest.getParameter("seq"));
			fCVO.time = httpServletRequest.getParameter("time");
			
			frDao.InsertComment(fCVO);
			FreeBoardVO fVO = new FreeBoardVO();
			fVO = fDao.Read(fCVO.freeNum);
			rlist = frDao.querry(fCVO.freeNum);
			int size = rlist.size();
			
			System.out.println(size);
			model.addAttribute("id",user_id);
			model.addAttribute("size",size);
			model.addAttribute("rlist",rlist);
			model.addAttribute("list",fVO);
			return "freeView";
		}
		else if(option.equals("commentDel")) {  //freeboard 댓글 삭제
			System.out.println("댓 삭");
			int num = Integer.parseInt(httpServletRequest.getParameter("commentNum"));
			frDao.DelComment(num);
			
			FreeBoardVO fVO = new FreeBoardVO();
			fVO = fDao.Read(Integer.parseInt(httpServletRequest.getParameter("freeNum")));
			
			rlist = frDao.querry(Integer.parseInt(httpServletRequest.getParameter("freeNum")));
			int size = rlist.size();
			
			System.out.println(size);
			model.addAttribute("id",user_id);
			model.addAttribute("size",size);
			model.addAttribute("rlist",rlist);
			model.addAttribute("list",fVO);
			return "freeView";
		}
		else if(option.equals("delFree")) {  //freeboard 글 삭제
			int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
			fDao.deleteFree(seq);
			frDao.DelCommentAll(seq);
			list = fDao.QueryAll();
		}
		else if(option.equals("gotoEnroll")) {  //글 등록 페이지 이동
			model.addAttribute("id",user_id);
			return "FreeWrite";
		}
		else if(option.equals("enroll")) {  //글을 등록
			System.out.println("글을 등록");
			FreeBoardVO fVO = new FreeBoardVO();
			fVO.title=httpServletRequest.getParameter("title");
			fVO.content=httpServletRequest.getParameter("content");
			fVO.userId=httpServletRequest.getParameter("writer");
			fVO.time=httpServletRequest.getParameter("time");
			
			System.out.println(fVO.time);
			fDao.insert(fVO);
		}
		else if(option.equals("modify")) {//수정 페이지로 이동
			int a = Integer.parseInt(httpServletRequest.getParameter("seq"));
			FreeBoardVO fVO = new FreeBoardVO();
			fVO=fDao.Read(a);
			
			fVO.setContent(fVO.getContent().replaceAll("<br>", "\r\n"));
			model.addAttribute("list",fVO);
			model.addAttribute("seq",a);
			return "FreeModify";
		}
		else if(option.equals("modifySuccess")) { //수정 완료
			FreeBoardVO fVO = new FreeBoardVO();
			
			fVO.seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
			fVO.title = httpServletRequest.getParameter("title");
			fVO.content = httpServletRequest.getParameter("content");
			fVO.userId = httpServletRequest.getParameter("writer");
			fVO.time = httpServletRequest.getParameter("time");
			
			fDao.modify(fVO); //수정 쿼리
			
			rlist = frDao.querry(fVO.seq);
			int size = rlist.size();
			
			System.out.println(size);
			model.addAttribute("id",user_id);
			model.addAttribute("size",size);
			model.addAttribute("rlist",rlist);
			model.addAttribute("list",fVO);
			return "freeView";
		}
		model.addAttribute("nowBlock", nowBlock);
		model.addAttribute("page", page);
		Integer p = Integer.parseInt(page);
		int paging = 10*(p-1);
		list = fDao.paging(paging);
		model.addAttribute("list",list);
		return "Free";
	}
	
	@RequestMapping(value = "/freeEnroll", method = {RequestMethod.GET, RequestMethod.POST})
	public String freeEnroll(HttpServletRequest request, Model model, String title, String content) {
		
		HttpSession session=request.getSession();
		
		String user_id=(String)session.getAttribute("userId");
		
		MultipartHttpServletRequest mhsr=(MultipartHttpServletRequest) request;
		FreeBoardVO fVO = new FreeBoardVO();
		byte[] file= "0".getBytes();
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format.format(time);
		
		try {
			file = mhsr.getFile("imgFile").getBytes();
			if(file.length==0) {
				fVO.setImg(null);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		
		try {
			fVO.setTitle(title);
			fVO.setContent(content);
			fVO.setTime(time1);
			fVO.setUserId(user_id);
			fVO.setImg(file);
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		fDao.insert(fVO);
		return "redirect:/free";
	}
	
	//-------------------------만든 이들 소개 페이지----------------------//
	@RequestMapping(value = "/developer", method = RequestMethod.GET)
	public String freeBoard(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("developer page return");
		HttpSession session=httpServletRequest.getSession();
		
		String user_id=(String)session.getAttribute("userId");;
		if(user_id==null) {  //로그인이 안되어 있을시 로그인 페이지로 이동
			return "login";
		}
		
		model.addAttribute("login",user_id);
		return "developer";
	}
	
	//-------------------------QnA 검색결과 반환 ----------------------//
	@RequestMapping(value = "/SQnA", method = RequestMethod.GET)
	public String SQnA(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("developer page return");
		HttpSession session=httpServletRequest.getSession();
		
		String user_id=(String)session.getAttribute("userId");;
		if(user_id==null) {
			return "login";
		}
		
		model.addAttribute("login",user_id);
		return "SQnA";
	}
	
	//--------------------------자유게시판 검색결과 반환--------------------//
	@RequestMapping(value = "/SFree", method = RequestMethod.GET)
	public String SFree(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("developer page return");
		HttpSession session=httpServletRequest.getSession();
		
		String user_id=(String)session.getAttribute("userId");;
		if(user_id==null) {
			return "login";
		}
		
		model.addAttribute("login",user_id);
		return "SFree";
	}
	
	//-------------------------CCTV 분석결과 검색결과 반환------------------//
	@RequestMapping(value = "/ScarList", method = RequestMethod.GET)
	public String ScarList(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("developer page return");
		HttpSession session=httpServletRequest.getSession();
		
		String user_id=(String)session.getAttribute("userId");;
		if(user_id==null) {
			return "login";
		}
		
		model.addAttribute("login",user_id);
		return "ScarList";
	}
	
	//-------------------------CarModel 검색결과 반환------------------//
		@RequestMapping(value = "/SCarModel", method = RequestMethod.GET)
		public String SCarModel(HttpServletRequest httpServletRequest, Model model) {
			System.out.println("developer page return");
			HttpSession session=httpServletRequest.getSession();
			
			String user_id=(String)session.getAttribute("userId");;
			if(user_id==null) {
				return "login";
			}
			
			model.addAttribute("login",user_id);
			return "SCarModel";
		}
}
	/*
	
	//---------------------------------자유게시판화면-------------------------------------//
		@RequestMapping(value = "/freeBoard", method = RequestMethod.GET)
		public String freeBoard(HttpServletRequest httpServletRequest, Model model) {
			System.out.println("first page return");
			
			HttpSession session=httpServletRequest.getSession();
			
			String user_id=(String)session.getAttribute("userId");;
			System.out.println("----------------------------------"+user_id);
			
			List<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
			list = fDao.QueryAll();
			String option = httpServletRequest.getParameter("option");
			if(option==null) {
				
			}
			
			else if(option.equals("gotoEnroll")) {  //글 작성 페이지로 이동
				model.addAttribute("id",user_id);
				System.out.println("이거되니");
				return "FreeEnroll";
			}
			
			else if(option.equals("enroll")) { //글 등록
				FreeBoardVO vo = new FreeBoardVO();
				vo.title = httpServletRequest.getParameter("title");
				vo.content = httpServletRequest.getParameter("content");
				vo.userId = httpServletRequest.getParameter("userId");
				vo.time = httpServletRequest.getParameter("timeString");
				
				fDao.insert(vo);
				list = fDao.QueryAll();
				model.addAttribute("list",list);
				return "freeBoard";
			}
			
			else if(option.equals("search")) { //검색
				String keyWord=httpServletRequest.getParameter("keyWord");
				list = fDao.Search("%"+keyWord+"%");
			}
			model.addAttribute("list",list);
			return "freeBoard";
		}
	
		
	//---------------------------------firstpage 띄우기-------------------------------------//
	@RequestMapping(value = "/firstpage", method = RequestMethod.GET)
	public String firstpage(HttpServletRequest httpServletRequest, Model model, HttpServletResponse response) {
		 
		MemberVO mVo=new MemberVO();
		
		String option=httpServletRequest.getParameter("option");
		System.out.println(httpServletRequest.getParameter("userId")+","+httpServletRequest.getParameter("userPw"));
		System.out.println();
		
		HttpSession session=httpServletRequest.getSession();
		
		String user_id;
		
		
		if(option==null) {
			System.out.println("first page loading");
		}
		
		else if(option.equals("login")) {
			mVo.userId=httpServletRequest.getParameter("userId");
			mVo.userPw=httpServletRequest.getParameter("userPw");
			MemberVO login = new MemberVO();
			login=mDao.login(mVo);
			String s = "no";
			if(login == null) {
				System.out.println("hey");
				model.addAttribute("failFlag",s);
			}
			else {
				session.setAttribute("userId", mVo.userId);
				user_id=(String)session.getAttribute("userId");
				System.out.println(user_id);
				model.addAttribute("id",user_id);
				return "menu";
				
			}
			
		}
		
		else if(option.equals("logOut")) {
			System.out.println("log Out!!!");
			session.invalidate();
			System.out.println("session delete");
			
			return "firstpage";
		}
			
		
		System.out.println("first page return");
		//model.addAttribute("serverTime", formattedDate );
		
		return "firstpage";
	}
	/*
	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public String menu(HttpServletRequest httpServletRequest, Model model) {
		loginMap loginMap1 = loginMap.getInstance();
		
		String option=httpServletRequest.getParameter("option");
		
		model.addAttribute("option",option);
		
		
		System.out.println("second page return");
		//model.addAttribute("serverTime", formattedDate );
		
		return "menu";
	}*/
	
	/*@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(HttpServletRequest httpServletRequest, Model model) {
		HttpSession session=httpServletRequest.getSession();
		String user_id=(String)session.getAttribute("userId"); ;
		model.addAttribute("id",user_id);
		return "menu";
	}
	
	//-----------------------------------------차량 리스트 띄우기--------------------------------------------------//
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest httpServletRequest, Model model) {
		HttpSession session=httpServletRequest.getSession();
		String user_id=(String)session.getAttribute("userId"); //아이디 세션 불러오기

		
		System.out.println("----------------------------------"+user_id);
		if(user_id==null) {
			return "firstpage";
		}
		
		
		
		List<listVO> list = new ArrayList<listVO>();
		
		listVO vo=new listVO();
		//DataMap dataMap = DataMap.getInstance();
		
		String option=httpServletRequest.getParameter("option");
		int firstIndex=0;
		int lastIndex=9;
		list = lDao.QueryAll();
		
		
		if(option==null) {
			
		}
		/*
		else if(option.equals("search")) {
			List<listVO> searchList = new ArrayList();
			System.out.println(httpServletRequest.getParameter("name"));
			for(int i=0;i<dataMap.list.size();i++) {
				if(httpServletRequest.getParameter("name").equals(dataMap.list.get(i).carNum)||httpServletRequest.getParameter("name").equals(dataMap.list.get(i).carColor)||httpServletRequest.getParameter("name").equals(dataMap.list.get(i).carKind)||httpServletRequest.getParameter("name").equals(dataMap.list.get(i).carMaster)){
					searchList.add(dataMap.list.get(i));
				}
			}
			model.addAttribute("list",searchList);
			return "list";
		}*/
		/*
		else if (option.equals("search")) {
			String msg=  httpServletRequest.getParameter("name");
			System.out.println(msg);
			list = lDao.searchName("%"+msg+"%");
			System.out.println(list);
		}
		
		else if(option.equals("page")) {
			int pageNum=Integer.parseInt(httpServletRequest.getParameter("pageNum"));
			System.out.println(pageNum);
			firstIndex=(pageNum-1)*10;
			lastIndex=firstIndex+9;
			System.out.println(firstIndex);
			System.out.println(lastIndex);
		}
		
		else if(option.equals("carKind")) {
			List<CarKindVO> clist = new ArrayList<CarKindVO>();
			String kind=httpServletRequest.getParameter("kind");
			System.out.println(kind);
			clist=cDao.Querrycar(kind);
			/*if(clist.isEmpty()) {
				kind="";
			}*/
		/*	
			model.addAttribute("id",kind);
			model.addAttribute("list",clist);
			return "carKind";
		}

	
		model.addAttribute("id",user_id);
		model.addAttribute("list",list);
		model.addAttribute("firstIndex",firstIndex);
		model.addAttribute("lastIndex", lastIndex);
		System.out.println("third page return");
		//model.addAttribute("serverTime", formattedDate );
		
		return "list";
	}

	*/
//--------------------------------회원가입-----------------------------//
	
	/*@RequestMapping(value = "/SignUp", method = RequestMethod.GET)
	public String signUp(HttpServletRequest httpServletRequest, Model model) {
		String option=httpServletRequest.getParameter("option");
		
		MemberVO mVo=new MemberVO();
		
		
		if(option==null) {
			
		}
		else if(option.equals("signUp")) {
			mVo.userId=httpServletRequest.getParameter("userId");
			mVo.userPw=httpServletRequest.getParameter("userPw");
			System.out.println(mVo.userId);
			mDao.InsertId(mVo);
			return "firstpage";
		}
		return "Signup";
	}*/
	
//-------------------------------QnA List---------------------------------------------//
	/*@RequestMapping(value = "/QnAList", method = RequestMethod.GET)
	public String QnAList(HttpServletRequest httpServletRequest, Model model,HttpServletResponse response) throws ServletException,IOException{
		List<QnAVO> list = new ArrayList<QnAVO>();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session=httpServletRequest.getSession();
		String user_id=(String)session.getAttribute("userId"); //아이디 세션 불러오기
		
		
		System.out.println("----------------------------------"+user_id);
		//if(user_id==null) {
			//return "firstpage";
		//}
		
		QnAVO qVo=new QnAVO();
		//DataMap dataMap = DataMap.getInstance();
		
		String option=httpServletRequest.getParameter("option");
		int firstIndex=0;
		int lastIndex=9;
		list = qDao.QueryAll();
			
		
		if(option==null) {
			
		}
		
		else if(option.equals("gotoEnroll")) {
			model.addAttribute("id",user_id);
			return "QnA";
		}
		else if(option.equals("enroll")) {                 //QnA 등록하기
			qVo.title=httpServletRequest.getParameter("title");
			qVo.content=httpServletRequest.getParameter("content");
			qVo.userId=httpServletRequest.getParameter("writer");
			qVo.time=httpServletRequest.getParameter("time");
			
			System.out.println(qVo.time);
			qDao.enrollQnA(qVo);  //insert qna
			list = qDao.QueryAll();
			model.addAttribute("id",user_id);
			model.addAttribute("list",list);
			model.addAttribute("firstIndex",firstIndex);
			model.addAttribute("lastIndex", lastIndex);
			System.out.println(list);
			return "QnAList";
		}
		
		else if(option.equals("page")) {      //QnA 리스트 페이지
			int pageNum=Integer.parseInt(httpServletRequest.getParameter("pageNum"));
			System.out.println(pageNum);
			firstIndex=(pageNum-1)*10;
			lastIndex=firstIndex+9;
			System.out.println(firstIndex);
			System.out.println(lastIndex);
		}
		
		else if(option.equals("search")) {     //QnA 검색
			System.out.println("search if 실행");
			String searchTag=httpServletRequest.getParameter("name");
			System.out.println(searchTag);
			list = qDao.search("%"+searchTag+"%");
		}
		
		else if(option.equals("read")) {      //QnA 열람
			List<qCommentVO> rlist = new ArrayList<qCommentVO>();
			int keyword=Integer.parseInt(httpServletRequest.getParameter("keyword"));
			System.out.println(keyword);
			QnAVO qRead=new QnAVO();
			qRead=qDao.read(keyword);
			rlist=rDao.CommentAll(keyword);
			
			model.addAttribute("id",user_id);
			model.addAttribute("QnAVO",qRead);
			return "QnARead";
		}
		
		else if(option.equals("delete")) {    //QnA 삭제
			int deleter=Integer.parseInt(httpServletRequest.getParameter("index"));
			System.out.println(deleter);
			qDao.delete(deleter);
			list = qDao.QueryAll();
			rDao.delete(deleter);
			model.addAttribute("list",list);
			model.addAttribute("firstIndex",firstIndex);
			model.addAttribute("lastIndex", lastIndex);
			return "QnAList";
		}
		
		else if(option.equals("comment")) {   //댓글 등록
			List<qCommentVO> rlist = new ArrayList<qCommentVO>();
			qCommentVO rvo= new qCommentVO();
			rvo.userId=user_id;
			rvo.text=httpServletRequest.getParameter("text");
			rvo.QnANum=Integer.parseInt(httpServletRequest.getParameter("QnANum"));
			System.out.println("리플 등록자 : "+rvo.userId);
			System.out.println("text : "+rvo.text);
			rDao.InsertComment(rvo);
			rlist=rDao.CommentAll(rvo.QnANum);
			
			QnAVO qRead=new QnAVO();
			qRead=qDao.read(rvo.QnANum);
			
			model.addAttribute("id",user_id);
			model.addAttribute("QnAVO",qRead);
			return "QnARead";
		}
		
		else if(option.equals("delComment")) {
			int delComment=Integer.parseInt(httpServletRequest.getParameter("index"));
			System.out.println(delComment);
			rDao.delete(delComment);
			
			List<qCommentVO> rlist = new ArrayList<qCommentVO>();
			int keyword=Integer.parseInt(httpServletRequest.getParameter("QnANum"));
			System.out.println(keyword);
			QnAVO qRead=new QnAVO();
			qRead=qDao.read(keyword);
			rlist=rDao.CommentAll(keyword);
			
			model.addAttribute("id",user_id);
			model.addAttribute("QnAVO",qRead);
			return "QnARead";
			
		}
		
				
		model.addAttribute("list",list);
		model.addAttribute("firstIndex",firstIndex);
		model.addAttribute("lastIndex", lastIndex);
		System.out.println("QnA list");
		return "exQnAList";
	}
	
	
}*/