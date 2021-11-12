package com.care.root.member.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceimpl implements MemberService{
	@Autowired MemberMapper mapper;
	BCryptPasswordEncoder encoder;
	
	public MemberServiceimpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	public int userCheck(String id, String pw){
		MemberDTO dto =  mapper.userCheck(id);
		if(dto != null) {
			//if(pw.equals(dto.getPw())) {
			if(encoder.matches(pw, dto.getPw())){//암호화되지 않은 사용자입력값, 암호화된 db에 있는 값 두개 비교
				return 0;  //성공
			}
		}

		return 1;//실패
	}
	//오버라이딩
	public void memberInfo(Model model) {
		model.addAttribute("memberList", mapper.memberInfo());
	}
	public void info(Model model, String id) {
		model.addAttribute("info", mapper.userCheck(id));
	}
	public int register(MemberDTO dto) {
		System.out.println("비번 변경 전 : " + dto.getPw());
		String securePw = encoder.encode(dto.getPw());
		System.out.println("비번 변경 후 : " + securePw);
		
		dto.setPw(securePw);
		
		int result = 0;
		try {
			result = mapper.register(dto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*
	public int register(MemberDTO dto) {
		int result = 0;
		String msg = "";
		try {//동일 아이디가 저장될 경우의 예외상황을 처리하자
			result = mapper.register(dto);
			msg = "회원가입에 성공하였습니다";
		} catch (Exception e) {
			msg = "동일한 아이디가 존재합니다";
			e.printStackTrace();
		}
		return result;
	}
	*/
	public void keepLogin(String sessionId, Date limitDate, String id) { //오버라이딩
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("limitDate", limitDate);
		map.put("id",id);
		mapper.keepLogin(map);
}
	public MemberDTO getUserSessionId(String sessionId) {
		return mapper.getUserSessionId(sessionId);
	}
}