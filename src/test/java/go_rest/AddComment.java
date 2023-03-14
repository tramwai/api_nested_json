package go_rest;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class AddComment {

    /**
     * {
     *     "code": 422,
     *     "meta": null,
     *     "data": [
     *         {
     *             "field": "post",
     *             "message": "must exist"
     *         },
     *         {
     *             "field": "post_id",
     *             "message": "can't be blank, is not a number"
     *         },
     *         {
     *             "field": "name",
     *             "message": "can't be blank"
     *         },
     *         {
     *             "field": "email",
     *             "message": "can't be blank, is invalid"
     *         },
     *         {
     *             "field": "body",
     *             "message": "can't be blank"
     *         }
     *     ]
     * }
     */

    private int code;
    private String meta;
    private List<CommentDataInfo> data;


}
