package com.github.nurkholik.ddi.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PageUtil {
	
	public static synchronized PageRequest toPageable(int page, int row) {
		return row <= 0 ? null : PageRequest.of( (page <= 0 ? 1 : page) -1, row);
	}

	public static synchronized PageRequest toPageable(int page, int row, Sort.Order...orders) {
		return row <= 0 ? null : PageRequest.of( (page <= 0 ? 1 : page) -1, row, Sort.by(orders));
	}
	
	public static synchronized int getOffset(int page, int row) {
		return (page -1) * row;
	}
	
	public static synchronized int getPage(int totalRecord, int row) {
		return BigDecimal.valueOf((double) totalRecord / (double) row).setScale(0, RoundingMode.UP).intValue();
	}

	public static synchronized PageRequest toPageable2(int page, int row) {
		return toPageable2(page, row, null);
	}

	public static synchronized PageRequest toPageable2(int page, int row, Sort sort) {
		return row <= 0 ? null :
				sort == null ?
						PageRequest.of( (page <= 0 ? 1 : page) -1, row) :
						PageRequest.of( (page <= 0 ? 1 : page) -1, row, sort);
	}
	
}
