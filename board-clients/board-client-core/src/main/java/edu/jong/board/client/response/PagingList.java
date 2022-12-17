package edu.jong.board.client.response;

import java.io.Serializable;
import java.util.List;

import edu.jong.board.client.request.PagingParam;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PagingList<E> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<E> list;
	
	private long page;
	
	private long count;

	private long totalCount;

	private long totalPage;
	
	private long pageGroup;
	
	private long pageGroupStart;
	
	private long pageGroupEnd;

	@Builder
	public PagingList(List<E> list, PagingParam pagingParam, long totalCount) {
		super();
		
		this.list = list;
		this.count = list.size();
	
		this.totalCount = totalCount;
		this.totalPage = (long) Math.ceil((double) totalCount / pagingParam.getPageRows());
		
		this.pageGroup = (long) Math.ceil((double) this.totalPage / pagingParam.getPageGroupSize());
		
		this.pageGroupStart = (this.pageGroup - 1) * pagingParam.getPageGroupSize();
		this.pageGroupStart = (this.pageGroupStart > 0) ? this.pageGroupStart : 0;
		
		this.pageGroupEnd = this.pageGroup * pagingParam.getPageGroupSize();
		this.pageGroupEnd = (this.totalPage > this.pageGroupEnd) ? this.pageGroupEnd : this.totalPage;
	}

}
