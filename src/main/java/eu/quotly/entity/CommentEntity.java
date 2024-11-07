package eu.quotly.entity;

import eu.quotly.Constants;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "quote_comments", schema = "quotly")
@EqualsAndHashCode(callSuper = true)
public class CommentEntity extends PanacheEntityBase {
  @Id
  @Column(name = "comment_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quote_comments_seq")
  @SequenceGenerator(name = "quote_comments_seq", sequenceName = "quote_comments_sequence", allocationSize = 1)
  private Long commentId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "quote_id", nullable = false)
  private QuoteEntity quote;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent", referencedColumnName = "comment_id")
  private CommentEntity parentComment;

  @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<CommentEntity> childComments;

  @Column(name = "comment", length = Constants.DB_EXTRA_LARGE_STRING_LENGTH)
  private String comment;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime creationTime = LocalDateTime.now();

  @Column(name = "updated_at")
  private LocalDateTime modificationTime;

  @Column(name = "deleted_at")
  private LocalDateTime deletionTime;

  @Override
  public String toString() {
    return "CommentEntity {"
      + "commentId=" + commentId
      + ", userId=" + user.getUserId()
      + ", quoteId=" + quote.getQuoteId()
      + ", parentCommentId=" + parentComment.getCommentId()
      + ", childCount=" + (childComments != null ? childComments.size() : 0)
      + ", comment=" + comment
      + ", creationTime=" + creationTime
      + ", modificationTime=" + modificationTime
      + ", deletionTime=" + deletionTime
      + "}";
  }
}
