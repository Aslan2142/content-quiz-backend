package com.aslan.contentquiz.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/result")
@RestController
public class ResultController {

    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService)
    {
        this.resultService = resultService;
    }

    @GetMapping(path = "{uuid}")
    public Result result(@PathVariable("uuid") String uuid)
    {
        return resultService.getResult(uuid);
    }

    @GetMapping(params = { "quizUuid", "username" })
    public Result result(@RequestParam String quizUuid, @RequestParam String username)
    {
        return resultService.getResult(quizUuid, username);
    }

    @GetMapping
    public List<Result> results()
    {
        return resultService.getResults();
    }

    @PostMapping
    public void resultAdd(@RequestBody Result result)
    {
        resultService.addResult(result);
    }

}
