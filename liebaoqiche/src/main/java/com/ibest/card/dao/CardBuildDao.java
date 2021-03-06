package com.ibest.card.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibest.framework.common.persistence.MyBatisDao;
import com.ibest.card.entity.CardBuild;

import com.ibest.card.dto.input.CardBuildInputDTO;

@MyBatisDao
public interface CardBuildDao {

	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	public int insert(CardBuild cardBuild);
	
	/**
	 * 校验名称唯一性
	 * @param name
	 * @return
	 */
	public CardBuild checkNameUnique(String name);
	
	/**
	 * 根据主键ID删除
	 * @param id
	 * @return
	 */
	public int deleteById(String id);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int deleteByIds(@Param("ids") List<String> ids); 
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	public int update(CardBuild cardBuild);
	
	/**
	 * 根据Id获取唯一记录
	 * @param id
	 * @return
	 */
	public CardBuild findById(String id);
	
	/**
	 * 根据指定对象查询唯一结果
	 * @param entity
	 * @return
	 */
	public CardBuild findOneByObject(CardBuildInputDTO cardBuildInputDto);
	
	/**
	 * 根据对象查询符合条件结果列表
	 * @param entity
	 * @return
	 */
	public List<CardBuild> findByObject(CardBuildInputDTO cardBuildInputDto);
	
	
	/**
	 * 根据对象查询符合条件记录总条数
	 * @param entity
	 * @return
	 */
	public long countByObject(CardBuildInputDTO cardBuildInputDto);
	
}