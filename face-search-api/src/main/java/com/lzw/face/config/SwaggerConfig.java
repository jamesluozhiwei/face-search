package com.lzw.face.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 配置
 * @author jamesluozhiwei
 * @date 2020/01/07
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //指向自己需扫描的包，一般指向 返回实体和控制层
                .apis(RequestHandlerSelectors.basePackage("com.lzw.face"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 设置页面显示信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //页面标题
                .title("face search api")
                //描述
                .description("更多资料请查看：https://www.cqwxhn.xin")
                //版本号
                .version("1.0.0")
                .build();
    }

}
