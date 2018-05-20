package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Project;

@Mapper
public interface ProjectDao {
    int insert(@Param("pojo") Project pojo);

    int insertSelective(@Param("pojo") Project pojo);

    int insertList(@Param("pojos") List<Project> pojo);

    int update(@Param("pojo") Project pojo);

    List<Project> findByUuid(@Param("uuid")String uuid);

    Project findFirstByUuid(@Param("uuid")String uuid);

    List<Project> findByStatusAndUuidIn(@Param("status")Integer status,@Param("uuidList")List<String> uuidList);



    /**
     * raceinfoDetail页面需要项目的查询
     * 查询所有登陆学生的没有参加该赛事的项目
     * @param stuUuid 学生UUID
     * @param raceinfoUuid 竞赛信息UUID
     * @return List<Project> 登陆学生的没有参加该赛事的项目列表
     */
    List<Project> getProjectForRaceinfoDetail(@Param("stuUuid") String stuUuid,@Param("raceinfoUuid") String raceinfoUuid);

    Integer countByStatus(@Param("status")Integer status);

    List<Project> findByStatus(@Param("status")Integer status);


    List<Project> find();

    Project findById(@Param("id")Integer id);


    List<Project> findByStatusAndTUuid(@Param("status")Integer status,@Param("tUuid")String tUuid);










}
