package com.apress.springrecipes.caching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JdbcCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbc;

    public JdbcCustomerRepository(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    @Cacheable(value = "customers")
    public Customer find(long customerId) {
        final String sql = "SELECT id, name FROM customer WHERE id=?";
        return jdbc.query(sql, (rs, rowNum) -> {
            Customer customer = new Customer(rs.getLong(1));
            customer.setName(rs.getString(2));
            return customer;
        }, customerId).stream().findFirst().orElse(null);
    }

    @Override
    @CachePut(value="customers", key = "#result.id")
    public Customer create(String name) {

        final String sql = "INSERT INTO customer (name) VALUES (?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            return ps;
        }, keyHolder);

        Customer customer = new Customer(keyHolder.getKey().longValue());
        customer.setName(name);

        return customer;
    }

    @Override
    @CacheEvict(value="customers", key="#customer.id")
    public void update(Customer customer) {
        final String sql = "UPDATE customer SET name=? WHERE id=?";
        jdbc.update(sql, customer.getName(), customer.getId());
    }

    @Override
    @CacheEvict(value="customers")
    public void remove(long customerId) {
        final String sql = "DELETE FROM customer WHERE id=?";
        jdbc.update(sql, customerId);
    }
}
