package com.apress.springrecipes.court.web.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.apress.springrecipes.court.domain.Member;
import com.apress.springrecipes.court.domain.Members;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.apress.springrecipes.court")
public class CourtRestConfiguration {

    @Bean
    public View membertemplate() {
        return new MarshallingView(jaxb2Marshaller());
    }

    @Bean
    public Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Members.class, Member.class);
        marshaller.setMarshallerProperties(Collections.singletonMap(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE));
        return marshaller;
    }

    @Bean
    public ViewResolver viewResolver() {
        return new BeanNameViewResolver();
    }

}
