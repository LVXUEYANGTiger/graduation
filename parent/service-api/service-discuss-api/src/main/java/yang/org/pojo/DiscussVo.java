package yang.org.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LvXueYang
 * @description 二级评论
 */
@Getter
@Setter
public class DiscussVo implements Serializable {
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
     * 被评论者的id
     */ 
    private String replayId;

    /**
     * 被评论者的昵称
     */
    private String replayNickname;

    /**
     * 点赞数
     */
    private Integer voteCount;

}
