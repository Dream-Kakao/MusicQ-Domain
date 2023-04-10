package com.musicq.musicqdomain.room.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageResponseDTO<E> {

	private int page;
	private int size;
	private int total;

	// 시작 페이지 번호
	private int start;

	// 끝 페이지 번호
	private int end;

	// 이전 페이지의 존재 여부
	private boolean prev;

	// 다음 페이지의 존재 여부
	private boolean next;

	private List<E> dtoList;

	@Builder(builderMethodName = "withAll")
	public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
		if (total <= 0) {
			return;
		}

		this.page = pageRequestDTO.getPage();
		this.size = pageRequestDTO.getSize();

		this.end = (int)(Math.ceil(this.page / 6.0)) * 6; // 화면 마지막 번호

		this.start = this.end - 5; //화면에서의 시작 번호

		int last = (int)(Math.ceil((total / (double)size))); //데이터의 개수를 계산한 마지막 페이지 번호

		this.end = end > last ? last : end;

		this.prev = this.start > 1;

		this.next = total > this.end * this.size;
	}
}
