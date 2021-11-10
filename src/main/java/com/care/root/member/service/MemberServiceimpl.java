package com.care.root.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceimpl implements MemberService{
	@Autowired MemberMapper mapper;
	
	public int userCheck(String id, String pw){
		MemberDTO dto =  mapper.userCheck(id);
		if(dto != null) {
			if(pw.equals(dto.getPw())) {
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
}
