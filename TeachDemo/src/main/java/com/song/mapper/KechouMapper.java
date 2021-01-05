package com.song.mapper;

import java.util.List;

import com.song.pojo.Kechou;

public interface KechouMapper {

	List<Kechou> selectById(int tid);
	void add(Kechou k);
	void update(Kechou k);
	void delete(int id);
}
