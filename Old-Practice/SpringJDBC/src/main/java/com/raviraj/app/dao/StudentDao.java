package com.raviraj.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.raviraj.app.model.Student;

@Repository
public class StudentDao {
   //Final (must use and we can't change)
	private final JdbcTemplate jdbcTemplate;
	//find and inject
	public StudentDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//RowMapper Interface
	// RowMapper to convert ResultSet → Student object
	RowMapper<Student> rowMapper = (rs, rowNum) -> 
		 new Student( 
				 rs.getInt("rollno"),
				 rs.getString("name"),
				 rs.getInt("marks")
				);	
	

	public void save(Student s) {
		String pst = "insert into Student(rollno, name, marks) values (?, ?, ?)";
		int rows = jdbcTemplate.update(pst, s.getRollno(), s.getName(), s.getMarks());
		System.out.println(rows + " row Affected");
	}

	public List<Student> findAll(){
		List<Student> students = new ArrayList<>();
		String pst = "Select * from Student"; 
		
		return jdbcTemplate.query(pst, rowMapper);
	}
}
