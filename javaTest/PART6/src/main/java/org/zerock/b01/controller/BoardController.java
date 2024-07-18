package org.zerock.b01.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.dto.*;
import org.zerock.b01.service.BoardService;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    @Value("${org.zerock.upload.path}") // import시에 springframework으로 시작하는 Value
    private String uploadPath;

    private final BoardService boardService;

    // 전체 목록
    // @Operation(summary = "list")
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        // PageResponseDTO<BoardListReplyCountDTO> responseDTO = boardService.listWithReplyCount(pageRequestDTO);

        // 게시글 목록의 첨부파일과 댓글의 수까지 모두 조회
        PageResponseDTO<BoardListAllDTO> responseDTO = boardService.listWithAll(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    // 등록 화면
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/register")
    public void registerGET() {
    }

    // 등록 처리 (C -> S 요청 -> S는 R에게 요청하여 insert)
    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("board POST register.......");

        if (bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }

        log.info(boardDTO);
        Long bno = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

/*    // 조회 구성
    @GetMapping("/read")
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
    }*/

    // 글 조회 & 수정/삭제 화면
    @GetMapping({"/read", "/modify"})
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
    }

    // 수정
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        log.info("board modify post......." + boardDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors.......");
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/modify?" + link;
        }

        boardService.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/board/read";
    }

    // 삭제
    /*@PostMapping("/remove")   // 첨부파일 추가 되기 전 이전 코드
    public String remove(Long bno, RedirectAttributes redirectAttributes) {

        log.info("remove post.. " + bno);

        boardService.remove(bno);

        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }*/

    /* 게시글 삭제의 경우 첨부파일 삭제도 함께 진행되어야 하기 때문에 BoardDTO 인자 값을 받아 처리 */
    @PostMapping("/remove")
    public String remove(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {

        // 삭제할 게시글 번호 -- boardDTO.bno
        Long bno = boardDTO.getBno();
        log.info("remove post.. " + bno);

        // 해당 게시글 삭제 로직
        boardService.remove(bno);

        // 게시물이 데이터베이스상에서 삭제되었다면 첨부파일 삭제
        // 해당 게시물의 첨부파일 정보 -- boardDTO.fileNames
        log.info(boardDTO.getFileNames());  // -- 해당 게시글에 첨부된 파일
        List<String> fileNames = boardDTO.getFileNames();

        // 삭제할 파일 내용이 있다면 실제 파일이 저장된 위치로 가서 해당 파일을 삭제 -- removeFiles()
        if (fileNames != null && fileNames.size() > 0) {
            removeFiles(fileNames);
        }

        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }

    // 실제 위치 (예제상 경로 C://upload)에 파일 삭제
    public void removeFiles(List<String> files){

        for (String fileName:files) {

            // 해당 위치의 실제 파일 자원 얻어옴 (uploadPath -- C://upload) (File.separator -- 운영체제별 구분자)
            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();

            try {
                // 삭제하려는 첨부파일의 형식 확인 (이미지인지, 일반 첨부 파일인지)
                String contentType = Files.probeContentType(resource.getFile().toPath());

                // 실제 파일 삭제
                resource.getFile().delete();

                // 이미지인 경우 썸네일 이미지도 삭제해야 하기 때문에 확인
                if (contentType.startsWith("image")) {
                    // 썸네일 이미지 파일 경로 생성
                    File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
                    // 해당 위치에 있는 파일 삭제
                    thumbnailFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }   // end try-catch
        }   // end for
    }
}
