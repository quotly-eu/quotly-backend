package eu.quotly.entity;

import eu.quotly.Constants;
import eu.quotly.entity.id.ReactionId;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "quote_reactions", schema = "quotly", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"user_id", "quote_id"})
})
@EqualsAndHashCode(callSuper = true)
public class ReactionEntity extends PanacheEntityBase {

  @EmbeddedId
  private ReactionId reactionId;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("userId")
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("quoteId")
  @JoinColumn(name = "quote_id", nullable = false)
  private QuoteEntity quote;

  @Column(name = "reaction_name", length = Constants.DB_SMALL_STRING_LENGTH)
  private String reactionName;

  @Column(name = "deleted_at")
  private LocalDateTime deletionTime;

  @Override
  public String toString() {
    return "ReactionEntity {"
      + "userId=" + user.getUserId()
      + ", quoteId=" + quote.getQuoteId()
      + ", reactionName=" + reactionName
      + ", deletionTime=" + deletionTime
      + "}";
  }
}
