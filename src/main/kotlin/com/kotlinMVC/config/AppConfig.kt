package com.kotlinMVC.config

import com.kotlinMVC.database.TodoDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    // initMethod: 빈이 만들어질 때 어떤 메소드를 참조할 지
    @Bean(initMethod = "init")
    fun todoDatabase (): TodoDatabase {
        return TodoDatabase()
    }
}