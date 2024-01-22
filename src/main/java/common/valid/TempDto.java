package common.valid;

import common.annotation.ValidationAnnotation;
import lombok.Getter;

@Getter
public class TempDto {
  @ValidationAnnotation
  private String value;
}
