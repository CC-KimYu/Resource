package Action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import Bean.LoginForm;
import DAO.DBHelper;

public class UpdateAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			DBHelper db = new DBHelper("root", "root");
			LoginForm user = (LoginForm) session.getAttribute("user");
			ArrayList<HashMap<String, String>> data = (ArrayList<HashMap<String, String>>) session.getAttribute("data");
			int fruit_number = 0;
			String sql = null;
			for (int i = 0; i < data.size(); i++) {
				fruit_number = Integer.parseInt(request.getParameter("fruit_number" + i));
				if(fruit_number>0){
					ResultSet rs1 = null;
					String sql1 = "select * from list where account='" + user.getAccount() + "' and fruit_id='"
							+ data.get(i).get("fruit_id") + "' and fruit_number=" + fruit_number;
					rs1 = db.ExecuteQuery(sql1);
					rs1.next();
					//System.out.println(rs1.getRow());
					if (rs1.getRow()==0) { //如果 修改后的数量跟存入数据库的一样 则无需修改
							System.out.println("需要修改");
							sql = "update list set fruit_number=" + fruit_number + " where account='" + user.getAccount()
									+ "' and fruit_id='" + data.get(i).get("fruit_id") + "'";
							db.ExecuteUpdate(sql);
					}else{
						System.out.println("无需修改");
					}
				}else{
					 System.out.println("成功刪除");
					 sql="delete from list where account='"+user.getAccount()+"' and fruit_id='"+data.get(i).get("fruit_id")+"'";
					 db.ExecuteDelete(sql);
				}
			}
			data.clear();
			ResultSet rs=db.ExecuteQuery("select * from list,fruit,user where list.account=user.account and list.fruit_id=fruit.fruit_id and list.account='"+user.getAccount()+"'");
			while(rs.next()){
				System.out.println(rs.getString("fruit_name"));
				HashMap<String, String> map=new HashMap<String,String>();
				map.put("fruit_id", rs.getString("fruit_id"));
				map.put("fruit_name", rs.getString("fruit_name"));
				map.put("fruit_price", rs.getString("fruit_price"));
				map.put("fruit_number", Integer.toString(rs.getInt("fruit_number")));
				map.put("xiaoji",Double.toString(Double.parseDouble(rs.getString("fruit_price"))* rs.getInt("fruit_number")));
				System.out.println("用户id："+rs.getString("account")+" 商品id:"+rs.getString("fruit_id")+" 数量："+rs.getInt("fruit_number"));
				data.add(map);
			}
			session.setAttribute("data", data);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ActionForward("/show.jsp");
	}
}
