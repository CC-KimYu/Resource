package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import DAO.DBHelper;

public class InfoAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String account=request.getParameter("account");
		String password=request.getParameter("password1");
		String nickname=request.getParameter("nickname");
		System.out.println(account+" "+password+" "+nickname);
		if(!account.equals("")&&!password.equals("")&&!nickname.equals("")){
			try{
				DBHelper db=new DBHelper("root", "root");
				String sql="insert into user values('"+account+"','"+password+"','"+nickname+"')";
				db.ExecuteInsert(sql);
				System.out.println("注册成功");
				return new ActionForward("/login.jsp");
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return new ActionForward("/loginFail.jsp");
	}
}
