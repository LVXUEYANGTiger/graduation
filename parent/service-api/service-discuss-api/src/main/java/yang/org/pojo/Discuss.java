package yang.org.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import yang.org.utils.NumericBooleanSerializer;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author LvXueYang
 * @description 评论类
 */
@Getter
@Setter
@Document(collection = "comment")
public class Discuss implements Serializable {

    /**
     * 评论id
     */
    @Id
   private String id;
    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    private String comment;
    /**
     * 评论创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /**
     * 评论页面
     */
    @NotBlank(message = "页面标识不能为空")
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
    @NotBlank(message = "用户头像不能为空")
    private String avatarUrl;
    /**
     * 是否是作者
     */
    private Boolean isMaster;
    /**
     * 评论者昵称
     */
    @NotBlank(message = "评论昵称不能为空")
    private String nickname;
    /**
     * 评论者id
     */
    @NotBlank(message = "评论者id不能为空")
    private String userId;

    /**
     * 评论所属的贴子
     */
    private String pid;
    /**
     * 回复评论所属的主帖子
     */
    @NotBlank(message = "评论所属主帖子不能为空")
    private String rid;

    /**
     * 是否可见
     */
    private Boolean isHide;

   @Override
   public String toString() {
    return "Discuss{" +
            "id='" + id + '\'' +
            ", comment='" + comment + '\'' +
            ", createdDate=" + createdDate +
            ", href='" + href + '\'' +
            ", isTop=" + isTop +
            ", vote=" + vote +
            ", avatarUrl='" + avatarUrl + '\'' +
            ", isMaster=" + isMaster +
            ", nickname='" + nickname + '\'' +
            ", userId='" + userId + '\'' +
            ", pid='" + pid + '\'' +
            ", rid='" + rid + '\'' +
            ", isHide=" + isHide +
            '}';
   }
}
