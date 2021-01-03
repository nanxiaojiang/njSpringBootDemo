package com.nj;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/14 23:08
 */
@Controller
@RestController
public class NjTestController {

    @ResponseBody
    @RequestMapping("/njtest")
    public String getParam(){
        return "njTest";
    }

    @RequestMapping("/nanJiangTest.jhtml")
    public String nanJiangTest(){
        return "nanjiang";
    }
}
