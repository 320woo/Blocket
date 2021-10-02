package com.b101.recruit.reponse;

import com.b101.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserInfoRes")
public class UserInfoRes extends BaseResponseBody {
    @ApiModelProperty(name = "userInfoId")
    long userInfoId;


    public static UserInfoRes of (int statusCode, long userInfoId) {
        UserInfoRes res = new UserInfoRes();
        res.setUserInfoId(userInfoId);
        return res;
    }


}
