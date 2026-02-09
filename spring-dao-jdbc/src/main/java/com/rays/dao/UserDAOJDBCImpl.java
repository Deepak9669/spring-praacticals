package com.rays.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rays.dto.UserDTO;

@Repository
public class UserDAOJDBCImpl implements UserDAOInt {

	private JdbcTemplate jdbcTemplate;

	private DataSource dataSource = null;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public long nextPk() {
		String sql = (" select max(id) from st_user");

		Long maxId = jdbcTemplate.queryForObject(sql, Long.class);

		if (maxId == null) {
			return 1;

		}
		return maxId + 1;
	}

	public long add(UserDTO dto) {

		long pk = nextPk();

		String sql = ("insert into st_user values( ? , ? , ? , ? , ?)");

		int i = jdbcTemplate.update(sql, pk, dto.getFirstName(), dto.getLastName(), dto.getLogin(), dto.getPassword());

		return i;
	}

	public void delete(int id) {

		String sql = ("delete from st_user where id = ?");

		int i = jdbcTemplate.update(sql, id);

		System.out.println("record deleted: " + i);

	}

	public void update(UserDTO dto) {
		String sql = "update st_user set first_name = ?, last_name = ?, login = ?, password = ? where id = ?";
		int i = jdbcTemplate.update(sql, dto.getFirstName(), dto.getLastName(), dto.getLogin(), dto.getPassword(),
				dto.getId());
		System.out.println("record updated successfully: " + i);
	}

	public UserDTO findByPk(int id) {
		Object[] object = { id };

		String sql = "select * from st_user where id = ?";
		UserDTO dto = jdbcTemplate.queryForObject(sql, object, new UserMaper());
		return dto;
	}

	public UserDTO findByLogin(String login) {
		Object[] object = { login };

		String sql = "select * from st_user where login = ? ";
		UserDTO dto = jdbcTemplate.queryForObject(sql, object, new UserMaper());
		return dto;
	}

	public UserDTO authenticate(String login, String password) {
		Object[] object = { login , password};

		String sql = "select * from st_user where login = ? and password = ? ";
		UserDTO dto = jdbcTemplate.queryForObject(sql, object, new UserMaper());
		return dto;
	}

	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		List<UserDTO> list = null ;
		
		StringBuffer sql = new StringBuffer("select * from st_user where 1=1");
		
		if (dto != null) {
			
			if (dto.getFirstName() != null && dto.getFirstName().length()>0) {
				sql.append(" and first_name like '" + dto.getFirstName() + "%'");
				
			}
			
			if (pageSize >0) {
				pageNo = (pageNo -1 )*pageSize;
				sql.append(" limit " + pageNo + "," + pageSize);
				
			}
			jdbcTemplate.query(sql.toString(), new UserMaper());
			
		}
		
		return list;
	}

}
