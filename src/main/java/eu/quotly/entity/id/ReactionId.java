package eu.quotly.entity.id;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ReactionId implements Serializable {
  private Long userId;
  private Long quoteId;

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    ReactionId that = (ReactionId) object;
    return Objects.equals(userId, that.userId) && Objects.equals(quoteId, that.quoteId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, quoteId);
  }
}
