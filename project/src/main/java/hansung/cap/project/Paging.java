package hansung.cap.project;

public class Paging {
	int count; //총 게시물 수
    int postNum = 10; //한페이지에 출력할 게시물 개수
    int pageNum;
    int displayPost;
    int pageNum_cnt;
    int endPageNum;
    int startPageNum;
    int endPageNum_tmp;
    boolean prev;
    boolean next;

    void set(int num, int size){
        this.count=size;
        this.pageNum = (int)Math.ceil((double)count/postNum);
        this.displayPost = (num-1)*postNum;
        this.pageNum_cnt = 3;
        this.endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
        this.startPageNum = endPageNum - (pageNum_cnt - 1);
        this.endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
        this.prev = startPageNum == 1 ? false : true;
        this.next = endPageNum * pageNum_cnt >= endPageNum_tmp ? false : true;
        
        if(endPageNum > pageNum) {
			 endPageNum = pageNum;
			}
        
    }
}
