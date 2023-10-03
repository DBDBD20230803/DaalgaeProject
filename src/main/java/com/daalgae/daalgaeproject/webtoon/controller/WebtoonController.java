package com.daalgae.daalgaeproject.webtoon.controller;


import com.daalgae.daalgaeproject.board.dao.BoardMapper;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.payment.dao.OrderPayMapper;
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

   /* @PostMapping("/purchaseDogGum")
    @ResponseBody
    public String purchaseDogGum(@RequestParam("memDogGum") Integer memDogGum) {
        boolean success = webtoonService.purchaseDogGum(memDogGum);
        if (success) {
            return "success";
        } else {
            return "failure";
        }
    }*/

   /* @PostMapping("/purchaseDogGum")
    @ResponseBody
    public String purchaseDongGum(
            @RequestParam String dogGumUseDate,
            @RequestParam int dogGumUseAmount,
            @RequestParam int refMemCode,
            @RequestParam Integer memDogGum) {

        System.out.println("컨트롤 안들어와 ?");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("어디서 문제야 ? ");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(dogGumUseDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("여기 오니 ?  : " + parsedDate);
        boolean success = webtoonService.purchaseDogGum(memDogGum);
        System.out.println("boolean 이 문제지? :" + success);
        if (success) {
            UseHistory useHistory = new UseHistory();
            useHistory.setDogGumUseDate(parsedDate);
            useHistory.setDogGumUseAmount(dogGumUseAmount);
            useHistory.setRefMemCode(refMemCode);
            int result = orderPayMapper.insertDogGumUse(useHistory);

            if (result == 1) {
                return "success";
            } else {
                return "error during saving";
            }
        } else {
            return "purchase failed";
        }
    }*/
   @PostMapping("webtoon/purchaseDogGum")
   @ResponseBody
   public String purchaseDongGum(
           @RequestParam String dogGumUseDate,
           @RequestParam int dogGumUseAmount,
           @RequestParam int memDogGum) {

       System.out.println("컨트롤러 진입했니 ???....");
       String result = webtoonService.purchaseDogGum(dogGumUseDate, dogGumUseAmount, memDogGum);
       System.out.println("컨트롤러 결과값 한번 보자 : " + result);
        return result;
   }
   @GetMapping("myPage/activitys")
    public String goActivity () {
       return "activity/activitys";
   }
}
