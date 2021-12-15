package yang.org.discuss.dao.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import yang.org.discuss.dao.UserDiscussDao;
import yang.org.entity.Page;
import yang.org.pojo.Discuss;
import yang.org.utils.ObjectMethods;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author LvXueYang
 * @description 普通用户的后台评论管理
 */
@Repository
public class UserDiscussDaoImpl implements UserDiscussDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @description 通过评论的id去删除评论
     * @param id:  评论的id
     * @return int 返回删除的评论的条数
     * @author LvXueYang
     * @date 2021/12/6 12:06
     */
    @Override
    public int delete(String id) {
        Discuss discuss = findOne(id);
        DeleteResult deleteResult = mongoTemplate.remove(discuss);
        return ((int) deleteResult.getDeletedCount());
    }
    /**
     * @description 批量删除评论id
     * @param ids: 评论id的集合
     * @return int
     * @author LvXueYang
     * @date 2021/12/6 12:20
     */
    @Override
    public int deleteMany(List<String> ids) {
        int count = 0;
        for (String id : ids) {
            Discuss discuss = findOne(id);
            DeleteResult deleteResult = mongoTemplate.remove(discuss);
            count += deleteResult.getDeletedCount();
        }
        return count;
    }
    /**
     * @description 插入一条评论
     * @param discuss: 评论的实体
     * @return int 返回插入的条数
     * @author LvXueYang
     * @date 2021/12/6 14:38
     */
    @Override
    public int save(Discuss discuss) {
        Discuss newDiscuss = mongoTemplate.insert(discuss);
        Query query = new Query();
        return mongoTemplate.find(query.addCriteria(Criteria.where("_id").is(newDiscuss.getId())),Discuss.class).size();
    }
    /**
     * @description 修改评论
     * @param discuss: 修改评论对象
     * @return int 返回修改的条数
     * @author LvXueYang
     * @date 2021/12/6 14:47
     */
    @Override
    public int update(Discuss discuss) {
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
     * @description 查询所有
     * @return List<Discuss>
     * @author LvXueYang
     * @date 2021/12/6 14:51
     */
    @Override
    public List<Discuss> findAll(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query,Discuss.class);
    }
    /**
     * @description 查询所有并按照时间排序
     * @param sort:
     * @return List<Discuss>
     * @author LvXueYang
     * @date 2021/12/6 14:54
     */
    @Override
    public List<Discuss> findAll(Sort sort,String userId) {
        sort = new Sort(Sort.DEFAULT_DIRECTION,"createdDate");
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        query.with(sort);
        return mongoTemplate.find(query,Discuss.class);
    }
    /**
     * @description 根据条件分页查询
     * @param map: map条件
     * @param pageIndex: 第几页
     * @param pageSize:  每页数量
     * @return Page<Discuss>
     * @author LvXueYang
     * @date 2021/12/7 9:58
     */
    @Override
    public Page<Discuss> findLikePages(Map<String, String> map, Integer pageIndex, Integer pageSize){
        // id 内容 昵称  用户id
        Query query = new Query();
        Pattern pattern = null;
        String regex ="";
        for (String key : map.keySet()) {
            String value = map.get(key);
            regex = "^.*" + value + ".*$";
            pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
            if("id".equals(key)){
                query.addCriteria(Criteria.where("_id").regex(pattern));
            } else {
                query.addCriteria(Criteria.where(key).regex(pattern));
            }
        }
        long count = mongoTemplate.count(query,Discuss.class);
        Page<Discuss> page = new Page<>(pageIndex, pageSize);
        page.setTotalCount((int) count);
        query.skip((long) (page.getCurrentPage() - 1) * pageSize).limit(pageSize);
        List<Discuss> rows = mongoTemplate.find(query, Discuss.class);
        page.build(rows);
        return page;
    }

    /**
     * @description 通过评论的id去查询评论
     * @param id: 评论的id
     * @return Discuss 返回一条评论
     * @author LvXueYang
     * @date 2021/12/6 12:02
     */
    @Override
    public Discuss findOne(String id) {
        return mongoTemplate.findById(id,Discuss.class);
    }
}
