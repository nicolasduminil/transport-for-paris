package fr.simplex_software.tfp.plan_journey.model.dtos;

//import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.json.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ResponseDto
{
  @JsonbProperty("result")
  private ResultDto result;
  @JsonbProperty("_metadata")
  private MetadataDto metadata;
}
