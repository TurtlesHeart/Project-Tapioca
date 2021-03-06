package kr.Tapioca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.Tapioca.domain.BoardVO;
import kr.Tapioca.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board*")
@AllArgsConstructor
public class BoardController {

	private BoardService service;
	
	@GetMapping("/list")
	private void list(Model model) {

		log.info("list");
		model.addAttribute("list" , service.getList());

	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board , RedirectAttributes rttr) {
		log.info("modify : " + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result" , "success");
		}
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("remove..." + bno);
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result" , "success");
		}
		return "redirect:/board/list";
	}
}
