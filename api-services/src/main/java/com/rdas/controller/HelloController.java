package com.rdas.controller;

import com.rdas.util.Loggable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by x148128 on 29/05/2017.
 *
 */
@Api(value = "2. RestController", description = " A SIMPLE hello Controller")
@RestController
public class HelloController {

    /**
     * http://localhost:8081/swagger-ui.html
     * localhost:8081/hello
     * @return
     */
    @Loggable(Loggable.DEBUG)
    @ApiOperation(value = "Hello.", notes = "Says Hello.")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public    @ResponseBody
    String greeting() {
        return "Hello World.";
    }
}
