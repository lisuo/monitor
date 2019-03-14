package com.monitor.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 *
 * @param <T>
 */
public interface BaseDao<T> extends BaseMapper<T> {

	/**
     * 查询实体集合
	 * 
	 * @param map
	 *            Map
	 * @return List<T>
	 */
	List<T> queryList(Map<String, Object> map);

	/**
	 * 查询总数
	 * 
	 * @param map
	 *            Map
	 * @return int
	 */
	int queryTotal(Map<String, Object> map);

	/**
	 * 删除多个实体
	 * 
	 * @param id
	 *            long[]
	 * @return 影响行数
	 */
	int deleteBatch(Long[] id);

}