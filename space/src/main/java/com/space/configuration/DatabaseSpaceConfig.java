package com.space.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "spacedbEntityManagerFactory",
        basePackages = {
                "com.space.database.space"
        },
        transactionManagerRef = "spaceTransactionManager"
)
public class DatabaseSpaceConfig {
    @Primary
    @Bean(name = "spaceDataSourceProperties")
    @ConfigurationProperties("space.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "spaceDataSource")
    @ConfigurationProperties("space.datasource.configuration")
    public DataSource dataSource(
            @Qualifier("spaceDataSourceProperties") DataSourceProperties spaceDataSourceProperties) {
        return spaceDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "spacedbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("spaceDataSource") DataSource spaceDataSource) {
        return builder
                .dataSource(spaceDataSource)
                .packages("com.space.database")
                .persistenceUnit("space")
                .build();
    }

    @Primary
    @Bean(name = "spaceTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("spacedbEntityManagerFactory") EntityManagerFactory db1EntityManagerFactory) {
        return new JpaTransactionManager(db1EntityManagerFactory);
    }

}
