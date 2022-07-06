package com.github.nurkholik.ddi.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage<Data> {
    int page;
    int total_page;
    long total_data;
    List<Data> data;

    public MyPage(List<Data> data, Page<?> pagedData) {
        setData(data);
        setPage(pagedData.getNumber() + 1);
        setTotal_page(pagedData.getTotalPages());
        setTotal_data(pagedData.getTotalElements());
    }

    public MyPage(Page<?> pagedData, MapData<Data> dataMapper) {
        setData(dataMapper.getData());
        setPage(pagedData.getNumber() + 1);
        setTotal_page(pagedData.getTotalPages());
        setTotal_data(pagedData.getTotalElements());
    }

    public interface MapData<Data> {
        public List<Data> getData();
    }
}