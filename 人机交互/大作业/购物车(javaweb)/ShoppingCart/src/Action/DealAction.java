package Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import Bean.Fruit;
import Bean.LoginForm;
import DAO.DBHelper;

public class DealAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		try {
			DBHelper db=new DBHelper("root", "root");
			HttpSession session=request.getSession();
			LoginForm user=(LoginForm)session.getAttribute("user");
			ArrayList<Fruit> fruits=(ArrayList)session.getAttribute("fruits"); //fruit表的所有信息
			String sql=null;
			for(int i=0;i<fruits.size();i++){
				ResultSet rsTemp=db.ExecuteQuery("select * from list where account='"+user.getAccount()+"' and fruit_id='"+fruits.get(i).getId()+"'");
				int fruit_number=Integer.parseInt(request.getParameter("fruit"+(i+1))); //购物页面选中的各水果数量
				//System.out.println(fruit_number);
				if (rsTemp.next()) {  //list数据库里面有这样一行记录 则在原先基础上添加	
					if(fruit_number>0){
						sql="update list set fruit_number="+(rsTemp.getInt("fruit_number")+fruit_number)+" where account='"+user.getAccount()+"' and fruit_id='"+fruits.get(i).getId()+"'";
						db.ExecuteUpdate(sql);
					}
				}
				else{
					if(fruit_number>0){
					sql="insert into list values('"+user.getAccount()+"','"+fruits.get(i).getId()+"', "+fruit_number+")";			
					db.ExecuteInsert(sql);
					}
				}	
				if(fruit_number>0){
					ResultSet rs1=db.ExecuteQuery("select fruit_stock from fruit where fruit_id='"+fruits.get(i).getId()+"'");
					int fruit_stock=0;
					if(rs1.next()){
						fruit_stock=rs1.getInt("fruit_stock");
						String sql1="update fruit set fruit_stock="+(fruit_stock-fruit_number)+" where fruit_id='"+fruits.get(i).getId()+"'";
						db.ExecuteUpdate(sql1);//更新数据库fruit
						System.out.println("更新fruit");
						fruits.get(i).setStock(fruits.get(i).getStock()-fruit_number);//更新sessin
					}
				}
			}
				ArrayList<HashMap<String, String>> data=new ArrayList<HashMap<String, String>>();
				ResultSet rs=db.ExecuteQuery("select * from list,fruit,user where list.account=user.account and list.fruit_id=fruit.fruit_id and list.account='"+user.getAccount()+"'");
				while(rs.next()){
					System.out.println(rs.getString("fruit_name"));
					HashMap<String, String> map=new HashMap<String,String>();
					map.put("fruit_id", rs.getString("fruit_id"));
					map.put("fruit_name", rs.getString("fruit_name"));
					map.put("fruit_price", rs.getString("fruit_price"));
					map.put("fruit_number", Integer.toString(rs.getInt("fruit_number")));
					map.put("xiaoji",Double.toString(Double.parseDouble(rs.getString("fruit_price"))* rs.getInt("fruit_number")));
					//System.out.println("用户id："+rs.getString("account")+" 商品id:"+rs.getString("fruit_id")+" 数量："+rs.getInt("fruit_number"));
					data.add(map);
				}
				session.setAttribute("data", data);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return new ActionForward("/show.jsp");
	}
}
