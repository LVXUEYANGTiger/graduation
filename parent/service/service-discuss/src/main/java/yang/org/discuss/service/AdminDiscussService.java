package yang.org.discuss.service;

import org.springframework.data.domain.Sort;
import yang.org.entity.Page;
import yang.org.pojo.Discuss;

import java.util.List;
import java.util.Map;

/**
 * @author LvXueYang
 * @description 管理员后台管理服务层
 */
public interface AdminDiscussService {
    /**
     * @description 通过评论的id去查询评论
     * @param id: 评论的id
     * @return Discuss 返回一条评论
     * @author LvXueYang
     * @date 2021/12/6 12:02
     */
    Discuss findOne(String id);
    /**
     * @description 通过评论的id去删除评论
     * @param id:  评论的id
     * @return int 返回删除的评论的条数
     * @author LvXueYang
     * @date 2021/12/6 12:06
     */
    int delete(String id);
    /**
     * @description 批量删除评论id
     * @param ids: 评论id的集合
     * @return int
     * @author LvXueYang
     * @date 2021/12/6 12:20
     */
    int deleteMany(List<String> ids);
    /**
     * @description 插入一条评论
     * @param discuss: 评论的实体
     * @return int 返回插入的条数
     * @author LvXueYang
     * @date 2021/12/6 14:38
     */
    int save(Discuss discuss);

    /**
     * @description 修改评论
     * @param discuss: 修改评论对象
     * @return int 返回修改的条数
     * @author LvXueYang
     * @date 2021/12/6 14:47
     */
    int update(Discuss discuss);
    /**
     * @description 查询所有
     * @return List<Discuss>
     * @author LvXueYang
     * @date 2021/12/6 14:51
     */
    List<Discuss> findAll();
    /**
     * @description 查询所有并按照时间排序
     * @param sort:
     * @return List<Discuss>
     * @author LvXueYang
     * @date 2021/12/6 14:54
     */
    List<Discuss> findAll(Sort sort);
    /**
     * @description 根据条件分页查询
     * @param map: 查询的条件
     * @param pageIndex: 第几页
     * @param pageSize: 每页数量
     * @return Page<Discuss>
     * @author LvXueYang
     * @date 2021/12/6 15:14
     */
    Page<Discuss> findLikePages(Map<String,String> map, Integer pageIndex, Integer pageSize);
}
