package hansung.cap.project;

public class Paging {
	public int count; //총 게시물 수
    public int postNum = 10; //한페이지에 출력할 게시물 개수
    public int pageNum;
    public int displayPost;
    public int pageNum_cnt;
    public int endPageNum;
    public int startPageNum;
    public int endPageNum_tmp;
    public boolean prev;
    public boolean next;

    void set(int num, int size){
        this.count=size;
        this.pageNum = (int)Math.ceil((double)count/postNum);
        this.displayPost = (num-1)*postNum;
        this.pageNum_cnt = 3;
        this.endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
        this.startPageNum = endPageNum - (pageNum_cnt - 1);
        this.endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
               
        if(endPageNum > pageNum) {
			 endPageNum = pageNum;
		}
       this.prev = startPageNum == 1 ? false : true;
       this.next = endPageNum >= pageNum ? false : true;
   }
}




