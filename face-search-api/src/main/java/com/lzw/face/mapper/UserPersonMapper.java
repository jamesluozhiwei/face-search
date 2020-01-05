package com.lzw.face.mapper;

import com.lzw.face.entity.UserPerson;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jamesluozhiwei
 * @since 2019-12-23
 */
public interface UserPersonMapper extends BaseMapper<UserPerson> {

    /**
     * 获取人物标签
     * @param openKey
     * @param ids
     * @return
     */
    Set<String> listPersonTag(@Param("openKey") String openKey,@Param("ids") List<Long> ids);

}
