package yang.org.discuss.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yang.org.discuss.service.AdminDiscussService;
import yang.org.entity.Page;
import yang.org.entity.Result;
import yang.org.entity.StatusCode;
import yang.org.pojo.Discuss;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author LvXueYang
 */
@Api(tags = "管理员后台评论")
@RestController("/admin")
public class AdminDiscussController {

    @Autowired
    private AdminDiscussService adminDiscussService;


    @ApiOperation(value = "通过评论id查询评论")
    @GetMapping("/findOne")
    public Result<Discuss> findOne(String id){
        Discuss discuss = adminDiscussService.findOne(id);
        return new Result<>(true, StatusCode.OK,"查询成功",discuss);
    }
    @ApiOperation(value = "根据id删除评论")
    @GetMapping("/delete")
    public Result<Integer> delete(String id){
        int count = adminDiscussService.delete(id);
        return new Result<>(true, StatusCode.OK,"删除成功",count);
    }
    @ApiOperation(value = "根据id批量删除文档")
    @PostMapping("/deleteMany")
    public Result<Integer> deleteMany(@RequestBody List<String> ids){
        int count = adminDiscussService.deleteMany(ids);
        return new Result<>(true, StatusCode.OK,"批量删除成功",count);
    }

    @ApiOperation(value = "增加一条评论")
    @PostMapping("/save")
    public Result<Integer> save(@Valid @RequestBody Discuss discuss){
        int count = adminDiscussService.save(discuss);
        return new Result<>(true, StatusCode.OK,"添加成功",count);
    }
    @ApiOperation(value = "根据id更新评论")
    @PostMapping("/update")
    public Result<Integer> update(@RequestBody Discuss discuss){
        int count = adminDiscussService.update(discuss);
        return new Result<>(true, StatusCode.OK,"更新成功",count);
    }
    @ApiOperation(value = "查询所有")
    @GetMapping("/findAllUserId")
    public Result<List<Discuss>> findAll(){
        List<Discuss> discussList = adminDiscussService.findAll();
        return new Result<>(true, StatusCode.OK,"查询成功",discussList);
    }
    @ApiOperation(value = "查询所有然后按时间排序")
    @GetMapping("/findAllSort")
    public Result<List<Discuss>> findAllOder(){
        List<Discuss> discussList = adminDiscussService.findAll();
        return new Result<>(true, StatusCode.OK,"查询成功",discussList);
    }
    @ApiOperation(value = "模糊查询并分页")
    @PostMapping("/findLikePages")
    public Result<Page<Discuss>> findLikePages(@RequestBody Map<String,String> map, Integer pageIndex, Integer pageSize){
        Page<Discuss> likePages = adminDiscussService.findLikePages(map, pageIndex, pageSize);
        return new Result<>(true, StatusCode.OK,"查询成功",likePages);
    }

}
