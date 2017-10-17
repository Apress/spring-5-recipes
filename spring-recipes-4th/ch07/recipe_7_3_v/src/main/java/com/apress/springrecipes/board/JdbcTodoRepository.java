package com.apress.springrecipes.board;

import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
class JdbcTodoRepository implements TodoRepository {

    private final JdbcTemplate jdbc;

    JdbcTodoRepository(DataSource dataSource) {
        this.jdbc=new JdbcTemplate(dataSource);
    }
    
    @Override
    public List<Todo> findAll() {
        return this.jdbc.query("select * from todo order by id", BeanPropertyRowMapper.newInstance(Todo.class));
    }

    @Override
    public List<Todo> findByOwner(String owner) {
        return this.jdbc.query("select * from todo where owner=? order by id", BeanPropertyRowMapper.newInstance(Todo.class), owner);
    }

    @Override
    public Todo findOne(long id) {
        return this.jdbc.queryForObject("select * from todo where id=?", BeanPropertyRowMapper.newInstance(Todo.class), id);
    }

    @Override
    public Todo save(Todo todo) {

        if (todo.getId() == null) {
            final String sql = "insert into todo (owner, description, completed) values (?,?,?)";
            final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

            this.jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, todo.getOwner());
                ps.setString(2, todo.getDescription());
                ps.setBoolean(3, todo.isCompleted());
                return ps;
            }, keyHolder);

            todo.setId(keyHolder.getKey().longValue());
        } else {
            final String sql = "update todo set owner=?, description=?, completed=? where id=?";
            this.jdbc.update(sql, todo.getOwner(), todo.getDescription(), todo.isCompleted(), todo.getId());
        }
        return todo;
    }

    @Override
    public void remove(long id) {
        this.jdbc.update("delete from todo where id=?", id);
    }
}
