package com.daalgae.daalgaeproject.webtoon.controller;


import com.daalgae.daalgaeproject.board.dao.BoardMapper;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.service.BoardServiceImpl;
import com.daalgae.daalgaeproject.common.paging.Pagenation;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.payment.dao.OrderPayMapper;
import com.daalgae.daalgaeproject.webtoon.model.dto.UseHistory;
import com.daalgae.daalgaeproject.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class WebtoonController {

    private final WebtoonService webtoonService;
    private final OrderPayMapper orderPayMapper;
    private final BoardMapper boardMapper;
    private final BoardServiceImpl boardService;
    private Process logger;

    @GetMapping("webtoon/dengInfo")
    public String goWebtoon(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();
            int member = webtoonService.getMemberByMemCode(memCode);
           /* Integer postCode = 0;
            postCode = boardMapper.dogGumUse(postCode);
*/

            model.addAttribute("member", member);
            model.addAttribute("memCode", memCode);
            /*  model.addAttribute("postCode", postCode);*/
        }

        return "webtoon/dengInfo";
    }


    @GetMapping("webtoon/webtoonDetail")
    public String goWebtoonDetailPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            int member = webtoonService.getMemberByMemCode(memCode);

            System.out.println("member 값은 ,,,??2222 : " + member);
            model.addAttribute("member", member);

        }
        return "webtoon/webtoonDetail";
    }


   @PostMapping("webtoon/purchaseDogGum")
   @ResponseBody
   public String purchaseDongGum(@RequestBody UseHistory requstDate ) {

       String dogGumUseDate = String.valueOf(requstDate.getDogGumUseDate());
       int dogGumUseAmount = requstDate.getDogGumUseAmount();
       Integer memDogGum = requstDate.getRefMemCode();

       String result = webtoonService.purchaseDogGum(dogGumUseDate, dogGumUseAmount, memDogGum);
        return result;
   }


   @GetMapping("/myPage/activity")
   public String goActivit () {
        return "activity/activitys";
   }
   @GetMapping("/webtoon/webtoonFile")
    public String uploadWebtoon() {
       return "webtoon/webtoonFile";
   }
}
