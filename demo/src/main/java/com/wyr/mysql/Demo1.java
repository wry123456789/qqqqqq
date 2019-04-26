package com.wyr.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.wyr.util.JdbcUtil;

public class Demo1 {
	public List<Student>  getAll() {
		delete();
		List<Student>list =new ArrayList<Student>();
		Connection conn=null;
		PreparedStatement ps=null;
		String sql ="select * from student";
		ResultSet rs=null;
		try {
			conn = JdbcUtil.getConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String sex=rs.getString("sex");
				Date date = rs.getDate("time");
					Student student = new Student(id,name,sex,date);
					list.add(student);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
		System.out.println(list);
		return list;
	}
public boolean delete() {
	Connection conn=null;
	PreparedStatement ps=null;
	String sql ="delete from student WHERE TO_DAYS(NOW()) - TO_DAYS(time)>30";
	try {
		conn = JdbcUtil.getConnection();
		ps=conn.prepareStatement(sql);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	boolean  flag=false;
	
try {
	flag=ps.execute(sql);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {
	JdbcUtil.close(conn, ps);
}
return flag;
	
}
//	public static void main(String[] args) {
//		Demo1 demo1 = new Demo1();
//		demo1.getAll();
//	}
	

}
