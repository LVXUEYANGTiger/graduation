package yang.org.discuss.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yang.org.discuss.service.FrontDiscussService;
import yang.org.entity.Page;
import yang.org.entity.Result;
import yang.org.entity.StatusCode;
import yang.org.pojo.Discuss;
import yang.org.pojo.DiscussQuery;
import yang.org.pojo.DiscussVo;

import java.util.List;


/**
 * @author LvXueYang
 */
@RestController
@RequestMapping("/front")
@Api(tags = "前端评论模块")
public class FrontDiscussController {

    @Autowired
    private FrontDiscussService frontDiscussService;

    @ApiOperation(value = "通过页面标识去查询所有")
    @GetMapping("/findAllByHref")
    public Result<List<DiscussQuery>> findAllByHref(String href){
        List<DiscussQuery> discussQueryList = frontDiscussService.findAllByHref(href);
        return new Result<>(true, StatusCode.OK,"查询成功",discussQueryList);
    }
    @ApiOperation(value = "通过pid去查询回复的哪个帖子")
    @GetMapping("/findPid")
    public Result<Discuss> findPid(String pid){
        Discuss discuss = frontDiscussService.findPid(pid);
        return new Result<>(true, StatusCode.OK,"查询成功",discuss);
    }
    @ApiOperation(value = "通过rid去查询主评论的二级评论有哪些")
    @GetMapping("/findAllRid")
    public Result<List<DiscussVo>> findAllRid(String rid){
        List<DiscussVo> discussVoList = frontDiscussService.findAllRid(rid);
        return new Result<>(true, StatusCode.OK,"查询成功",discussVoList);
    }

    @ApiOperation(value = "评论点赞")
    @PostMapping("/update")
    public Result<Integer> update(@RequestBody Discuss discuss,String userId){
        int count = frontDiscussService.update(discuss,userId);
        return new Result<>(true, StatusCode.OK,"更新成功",count);
    }
    @ApiOperation(value = "添加评论")
    @PostMapping("/save")
    public Result<Integer> save(@RequestBody Discuss discuss){
        int count = frontDiscussService.save(discuss);
        return new Result<>(true, StatusCode.OK,"添加成功",count);
    }
    @ApiOperation(value = "查询主评论封装好子评论并分页")
    @GetMapping("/findLikePages")
    public Result<Page<DiscussQuery>> findLikePages(String href, Integer pageIndex, Integer pageSize){
        Page<DiscussQuery> likePages = frontDiscussService.findLikePages(href,pageIndex,pageSize);
        return new Result<>(true, StatusCode.OK,"查询成功",likePages);
    }

}
