package com.example.demo.controller;

import com.example.demo.pojo.Animal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(value = "测试增删改查接口", description = "增删改查")
@RestController
public class TestControllerInterface {

    @ApiOperation("写入数据")
    @GetMapping("t_insert")
    public void insert(@ModelAttribute Animal animal) {

    }

    ;

    @ApiOperation("删除数据")
    @PostMapping("t_delete")
    public void delete(@ModelAttribute Animal animal) {

    }

    ;

    @ApiOperation("更新数据")
    @PostMapping("t_update")
    public void update(@ModelAttribute Animal animal) {
    }

    ;

    @ApiOperation("查询数据")
    @GetMapping("t_select")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path", dataType = "int"))
    public void select(Integer id) {
    }

    ;
}