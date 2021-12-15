package yang.org.discuss.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yang.org.discuss.dao.FrontDiscussDao;
import yang.org.discuss.service.FrontDiscussService;
import yang.org.entity.Page;
import yang.org.exception.ParamsErrorException;
import yang.org.pojo.Discuss;
import yang.org.pojo.DiscussQuery;
import yang.org.pojo.DiscussVo;

import java.util.List;

/**
 * @author LvXueYang
 * @description 评论模块前端管理
 */
@Service
public class FrontDiscussServiceImpl implements FrontDiscussService {
    @Autowired
    private FrontDiscussDao frontDiscussDao;

    @Override
    public List<DiscussQuery> findAllByHref(String href) {

        if(StringUtils.isBlank(href)){
            throw new ParamsErrorException("href参数不能为空");
        } else {
            return frontDiscussDao.findAllByHref(href);
        }
    }

    @Override
    public Discuss findPid(String pid) {
        if(StringUtils.isBlank(pid)){
            throw new ParamsErrorException("pid参数不能为空");
        } else {
            return frontDiscussDao.findPid(pid);
        }
    }

    @Override
    public List<DiscussVo> findAllRid(String rid) {
        if(StringUtils.isBlank(rid)){
            throw new ParamsErrorException("rid参数不能为空");
        } else {
            return frontDiscussDao.findAllRid(rid);
        }
    }

    @Override
    public int update(Discuss discuss, String userId) {
        if(StringUtils.isBlank(userId)){
            throw new ParamsErrorException("userId参数不能为空");
        } else {
            return frontDiscussDao.update(discuss,userId);
        }
    }

    @Override
    public int save(Discuss discuss) {
        if (discuss == null) {
            throw new ParamsErrorException("评论实体不能为空");
        } else {
            return frontDiscussDao.save(discuss);
        }
    }

    @Override
    public Page<DiscussQuery> findLikePages(String href, Integer pageIndex, Integer pageSize) {
        if(!StringUtils.isNumeric(pageIndex.toString()) || !StringUtils.isNumeric(pageSize.toString())){
            throw new ParamsErrorException("pageIndex和pageSize不能为空");
        } else {
            return frontDiscussDao.findLikePages(href,pageIndex,pageSize);
        }
    }
}
