package go_rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.util.Arrays;

public class CommentAction {

    static Logger logger = LogManager.getLogger(CommentAction.class);

    Response response;

    @BeforeTest
    public void beforeTest(){
        logger.info("Starting the API test");
        RestAssured.baseURI = ConfigReader.getProperty("GoRestBaseURI");
    }

    @Test
    public void addCommentToGoRest(){

        // build the request body for CommentDataInfo class
        CommentDataInfo commentDataInfo1 = CommentDataInfo
                .builder()
                .field("post")
                .message("must exist")
                .build();

        CommentDataInfo commentDataInfo2 = CommentDataInfo
                .builder()
                .field("post_id")
                .message("can't be blank, is not a number")
                .build();

        CommentDataInfo commentDataInfo3 = CommentDataInfo
                .builder()
                .field("name")
                .message("can't be blank")
                .build();

        CommentDataInfo commentDataInfo4 = CommentDataInfo
                .builder()
                .field("email")
                .message("can't be blank, is invalid")
                .build();

        CommentDataInfo commentDataInfo5 = CommentDataInfo
                .builder()
                .field("body")
                .message("can't be blank")
                .build();


        AddComment addComment = AddComment
                .builder()
                .code(422)
                .meta(null)
                .data(Arrays.asList(commentDataInfo1, commentDataInfo2, commentDataInfo3, commentDataInfo4, commentDataInfo5))
                .build();



        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("GoRestToken"))
                .body(addComment)
                .when().post("public-api/comments")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(Matchers.lessThan(3000L))
                .extract().response();


        Assert.assertEquals(response.jsonPath().getInt("code"), 422);
        Assert.assertEquals(response.jsonPath().getString("meta"), null);
        Assert.assertEquals(response.jsonPath().getString("data.get(0).message"), "must exist");
        Assert.assertEquals(response.jsonPath().getString("data.get(1).field"), "post_id");

    }



}
