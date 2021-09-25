package com.couple.mall.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource messageSource;

    @Test
    void testMessage(){
        String hello = messageSource.getMessage("test", null, null);
        assertThat(hello).isEqualTo("테스트");
    }
    @Test
    void testWithArgsMessage(){
        String hello = messageSource.getMessage("test.args", new Object[]{"형서"}, null);
        assertThat(hello).isEqualTo("테스트 by 형서");
    }
    @Test
    void notFoundMessageCode(){
        assertThatThrownBy(()-> messageSource.getMessage("null",null,null))
                .isInstanceOf(NoSuchMessageException.class);
    }
    @Test
    void enLang(){
        assertThat(messageSource.getMessage("test", null, Locale.ENGLISH)).isEqualTo("test");
    }
}
