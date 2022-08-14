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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
		
		String login = null;
		String option = httpServletRequest.getParameter("option");
		
		if(user_id==null) {
			System.out.println("로그인이 되지 않았습니다");
			login = "Login";
			model.addAttribute("login", "login");
		}
		else {
			login = "LogOut";
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
				model.addAttribute("failFlag",signal);
			}
			else {
				session.setAttribute("userId", mVo.userId);
				user_id=(String)session.getAttribute("userId");
				
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
		
		if(user_id!=null) {  //로그인 되어있을시
			return "index";
		}
		
		MemberVO mVo = new MemberVO();
		
		if(forgot_id==null) {
			
		}
		else {
			mVo.userId=forgot_id;
			mVo.userEmail=forgot_email;
			mVo.userQuestion=forgot_answer;
			
			String userPw=mDao.forgot(mVo);
			
			
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
		HttpSession session=httpServletRequest.getSession();
		String user_id=(String)session.getAttribute("userId");;
		model.addAttribute("id",user_id);

		if(user_id==null) {
			return "login";
		}
		
		List<listVO> list = new ArrayList<listVO>();
		list = lDao.QueryAll();
		
		String option = httpServletRequest.getParameter("option");
		String page = httpServletRequest.getParameter("page");
		
		if(page==null) {
			page="1";
		}
		int Ipage = Integer.parseInt(page);
		
		lpagingVO pVo = new lpagingVO(Ipage, list);
		
		if(option==null) {	//웹 페이지에서 넘겨준 값이 있으면 해당 페이지 값으로
			
		}
		else if(option.equals("search")) {  //검색 기능
			String text = httpServletRequest.getParameter("name");
			String sel = httpServletRequest.getParameter("sel");
			List<listVO> clist =  new ArrayList<listVO>();
			if(text.equals("")) {			//검색 값이 없으면
				model.addAttribute("page", page);
				model.addAttribute("list",list);
				model.addAttribute("startPageNum", pVo.startPageNum);
				model.addAttribute("endPageNum", pVo.endPageNum);
				model.addAttribute("prev", pVo.prev);
				model.addAttribute("next", pVo.next);
				model.addAttribute("select", Ipage);
				return "carList";
			}
			if(sel.equals("model")) {  //모델 명으로 검색
				clist = lDao.QueryModel("%"+text+"%");
			}
			else if(sel.equals("time")) {  //차량이 지나간 시간으로 검색
				clist = lDao.QueryTime("%"+text+"%");
			}
			model.addAttribute("list",list);
			lpagingVO spVo = new lpagingVO(Ipage, list);
			session.setAttribute("list", list);
			
			model.addAttribute("startPageNum", spVo.startPageNum);
			model.addAttribute("endPageNum", spVo.endPageNum);
			model.addAttribute("prev", spVo.prev);
			model.addAttribute("next", spVo.next);
			model.addAttribute("select", Ipage);
			return "ScarList";
		}
		
		else if(option.equals("img")) {
			int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
			listVO lVO = new listVO();
			lVO = lDao.selectOne(seq);
			
			String img = lVO.imgUrl;
			String[] imgUrl = img.split("\\?");
			lVO.setImgUrl(imgUrl[0]);
			String s = "hi";
			model.addAttribute("cctv",lVO);
			
			return "Capture";
		}
		
		else if(option.equals("cctv")) {
			List<listVO> clist =  new ArrayList<listVO>();
			clist = lDao.QueryAll();
			int size = clist.size();
			String url="";
			
			for(int i=0; i<size; i++) {
				String[] imgUrl = clist.get(i).imgUrl.split("\\?");
				url=imgUrl[0]+"?"+url;
			}
			
			System.out.println(url);
			
			model.addAttribute("list",url);
			
			return "CCTV";
		}
		
		else if(option.equals("cctvNow")) {
			int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
		}
		
		else {
			
		}
		model.addAttribute("page", page);
		model.addAttribute("list",list);
		// 시작 및 끝 번호
		model.addAttribute("startPageNum", pVo.startPageNum);
		model.addAttribute("endPageNum", pVo.endPageNum);

		// 이전 및 다음 
		model.addAttribute("prev", pVo.prev);
		model.addAttribute("next", pVo.next);
		
		// 현재 페이지
		model.addAttribute("select", Ipage);
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
			list = qDao.QueryAll();
			QnAVO qVo = new QnAVO();	
			
			String option = httpServletRequest.getParameter("option");
			String page = httpServletRequest.getParameter("page");

			if(page==null) {
				page="1";
			}
			int Ipage = Integer.parseInt(page);
			
			qpagingVO pVo = new qpagingVO(Ipage, list);
			
			
			if(option==null) {
				
			}
			else if(option.equals("search")) {  //검색
				String keyWord = httpServletRequest.getParameter("keyWord");
				String select = httpServletRequest.getParameter("select");
				if(keyWord.equals("")) {
					model.addAttribute("page", page);
					model.addAttribute("list",list);
					model.addAttribute("startPageNum", pVo.startPageNum);
					model.addAttribute("endPageNum", pVo.endPageNum);
					model.addAttribute("prev", pVo.prev);
					model.addAttribute("next", pVo.next);
					model.addAttribute("select", Ipage);
					return "QnA";
				}
				
				if(select.equals("title")) {  //제목으로 검색
					list = qDao.searchTitle("%"+keyWord+"%");
				}
				else if(select.equals("userId")) {  //작성자 아이디로 검색
					list = qDao.searchUser("%"+keyWord+"%");
				}
				else if(select.equals("multi")) { //제목 + 작성자로 검색
					list = qDao.search("%"+keyWord+"%");
				}				
				model.addAttribute("list",list);
				qpagingVO spVo = new qpagingVO(Ipage, list);
				session.setAttribute("list", list);
				
				model.addAttribute("startPageNum", spVo.startPageNum);
				model.addAttribute("endPageNum", spVo.endPageNum);
				model.addAttribute("prev", spVo.prev);
				model.addAttribute("next", spVo.next);
				model.addAttribute("select", Ipage);
				return "SQnA";
			}
			else if(option.equals("read")) {  //글 열람
				System.out.println("QnA read");
				int a = Integer.parseInt(httpServletRequest.getParameter("seq"));
				
				qVo = qDao.read(a);
				
				qVo.setContent(qVo.content.replaceAll("\r\n", "<br>"));
				
				if(qVo.image!=null) {
					String imgUrl = "/getByteImage?option=qna&number="+a;
					model.addAttribute("imgSrc", imgUrl);
				}
				
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
				
				model.addAttribute("list",qVo);
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
				
				qVo = qDao.read(a);
				
				if((user_id).equals("manage1234")) {
					model.addAttribute("manage",user_id);
				}
				
				List<qCommentVO> qlist = new ArrayList<qCommentVO>();
				qlist = qrDao.CommentAll(a);
				
				model.addAttribute("rlist",qlist);
				model.addAttribute("list",qVo);
				
				return "QnAView";
			}
			else if(option.equals("goToenroll")) { //QnA 작성 칸으로 이동
				model.addAttribute("id",user_id);
				return "QnAWrite";
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
			model.addAttribute("page", page);
			model.addAttribute("list",list);
			// 시작 및 끝 번호
			model.addAttribute("startPageNum", pVo.startPageNum);
			model.addAttribute("endPageNum", pVo.endPageNum);

			// 이전 및 다음 
			model.addAttribute("prev", pVo.prev);
			model.addAttribute("next", pVo.next);
			
			// 현재 페이지
			model.addAttribute("select", Ipage);
			return "QnA";
		}

	//--------------------carModel페이지-------------------------//
	@RequestMapping(value = "/CarModel", method = RequestMethod.GET)
	public String carModel(HttpServletRequest httpServletRequest, Model model) {
		//많은 차들의 다양한 정보를 제공하는 페이지
		HttpSession session=httpServletRequest.getSession();
		
		List<CarKindVO> list = new ArrayList<CarKindVO>();
		list = cDao.QuerryAll();
		CarKindVO cVO = new CarKindVO();
		String page = httpServletRequest.getParameter("page");
		String option = httpServletRequest.getParameter("option");
		
		if(page==null) {
			page = "1";
		}
		int Ipage = Integer.parseInt(page);
		
		cpagingVO pVo = new cpagingVO(Ipage, list);

		if(option==null) {
		}
		else if(option.equals("search")) {  //검색 기능
			String searchText = httpServletRequest.getParameter("name");
			String sel =  httpServletRequest.getParameter("sel");
			if(searchText=="") {
				model.addAttribute("page", page);
				model.addAttribute("list",list);
				model.addAttribute("startPageNum", pVo.startPageNum);
				model.addAttribute("endPageNum", pVo.endPageNum);
				model.addAttribute("prev", pVo.prev);
				model.addAttribute("next", pVo.next);
				model.addAttribute("select", Ipage);
				return "CarModel";
			}
			if(sel.equals("carkind")){ //차 이름으로 검색
				list = cDao.Querrycar("%"+searchText+"%");
			}
			else if(sel.equals("carmaker")) {  //제조사로 검색
				list = cDao.QuerryMaker("%"+searchText+"%");
			}
			model.addAttribute("list",list);
			cpagingVO spVo = new cpagingVO(Ipage, list);
			session.setAttribute("list", list);
			
			model.addAttribute("startPageNum", spVo.startPageNum);
			model.addAttribute("endPageNum", spVo.endPageNum);
			model.addAttribute("prev", spVo.prev);
			model.addAttribute("next", spVo.next);
			model.addAttribute("select", Ipage);
			return "SCarModel";
		}
		model.addAttribute("page", page);
		model.addAttribute("list",list);
		// 시작 및 끝 번호
		model.addAttribute("startPageNum", pVo.startPageNum);
		model.addAttribute("endPageNum", pVo.endPageNum);

		// 이전 및 다음 
		model.addAttribute("prev", pVo.prev);
		model.addAttribute("next", pVo.next);
		
		// 현재 페이지
		model.addAttribute("select", Ipage);
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
		
		String page = httpServletRequest.getParameter("page");
		String option = httpServletRequest.getParameter("option");
		
		if(page==null) {
			page="1";
		}
		int Ipage = Integer.parseInt(page);
		
		pagingVO pVo = new pagingVO(Ipage, list);
		
		if(option==null) {
			
		}
		else if(option.equals("search")) {  //freeboard 검색
			String s = httpServletRequest.getParameter("text");
			String select = httpServletRequest.getParameter("select");
			if(s.equals("")) {
				model.addAttribute("page", page);
				model.addAttribute("list",list);
				model.addAttribute("startPageNum", pVo.startPageNum);
				model.addAttribute("endPageNum", pVo.endPageNum);
				model.addAttribute("prev", pVo.prev);
				model.addAttribute("next", pVo.next);
				model.addAttribute("select", Ipage);
				return "Free";
			}
			
			if(select.equals("title")) { //제목으로 검색
				list = fDao.SearchTitle("%"+s+"%");
			}
			else if(select.equals("userId")) {  //작성자로 검색
				list = fDao.SearchUser("%"+s+"%");
			}
			else if(select.equals("multi")) {  // 둘 다 검색
				list = fDao.Search("%"+s+"%");
			}
			model.addAttribute("list",list);
			pagingVO spVo = new pagingVO(Ipage, list);
			session.setAttribute("list", list);
			
			model.addAttribute("startPageNum", spVo.startPageNum);
			model.addAttribute("endPageNum", spVo.endPageNum);
			model.addAttribute("prev", spVo.prev);
			model.addAttribute("next", spVo.next);
			model.addAttribute("select", Ipage);
			return "SFree";
		}
		else if(option.equals("view")) {   //freeboard 보기
			int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
			
			fVo = fDao.Read(seq);
			System.out.println(seq);
			rlist = frDao.querry(seq);
			int size = rlist.size();
			
			fVo.setContent(fVo.content.replaceAll("\r\n", "<br>"));
			if(fVo.image!=null) {
				String imgUrl = "/getByteImage?option=free&number="+seq;
				model.addAttribute("imgSrc", imgUrl);
			}
			
			model.addAttribute("id",user_id);
			model.addAttribute("size",size);
			model.addAttribute("rlist",rlist);
			model.addAttribute("list",fVo);
			return "freeView";
		}
		else if(option.equals("comment")) {  //댓글 쓰기
			fCommentVO fCVO = new fCommentVO();
			fCVO.userId=user_id;
			fCVO.text = httpServletRequest.getParameter("comment");
			fCVO.freeNum = Integer.parseInt(httpServletRequest.getParameter("seq"));
			fCVO.time = httpServletRequest.getParameter("time");
			
			frDao.InsertComment(fCVO);
			
			fVo= fDao.Read(fCVO.freeNum);
			rlist = frDao.querry(fCVO.freeNum);
			int size = rlist.size();
			
			System.out.println(size);
			model.addAttribute("id",user_id);
			model.addAttribute("size",size);
			model.addAttribute("rlist",rlist);
			model.addAttribute("list",fVo);
			return "freeView";
		}
		else if(option.equals("commentDel")) {  //freeboard 댓글 삭제
			int num = Integer.parseInt(httpServletRequest.getParameter("commentNum"));
			frDao.DelComment(num);
			
			fVo = fDao.Read(Integer.parseInt(httpServletRequest.getParameter("freeNum")));
			
			rlist = frDao.querry(Integer.parseInt(httpServletRequest.getParameter("freeNum")));
			int size = rlist.size();
			
			System.out.println(size);
			model.addAttribute("id",user_id);
			model.addAttribute("size",size);
			model.addAttribute("rlist",rlist);
			model.addAttribute("list",fVo);
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

		else if(option.equals("modify")) {//수정 페이지로 이동
			int a = Integer.parseInt(httpServletRequest.getParameter("seq"));
			
			fVo=fDao.Read(a);
			
			fVo.setContent(fVo.getContent().replaceAll("<br>", "\r\n"));
			model.addAttribute("list",fVo);
			model.addAttribute("seq",a);
			return "FreeModify";
		}
		else if(option.equals("modifySuccess")) { //수정 완료
					
			fVo.seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
			fVo.title = httpServletRequest.getParameter("title");
			fVo.content = httpServletRequest.getParameter("content");
			fVo.userId = httpServletRequest.getParameter("writer");
			fVo.time = httpServletRequest.getParameter("time");
			
			fDao.modify(fVo); //수정 쿼리
			
			rlist = frDao.querry(fVo.seq);
			int size = rlist.size();
			
			model.addAttribute("id",user_id);
			model.addAttribute("size",size);
			model.addAttribute("rlist",rlist);
			model.addAttribute("list",fVo);
			return "freeView";
		}
		model.addAttribute("page", page);
		model.addAttribute("list",list);
		// 시작 및 끝 번호
		model.addAttribute("startPageNum", pVo.startPageNum);
		model.addAttribute("endPageNum", pVo.endPageNum);

		// 이전 및 다음 
		model.addAttribute("prev", pVo.prev);
		model.addAttribute("next", pVo.next);
		
		// 현재 페이지
		model.addAttribute("select", Ipage);
		
		return "Free";
	}
	
	//자유게시판 등록
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
				fVO.setImage(null);
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
			if(file.length==0) {
				fVO.setImage(null);
			}
			else {
				fVO.setImage(file);
			}
			
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		fDao.insert(fVO);
		return "redirect:/free";
	}
	
	@RequestMapping(value = "/QnAEnroll", method = {RequestMethod.GET, RequestMethod.POST})
	public String QnAEnroll(HttpServletRequest request, Model model, String title, String content) {
		HttpSession session=request.getSession();
		
		String user_id=(String)session.getAttribute("userId");
		
		MultipartHttpServletRequest mhsr=(MultipartHttpServletRequest) request;
		QnAVO qVO = new QnAVO();
		byte[] file= "0".getBytes();
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format.format(time);
		
		
		try {
			file = mhsr.getFile("imgFile").getBytes();
			if(file.length==0) {
				qVO.setImage(null);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		
		try {
			qVO.setTitle(title);
			qVO.setContent(content);
			qVO.setTime(time1);
			qVO.setUserId(user_id);
			if(file.length==0) {
				qVO.setImage(null);
			}
			else {
				qVO.setImage(file);
			}
			
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		qDao.enrollQnA(qVO);
		return "redirect:/QnA";
	}
	
	//자유게시판,QnA 페이지(<img>)에서 불러오기
	@RequestMapping(value="/getByteImage", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getByteImage(HttpServletRequest request) {//ResponseEntity�� HttpEntity�� ��ӹ������ν� HttpHeader�� body�� ���� �� ����
		String option = request.getParameter("option");
		if(option==null) {
			
		}
		else if(option.equals("free")) {
			FreeBoardVO vo = new FreeBoardVO();
			String a = request.getParameter("number");
			int temp = Integer.parseInt(a) ;
			
				
			vo = fDao.Read(temp);
		    byte[] imageContent = vo.image;
		   
		    final HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.IMAGE_PNG);  
		    return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
		}
		
		else if(option.equals("qna")) {
			QnAVO vo = new QnAVO();
			String a = request.getParameter("number");
			int temp = Integer.parseInt(a) ;
			
				
			vo = qDao.read(temp);
		    byte[] imageContent = vo.image;
		    
		    final HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.IMAGE_PNG);  
		    return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
		}
		return new ResponseEntity<byte[]>(null);
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
		
		HttpSession session=httpServletRequest.getSession();
		
		String user_id=(String)session.getAttribute("userId");;
		if(user_id==null) {
			return "login";
		}
		model.addAttribute("login",user_id);
		
		List<QnAVO> list = (List<QnAVO>)session.getAttribute("list");
		String page = httpServletRequest.getParameter("page");
		if(page==null) {		//웹 페이지에서 넘겨준 값이 없으면 초기 페이지 값 1
			page = "1";
		}
		int Ipage = Integer.parseInt(page);
		qpagingVO pVo = new qpagingVO(Ipage, list);
		
		model.addAttribute("page", page);
		model.addAttribute("list",list);
		// 시작 및 끝 번호
		model.addAttribute("startPageNum", pVo.startPageNum);
		model.addAttribute("endPageNum", pVo.endPageNum);

		// 이전 및 다음 
		model.addAttribute("prev", pVo.prev);
		model.addAttribute("next", pVo.next);

		model.addAttribute("select", Ipage);
		return "SQnA";
	}
	
	//--------------------------자유게시판 검색결과 반환--------------------//
	@RequestMapping(value = "/SFree", method = RequestMethod.GET)
	public String SFree(HttpServletRequest httpServletRequest, Model model) {
		HttpSession session=httpServletRequest.getSession();
		
		String user_id=(String)session.getAttribute("userId");;
		if(user_id==null) {
			return "login";
		}
		model.addAttribute("login",user_id);
		
		List<FreeBoardVO> list = (List<FreeBoardVO>)session.getAttribute("list");
		String page = httpServletRequest.getParameter("page");
		if(page==null) {		//웹 페이지에서 넘겨준 값이 없으면 초기 페이지 값 1
			page = "1";
		}
		int Ipage = Integer.parseInt(page);
		pagingVO pVo = new pagingVO(Ipage, list);
		
		model.addAttribute("page", page);
		model.addAttribute("list",list);
		// 시작 및 끝 번호
		model.addAttribute("startPageNum", pVo.startPageNum);
		model.addAttribute("endPageNum", pVo.endPageNum);

		// 이전 및 다음 
		model.addAttribute("prev", pVo.prev);
		model.addAttribute("next", pVo.next);

		model.addAttribute("select", Ipage);
		return "SFree";
	}
	
	//-------------------------CCTV 분석결과 검색결과 반환------------------//
	@RequestMapping(value = "/ScarList", method = RequestMethod.GET)
	public String ScarList(HttpServletRequest httpServletRequest, Model model) {
		
		HttpSession session=httpServletRequest.getSession();
		
		String user_id=(String)session.getAttribute("userId");;
		if(user_id==null) {
			return "login";
		}
		
		model.addAttribute("login",user_id);
		
		List<listVO> list = (List<listVO>)session.getAttribute("list");
		String page = httpServletRequest.getParameter("page");
		if(page==null) {		//웹 페이지에서 넘겨준 값이 없으면 초기 페이지 값 1
			page = "1";
		}
		int Ipage = Integer.parseInt(page);
		lpagingVO pVo = new lpagingVO(Ipage, list);
		
		model.addAttribute("page", page);
		model.addAttribute("list",list);
		// 시작 및 끝 번호
		model.addAttribute("startPageNum", pVo.startPageNum);
		model.addAttribute("endPageNum", pVo.endPageNum);

		// 이전 및 다음 
		model.addAttribute("prev", pVo.prev);
		model.addAttribute("next", pVo.next);

		model.addAttribute("select", Ipage);
		return "ScarList";
	}
	
	//-------------------------CarModel 검색결과 반환------------------//
		@RequestMapping(value = "/SCarModel", method = RequestMethod.GET)
		public String SCarModel(HttpServletRequest httpServletRequest, Model model) {
			
			HttpSession session=httpServletRequest.getSession();
			
			String user_id=(String)session.getAttribute("userId");;
			if(user_id==null) {
				return "login";
			}
			model.addAttribute("login",user_id);
			
			List<CarKindVO> list = (List<CarKindVO>)session.getAttribute("list");
			String page = httpServletRequest.getParameter("page");
			if(page==null) {		//웹 페이지에서 넘겨준 값이 없으면 초기 페이지 값 1
				page = "1";
			}
			int Ipage = Integer.parseInt(page);
			cpagingVO pVo = new cpagingVO(Ipage, list);
			
			model.addAttribute("page", page);
			model.addAttribute("list",list);
			// 시작 및 끝 번호
			model.addAttribute("startPageNum", pVo.startPageNum);
			model.addAttribute("endPageNum", pVo.endPageNum);

			// 이전 및 다음 
			model.addAttribute("prev", pVo.prev);
			model.addAttribute("next", pVo.next);

			model.addAttribute("select", Ipage);
			
			
			return "SCarModel";
		}
}
	