package com.rdas.controller;

import com.github.tomaslanger.chalk.Chalk;
import com.rdas.util.Loggable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by x148128 on 29/05/2017.
 * http://localhost:8088/apislv/swagger-ui.html
 */
@Api(value = "2. RestController", description = " A SIMPLE hello Controller")
@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);
    /**
     * localhost:8088/apislv/hello
     */
    @Loggable(Loggable.DEBUG)
    @ApiOperation(value = "Hello.", notes = "Says Hello.")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public    @ResponseBody
    String greeting() {
        logger.info("{}" , Chalk.on("\tInvoking greeting ").green());
        return "Hello World.";
    }
}
