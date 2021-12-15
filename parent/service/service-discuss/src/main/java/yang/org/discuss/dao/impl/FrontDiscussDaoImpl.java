package yang.org.discuss.dao.impl;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import yang.org.discuss.dao.FrontDiscussDao;
import yang.org.entity.Page;
import yang.org.pojo.Discuss;
import yang.org.pojo.DiscussQuery;
import yang.org.pojo.DiscussVo;
import yang.org.utils.ObjectMethods;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LvXueYang
 * @description 评论模块前端管理
 */
@Repository
public class FrontDiscussDaoImpl implements FrontDiscussDao {
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * @description 通过页面的链接获取主评论和次评论的内容
     * @param href: 页面的链接
     * @return List<DiscussQuery>
     * @author LvXueYang
     * @date 2021/12/4 9:23
     */
    @Override
    public List<DiscussQuery> findAllByHref(String href) {
        Query query = new Query(Criteria.where("href").is(href).and("pid").exists(false).and("rid").exists(false)).with(Sort.by(Sort.Order.desc("createdDate")));
        List<DiscussQuery> discussQueryList = mongoTemplate.find(query, DiscussQuery.class, "comment");
        for (DiscussQuery discussQuery : discussQueryList) {
            String rid = discussQuery.getId();
            // 通过rid去查询二级评论
            List<DiscussVo> children = findAllRid(rid);
            discussQuery.setChildren(children);
            discussQuery.setReplyCount(children.size());
        }
        return discussQueryList;
    }

    /**
     * @description 通过rid去查询主评论的二级评论有哪些
     * @param rid:  评论的rid
     * @return List<DiscussVo> 二级评论
     * @author LvXueYang
     * @date 2021/12/4 9:24
     */
    @Override
    public List<DiscussVo> findAllRid(String rid) {
        Query query = new Query(Criteria.where("rid").is(rid)).with(Sort.by(Sort.Order.asc("createdDate")));
        List<DiscussVo> discussVoList = mongoTemplate.find(query, DiscussVo.class, "comment");
        for (DiscussVo vo : discussVoList) {
            // 通过pid去查询回复的userId 和 昵称
            Discuss discuss = findPid(vo.getPid());
            vo.setReplayId(discuss.getUserId());
            vo.setReplayNickname(discuss.getNickname());
            if(discuss.getVote() == null || discuss.getVote().size() == 0){
                vo.setVoteCount(0);
            } else {
                vo.setVoteCount(discuss.getVote().size());
            }

        }
        return discussVoList;
    }
    /**
     * @description 点赞功能
     * @param discuss: 评论的实体
     * @param userId: 用户
     * @return int 更新的数量
     * @author LvXueYang
     * @date 2021/12/6 16:47
     */
    @Override
    public int update(Discuss discuss, String userId) {

        List<String> list = discuss.getVote();
        if(list != null){
            if(!list.contains(discuss.getUserId())){
                list.add(userId);
            }
        } else {
            list = new ArrayList<>();
            list.add(userId);
        }
        discuss.setVote(list);
        Query query = new Query(Criteria.where("_id").is(discuss.getId()));
        Update update = new Update();
        Map<Field, Method> objectAndGetMethod = ObjectMethods.getObjectAndGetMethod(discuss);
        for (Field field : objectAndGetMethod.keySet()) {
            try {
                update.set(field.getName(),objectAndGetMethod.get(field).invoke(discuss));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        UpdateResult result = mongoTemplate.upsert(query, update, Discuss.class);
        return (int) result.getModifiedCount();
    }
    /**
     * @description 增加评论
     * @param discuss:
     * @return int
     * @author LvXueYang
     * @date 2021/12/6 16:54
     */
    @Override
    public int save(Discuss discuss) {
        Discuss newDiscuss = mongoTemplate.insert(discuss);
        Query query = new Query();
        return mongoTemplate.find(query.addCriteria(Criteria.where("_id").is(newDiscuss.getId())),Discuss.class).size();
    }
    /**
     * @description  评论模块分页
     * @param href:  哪块的模块
     * @param pageIndex: 第几页
     * @param pageSize:  每页有多少条
     * @return Page<DiscussQuery>
     * @author LvXueYang
     * @date 2021/12/6 20:18
     */
    @Override
    public Page<DiscussQuery> findLikePages(String href, Integer pageIndex, Integer pageSize) {
        Query query = new Query(Criteria.where("href").is(href).and("pid").exists(false).and("rid").exists(false)).with(Sort.by(Sort.Order.desc("createdDate")));
        long count = mongoTemplate.count(query,"comment");
        Page<DiscussQuery> page = new Page<>(pageIndex, pageSize);
        page.setTotalCount((int) count);
        query.skip((long) (page.getCurrentPage() - 1) * pageSize).limit(pageSize);
        List<DiscussQuery> rows = mongoTemplate.find(query, DiscussQuery.class,"comment");

        for (DiscussQuery discussQuery : rows) {
            String rid = discussQuery.getId();
            // 通过rid去查询二级评论
            List<DiscussVo> children = findAllRid(rid);
            discussQuery.setChildren(children);
            discussQuery.setReplyCount(children.size());
        }

        page.build(rows);
        return page;
    }

    /**
     * @description 通过pid去查询回复的哪个帖子
     * @param pid:  pid对应的是回复的帖子
     * @return DiscussVo 二级评论实体
     * @author LvXueYang
     * @date 2021/12/4 9:24
     */
    @Override
    public Discuss findPid(String pid) {
        // 通过pid去获取回复的是哪个帖子
        Query query = new Query(Criteria.where("id").is(pid)).with(Sort.by(Sort.Order.desc("createdDate")));
        return mongoTemplate.findOne(query,Discuss.class,"comment");
    }
}
