package com.apress.springrecipes.vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class NamedJdbcVehicleDao extends NamedParameterJdbcDaoSupport implements VehicleDao {

    private static final String INSERT_SQL = "INSERT INTO VEHICLE (COLOR, WHEEL, SEAT, VEHICLE_NO) VALUES (:color, :wheel, :seat, :vehicleNo)";
    private static final String UPDATE_SQL = "UPDATE VEHICLE SET COLOR=?,WHEEL=?,SEAT=? WHERE VEHICLE_NO=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM VEHICLE";
    private static final String SELECT_ONE_SQL = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = ?";
    private static final String DELETE_SQL = "DELETE FROM VEHICLE WHERE VEHICLE_NO=?";
    private static final String COUNT_ALL_SQL = "SELECT COUNT(*) FROM VEHICLE";
    private static final String SELECT_COLOR_SQL = "SELECT COLOR FROM VEHICLE WHERE VEHICLE_NO=?";


    @Override
    public void insert(final Vehicle vehicle) {

        SqlParameterSource parameterSource = new MapSqlParameterSource(toParameterMap(vehicle));
        getNamedParameterJdbcTemplate().update(INSERT_SQL, parameterSource);
    }

    private Map<String, Object> toParameterMap(Vehicle vehicle) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("vehicleNo", vehicle.getVehicleNo());
        parameters.put("color", vehicle.getColor());
        parameters.put("wheel", vehicle.getWheel());
        parameters.put("seat", vehicle.getSeat());
        return parameters;
    }

    @Override
    public void insert(Collection<Vehicle> vehicles) {
        getJdbcTemplate().batchUpdate(INSERT_SQL, vehicles, vehicles.size(), this::prepareStatement);
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        return getJdbcTemplate().queryForObject(SELECT_ONE_SQL, BeanPropertyRowMapper.newInstance(Vehicle.class), vehicleNo);
    }

    @Override
    public List<Vehicle> findAll() {
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(SELECT_ALL_SQL);
        return rows.stream().map(row -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNo((String) row.get("VEHICLE_NO"));
            vehicle.setColor((String) row.get("COLOR"));
            vehicle.setWheel((Integer) row.get("WHEEL"));
            vehicle.setSeat((Integer) row.get("SEAT"));
            return vehicle;
        }).collect(Collectors.toList());
    }

    private Vehicle toVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(rs.getString("VEHICLE_NO"),
                rs.getString("COLOR"), rs.getInt("WHEEL"),
                rs.getInt("SEAT"));
    }

    private void prepareStatement(PreparedStatement ps, Vehicle vehicle) throws SQLException {
        ps.setString(1, vehicle.getColor());
        ps.setInt(2, vehicle.getWheel());
        ps.setInt(3, vehicle.getSeat());
        ps.setString(4, vehicle.getVehicleNo());
    }

    @Override
    public void update(Vehicle vehicle) {
        getJdbcTemplate().update(UPDATE_SQL, ps -> prepareStatement(ps, vehicle));
    }

    @Override
    public void delete(Vehicle vehicle) {
        getJdbcTemplate().update(DELETE_SQL, vehicle.getVehicleNo());
    }

    @Override
    public String getColor(String vehicleNo) {
        return getJdbcTemplate().queryForObject(SELECT_COLOR_SQL, String.class, vehicleNo);
    }

    @Override
    public int countAll() {
        return getJdbcTemplate().queryForObject(COUNT_ALL_SQL, Integer.class);
    }
}
