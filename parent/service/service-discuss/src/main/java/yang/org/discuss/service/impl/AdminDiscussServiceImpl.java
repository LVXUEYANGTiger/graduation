package yang.org.discuss.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yang.org.discuss.dao.AdminDiscussDao;
import yang.org.discuss.service.AdminDiscussService;
import yang.org.entity.Page;
import yang.org.exception.ParamsErrorException;
import yang.org.pojo.Discuss;

import java.util.List;
import java.util.Map;

/**
 * @author LvXueYang
 * @description 管理员后端评论管理
 */
@Service
public class AdminDiscussServiceImpl implements AdminDiscussService {
    @Autowired
    private AdminDiscussDao adminDiscussDao;

    @Override
    public Discuss findOne(String id) {
        if(StringUtils.isBlank(id)){
            throw new ParamsErrorException("评论id不能为空");
        } else {
            return adminDiscussDao.findOne(id);
        }
    }

    @Override
    public int delete(String id) {
        if(StringUtils.isBlank(id)){
            throw new ParamsErrorException("评论id不能为空");
        } else {
            return adminDiscussDao.delete(id);
        }
    }

    @Override
    public int deleteMany(List<String> ids) {
        if(ids != null && ids.size() > 0){
            return adminDiscussDao.deleteMany(ids);
        } else {
            throw new ParamsErrorException("评论id集合不能为空");
        }
    }

    @Override
    public int save(Discuss discuss) {
        if(discuss == null){
            throw new ParamsErrorException("评论对象不能为空");
        } else {
            return adminDiscussDao.save(discuss);
        }
    }

    @Override
    public int update(Discuss discuss) {
        if(discuss == null){
            throw new ParamsErrorException("评论对象不能为空");
        } else {
            return adminDiscussDao.update(discuss);
        }
    }

    @Override
    public List<Discuss> findAll() {
        return adminDiscussDao.findAll();
    }

    @Override
    public List<Discuss> findAll(Sort sort) {
        return adminDiscussDao.findAll();
    }

    @Override
    public Page<Discuss> findLikePages(Map<String, String> map, Integer pageIndex, Integer pageSize){
        if(!StringUtils.isNumeric(pageIndex.toString()) || !StringUtils.isNumeric(pageSize.toString())){
            throw new ParamsErrorException("pageIndex和pageSize不能为空");
        } else {
            return adminDiscussDao.findLikePages(map,pageIndex,pageSize);
        }
    }
}
