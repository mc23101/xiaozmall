package com.zhangsisiyao.xiaozmall.product.config.knife4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfoBuilder())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhangsisiyao.xiaozmall.product.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfoBuilder() {
        return new ApiInfoBuilder()
                .title("product服务接口文档")
                .description("product服务接口文档")
                .termsOfServiceUrl("服务url")
                .contact(new Contact("zhangsisiyao", "url", "2963456487@qq.com"))
                .version("1.0")
                .build();
    }
}
