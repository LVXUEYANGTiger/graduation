package yang.org.discuss.service;

import yang.org.entity.Page;
import yang.org.pojo.Discuss;
import yang.org.pojo.DiscussQuery;
import yang.org.pojo.DiscussVo;

import java.util.List;

/**
 * @author LvXueYang
 * @description 评论前端模块管理
 */
public interface FrontDiscussService {
    /**
     * @description 通过页面的链接获取主评论和次评论的内容
     * @param href: 页面的链接
     * @return List<DiscussQuery>
     * @author LvXueYang
     * @date 2021/12/4 9:23
     */
    List<DiscussQuery> findAllByHref(String href);
    /**
     * @description 通过pid去查询回复的哪个帖子
     * @param pid:  pid对应的是回复的帖子
     * @return DiscussVo 二级评论实体
     * @author LvXueYang
     * @date 2021/12/4 9:24
     */
    Discuss findPid(String pid);
    /**
     * @description 通过rid去查询主评论的二级评论有哪些
     * @param rid:  评论的rid
     * @return List<DiscussVo> 二级评论
     * @author LvXueYang
     * @date 2021/12/4 9:24
     */
    List<DiscussVo> findAllRid(String rid);

    /**
     * @description 点赞功能
     * @param discuss: 评论的实体
     * @param userId: 用户
     * @return int 更新的数量
     * @author LvXueYang
     * @date 2021/12/6 16:47
     */
    int update(Discuss discuss,String userId);
    /**
     * @description 增加评论
     * @param discuss:
     * @return int
     * @author LvXueYang
     * @date 2021/12/6 16:54
     */
    int save(Discuss discuss);
    /**
     * @description  评论模块分页
     * @param href:  哪块的模块
     * @param pageIndex: 第几页
     * @param pageSize:  每页有多少条
     * @return Page<DiscussQuery>
     * @author LvXueYang
     * @date 2021/12/6 20:18
     */
    Page<DiscussQuery> findLikePages(String href, Integer pageIndex, Integer pageSize);
}
