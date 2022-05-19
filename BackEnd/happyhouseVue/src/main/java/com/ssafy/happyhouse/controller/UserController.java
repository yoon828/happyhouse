package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.UserService;
@Controller
@RequestMapping("/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/regist")
	public String register() {
		return "user/regist";
	}
	
	@PostMapping("/regist")
	public String register(UserDto userDto, Model model) throws Exception {
		logger.debug("userDto info : {}", userDto);
		userService.registUser(userDto);
		return "redirect:/user/login";
	}
	
	@GetMapping("/idcheck")
//	@ResponseBody
	public @ResponseBody String idCheck(@RequestParam("ckid") String checkId) throws Exception {
		int idCount = userService.idCheck(checkId);
		JSONObject json = new JSONObject();
		json.put("idcount", idCount);
		return json.toString();
	}
	
	@GetMapping(value = "/user")
	public ResponseEntity<?> userList(){
		try {
			List<UserDto> list = userService.listUser();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "user/delete";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam Map<String, String> map, Model model,  HttpSession session, HttpServletResponse response) throws Exception{
		logger.debug("map : {}", map.get("deluserid"));
		logger.debug("map : {}", map.get("deluserpw"));
		Map<String, String> delmap = new HashMap<String, String>();
		delmap.put("userid", map.get("deluserid"));
		delmap.put("userpw", map.get("deluserpw"));
		UserDto userDtoDel = userService.login(delmap);
		String userid = delmap.get("userid");
		if(userDtoDel != null) {
			userService.deleteUser(userid);
			session.invalidate();
			return "redirect:/";
		} else {
			model.addAttribute("deleteMsg", "비밀번호를 확인하세요.");
			return "user/delete";
		}
	}
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session, HttpServletResponse response) throws Exception {
		logger.debug("map : {}", map.get("userid"));
		UserDto userDto = userService.login(map);
		if(userDto !=null) {
			session.setAttribute("userInfo", userDto);
			Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
			cookie.setPath("/");
			if("saveok".equals(map.get("idsave"))) {
				cookie.setMaxAge(60*60*24*365 *40);
			} else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			return "redirect:/";
		} else {
			model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
			return "user/login";
		}
	}
	
	@GetMapping("/list")
	public String list() {
		return "user/list";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/updateCheck")
	public String updateCheck() {
		return "user/updateCheck";
	}
	
	@PostMapping("/updateCheck")
	public String updateCheck(@RequestParam Map<String, String> map, Model model, HttpServletResponse response) throws Exception {
		logger.debug("map : {}", map.get("upChkuserid"));
		logger.debug("map : {}", map.get("upChkuserpw"));
		Map<String, String> upMap = new HashMap<String, String>();
		upMap.put("userid", map.get("upChkuserid"));
		upMap.put("userpw", map.get("upChkuserpw"));
		UserDto userDtoUp = userService.login(upMap);
		if(userDtoUp != null) {
			model.addAttribute("updateUserInfo", userDtoUp);
			return "user/update";
		} else {
			model.addAttribute("updateMsg", "비밀번호를 확인하세요");
			return "user/updateCheck";
		}
	}
	@GetMapping("/myInfoCheck")
	public String myInfoCheck() {
		return "user/myInfoCheck";
	}
	
	@PostMapping("/myInfoCheck")
	public String myInfoCheck(@RequestParam Map<String, String> map, Model model, HttpServletResponse response) throws Exception{
		logger.debug("map : {}", map.get("infoChkuserid"));
		logger.debug("map : {}", map.get("infoChkuserpw"));
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("userid", map.get("infoChkuserid"));
		infoMap.put("userpw", map.get("infoChkuserpw"));
		UserDto userDtoInfo = userService.login(infoMap);
		if(userDtoInfo != null) {
			model.addAttribute("infoUser", userDtoInfo);
			return "user/myInfo";
		} else {
			model.addAttribute("infoMsg", "비밀번호를 확인하세요");
			return "user/myInfoCheck";
		}
	}
	@PostMapping("/update")
	public String update(UserDto userDto, Model model) throws Exception {
		System.out.println(userDto.toString());
		int i = userService.updateUser(userDto);
		System.out.println("update 인트 : " + i);
		model.addAttribute("updateUserInfo", userDto);
		return "user/update";
	}
	
	@GetMapping("/idFind")
	public String idFind() throws Exception{
		return "user/idFind";
	}
	@PostMapping("/idFind")
	public String idFind(@RequestParam Map<String, String> map, Model model, HttpServletResponse response) throws Exception{
		logger.debug("map : {}", map.get("username"));
		logger.debug("map : {}", map.get("useraddress"));
		logger.debug("map : {}", map.get("usernumber"));
		String userid = userService.idFind(map);
		if(userid != null) {
			logger.debug("찾은 아이디 : {}", userid);
			model.addAttribute("idFindMsg", "찾으시는 아이디는 " + userid +" 입니다.");
			return "user/idFind";
		} else {
			model.addAttribute("idFindMsg", "입력한 정보를 다시 확인하세요.");
			return "user/idFind";
		}
	}
	
	@GetMapping("/pwFind")
	public String pwFind() throws Exception{
		return "user/pwFind";
	}
	
	@PostMapping("/pwFind")
	public String pwFind(@RequestParam Map<String, String> map, Model model, HttpServletResponse response) throws Exception{
		logger.debug("map : {}", map.get("username"));
		logger.debug("map : {}", map.get("userid"));
		logger.debug("map : {}", map.get("useraddress"));
		logger.debug("map : {}", map.get("usernumber"));
		String userpw = userService.pwFind(map);
		if(userpw != null) {
			logger.debug("찾은 비밀번호 : {}", userpw);
			model.addAttribute("pwFindMsg", "임시비밀번호는 " + userpw +" 입니다.");
			return "user/pwFind";
		} else {
			model.addAttribute("pwFindMsg", "입력한 정보를 다시 확인하세요.");
			return "user/pwFind";
		}
	}
}
