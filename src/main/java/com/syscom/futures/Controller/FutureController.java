package com.syscom.futures.Controller;

import com.syscom.futures.Model.FUS101;
import com.syscom.futures.Service.FutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/futures")
public class FutureController {
    @Autowired
    FutureService futureService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/pathsend")
    @ResponseBody
    public Object pathsend(@RequestParam String svrName, @RequestParam short funCode, @RequestBody FUS101 fus101) {
        return futureService.pathsend(svrName, funCode, fus101);
    }
}
