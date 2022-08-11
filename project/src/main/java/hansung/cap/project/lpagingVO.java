package hansung.cap.project;

import java.util.List;

public class lpagingVO {
	//게시물 총개수
		int count;
		
		//한 페이지에 출력할 게시물 갯수
		int postNum;
		
		//하단 페이징 번호([게시물 총 갯수 / 한 페이지에 출력할 개수]의 올림)
		int pageNum;
		
		//출력할 게시물
		int displayPost;
		
		// 한번에 표시할 페이징 번호의 갯수
		int pageNum_cnt;

		// 표시되는 페이지 번호 중 마지막 번호
		int endPageNum;

		// 표시되는 페이지 번호 중 첫번째 번호
		int startPageNum;
		
		// 마지막 번호 재계산
		int endPageNum_tmp;

		//이전, 다음 버튼 유무
		boolean prev;
		boolean next;
		
		//리스트
		List<listVO> list;
		
		public lpagingVO() {
			
			this.count=0;
			this.postNum=0;
			this.pageNum=0;
			this.displayPost=0;
			this.pageNum_cnt=0;
			this.endPageNum=0;
			this.startPageNum=0;
			this.endPageNum_tmp=0;
			this.prev=true;
			this.next=true;
			this.list = null;
			
		}
		
		public lpagingVO(int num, List<listVO> list) {
			this.count = list.size();
			this.postNum=10;
			this.pageNum = (int)Math.ceil((double)count/postNum);
			this.displayPost = (num-1)*postNum;
			this.pageNum_cnt=3;
			this.endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
			this.startPageNum = endPageNum - (pageNum_cnt - 1);
			this.endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
			this.prev = startPageNum == 1 ? false : true;
			this.next = endPageNum * pageNum_cnt >= endPageNum_tmp ? false : true;
			
			if(endPageNum > pageNum) {
				 endPageNum = pageNum;
				}
			
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getPostNum() {
			return postNum;
		}

		public void setPostNum(int postNum) {
			this.postNum = postNum;
		}

		public int getPageNum() {
			return pageNum;
		}

		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}

		public int getDisplayPost() {
			return displayPost;
		}

		public void setDisplayPost(int displayPost) {
			this.displayPost = displayPost;
		}

		public int getPageNum_cnt() {
			return pageNum_cnt;
		}

		public void setPageNum_cnt(int pageNum_cnt) {
			this.pageNum_cnt = pageNum_cnt;
		}

		public int getEndPageNum() {
			return endPageNum;
		}

		public void setEndPageNum(int endPageNum) {
			this.endPageNum = endPageNum;
		}

		public int getStartPageNum() {
			return startPageNum;
		}

		public void setStartPageNum(int startPageNum) {
			this.startPageNum = startPageNum;
		}

		public int getEndPageNum_tmp() {
			return endPageNum_tmp;
		}

		public void setEndPageNum_tmp(int endPageNum_tmp) {
			this.endPageNum_tmp = endPageNum_tmp;
		}

		public boolean isPrev() {
			return prev;
		}

		public void setPrev(boolean prev) {
			this.prev = prev;
		}

		public boolean isNext() {
			return next;
		}

		public void setNext(boolean next) {
			this.next = next;
		}

		public List<listVO> getList() {
			return list;
		}

		public void setList(List<listVO> list) {
			this.list = list;
		}
}
