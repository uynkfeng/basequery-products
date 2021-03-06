package com.eenet.basequery.login;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eenet.authen.AdminUserLoginAccount;
import com.eenet.authen.extension.web.GatherAppNUserIdentifierFilter;
import com.eenet.base.SimpleResponse;
import com.eenet.base.SimpleResultSet;
import com.eenet.base.query.QueryCondition;
import com.eenet.basequery.authen.AuthenUtils;
import com.eenet.basequery.authen.Constant;
import com.eenet.basequery.increment.Increment;
import com.eenet.basequery.increment.IncrementService;
import com.eenet.util.EEBeanUtils;

@Controller
public class LoginController {
	
	@Autowired
	private IncrementService incrementService;
	
	@RequestMapping(value="/login")
	public String login(HttpServletResponse response,HttpSession session,String loginAccount,String password,RedirectAttributes redirectAttributes){
		SimpleResponse errResponse = new SimpleResponse();
		errResponse.setSuccessful(false);
		
		if(EEBeanUtils.isNULL(loginAccount) || EEBeanUtils.isNULL(password)){
			errResponse.addMessage("账号或密码参数丢失");
			redirectAttributes.addFlashAttribute("errResponse", errResponse);
			redirectAttributes.addFlashAttribute("errMsg", errResponse.getStrMessage());
			return "redirect:/";
		}
		try {
			//调用api验证账号密码，获取授权码
			String code = AuthenUtils.loginUserGrantCode(loginAccount, password);
			if("".equals(code)){
				errResponse.addMessage("账号或密码输入错误");
				redirectAttributes.addFlashAttribute("errResponse", errResponse);
				redirectAttributes.addFlashAttribute("errMsg", errResponse.getStrMessage());
				return "redirect:/";
			}
			HashMap<String,String> tokenMap = AuthenUtils.grantToken(code);//获取令牌
			AdminUserLoginAccount adminUserLoginAccount = AuthenUtils.getAdminUserInfo(tokenMap.get("userId"),tokenMap.get("accessToken"));//获取个人信息
			if(adminUserLoginAccount!=null){
				adminUserLoginAccount.setLoginAccount(loginAccount);
				session.setAttribute(Constant.adminUserLoginInfoSession, adminUserLoginAccount);
				
//				response.addCookie(new Cookie(GatherAppNUserIdentifierFilter.AppID_PARAM_TAG, Constant.appId));
//				response.addCookie(new Cookie(GatherAppNUserIdentifierFilter.AppSecretKey_PARAM_TAG, AuthenUtils.encrypt(Constant.appSecretKey + "##" + System.currentTimeMillis())));
//				response.addCookie(new Cookie(GatherAppNUserIdentifierFilter.RedirectURI_PARAM_TAG, Constant.appDomain));
				response.addCookie(new Cookie(GatherAppNUserIdentifierFilter.UserId_PARAM_TAG, adminUserLoginAccount.getUserInfo().getAtid()));
				response.addCookie(new Cookie(GatherAppNUserIdentifierFilter.UserAccessToken_PARAM_TAG, tokenMap.get("accessToken")));
				response.addCookie(new Cookie(GatherAppNUserIdentifierFilter.UserType_PARAM_TAG, "adminUser"));
				
				
				
				
				   return "redirect:/main"; 
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		errResponse.addMessage("系统出错，请联系管理员");
		redirectAttributes.addFlashAttribute("errResponse", errResponse);
		redirectAttributes.addFlashAttribute("errMsg", errResponse.getStrMessage());
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.removeAttribute(Constant.adminUserLoginInfoSession);
		return "redirect:/";
	}
	
	@RequestMapping(value="/main")
	public String main(){
		//显示后台整体框架
		
		return "main";
	}
	
	@RequestMapping(value="/index")
	public String index(QueryCondition queryCondition,Model model){
		SimpleResultSet<Increment>  result =  incrementService.getIncrement(queryCondition);
		model.addAttribute("resultMap", result);
		return "index";
	}
	
}
