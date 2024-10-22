package eu.quotly.entity.id;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class ReactionId implements Serializable {

  private Long userId;
  private Long quoteId;

  public ReactionId() {}

  public ReactionId(Long userId, Long quoteId) {
    this.userId = userId;
    this.quoteId = quoteId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ReactionId that = (ReactionId) o;
    return Objects.equals(userId, that.userId) && Objects.equals(quoteId, that.quoteId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, quoteId);
  }
}
