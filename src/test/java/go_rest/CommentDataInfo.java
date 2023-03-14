package go_rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDataInfo {

    private String field;
    private String message;

}
