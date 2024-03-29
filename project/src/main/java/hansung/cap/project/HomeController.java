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
import org.springframework.web.bind.annotation.RequestParam;
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
	private listDAO lDao; // 차량 리스트 DAO
	@Inject
	private MemberDAO mDao; // 회원가입 및 로그인 DAO
	@Inject
	private QnADAO qDao; // QnA DAO
	@Inject
	private CarKindDAO cDao; // 차 데이터 확인 DAO
	@Inject
	private qCommentDAO qrDao; // QnA 리플 관련 DAO
	@Inject
	private fCommentDAO frDao; // Freeboard 리플 관련 DAO
	@Inject
	private FreeBoardDAO fDao;// 자유 게시판 관련 DAO

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	// ---------------------------------인덱스
	// 화면-------------------------------------//
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("first page return");

		HttpSession session = httpServletRequest.getSession();

		String user_id = (String) session.getAttribute("userId");
		;
		System.out.println("----------------------------------" + user_id);
		model.addAttribute("id", user_id);

		String option = httpServletRequest.getParameter("option");

		if (user_id == null) {
			System.out.println("로그인이 되지 않았습니다");
			model.addAttribute("login", "login");
		} else {
			model.addAttribute("login", user_id);
			model.addAttribute("logOut", "logOut");
		}

		if (option == null) {} 
		else if (option.equals("logOut")) {
			System.out.println("log Out!!!");
			session.invalidate();
			System.out.println("session delete");

			model.addAttribute("login", "login");
			model.addAttribute("logOut", "");
			return "index";
		}

		return "index";
	}

	// ---------------------------------로그인
	// 페이지-------------------------------------//
	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public String login(HttpServletRequest httpServletRequest, Model model, String id, String pw) {

		HttpSession session = httpServletRequest.getSession();
		String referer = httpServletRequest.getHeader("Referer");
		System.out.println(id);
		String user_id = (String) session.getAttribute("userId");
		;

		if (user_id != null) { // 로그인이 될 상황일시
			model.addAttribute("login", user_id);
			return "redirect:" + referer;
		}

		MemberVO mVo = new MemberVO();

		// String option=httpServletRequest.getParameter("option");
		// System.out.println(httpServletRequest.getParameter("userId")+","+httpServletRequest.getParameter("userPw"));
		// System.out.println(option);
		
		if (id == null) {
			System.out.println("option null : loginpage loading");
		}

		else {
			mVo.userId = id;
			mVo.userPw = pw;
			MemberVO login = new MemberVO();
			login = mDao.login(mVo);
			String signal = "no";
			if (login == null) {
				model.addAttribute("failFlag", signal);
			} else {
				session.setAttribute("userId", mVo.userId);
				user_id = (String) session.getAttribute("userId");

				model.addAttribute("id", user_id);
				model.addAttribute("login", user_id);

				model.addAttribute("logOut", "logOut");
				return "index";
			}
		}
		//System.out.println(httpServletRequest.getParameter("id"));
		return "login";
	}

	// ---------------------------------회원가입-------------------------------------//
	@RequestMapping(value = "/create", method = { RequestMethod.POST, RequestMethod.GET })
	public String create(HttpServletRequest httpServletRequest, Model model, String userId, String userPw,
			String userName, String userEmail, String userQuestion) {
		HttpSession session = httpServletRequest.getSession();

		String user_id = (String) session.getAttribute("userId");
		;

		if (user_id != null) { // 로그인이 되어 있을 시
			return "index";
		}

		MemberVO mVo = new MemberVO();

		if (userId == null) {} 
		else {
			mVo.userId = userId;
			mVo.userPw = userPw;
			mVo.userName = userName;
			mVo.userEmail = userEmail;
			mVo.userQuestion = userQuestion;
			int result = mDao.idCheck(mVo);

			if (mVo.userId.equals("manage1234")) {
				model.addAttribute("fail", "manage");
				return "create";
			}

			if (result == 0) { // 중복이 아니라면 쿼리에서 0 반환
				mDao.InsertId(mVo);
				String s = "login";
				model.addAttribute("loginAgain", s);
				System.out.println("create account success");
				return "login";
			} else if (result == 1) { // 중복이라면 쿼리에서 1 반환
				String r = "redundancy";
				model.addAttribute("redundancy", r);
				System.out.println("create account fail : rename id");
				return "create";
			}
		}

		return "create";
	}

	// ---------------------------------비밀번호 찾는
	// 페이지-------------------------------------//
	@RequestMapping(value = "/forgot", method = { RequestMethod.POST, RequestMethod.GET })
	public String forgot(HttpServletRequest httpServletRequest, Model model, String forgot_id, String forgot_email,
			String forgot_answer) {
		HttpSession session = httpServletRequest.getSession();

		String user_id = (String) session.getAttribute("userId");
		;

		if (user_id != null) { // 로그인 되어있을시
			return "index";
		}

		MemberVO mVo = new MemberVO();

		if (forgot_id == null) {

		} else {
			mVo.userId = forgot_id;
			mVo.userEmail = forgot_email;
			mVo.userQuestion = forgot_answer;

			String userPw = mDao.forgot(mVo);

			if (userPw == null) { // 해당 답변에 해당하는 비밀번호가 없을 때
				System.out.println("fail");
				String signal = "no";
				model.addAttribute("failFlag", signal);

			} else { // 비밀번호 알려주는 페이지로 return
				model.addAttribute("userPw", userPw);
				return "forgotRecord";
			}
		}

		return "forgot";
	}

	// ---------------------------------CCTV 분석
	// 페이지-------------------------------------//
	@RequestMapping(value = "/carList", method = RequestMethod.GET)
	public String carList(HttpServletRequest httpServletRequest, Model model) {
		HttpSession session = httpServletRequest.getSession();
		String user_id = (String) session.getAttribute("userId");
		;
		model.addAttribute("id", user_id);

		if (user_id == null) {
			return "login";
		}
		
		List<listVO> list = new ArrayList<listVO>();
		list = lDao.QueryAll();
		listVO lVo = new listVO();
		String timeFlag;
		timeFlag = httpServletRequest.getParameter("timeFlag");
		if(timeFlag==null) {
			timeFlag="0";
		}
		
		model.addAttribute("flag",timeFlag);
		
		String option = httpServletRequest.getParameter("option");
		String Num = httpServletRequest.getParameter("num");
		String key = httpServletRequest.getParameter("key");
		String select = httpServletRequest.getParameter("select");
		

		int pageNum;

		if (Num == null) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(Num);
		}

		Paging paging = new Paging();
		paging.set(pageNum, list.size());

		try {
			if(timeFlag.equals("0")) {
				list = lDao.listPage(paging.displayPost, paging.postNum);
			}
			else {
				list = lDao.listPageTime(paging.displayPost, paging.postNum);
			}
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}

		if (option == null) { // 웹 페이지에서 넘겨준 값이 있으면 해당 페이지 값으로

		} else if (option.equals("search")) { // 검색 기능
			try {
				if (key.length() == 0) {} 
				else {
					if (select.equals("model")) { // 제목으로 검색
						list = lDao.searchName("%" + key + "%");
						paging.set(pageNum, list.size());
						
						if(timeFlag.equals("0")){
							list = lDao.searchModel(paging.displayPost, paging.postNum, "%" + key + "%"); // 이게 문제임
						}
						else {
							list = lDao.searchModelTime(paging.displayPost, paging.postNum, "%" + key + "%"); // 이게 문제임
						}
					} 
					
					else if (select.equals("time")) {
						list = lDao.QueryTime("%" + key + "%");
						paging.set(pageNum, list.size());
						if(timeFlag.equals("0")){
							list = lDao.searchTime(paging.displayPost, paging.postNum, "%" + key + "%"); // 이게 문제임
						}
						else {
							list = lDao.searchTimeTime(paging.displayPost, paging.postNum, "%" + key + "%"); // 이게 문제임
						}
					}
		
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		else if (option.equals("img")) {

			listVO lVO = new listVO();

			select = httpServletRequest.getParameter("sel");

			if (select == null) {

			} else if (select.equals("sel")) {
				String url = httpServletRequest.getParameter("url");
				System.out.println(url);
				url = url.replace("@", "%");
				url = url.replace(" ", "+");
				System.out.println(url);
				lVO = lDao.selectImg("%" + url + "%");
				System.out.println(lVO.imgUrl);
				model.addAttribute("cctv", lVO);

				return "Capture";
			} else {

			}
			int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
			lVO = lDao.selectOne(seq);
			String img = lVO.imgUrl;
			String[] imgUrl = img.split("\\?");
			lVO.setImgUrl(imgUrl[0]);
			
			model.addAttribute("cctv", lVO);

			return "Capture";
		}

		else if (option.equals("cctv")) {
			List<listVO> clist = new ArrayList<listVO>();
			clist = lDao.QueryAll();
			int size = clist.size();
			String url = null;

			for (int i = 0; i < size; i++) {
				System.out.println(clist.get(i).imgUrl);
				String[] imgUrl = clist.get(i).imgUrl.split("\\?");
				if(url==null) {
					url=imgUrl[0];
				}
				else {
					url =  imgUrl[0] + "?" + url;
				}
			}

			System.out.println(url);

			model.addAttribute("list", url);

			return "CCTV";
		}

		else if (option.equals("cctvNow")) {
			int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));

			System.out.println("seq="+seq);
			List<listVO> clist = new ArrayList<listVO>();
			clist = lDao.All();
			int size = clist.size();
			int imgSeq = 0;
			System.out.println(clist.get(0).seq + ", "+clist.get(0).model);
			for (int i = 0; i < size; i++) {
				if (seq == clist.get(i).seq) {
					System.out.println("hi " + clist.get(i).seq);
					imgSeq = i;
					break;
				}
			}
			
			System.out.println(imgSeq);
			String url=null;

			for (int i = imgSeq; i < size; i++) {
				System.out.println(i);
				String[] imgUrl = clist.get(i).imgUrl.split("\\?");
				if(url==null) {
					url=imgUrl[0];
				}
				else {
					url =  url + "?" + imgUrl[0];
				}
				

			}
		
			model.addAttribute("list", url);

			return "CCTV";
		}

		else {

		}
		model.addAttribute("list", list);
		model.addAttribute("page", paging.pageNum);

		// 시작 및 끝 번호
		model.addAttribute("startPageNum", paging.startPageNum);
		model.addAttribute("endPageNum", paging.endPageNum);

		// 이전 및 다음
		model.addAttribute("prev", paging.prev);
		model.addAttribute("next", paging.next);

		// 현재 페이지
		model.addAttribute("select", pageNum);
		
		model.addAttribute("flag", timeFlag);
		model.addAttribute("option", option);
		model.addAttribute("key", key);
		model.addAttribute("sel", select);
		return "carList";
	}

	// ---------------------------------QnA
	// 페이지-------------------------------------//
	@RequestMapping(value = "/QnA", method = RequestMethod.GET)
	public String QnA(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("QnAList return");

		HttpSession session = httpServletRequest.getSession();

		String user_id = (String) session.getAttribute("userId");
		;
		if (user_id == null) {
			return "login";
		}
		System.out.println("----------------------------------" + user_id);

		model.addAttribute("id", user_id);

		List<QnAVO> list = new ArrayList<QnAVO>();
		list = qDao.QueryAll();
		QnAVO qVo = new QnAVO();

		String option = httpServletRequest.getParameter("option");
		String Num = httpServletRequest.getParameter("num");

		int pageNum;

		if (Num == null) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(Num);
		}

		Paging paging = new Paging();
		paging.set(pageNum, list.size());

		try {
			list = qDao.listPage(paging.displayPost, paging.postNum);
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		if (option == null) {

		} 
		
		else if (option.equals("search")) { // 검색
			try {
				String key = httpServletRequest.getParameter("key");
				String select = httpServletRequest.getParameter("select");
				if(key.length() == 0) {
					
				}
				else {
					if (select.equals("title")) {
						list = qDao.searchTitle("%" + key + "%");
						paging.set(pageNum,  list.size());
						
						list = qDao.searchTitle(paging.displayPost, paging.postNum, "%" + key + "%");
					}
					else if (select.equals("userId")) {
						list = qDao.searchUser("%" + key + "%");
						paging.set(pageNum, list.size());

						list = qDao.searchUser(paging.displayPost, paging.postNum, "%" + key + "%");
					}
					else if (select.equals("multi")) { // 둘 다 검색
						list = qDao.search("%" + key + "%");
						paging.set(pageNum, list.size());
						list = qDao.searchAll(paging.displayPost, paging.postNum, "%" + key + "%"); // 이게 문제임

					}

					model.addAttribute("option", option);
					model.addAttribute("key", key);
					model.addAttribute("sel", select);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} 
		
		else if (option.equals("read")) { // 글 열람
			System.out.println("QnA read");
			int a = Integer.parseInt(httpServletRequest.getParameter("seq"));

			qVo = qDao.read(a);

			qVo.setContent(qVo.content.replaceAll("\r\n", "<br>"));

			if (qVo.image != null) {
				String imgUrl = "/getByteImage?option=qna&number=" + a;
				model.addAttribute("imgSrc", imgUrl);
			}

			if ((user_id).equals("manage1234")) { // 관리자 아이디일시 QnA 답변란 제공
				model.addAttribute("manage", user_id);
			}

			String key = httpServletRequest.getParameter("del");

			if (key == null) {

			} else if (key.equals("delete")) { // 답글 삭제
				int num = Integer.parseInt(httpServletRequest.getParameter("num"));
				qrDao.delete(num);
			}

			List<qCommentVO> qlist = new ArrayList<qCommentVO>();
			qlist = qrDao.CommentAll(a); // QnA 답글 select

			if ((qlist) != null) { // 답글이 하나라도 있으면 적용
				model.addAttribute("rlist", qlist);
				model.addAttribute("size",qlist.size());
			}

			model.addAttribute("list", qVo);
			return "QnAView";
		}
		
		else if (option.equals("enroll")) { // QnA 댓글 등록
			qCommentVO vo = new qCommentVO();
			vo.QnANum = Integer.parseInt(httpServletRequest.getParameter("seq"));
			vo.text = httpServletRequest.getParameter("text");
			vo.userId = user_id;
			vo.time = httpServletRequest.getParameter("time");
			qrDao.InsertComment(vo);

			int a = vo.QnANum;

			qVo = qDao.read(a);

			if ((user_id).equals("manage1234")) {
				model.addAttribute("manage", user_id);
			}

			List<qCommentVO> qlist = new ArrayList<qCommentVO>();
			qlist = qrDao.CommentAll(a);

			model.addAttribute("rlist", qlist);
			model.addAttribute("list", qVo);

			return "QnAView";
		} 
		
		else if (option.equals("goToenroll")) { // QnA 작성 칸으로 이동
			model.addAttribute("id", user_id);
			return "QnAWrite";
		}

		else if (option.equals("modify")) { // QnA 글 수정페이지로 이동
			int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
			qVo = qDao.read(seq);
			qVo.setContent(qVo.getContent().replace("<br>", "\r\n"));
			model.addAttribute("list", qVo);
			model.addAttribute("id", user_id);
			return "QnAModify";
		} 
		

		else if (option.equals("delQnA")) { // QnA 글 삭제
			int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
			qDao.delete(seq);
			qrDao.deleteAll(seq);
		}
		model.addAttribute("list", list);
		model.addAttribute("page", paging.pageNum);

		// 시작 및 끝 번호
		model.addAttribute("startPageNum", paging.startPageNum);
		model.addAttribute("endPageNum", paging.endPageNum);

		// 이전 및 다음
		model.addAttribute("prev", paging.prev);
		model.addAttribute("next", paging.next);

		// 현재 페이지
		model.addAttribute("select", pageNum);
		return "QnA";
	}

	// --------------------carModel페이지-------------------------//
	@RequestMapping(value = "/CarModel", method = RequestMethod.GET)
	public String carModel(HttpServletRequest httpServletRequest, Model model) {
		// 많은 차들의 다양한 정보를 제공하는 페이지
		//HttpSession session = httpServletRequest.getSession();

		List<CarKindVO> list = new ArrayList<CarKindVO>();
		list = cDao.QuerryAll();
		CarKindVO cVO = new CarKindVO();
		
		String option = httpServletRequest.getParameter("option");
		String Num = httpServletRequest.getParameter("num");

		int pageNum;

		if (Num == null) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(Num);
		}

		Paging paging = new Paging();
		paging.postNum = 6;
		paging.set(pageNum, list.size());

		try {
			list = cDao.listPage(paging.displayPost, paging.postNum);
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}

		if (option == null) {
		} else if (option.equals("search")) { // 검색 기능
			try {
			String key = httpServletRequest.getParameter("key");
			String select = httpServletRequest.getParameter("select");
			if (key.length() == 0) {
			}
			if (select.equals("carkind")) { // 차 이름으로 검색
				list = cDao.Querrycar("%" + key + "%");
				paging.set(pageNum,  list.size());
				
				list = cDao.searchKind(paging.displayPost, paging.postNum, "%" + key + "%");
			} else if (select.equals("carmaker")) { //제조사로 검색
				list = cDao.QuerryMaker("%" + key + "%");
				paging.set(pageNum,  list.size());
				
				list = cDao.searchMaker(paging.displayPost, paging.postNum, "%" + key + "%");
			}
			model.addAttribute("option", option);
			model.addAttribute("key", key);
			model.addAttribute("sel", select);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("page", paging.pageNum);

		// 시작 및 끝 번호
		model.addAttribute("startPageNum", paging.startPageNum);
		model.addAttribute("endPageNum", paging.endPageNum);

		// 이전 및 다음
		model.addAttribute("prev", paging.prev);
		model.addAttribute("next", paging.next);

		// 현재 페이지
		model.addAttribute("select", pageNum);
		return "CarModel";
	}

	// ----------------------------자유게시판------------------------------//
	@RequestMapping(value = "/free", method = { RequestMethod.GET, RequestMethod.POST })
	public String free(HttpServletRequest httpServletRequest, Model model) {
		HttpSession session = httpServletRequest.getSession();
		String user_id = (String) session.getAttribute("userId");
		;
		model.addAttribute("id", user_id);

		if (user_id == null) { // 로그인 안되어있을시 로그인 페이지로 이동
			return "login";
		}
		List<fCommentVO> rlist = new ArrayList<fCommentVO>(); // 리플 관련 리스트
		List<FreeBoardVO> list = new ArrayList<FreeBoardVO>(); // 게시판 관련 리스트
		list = fDao.QueryAll();
		FreeBoardVO fVo = new FreeBoardVO();

		String option = httpServletRequest.getParameter("option");
		String Num = httpServletRequest.getParameter("num");

		int pageNum;

		if (Num == null) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(Num);
		}

		Paging paging = new Paging();
		paging.set(pageNum, list.size());

		try {
			list = fDao.listPage(paging.displayPost, paging.postNum);
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}

		if (option == null) {

		} 
		
		else if (option.equals("search")) { // freeboard 검색
			try {

				String key = httpServletRequest.getParameter("key");
				String select = httpServletRequest.getParameter("select");
				if (key.length() == 0) {

				} 
				else {
					if (select.equals("title")) { // 제목으로 검색
						list = fDao.SearchTitle("%" + key + "%");
						paging.set(pageNum, list.size());

						list = fDao.searchTitle(paging.displayPost, paging.postNum, "%" + key + "%"); // 이게 문제임

					} 
					
					else if (select.equals("userId")) { // 작성자로 검색
						list = fDao.SearchUser("%" + key + "%");
						paging.set(pageNum, list.size());

						list = fDao.searchUser(paging.displayPost, paging.postNum, "%" + key + "%"); // 이게 문제임

					} 
					
					else if (select.equals("multi")) { // 둘 다 검색
						list = fDao.Search("%" + key + "%");
						paging.set(pageNum, list.size());
						list = fDao.searchAll(paging.displayPost, paging.postNum, "%" + key + "%"); // 이게 문제임

					}

					model.addAttribute("option", option);
					model.addAttribute("key", key);
					model.addAttribute("sel", select);
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} 
		
		else if (option.equals("view")) { // freeboard 보기
			int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));

			fVo = fDao.Read(seq);
			System.out.println(seq);
			rlist = frDao.querry(seq);
			int size = rlist.size();

			fVo.setContent(fVo.content.replaceAll("\r\n", "<br>"));
			if (fVo.image != null) {
				String imgUrl = "/getByteImage?option=free&number=" + seq;
				model.addAttribute("imgSrc", imgUrl);
			}

			model.addAttribute("id", user_id);
			model.addAttribute("size", size);
			model.addAttribute("rlist", rlist);
			model.addAttribute("list", fVo);
			return "freeView";
		} 
		
		else if (option.equals("comment")) { // 댓글 쓰기
			fCommentVO fCVO = new fCommentVO();
			fCVO.userId = user_id;
			fCVO.text = httpServletRequest.getParameter("comment");
			fCVO.freeNum = Integer.parseInt(httpServletRequest.getParameter("seq"));
			fCVO.time = httpServletRequest.getParameter("time");

			frDao.InsertComment(fCVO);

			fVo = fDao.Read(fCVO.freeNum);
			rlist = frDao.querry(fCVO.freeNum);
			int size = rlist.size();

			System.out.println(size);
			model.addAttribute("id", user_id);
			model.addAttribute("size", size);
			model.addAttribute("rlist", rlist);
			model.addAttribute("list", fVo);
			return "freeView";
		}
		
		else if (option.equals("commentDel")) { // freeboard 댓글 삭제
			int num = Integer.parseInt(httpServletRequest.getParameter("commentNum"));
			frDao.DelComment(num);

			fVo = fDao.Read(Integer.parseInt(httpServletRequest.getParameter("freeNum")));

			rlist = frDao.querry(Integer.parseInt(httpServletRequest.getParameter("freeNum")));
			int size = rlist.size();

			System.out.println(size);
			model.addAttribute("id", user_id);
			model.addAttribute("size", size);
			model.addAttribute("rlist", rlist);
			model.addAttribute("list", fVo);
			return "freeView";
		} 
		
		else if (option.equals("delFree")) { // freeboard 글 삭제
			int seq = Integer.parseInt(httpServletRequest.getParameter("seq"));
			fDao.deleteFree(seq);
			frDao.DelCommentAll(seq);
			list = fDao.QueryAll();
			paging.set(pageNum, list.size());
			
			try {
				list = fDao.listPage(paging.displayPost, paging.postNum);
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}

		} 
		
		else if (option.equals("gotoEnroll")) { // 글 등록 페이지 이동
			model.addAttribute("id", user_id);
			return "FreeWrite";
		}

		else if (option.equals("modify")) {// 수정 페이지로 이동
			int a = Integer.parseInt(httpServletRequest.getParameter("seq"));

			fVo = fDao.Read(a);

			fVo.setContent(fVo.getContent().replaceAll("<br>", "\r\n"));
			model.addAttribute("list", fVo);
			model.addAttribute("seq", a);
			return "FreeModify";
		} 
		System.out.println(paging.startPageNum);
		System.out.println(paging.endPageNum);
		System.out.println(paging.pageNum);

		model.addAttribute("list", list);
		model.addAttribute("page", paging.pageNum);

		// 시작 및 끝 번호
		model.addAttribute("startPageNum", paging.startPageNum);
		model.addAttribute("endPageNum", paging.endPageNum);

		// 이전 및 다음
		model.addAttribute("prev", paging.prev);
		model.addAttribute("next", paging.next);

		// 현재 페이지
		model.addAttribute("select", pageNum);

		return "Free";
	}

	// 자유게시판 등록
	@RequestMapping(value = "/freeEnroll", method = { RequestMethod.GET, RequestMethod.POST })
	public String freeEnroll(HttpServletRequest request, Model model, String title, String content) {

		HttpSession session = request.getSession();

		String user_id = (String) session.getAttribute("userId");

		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
		FreeBoardVO fVO = new FreeBoardVO();
		byte[] file = "0".getBytes();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format.format(time);

		try {
			file = mhsr.getFile("imgFile").getBytes();
			if (file.length == 0) {
				fVO.setImage(null);
			}

		} catch (IOException e1) {
			
			System.out.println(e1.getMessage());
		}

		try {
			fVO.setTitle(title);
			fVO.setContent(content);
			fVO.setTime(time1);
			fVO.setUserId(user_id);
			if (file.length == 0) {
				fVO.setImage(null);
			} else {
				fVO.setImage(file);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String option = request.getParameter("option");
		
		if(option==null) {
			fDao.insert(fVO);
		}
		else if(option.equals("modify")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			fVO.setSeq(seq);
			fDao.modify(fVO);
			
			return "redirect:/free?option=view&seq="+seq;
		}
		
		return "redirect:/free";
	}

	@RequestMapping(value = "/QnAEnroll", method = { RequestMethod.GET, RequestMethod.POST })
	public String QnAEnroll(HttpServletRequest request, Model model, String title, String content) {
		HttpSession session = request.getSession();

		String user_id = (String) session.getAttribute("userId");

		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
		QnAVO qVO = new QnAVO();
		byte[] file = "0".getBytes();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format.format(time);

		try {
			file = mhsr.getFile("imgFile").getBytes();
			if (file.length == 0) {
				qVO.setImage(null);
			}

		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}

		try {
			qVO.setTitle(title);
			qVO.setContent(content);
			qVO.setTime(time1);
			qVO.setUserId(user_id);
			if (file.length == 0) {
				qVO.setImage(null);
			} else {
				qVO.setImage(file);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String option = request.getParameter("option");
		
		if(option==null) {
			qDao.enrollQnA(qVO);
		}
		else if(option.equals("modify")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			qVO.setSeq(seq);
			qDao.modify(qVO);
			
			return "redirect:/QnA?option=read&seq="+seq;
		}
		
		
		return "redirect:/QnA";
	}

	// 자유게시판,QnA 페이지(<img>)에서 불러오기
	@RequestMapping(value = "/getByteImage", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getByteImage(HttpServletRequest request) {// ResponseEntity�� HttpEntity�� ��ӹ������ν�
																			// HttpHeader�� body�� ���� �� ����
		String option = request.getParameter("option");
		if (option == null) {

		} else if (option.equals("free")) {
			FreeBoardVO vo = new FreeBoardVO();
			String a = request.getParameter("number");
			int temp = Integer.parseInt(a);

			vo = fDao.Read(temp);
			byte[] imageContent = vo.image;

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
		}

		else if (option.equals("qna")) {
			QnAVO vo = new QnAVO();
			String a = request.getParameter("number");
			int temp = Integer.parseInt(a);

			vo = qDao.read(temp);
			byte[] imageContent = vo.image;

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
		}
		return new ResponseEntity<byte[]>(null);
	}

	// -------------------------만든 이들 소개 페이지----------------------//
	@RequestMapping(value = "/developer", method = RequestMethod.GET)
	public String freeBoard(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("developer page return");
		HttpSession session = httpServletRequest.getSession();

		String user_id = (String) session.getAttribute("userId");
		
		model.addAttribute("login", user_id);
		return "developer";
	}

	
}
