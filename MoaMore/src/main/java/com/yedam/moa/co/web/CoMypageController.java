package com.yedam.moa.co.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.moa.co.service.CoService;
import com.yedam.moa.co.service.CoVO;
import com.yedam.moa.comm.service.Impl.CommServiceImpl;
import com.yedam.moa.hire.HireVO;
import com.yedam.moa.self.SelfVO;

@Controller
public class CoMypageController {
	@Autowired
	CoService service;
	
	@Autowired
	CommServiceImpl commServiceImpl;

	// 기업마이페이지
	@GetMapping("/coMypage")
	public String coMypage(Principal principal, Model model,CoVO vo) {
		vo.setId(principal.getName());
		model.addAttribute("followers",service.selectFollowers(vo)); //나를 관심가진 수 가져오기
		model.addAttribute("id", vo); // user id 가져오기
		model.addAttribute("totalInter", service.selectTotalInter(vo)); // 관심게시글 수 가져오기
		model.addAttribute("commList", commServiceImpl.getCodes("Z")); // 제안모달 기술스택가져오기
		return "co/coMypage";
	}

	// 기업정보등록페이지
	@GetMapping("/coInfoPage") // 기업아이디 들고 들어가서 해당 아이디에 해당하는 기업정보 있다면 model에 service담아서 미리출력
	public String coInfoPage(Model model, Principal principal, CoVO vo) {
		vo.setId(principal.getName());
		model.addAttribute("co", service.selectCo(vo));
		return "co/coInfo";
	}

	// 구인공고목록
	@GetMapping("/coRecruit")
	public String coRecruitPage(Model model, Principal principal, HireVO vo) {
		vo.setId(principal.getName());
		model.addAttribute("rec", service.selectRec(vo));
		return "co/coRecruit";
	}
	
	//제안모달창 열었을 때 필요한 정보들
	@GetMapping("/getOfferModalData")
	@ResponseBody
	public List<HireVO> getOfferModalData(HireVO vo){
		
		return service.selectRec(vo);//구인공고목록
		
	}

	// 관심셀프구직게시글목록
	@GetMapping("/selectInterNoti")
	@ResponseBody
	public List<SelfVO> selectInterNoti(Principal principal, SelfVO vo) {
		vo.setId(principal.getName());
		System.out.println(service.selectInterNoti(vo));
		return service.selectInterNoti(vo);
	}
	//셀프구직게시글 관심해제
	@PostMapping("/deleteInter")
	@ResponseBody
	public String deleteInter(Principal principal, SelfVO vo) {
		vo.setId(principal.getName());
		service.deleteInfer(vo);
		System.out.println(vo);
		return "success";
	}
	
	//구직에 제안
	@PostMapping("/offerSelf")
	public String offerSelf(CoVO vo) {
		service.afterOffer(vo);
		return "redirect:coMypage";
	}
	

	// 제안셀프구직게시글
	@GetMapping("/selectOfferNoti")
	@ResponseBody
	public List<SelfVO> selectOfferNoti(Principal principal, SelfVO vo) {
		vo.setId(principal.getName());
		return service.selectOfferNoti(vo);
	}
	
	//기업정보등록/수정
	@PostMapping("/saveCoInfo")
	public String saveCoInfo(Principal principal, CoVO vo) {
		System.out.println(vo);
		vo.setId(principal.getName());
		
		service.saveCoInfo(vo);
		return "redirect:coInfoPage";
	}
	
	//기업아이템페이지
	@GetMapping("/coProducts")
	public String coProductsPage(Model model, Principal principal, CoVO vo) {
		return "co/coProducts";
	}

}
