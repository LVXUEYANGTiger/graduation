package yang.org.discuss.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yang.org.discuss.dao.UserDiscussDao;
import yang.org.discuss.service.UserDiscussService;
import yang.org.entity.Page;
import yang.org.exception.ParamsErrorException;
import yang.org.pojo.Discuss;

import java.util.List;
import java.util.Map;

/**
 * @author LvXueYang
 * @description 用户后台评论服务实现层
 */
@Service
public class UserDiscussServiceImpl implements UserDiscussService {
    @Autowired
    private UserDiscussDao userDiscussDao;

    @Override
    public Discuss findOne(String id) {
        if(StringUtils.isBlank(id)){
            throw new ParamsErrorException("评论id不能为空");
        } else {
            return userDiscussDao.findOne(id);
        }
    }

    @Override
    public int delete(String id) {

        if(StringUtils.isBlank(id)){
            throw new ParamsErrorException("评论id不能为空");
        } else {
            return userDiscussDao.delete(id);
        }
    }

    @Override
    public int deleteMany(List<String> ids) {
        if(ids != null && ids.size() > 0){
           return userDiscussDao.deleteMany(ids);
        } else {
            throw new ParamsErrorException("评论id不能为空");
        }
    }

    @Override
    public int save(Discuss discuss) {
        if(discuss == null){
            throw new ParamsErrorException("评论对象不能为空");
        } else {
            return userDiscussDao.save(discuss);
        }
    }

    @Override
    public int update(Discuss discuss) {
        if(discuss == null){
            throw new ParamsErrorException("评论对象不能为空");
        } else {
            return userDiscussDao.update(discuss);
        }
    }

    @Override
    public List<Discuss> findAll(String userId) {
        if(StringUtils.isBlank(userId)){
            throw new ParamsErrorException("userId不能为空");
        } else {
            return userDiscussDao.findAll(userId);
        }
    }

    @Override
    public List<Discuss> findAll(Sort sort, String userId) {
        if(StringUtils.isBlank(userId)){
            throw new ParamsErrorException("userId不能为空");
        } else {
            return userDiscussDao.findAll(null,userId);
        }
    }


    @Override
    public Page<Discuss> findLikePages(Map<String, String> map, Integer pageIndex, Integer pageSize) {
        if(!StringUtils.isNumeric(pageIndex.toString()) || !StringUtils.isNumeric(pageSize.toString())){
            throw new ParamsErrorException("pageIndex和pageSize不能为空");
        } else {
            return userDiscussDao.findLikePages(map,pageIndex,pageSize);
        }
    }
}
