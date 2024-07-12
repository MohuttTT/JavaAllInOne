package org.zerock.w2.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w2.dao.TodoDAO;
import org.zerock.w2.domain.TodoVO;
import org.zerock.w2.dto.TodoDTO;
import org.zerock.w2.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
// ModelMapper의 동작을 확인
// ModelMapper && TodoDAO를 이용할수 있게 구성하고 새로운 TodoDTO를 등록하는 기능 추가
public enum TodoService {
    INSTANCE;

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {

        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }
    public void register(TodoDTO todoDTO) throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        //System.out.println("todoVO:" + todoVO);
        log.info(todoVO);

        dao.insert(todoVO); // int를 반환하므로 이를 이용해서 예회 처리도 가능
    }
    public List<TodoDTO> listAll() throws Exception {
        List<TodoVO> voList = dao.selectAll();
        log.info("voList..................");
        log.info(voList);

        List<TodoDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo,TodoDTO.class)).collect(Collectors.toList());

        return dtoList;
    }

    // TodoService의 조회 기능 구현
    // TodoDAO의 selectOne()을 통해 TOdoVO객체를 가져오고 , modelMapper-per를 이용해서 이를 TodoDTO로 변환
    public TodoDTO get(Long tno) throws Exception {

        log.info("tno:" + tno);
        TodoVO todoVO = dao.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;

    }

    // 삭제 구현
    public void remove(Long tno) throws Exception {
        log.info("tno:" + tno);
        dao.deleteOne(tno); // dao객체를 이용하여 tno에 해당하는 항목 삭제
    }

    // 수정 구현
    public void modify(TodoDTO todoDTO)throws Exception{
        log.info("todoDTO:" + todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class); // todoDTO를 TodoVo 객체로 변환
        dao.updateOne(todoVO); //dao객체를 이용하여 todoVO 객체를 업데이트
    }
}
