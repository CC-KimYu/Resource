package Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

public class LoginAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		try {
			HttpSession session=request.getSession();
			ArrayList<Fruit> fruits=new ArrayList<Fruit>();
			LoginForm loginForm=(LoginForm)form;
			LoginForm user=new LoginForm();
			String account=loginForm.getAccount(); 
			String password=loginForm.getPassword();
			//System.out.println(account+"  "+password);
			DBHelper db=new DBHelper("root", "root");
			String sql="select * from user";
			ResultSet rs=db.ExecuteQuery(sql);
			boolean flag=false;
			while(rs.next()){
				if(rs.getString("account").equals(account)&&rs.getString("password").equals(password)){
					flag=true;
					user.setAccount(account);
					user.setPassword(password);
					user.setNickname(rs.getString("nickname"));
				}
			}
			//System.out.println(flag);
			if(flag){ //成功登录
				ResultSet rs1=db.ExecuteQuery("select * from fruit");
				while(rs1.next()){
					Fruit fruit=new Fruit();
					fruit.setName(rs1.getString("fruit_name"));
					fruit.setId(rs1.getString("fruit_id"));
					fruit.setPrice(rs1.getString("fruit_price"));
					fruit.setStock(rs1.getInt("fruit_stock"));
					fruits.add(fruit);
				}
				session.setAttribute("fruits",fruits );
				session.setAttribute("user", user);
				return new ActionForward("/shopping.jsp");
			}else{
				return new ActionForward("/loginFail.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
