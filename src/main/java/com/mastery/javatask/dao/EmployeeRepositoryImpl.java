package com.mastery.javatask.dao;

import com.mastery.javatask.dto.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository{

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("select * from employee order by employee_id desc", this::getEmployeeRowMapper);
    }

    private Employee getEmployeeRowMapper(ResultSet rs, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getLong("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDepartmentId(rs.getLong("department_id"));
        employee.setJobTitle(rs.getString("job_title"));
        employee.setGender(rs.getString("gender"));
        employee.setDateOfBirth(rs.getDate("date_of_birth"));
        return employee;
    }

    @Override
    public Employee findOne(Long id) {
        final String query = "select * from employee where employee_id = ?";
        return jdbcTemplate.queryForObject(query, this::getEmployeeRowMapper, id);
    }

    @Override
    public Employee save(Employee employee) {
        final String query = "insert into employee (first_name, last_name, department_id, job_title, gender, date_of_birth) " +
                "values (:firstName, :lastName, :departmentId, :jobTitle, :gender, :dateOfBirth);";
        MapSqlParameterSource params = generateParamsMap(employee);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(query, params, keyHolder, new String[]{"employee_id"});
        long createdEmployeeId = Objects.requireNonNull(keyHolder.getKey().longValue());
        return findOne(createdEmployeeId);
    }

    private MapSqlParameterSource generateParamsMap(Employee employee){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("firstName", employee.getFirstName());
        params.addValue("lastName", employee.getLastName());
        params.addValue("departmentId", employee.getDepartmentId());
        params.addValue("jobTitle", employee.getJobTitle());
        params.addValue("gender", employee.getGender());
        params.addValue("dateOfBirth", employee.getDateOfBirth());
        return params;
    }

    @Override
    public Employee update(Employee employee) {
        final String query = "update employee set first_name = :firstName, last_name = :lastName, " +
                "department_id = :departmentId, job_title = :jobTitle, gender = :gender, " +
                "date_of_birth = :dateOfBirth where employee_id = " + employee.getEmployeeId();
        MapSqlParameterSource params = generateParamsMap(employee);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(query, params, keyHolder, new String[]{"employee_id"});
        long updatedEmployeeId = Objects.requireNonNull(keyHolder.getKey().longValue());
        return findOne(updatedEmployeeId);
    }

    @Override
    public void delete(Long id) {
        final String query = "delete from employee where employee_id = ?";
        jdbcTemplate.update(query, id);
    }
}
