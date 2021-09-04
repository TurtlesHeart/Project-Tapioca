package kr.Tapioca.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.Tapioca.domain.BoardVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("New Title");
		board.setContent("New Contents");
		board.setWriter("New Writer");
		
		service.register(board);
		
		log.info("생성된 게시물의 번호 : " + board.getBno());
	}
	
	@Test
	public void testgetList() {
		service.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testget() {
		log.info(service.get(1L));
	}
	
	@Test
	public void testDelete() {
		log.info("Remove Result : " + service.remove(2L));

	}
	
	@Test
	public void testUpdate() {
		
		BoardVO board = service.get(1L);
		
		if (board == null) {
			return;
		}
		
		board.setTitle("제목수정합니다.");
		log.info("Modify Result : " + service.modify(board));
	}
}
