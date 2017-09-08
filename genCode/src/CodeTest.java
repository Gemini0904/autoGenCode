import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CodeTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null; 
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("show full columns from rd_templet ");
			
			while (rs.next()) {
				String field = rs.getString("Field") ;
				String comment = rs.getString("Comment") ;
				String type = rs.getString("Type") ;
				type = type.split("\\(")[0];
				String txt = "private ";
				
				String[] fields = field.split("_");
				String finalField = "";
				for (int i = 0; i <fields.length; i ++) {
					String value = fields[i];
					if (i!=0) {
						value =value.substring(0, 1).toUpperCase() + value.substring(1);
					}
					finalField += value;
				}
				String jdbcType = "";
				if ("bigint".equals(type)) {
					txt += "Long ";
					jdbcType = "NUMERIC";
				}else if ("varchar".equals(type)) {
					txt += "String ";
					jdbcType = "VARCHAR";
				}else if ("decimal".equals(type)) {
					txt += "BigDecimal ";
					jdbcType = "NUMERIC";
				}else if ("char".equals(type)) {
					txt += "String ";
					jdbcType = "VARCHAR";
				}else if ("int".equals(type)) {
					txt += "Integer ";
					jdbcType = "NUMERIC";
				}
//				System.out.print(field+",");//表字段
//				System.out.print("@"+finalField+",");//属性字段(artery用)
//				System.out.print("#{"+finalField+",jdbcType="+jdbcType+"},");//属性字段(mybatis用)
//				System.out.println("/** " + comment + "  */");
//				System.out.println(txt + finalField + ";");
				System.out.println("if(jsonObject.has(\"" + finalField + "\")){//"+comment);
				System.out.println("     rdTemplet.set"+finalField.replace(finalField.substring(0, 1), finalField.substring(0, 1).toUpperCase())+"(jsonObject.get(\"" + finalField + "\")+\"\");");
				System.out.println(" }");
//				System.out.println("<if test=\""+finalField+" != null and "+finalField +" !='' \">  and a."+ field +"= #{"+finalField+",jdbcType="+jdbcType+"} </if>");
//				System.out.println("<if test=\""+finalField+" != null and "+finalField +" !='' \"> "+ field +"= #{"+finalField+",jdbcType="+jdbcType+"} ,</if>");
//				System.out.println("<element name=\""+finalField+"\" type=\"string\">");
//				System.out.println(" <annotation>");
//				System.out.println("  <documentation>"+comment+"</documentation>");
//				System.out.println(" </annotation>");
//				System.out.println("</element>");
//				System.out.println("@XNode(\""+finalField+"\")");
//				System.out.println("private String " + finalField + ";   // " + comment);
			}
			
//			System.out.println("====================================");
			
			while (rs.next()) {
				String field = rs.getString("Field") ;
				String txt = "<result column=\"";
				String[] fields = field.split("_");
				String finalField = "";
				for (int i = 0; i <fields.length; i ++) {
					String value = fields[i];
					if (i!=0) {
						value =value.substring(0, 1).toUpperCase() + value.substring(1);
					}
					finalField += value;
				}
				System.out.println(txt +field + "\" property=\"" +finalField + "\" />");
			}
			
			while (rs.next()) {
				String field = rs.getString("Field") ;
				String comment = rs.getString("Comment") ;
				String type = rs.getString("Type") ;
				type = type.split("\\(")[0];
				String txt = "private ";
				
				String[] fields = field.split("_");
				String finalField = "";
				for (int i = 0; i <fields.length; i ++) {
					String value = fields[i];
					if (i!=0) {
						value =value.substring(0, 1).toUpperCase() + value.substring(1);
					}
					finalField += value;
				}
				if ("bigint".equals(type)) {
					txt += "Long ";
				}else if ("varchar".equals(type)) {
					txt += "String ";
				}else if ("decimal".equals(type)) {
					txt += "BigDecimal ";
				}else if ("char".equals(type)) {
					txt += "String ";
				}else if ("int".equals(type)) {
					txt += "Integer ";
				}
				System.out.println(txt + finalField + "; // " + comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
