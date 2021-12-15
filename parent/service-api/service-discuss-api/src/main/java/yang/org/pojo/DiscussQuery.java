package yang.org.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import yang.org.utils.NumericBooleanSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author LvXueYang
 * @description 查询主评论的类
 */
public class DiscussQuery implements Serializable {
    /**
     * 评论id
     */
    @Id
    private String id;
    /**
     * 评论内容
     */
    private String comment;
    /**
     * 评论创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /**
     * 评论页面
     */
    private String href;

    /**
     * 评论是否置顶
     */
    @JsonSerialize(using = NumericBooleanSerializer.class)
    private Boolean isTop;
    /**
     * 存储点赞人的uid
     */
    private List<String> vote;
    /**
     * 评论头像的路径
     */
    private String avatarUrl;
    /**
     * 是否是作者
     */
    private Boolean isMaster;
    /**
     * 评论者昵称
     */
    private String nickname;
    /**
     * 评论者id
     */
    private String userId;

    /**
     * 评论所属的贴子
     */
    private String pid;
    /**
     * 回复评论所属的主帖子
     */
    private String rid;

    /**
     * 是否可见
     */
    private Boolean isHide;
    /**
     * 二级评论
     */ 
    private List<DiscussVo> children;
    /**
     * 点赞数量
     */
    private Integer voteCount;
    /**
     * 回复的数量
     */
    private Integer replyCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public List<String> getVote() {
        return vote;
    }

    public void setVote(List<String> vote) {
        this.vote = vote;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getMaster() {
        return isMaster;
    }

    public void setMaster(Boolean master) {
        isMaster = master;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
        this.voteCount = vote.size();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public Boolean getHide() {
        return isHide;
    }

    public void setHide(Boolean hide) {
        isHide = hide;
    }

    public List<DiscussVo> getChildren() {
        return children;
    }

    public void setChildren(List<DiscussVo> children) {
        this.children = children;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }
}
