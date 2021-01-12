package com.cos.test.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cos.test.config.DB;
import com.cos.test.domain.user.dto.JoinReqDto;
import com.cos.test.domain.user.dto.LoginReqDto;


public class UserDao {
	
	
	public int count() { 
		String sql = "SELECT count(*) FROM User";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();		
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return -1;
	}
	
	public int delete(int userId) {
		String sql = "DELETE FROM User WHERE id = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,userId);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public List<User> list (int page) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT id, username, password, email, role, createDate ");
		sb.append("FROM user ORDER BY id ASC LIMIT ? , 5");
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();
		try {
			String sql = sb.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page*5);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User().builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.password(rs.getString("password"))
						.email(rs.getString("email"))
						.role(rs.getString("role"))
						.createDate(rs.getString("createDate"))
						.build();
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public int findByUsername(String username) {
		String sql = "SELECT id, username, password, role FROM User WHERE username = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return -1;
	}
	
	public User findByUsernameAndPassword(LoginReqDto dto) {
		String sql = "SELECT id, username, password, role FROM User WHERE username = ? AND password = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				User user = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.password(rs.getString("password"))
						.role(rs.getString("role"))
						.build();
						return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
	
	public int join(JoinReqDto dto) {
		String sql = "INSERT INTO user(username, password, email, role, createDate) VALUE (?,?,?, 'USER', now())";           
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
}
