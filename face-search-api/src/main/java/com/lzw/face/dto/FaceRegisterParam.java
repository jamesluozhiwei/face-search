package com.lzw.face.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 人脸注册参数
 * @author jamesluozhiwei
 * @date 2020/01/05 14:07
 */
@Data
@Builder
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
public class FaceRegisterParam  implements Serializable {


    private static final long serialVersionUID = 1L;

    private String openKey;

    private long personId;

    private List<String> imgData;

}
